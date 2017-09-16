package com.example.administrator.weather.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.administrator.weather.R;

import java.util.Timer;
import java.util.TimerTask;

public class Input extends AppCompatActivity {
    private Timer timer;
    private TimerTask timertask;
    private Thread thread;
    private boolean isfirst = true;
    private boolean isConnected;
    private boolean ischange = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        timer = new Timer();
        timertask = new TimerTask() {
            @Override
            public void run() {
                ConnectivityManager cm =
                        (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                isConnected = activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting();
                if (isConnected) {
                    if (!ischange) {
                        ischange = true;
                        Intent intent = new Intent();
                        intent.setClass(Input.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    if (isfirst) {
                        Log.v("aijdfi", "请链接网络");
                        isfirst = false;
                    }
                }
            }
        };
        timer.schedule(timertask,100,100);
    }

    public void show(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示")
                .setMessage("请链接网络")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    @Override
    protected void onStop() {
        timer = null;
        timertask = null;
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        timer = null;
        timertask = null;
        super.onDestroy();
    }
}
