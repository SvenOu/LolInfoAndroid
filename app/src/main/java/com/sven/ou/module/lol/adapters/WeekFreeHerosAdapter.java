package com.sven.ou.module.lol.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sven.ou.R;
import com.sven.ou.common.entity.DaiWanLolResult;
import com.sven.ou.common.utils.Logger;
import com.sven.ou.module.lol.entity.thisweek.Hero;
import com.sven.ou.module.lol.oberver.LolObserver;
import com.sven.ou.network.Network;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WeekFreeHerosAdapter
        extends RecyclerView.Adapter<WeekFreeHerosAdapter.ViewHolder> {

    private static final String TAG = WeekFreeHerosAdapter.class.getSimpleName();

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private Context context;
    private List<Hero> heros;
    private Callback callback;

    public void add(List<Hero> heros) {
        int previousDataSize = this.heros.size();
        this.heros.addAll(heros);
        notifyItemRangeInserted(previousDataSize, heros.size());
    }

    public Hero getWeekFreeHeroAt(int position) {
        return heros.get(position);
    }

    public WeekFreeHerosAdapter(Context context, List<Hero> heros, Callback callback) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        this.heros = heros;
        this.context = context;
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.week_free_hero, parent, false);
        view.setBackgroundResource(mBackground);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.hero = heros.get(position);
        holder.mTextView.setText(holder.hero.getName() + ": " + holder.hero.getTitle());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onItemClick(holder);
            }
        });

        Network.getDaiWanLolDataApi().getChampionIconById(Integer.parseInt(holder.hero.getKey())).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new LolHeroObserver<DaiWanLolResult<List<Map>>>(context,  holder.mImageView) {
                    @Override
                    public void onSuccess(DaiWanLolResult<List<Map>> listDaiWanLolResult, ImageView imageView) {
                        if(null == listDaiWanLolResult.getData() ||
                                listDaiWanLolResult.getData().size() <= 0){
                            Logger.e(TAG, "listDaiWanLolResult is not valid.");
                        }
                        String imageUrl = (String) listDaiWanLolResult.getData().get(0).get("return");
                        ImageLoader.getInstance().displayImage(imageUrl, holder.mImageView);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return heros.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Hero hero;

        public final View mView;
        public final ImageView mImageView;
        public final TextView mTextView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = (ImageView) view.findViewById(R.id.avatar);
            mTextView = (TextView) view.findViewById(R.id.heroDesc);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTextView.getText();
        }
    }

    public abstract static class LolHeroObserver<T> implements Observer<T> {
        private static final String TAG = LolObserver.class.getSimpleName();
        private Context context;
        private WeakReference<ImageView> imageViewWeakReference;
        public LolHeroObserver(Context context, ImageView imageView) {
            this.context = context;
            this.imageViewWeakReference = new WeakReference<ImageView>(imageView);
        }

        @Override
        public void onCompleted() {
            Logger.i(TAG, "onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNext(T t) {
            ImageView imageView = this.imageViewWeakReference.get();
            if(null == imageView){
                Logger.w(TAG, "imageView is not available.");
                return;
            }
            onSuccess(t, imageView);
        }
        public abstract void onSuccess(T t, ImageView imageView);
    }

    public interface Callback{
       void onItemClick(ViewHolder viewHolder);
    }
}