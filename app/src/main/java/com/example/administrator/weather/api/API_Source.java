package com.example.administrator.weather.api;

/**
 * Created by Administrator on 2017/9/12.
 */

public class API_Source {
    private Result result;
    private int error_code;
    private String reason;

    public Result getResult() {
        return result;
    }

    public int getError_code() {
        return error_code;
    }

    public String getReason() {
        return reason;
    }
}
