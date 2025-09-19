package com.example.weather_dashboard.models;

public class Weather {

    private Main main;
    private String name;

    public Weather(){
    }

    public Weather (Main main, String name){
        this.main = main;
        this.name = name;
    }
    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}






