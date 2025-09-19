package com.example.weather_dashboard.controllers;

import com.example.weather_dashboard.models.Weather;
import com.example.weather_dashboard.models.Main;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@RestController
@RequestMapping("/weather")

public class WeatherController {
    @Value("${api.key}")
    private String apiKey;
    @GetMapping("")
    /*calling a for a map which stores key/value pairs. Object is to cover multiple different types together */
    public Map<String, Object> getWeather(@RequestParam String city) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q="
                + city
                + "&appid=" + apiKey;

        RestTemplate restTemplate = new RestTemplate();
        Weather weather = restTemplate.getForObject(url, Weather.class);

        Map<String, Object> response = new HashMap<>();

        if (weather == null || weather.getMain() == null){
            response.put("error", "weather not available in " + city);
            return response;
        }
        double kelvinTemp = weather.getMain().getTemp();
        double farenheit = (kelvinTemp - 273.15) * 9/5 +32;
        long roundedTemp = Math.round(farenheit);
        int humidity = weather.getMain().getHumidity();

        response.put("city", city);
        response.put("temperature", roundedTemp);
        response.put("humidity", weather.getMain().getHumidity());

        return response;


    }
}
