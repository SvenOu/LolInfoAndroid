package com.sven.ou.module.lol.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sven.ou.R;
import com.sven.ou.module.lol.activities.CheeseDetailActivity;
import com.sven.ou.module.lol.entity.Video;

import java.util.List;

public class NewestVideoViewAdapter
        extends RecyclerView.Adapter<NewestVideoViewAdapter.ViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private List<Video> videos;

    public void add(List<Video> videos) {
        int previousDataSize = this.videos.size();
        this.videos.addAll(videos);
        notifyItemRangeInserted(previousDataSize, videos.size());
    }

    public Video getVideoAt(int position) {
        return videos.get(position);
    }

    public NewestVideoViewAdapter(Context context, List<Video> videos) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        this.videos = videos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
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
                // TODO: 2016/8/31
//                Context context = v.getContext();
//                Intent intent = new Intent(context, CheeseDetailActivity.class);
//                intent.putExtra(CheeseDetailActivity.EXTRA_NAME, holder.video);
//
//                context.startActivity(intent);
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
            mTextView = (TextView) view.findViewById(android.R.id.text1);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTextView.getText();
        }
    }
}