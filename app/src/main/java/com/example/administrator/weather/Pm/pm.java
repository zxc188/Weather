package com.example.administrator.weather.Pm;

/**
 * Created by Administrator on 2017/9/12.
 */

public class pm {
    private String key;
    private String show_desc;
    private pm_main pm25;
    private String dateTime;
    private String cityName;

    public String getKey() {
        return key;
    }

    public String getShow_desc() {
        return show_desc;
    }

    public pm_main getPm25() {
        return pm25;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getCityName() {
        return cityName;
    }
}
