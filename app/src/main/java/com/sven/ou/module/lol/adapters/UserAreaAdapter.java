package com.sven.ou.module.lol.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sven.ou.R;
import com.sven.ou.common.utils.Logger;
import com.sven.ou.module.lol.db.Area_;
import com.sven.ou.module.lol.db.BattleLevel;
import com.sven.ou.module.lol.entity.Area;
import com.sven.ou.module.lol.entity.UserArea;

import java.lang.ref.WeakReference;
import java.util.List;

import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserAreaAdapter extends RecyclerView.Adapter<UserAreaAdapter.ViewHolder> {

    private static final String TAG = UserAreaAdapter.class.getSimpleName();

    private Context context;
    private List<UserArea> authorList;
    private Callback callback;

    public UserAreaAdapter(Context context, List<UserArea> userAreaList, Callback callback) {
        this.context = context;
        this.authorList = userAreaList;
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_area_info_item, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final UserArea userArea = authorList.get(position);
        loadItemInfo(holder, userArea);
        holder.summonerText.setText(userArea.getName());
        holder.convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onItemClick(holder, userArea);
            }
        });
        holder.convertView.setTag(userArea);
    }

    private void loadItemInfo(ViewHolder holder, UserArea userArea) {
        final WeakReference<ViewHolder> holderWeakReference = new WeakReference(holder);
        final int tier = userArea.getTier();
        final int queue = userArea.getQueue();
        final int areaId = userArea.getArea_id();
        Single.create(new Single.OnSubscribe<UserInfoData>() {
            @Override
            public void call(SingleSubscriber<? super UserInfoData> singleSubscriber) {
                UserInfoData userInfoData = new UserInfoData();
                userInfoData.battleLevel =  BattleLevel.findBattleLevel(tier, queue);
                userInfoData.area = Area_.findArea_(areaId).toArea();
                singleSubscriber.onSuccess(userInfoData);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserInfoData>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(UserInfoData userInfoData) {
                        ViewHolder holder = holderWeakReference.get();
                        if(null != holder && null != userInfoData){
                            if(null != userInfoData.battleLevel){
                                holder.queueText.setText(userInfoData.battleLevel.getDesc());
                            }
                            if(null != userInfoData.area){
                                holder.serverNameText.setText(userInfoData.area.getName());
                                holder.serverAreaText.setText(userInfoData.area.getIsp());
                            }
                        }
                    }
                });
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return authorList.size();
    }

    private class UserInfoData{
        BattleLevel battleLevel;
        Area area;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        View convertView;
        TextView summonerText;
        TextView serverNameText;
        TextView queueText;
        TextView serverAreaText;

        public ViewHolder(View convertView) {
            super(convertView);

            this.convertView = convertView;

            this.summonerText = (TextView) convertView.
                    findViewById(R.id.summonerText);

            this.serverNameText = (TextView) convertView.
                    findViewById(R.id.serverNameText);

            this.queueText = (TextView) convertView.
                    findViewById(R.id.queueText);

            this.serverAreaText = (TextView) convertView.
                    findViewById(R.id.serverAreaText);
        }
    }

    public interface Callback{
        void onItemClick(ViewHolder viewHolder, UserArea userArea);
    }
}
