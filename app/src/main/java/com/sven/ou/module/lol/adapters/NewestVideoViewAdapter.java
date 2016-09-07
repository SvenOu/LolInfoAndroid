package com.sven.ou.module.lol.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sven.ou.LolApplication;
import com.sven.ou.R;
import com.sven.ou.module.lol.activities.CheeseDetailActivity;
import com.sven.ou.module.lol.entity.Video;

import java.util.List;

import rx.subjects.PublishSubject;

public class NewestVideoViewAdapter
        extends RecyclerView.Adapter<NewestVideoViewAdapter.ViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private List<Video> videos;
    private CallBack callBack;

    public void add(List<Video> videos) {
        int previousDataSize = this.videos.size();
        this.videos.addAll(videos);
        notifyItemRangeInserted(previousDataSize, videos.size());
    }

    public void clearVideoData(){
        if(null != videos){
            videos.clear();
            notifyDataSetChanged();
        }
    }

    public Video getVideoAt(int position) {
        return videos.get(position);
    }

    public NewestVideoViewAdapter(Context context, List<Video> videos, CallBack callBack) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        this.videos = videos;
        this.callBack = callBack;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.newest_video_item, parent, false);

        ImageView mTextView = (ImageView) view.findViewById(R.id.avatar);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)mTextView.getLayoutParams();
        layoutParams.width = (int) ((LolApplication.getDisplayMetrics().widthPixels - 40) / 2.0);
        layoutParams.height = (int) (layoutParams.width / 16.0 * 9.0);
        mTextView.setLayoutParams(layoutParams);

        view.setBackgroundResource(mBackground);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.video = videos.get(position);
        holder.mTextView.setText(videos.get(position).getTitle());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onItemClick(holder);
            }
        });
        ImageLoader.getInstance().displayImage(holder.video.getImg(), holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Video video;
        public final View mView;
        public final ImageView mImageView;
        public final TextView mTextView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = (ImageView) view.findViewById(R.id.avatar);
            mTextView = (TextView) view.findViewById(R.id.videoTitle);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTextView.getText();
        }
    }

    public interface CallBack{
        void onItemClick(ViewHolder viewHolder);
    }
}