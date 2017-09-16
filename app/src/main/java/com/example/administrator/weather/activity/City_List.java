package com.example.administrator.weather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.weather.R;
import com.example.administrator.weather.adapter.Muadapter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class City_List extends AppCompatActivity {
    private ListView listView;
    private String[] str ={"北京","上海","广州","银川","深圳","南京","苏州","石家庄","南宁","西安","哈尔滨","呼和浩特","天津","武汉","成都","厦门"} ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_list);
        listView = (ListView) findViewById(R.id.list);
        LinkedList<Map<String, String>> linkedList = new LinkedList<>();
        for(int i=0;i<str.length;i++) {
            Map<String, String> map = new HashMap<>();
            map.put("city_name", str[i]);
            linkedList.add(map);
        }
        Muadapter muadapter = new Muadapter(getApplicationContext(), linkedList);
        listView.setAdapter(muadapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("citys_name", str[position]);
                setResult(2,intent);
                finish();
            }
        });
    }
}
