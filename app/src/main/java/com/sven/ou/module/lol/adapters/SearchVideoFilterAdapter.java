package com.sven.ou.module.lol.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


import com.sven.ou.R;

import java.util.List;

public class SearchVideoFilterAdapter extends BaseAdapter implements Filterable {

    private static final String TAG = SearchVideoFilterAdapter.class.getSimpleName();

    private List<String> filterList;
    private Context ctx;
    private SearchVideoCallback searchVideoCallback;

    public SearchVideoFilterAdapter(Context ctx, SearchVideoCallback searchVideoCallback) {
        this.ctx = ctx;
        this.searchVideoCallback = searchVideoCallback;
    }

    @Override
    public int getCount() {
        return this.filterList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.filterList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.video_history_list_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.historyDesc.setText(filterList.get(position));
        return convertView;
    }

    private static class ViewHolder{
        public View itemView;
        public TextView historyDesc;

        public ViewHolder(View itemView) {
            this.itemView = itemView;
            this.historyDesc = (TextView) itemView.findViewById(R.id.historyDesc);
        }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private final Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint != null) {
                // Retrieve the autocomplete results.
                filterList = searchVideoCallback.onGetFilterList(constraint.toString());
                // Assign the data to the FilterResults
                filterResults.values = filterList;
                filterResults.count = filterList.size();
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            if (filterResults != null && filterResults.count > 0) {
                notifyDataSetChanged();
            }
            else {
                notifyDataSetInvalidated();
            }
        }
    };

    public interface SearchVideoCallback{
        public List<String> onGetFilterList(String input);
    }

}
