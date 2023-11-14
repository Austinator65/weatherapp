package com.austin.weatherapp.controller;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class WeatherService {
    private OkHttpClient client;
    private Response response;
    private String unit;

    public JSONObject getWeatherObject(String name) {
        client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.openweathermap.org/data/2.5/weather?q=" + name
                        + "&units="+getUnit()+"&appid=6bed260650ab58e2e87160501c177ba8")
                .build();

        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return new JSONObject(response.body().string());
            }
        } catch (IOException | JSONException e) {
            // handle exceptions
            e.printStackTrace();
        }

        return null;

    }

    public JSONArray getWeatherArray(String name) throws JSONException {

        JSONArray weatherJSONArray = getWeatherObject(name).getJSONArray("weather");

        return weatherJSONArray;
    }

    public JSONObject getMainObject(String name) throws JSONException {
        JSONObject mainObject = getWeatherObject(name).getJSONObject("main");
        return mainObject;
    }

    public JSONObject getWindObjewct(String name) throws JSONException {
        JSONObject windObject = getWeatherObject(name).getJSONObject("wind");
        return windObject;
    }

    public JSONObject getCloudObject(String name) throws JSONException {
        JSONObject cloudObject = getWeatherObject(name).getJSONObject("cloud");
        return cloudObject;
    }

    public JSONObject getSun(String name) throws JSONException {
        JSONObject sunObject = getWeatherObject(name).getJSONObject("sys");
        return sunObject;
    }

    public void setUnit(String unit){
        this.unit = unit;
    }

    public String getUnit(){
        return unit;
    }
}
