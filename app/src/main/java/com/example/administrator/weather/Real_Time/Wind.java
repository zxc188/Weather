package com.example.administrator.weather.Real_Time;

/**
 * Created by Administrator on 2017/9/12.
 */

public class Wind {
    private String windspeed;
    private String direct;
    private String power;
    private String offset;

    public String getWindspeed() {
        return windspeed;
    }

    public String getDirect() {
        return direct;
    }

    public String getPower() {
        return power;
    }

    public String getOffset() {
        return offset;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "windspeed='" + windspeed + '\'' +
                ", direct='" + direct + '\'' +
                ", power='" + power + '\'' +
                ", offset='" + offset + '\'' +
                '}';
    }
}
