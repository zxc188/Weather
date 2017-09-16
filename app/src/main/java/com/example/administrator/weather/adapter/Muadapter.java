package com.example.administrator.weather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.weather.R;

import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/16.
 */

public class Muadapter extends BaseAdapter {
    private LayoutInflater inflater;
    private LinkedList<Map<String, String>> list;
    private Context context;
    public Muadapter(Context context, LinkedList<Map<String,String>> list) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item, null);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tx_city_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText((String) list.get(position).get("city_name"));
        return convertView;
    }
    static class ViewHolder {
        TextView name;
    }
}
