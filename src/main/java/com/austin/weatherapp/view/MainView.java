package com.austin.weatherapp.view;

import java.io.IOException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import com.austin.weatherapp.controller.WeatherService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "")
public class MainView extends VerticalLayout {

    @Autowired
    private WeatherService weatherService = new WeatherService();

    public MainView() throws IOException {

        try {
            System.out.println("Data: " + weatherService.getWeather("Sandton").getString("coord").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        H1 label = new H1("Hello world");

        add(label);

    }
}
