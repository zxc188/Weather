package com.example.administrator.weather.Weather;

import java.util.LinkedList;

/**
 * Created by Administrator on 2017/9/12.
 */

public class Weather_info {
    private LinkedList<String> dawn;
    private LinkedList<String> day;
    private LinkedList<String> night;

    public LinkedList<String> getDawn() {
        return dawn;
    }

    public LinkedList<String> getDay() {
        return day;
    }

    public LinkedList<String> getNight() {
        return night;
    }

    @Override
    public String toString() {
        return "Weather_info{" +
                "dawn=" + dawn +
                ", day=" + day +
                ", night=" + night +
                '}';
    }
}
