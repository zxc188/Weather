package com.example.administrator.weather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.weather.R;
import com.example.administrator.weather.Weather.Weather_Item;

import java.util.LinkedList;

/**
 * Created by Administrator on 2017/9/16.
 */

public class My_RecyAdapter extends RecyclerView.Adapter<My_RecyAdapter.VHolder> {
    private Context context;
    private LinkedList<Weather_Item> weather_items;

    public My_RecyAdapter(Context context,LinkedList<Weather_Item>linkedList) {
        this.context = context;
        this.weather_items = linkedList;
    }

    @Override
    public VHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recy_item, parent, false));
    }


    @Override
    public void onBindViewHolder(My_RecyAdapter.VHolder holder, int position) {
        for(int i=1;i<10;i++) {
            holder.tx_day.setText(weather_items.get(position).getDate());
            holder.tx_week.setText("星期"+weather_items.get(position).getWeek());
            holder.tx_one.setText(weather_items.get(position).getInfo().getDay().get(1));
            holder.tx_two.setText(weather_items.get(position).getInfo().getDay().get(2));
            holder.tx_three.setText(weather_items.get(position).getInfo().getDay().get(3));
            holder.tx_four.setText(weather_items.get(position).getInfo().getDay().get(4));
            holder.tx_five.setText(weather_items.get(position).getInfo().getDay().get(5));
        }
    }


    @Override
    public int getItemCount() {
        return weather_items == null ? 0 : weather_items.size();
    }

    class VHolder extends RecyclerView.ViewHolder {
        private TextView tx_week;
        private TextView tx_day;
        private TextView tx_one;
        private TextView tx_two;
        private TextView tx_three;
        private TextView tx_four;
        private TextView tx_five;
        public VHolder(View itemView) {
            super(itemView);
            tx_day = (TextView) itemView.findViewById(R.id.tx_date);
            tx_week = (TextView) itemView.findViewById(R.id.tx_week);
            tx_one = (TextView) itemView.findViewById(R.id.tx_one);
            tx_three = (TextView) itemView.findViewById(R.id.tx_three);
            tx_two = (TextView) itemView.findViewById(R.id.tx_two);
            tx_four = (TextView) itemView.findViewById(R.id.tx_four);
            tx_five = (TextView) itemView.findViewById(R.id.tx_five);
        }
    }

    public void setWeather_items(LinkedList<Weather_Item> weather_items) {
        this.weather_items = weather_items;
        notifyDataSetChanged();
    }
}

