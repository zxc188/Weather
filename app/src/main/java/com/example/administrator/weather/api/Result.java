package com.example.administrator.weather.api;

import com.example.administrator.weather.Life.Life;
import com.example.administrator.weather.Pm.pm;
import com.example.administrator.weather.Real_Time.Realtime;
import com.example.administrator.weather.Weather.Weather_Item;

import java.util.LinkedList;

/**
 * Created by Administrator on 2017/9/12.
 */

public class Result {
    private Realtime realtime;
    private Life life;
    private LinkedList<Weather_Item> weather;
    private pm pm25;
    private int isForeign;

    public Realtime getRealtime() {
        return realtime;
    }

    public Life getLife() {
        return life;
    }

    public LinkedList<Weather_Item> getWeather() {
        return weather;
    }

    public pm getPm25() {
        return pm25;
    }

    public int  getIsForeign() {
        return isForeign;
    }
}
