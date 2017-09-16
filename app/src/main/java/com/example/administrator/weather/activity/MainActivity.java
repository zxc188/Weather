package com.example.administrator.weather.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.weather.R;
import com.example.administrator.weather.Weather.Weather_Item;
import com.example.administrator.weather.adapter.My_RecyAdapter;
import com.example.administrator.weather.api.API_Source;
import com.example.administrator.weather.dao.DBUtil;
import com.example.administrator.weather.dao.DBdao;
import com.google.gson.Gson;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private RequestQueue requestQueue;
    private DBUtil dbUtil;
    private Handler handler;
    private SearchView searchView;
    private Cursor cursor;
    private TextView tx_qing;
    private TextView tx_du;
    private static boolean first = true;
    private TextView tx_city;
    private RecyclerView recyclerView;
    private LinkedList<Weather_Item> weather_items;
    private My_RecyAdapter my_recyAdapter;
    private TextView tx_ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tx_city = (TextView) findViewById(R.id.tx_city);
        tx_qing = (TextView) findViewById(R.id.tx_qing);
        tx_du = (TextView) findViewById(R.id.tx_du);
        recyclerView = (RecyclerView) findViewById(R.id.recy);
        tx_ref = (TextView) findViewById(R.id.tx_ref);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        weather_items = new LinkedList<>();
        my_recyAdapter = new My_RecyAdapter(MainActivity.this, weather_items);
        recyclerView.setAdapter(my_recyAdapter);
        dbUtil = new DBUtil(getApplicationContext());
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                String response = bundle.getString("responsebody");
                tx_city.setText(bundle.getString("city"));
                Gson gson = new Gson();
                API_Source api_source = gson.fromJson(response.toString(), API_Source.class);
                tx_ref.setText("更新于  "+api_source.getResult().getRealtime().getDate()+"  "+api_source.getResult().getRealtime().getTime());
                weather_items = api_source.getResult().getWeather();
                my_recyAdapter.setWeather_items(weather_items);
                tx_qing.setText(api_source.getResult().getRealtime().getWeather().getInfo());
                tx_du.setText(api_source.getResult().getRealtime().getWeather().getTemperature()+"°");
                /*
                * 更新界面
                * */
                Log.v("ajdsfijre", response);
            }
        };
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Reflish_UI("北京");
            }
        });
        thread.start();
    }

    public void Reflish_UI(final String cityname) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                StringRequest stringRequest = new StringRequest("http://api.avatardata.cn/Weather/Query?key=33eba36d156448f790a3efa05e0b8798&cityname=" + cityname
                        , new Response.Listener<String>() {
                    @Override
                    /*
                    * 请求成功
                    * */
                    public void onResponse(String response) {
                        Message message = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putString("responsebody", response);
                        bundle.putString("city", cityname);
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    /*
                    * 请求失败
                    * */
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                requestQueue.add(stringRequest);
            }
        });
        thread.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem search = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(search);
        final SearchView.SearchAutoComplete searchAutoComplete=(SearchView.SearchAutoComplete)searchView.findViewById(R.id.search_src_text);
        searchAutoComplete.setThreshold(1);
        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {
                return false;
            }

            @Override
            public boolean onSuggestionClick(int position) {
                Log.v("ajidfjr", cursor.getString(1));
                Reflish_UI(cursor.getString(1));
                return false;
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                cursor = TextUtils.isEmpty(newText) ? null : dbUtil.queryData(newText);
                // 不要频繁创建适配器，如果适配器已经存在，则只需要更新适配器中的cursor对象即可。
                if (searchView.getSuggestionsAdapter() == null) {
                    searchView.setSuggestionsAdapter(new SimpleCursorAdapter(MainActivity.this, R.layout.search_item, cursor, new String[]{DBdao.COLUMN_ID}, new int[]{R.id.text1}));
                } else {
                    searchView.getSuggestionsAdapter().changeCursor(cursor);
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_weather:
                if (first) {
                    dbUtil.Add_all();
                    first = false;
                }
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, City_List.class);
                startActivityForResult(intent, 1);
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == 2) {
            String city = data.getStringExtra("citys_name");
            Reflish_UI(city);
        }
    }

    @Override
    protected void onDestroy() {
        dbUtil.close();
        super.onDestroy();
    }
}
