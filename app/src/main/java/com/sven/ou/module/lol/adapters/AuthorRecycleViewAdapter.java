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
import com.sven.ou.module.lol.entity.Author;

import java.util.List;

public class AuthorRecycleViewAdapter
        extends RecyclerView.Adapter<AuthorRecycleViewAdapter.ViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private List<Author> authors;
    private Callback callback;

    public void add(List<Author> authors) {
        int previousDataSize = this.authors.size();
        this.authors.addAll(authors);
        notifyItemRangeInserted(previousDataSize, authors.size());
    }

    public Author getAuthorAt(int position) {
        return authors.get(position);
    }

    public AuthorRecycleViewAdapter(Context context, List<Author> authors, Callback callback) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        this.authors = authors;
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.author_info_list_item, parent, false);
        view.setBackgroundResource(mBackground);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.author = authors.get(position);
        holder.mTextView.setText(authors.get(position).getDesc());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onItemClick(holder);
            }
        });
        ImageLoader.getInstance().displayImage(holder.author.getImg(), holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return authors.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Author author;

        public final View mView;
        public final ImageView mImageView;
        public final TextView mTextView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = (ImageView) view.findViewById(R.id.avatar);
            mTextView = (TextView) view.findViewById(R.id.authorDesc);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTextView.getText();
        }
    }

    public interface Callback{
        void onItemClick(ViewHolder viewHolder);
    }
}