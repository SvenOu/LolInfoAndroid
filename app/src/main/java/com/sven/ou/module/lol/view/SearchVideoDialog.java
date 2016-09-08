package com.sven.ou.module.lol.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.util.Log;
import com.paginate.Paginate;
import com.sven.ou.R;
import com.sven.ou.common.entity.DaiWanLolResult;
import com.sven.ou.common.utils.DebounceTextWatcher;
import com.sven.ou.common.utils.Logger;
import com.sven.ou.common.utils.ServiceUtil;
import com.sven.ou.common.utils.ViewUtil;
import com.sven.ou.module.launch.db.SearchHistory;
import com.sven.ou.module.lol.adapters.NewestVideoViewAdapter;
import com.sven.ou.module.lol.adapters.SearchVideoFilterAdapter;
import com.sven.ou.module.lol.entity.Video;
import com.sven.ou.module.lol.oberver.LolObserver;
import com.sven.ou.module.lol.presenter.SearchVideoDialogPresenter;
import com.sven.ou.navigation.Navigator;


import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchVideoDialog extends Dialog implements Paginate.Callbacks{

    private static final String TAG = SearchVideoDialog.class.getSimpleName();

    @BindView(R.id.iconAllowBack) ImageView iconAllowBack;
    @BindView(R.id.searchField) AutoCompleteTextView searchField;
    @BindView(R.id.iconClear) ImageView iconClear;
    @BindView(R.id.searchVideoRecyclerview) RecyclerView searchVideoRecyclerview;

    private Paginate paginate;
    private static final int ROW_VIEW_COUNT = 2;
    private NewestVideoViewAdapter newestVideoViewAdapter;
    private String keyWord;

    private Navigator navigator;
    private SearchVideoDialogPresenter searchVideoDialogPresenter;
    private DebounceTextWatcher debounceTextWatcher;
    private SearchVideoFilterAdapter searchVideoFilterAdapter;

    private WeakReference<Activity> weakActivityReference;

    public SearchVideoDialog(Activity activity, Navigator navigator, SearchVideoDialogPresenter searchVideoDialogPresenter) {
        super(activity, R.style.TranslucentDialog);
        this.searchVideoDialogPresenter = searchVideoDialogPresenter;
        this.navigator = navigator;
        this.weakActivityReference = new WeakReference<Activity>(activity);
        init();
    }

    @OnClick(R.id.iconAllowBack)
    public void iconAllowBackClick(View view) {
        this.hide();
    }
    @OnClick(R.id.iconClear)
    public void iconClearClick(View view) {
        searchField.setText("");
        newestVideoViewAdapter.clearVideoData();
    }

    private void init() {
        setContentView(R.layout.dialog_search_video);
        ButterKnife.bind(this);
        //设置数据源
        searchVideoFilterAdapter = new SearchVideoFilterAdapter(getContext(), new SearchVideoFilterAdapter.SearchVideoCallback() {
            @Override
            public List<String> onGetFilterList(String input) {
                List<String> filterList = new ArrayList<>();
                List<SearchHistory> histories = searchVideoDialogPresenter.getSearchHistory(input, 20);
                for(SearchHistory history: histories){
                    if(SearchHistory.HISTORY_TYPE_VIDEO.equals(history.getType())){
                        filterList.add(history.getContent());
                    }
                }
                return filterList;
            }
        });

        searchField.setAdapter(searchVideoFilterAdapter);
        if(null == debounceTextWatcher){
            debounceTextWatcher = new DebounceTextWatcher() {
                @Override
                protected void delayAfterTextChanged(String afterChangeText) {
                    if(TextUtils.isEmpty(afterChangeText)){
                        iconClear.setVisibility(View.GONE);
                    }else{
                        iconClear.setVisibility(View.VISIBLE);
                    }
                }
            };
        }
        searchField.addTextChangedListener(new DebounceTextWatcher() {
            @Override
            protected void delayAfterTextChanged(String afterChangeText) {
                if(TextUtils.isEmpty(afterChangeText)){
                    iconClear.setVisibility(View.GONE);
                }else{
                    iconClear.setVisibility(View.VISIBLE);
                }
            }
        });
        searchField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    keyWord = String.valueOf(textView.getText());
                    if(TextUtils.isEmpty(keyWord)){
                        newestVideoViewAdapter.clearVideoData();
                        ViewUtil.hideKeyboardFrom(getContext(), textView);
                        return true;
                    }
                    loadFirstPage();
                    SearchHistory searchHistory = new SearchHistory();
                    searchHistory.setHistoryId(ServiceUtil.generateId());
                    searchHistory.setCreateDate(new Date());
                    searchHistory.setContent(keyWord);
                    searchHistory.setType(SearchHistory.HISTORY_TYPE_VIDEO);
                    searchVideoDialogPresenter.saveSearchHistory(searchHistory);
                    ViewUtil.hideKeyboardFrom(getContext(), textView);
                    searchVideoFilterAdapter.clearFilters();
                    return true;
                }
                return false;
            }
        });
        searchField.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                keyWord = (String) searchVideoFilterAdapter.getItem(position);
                if(TextUtils.isEmpty(keyWord)){
                    newestVideoViewAdapter.clearVideoData();
                    return;
                }
                loadFirstPage();
                ViewUtil.hideKeyboardFrom(getContext(), searchField);
            }
        });
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        // If RecyclerView was recently bound, unbind
        if (paginate != null) {
            paginate.unbind();
        }
        searchVideoRecyclerview.setLayoutManager(new GridLayoutManager(getContext().getApplicationContext(), ROW_VIEW_COUNT));
        newestVideoViewAdapter = new NewestVideoViewAdapter(getContext(), new ArrayList(0), new NewestVideoViewAdapter.CallBack() {
            @Override
            public void onItemClick(NewestVideoViewAdapter.ViewHolder viewHolder) {
                Video video = viewHolder.video;
                Activity activity = weakActivityReference.get();
                if(null == activity){
                    Logger.e(TAG, "activity is null. cannot play video.");
                    return;
                }
                navigator.goToVideoPlayActivity(activity, video);

            }
        });
        searchVideoRecyclerview.setAdapter(newestVideoViewAdapter);
        paginate = Paginate.with(searchVideoRecyclerview, this).build();
        paginate.setHasMoreDataToLoad(false);
    }

    /**
     * Called when next page of data needs to be loaded.
     */
    @Override
    public synchronized void onLoadMore() {
        searchVideoDialogPresenter.loadNextNewestVideoPage(new LolObserver<DaiWanLolResult<List<Video>>>(getContext().getApplicationContext()) {
            @Override
            public void onNext(DaiWanLolResult<List<Video>> listDaiWanLolResult) {
                List<Video> authorList = listDaiWanLolResult.getData();
                newestVideoViewAdapter.add(authorList);
            }
        }, keyWord);
    }

    private void loadFirstPage(){
        if(TextUtils.isEmpty(keyWord)){
            Log.e(TAG, "keyWord is empty !");
            return;
        }
        searchVideoDialogPresenter.loadNewestVideoPage(new LolObserver<DaiWanLolResult<List<Video>>>(getContext().getApplicationContext()) {
            @Override
            public void onNext(DaiWanLolResult<List<Video>> listDaiWanLolResult) {
                if(null == listDaiWanLolResult.getData() || listDaiWanLolResult.getData().size() <= 0){
                    Toast.makeText(getContext(), R.string.not_found_video, Toast.LENGTH_LONG).show();
                    return;
                }
                newestVideoViewAdapter.clearVideoData();
                List<Video> authorList = listDaiWanLolResult.getData();
                newestVideoViewAdapter.add(authorList);
            }
        }, keyWord, 1);
    }

    /**
     * Called to check if loading of the next page is currently in progress. While loading is in progress
     * {@link Paginate.Callbacks#onLoadMore} won't be called.
     *
     * @return true if loading is currently in progress, false otherwise.
     */
    @Override
    public synchronized boolean isLoading() {
        return searchVideoDialogPresenter.isLoadingPageData();
    }

    /**
     * Called to check if there is more data (more pages) to load. If there is no more pages to load, {@link
     * Paginate.Callbacks#onLoadMore} won't be called and loading row, if used, won't be added.
     *
     * @return true if all pages has been loaded, false otherwise.
     */
    @Override
    public boolean hasLoadedAllItems() {
        return searchVideoDialogPresenter.hasLoadedAllIPages();
    }
}
