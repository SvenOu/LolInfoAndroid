package com.sven.ou.module.lol.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sven.ou.R;
import com.sven.ou.module.lol.entity.Area;

import java.util.List;

public class SpinnerAreaAdapter extends BaseAdapter{

    private Context context;
    private List<Area> areaList;

    public SpinnerAreaAdapter(Context context, List<Area> areaList) {
        this.context = context;
        this.areaList = areaList;
    }

    @Override
    public int getCount() {
        return areaList.size();
    }

    @Override
    public Object getItem(int position) {
        return areaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        Area area = (Area) getItem(position);
        if(null == convertView){
            convertView = LayoutInflater.from(context).inflate(R.layout.area_spinner_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.areaText.setText(area.getName());
        return convertView;
    }

    private class ViewHolder{
        View convertView;
        TextView areaText;

        public ViewHolder(View convertView) {
            this.convertView = convertView;
            this.areaText = (TextView) convertView.
                    findViewById(R.id.areaText);
        }
    }
}
