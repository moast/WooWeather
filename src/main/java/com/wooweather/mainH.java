package com.wooweather;

/**
 * Created by mostafa on 12/16/2015.
 */
public class mainH {
    int humidity, pressure,speed,longitude;
    long sunrise,sunset;

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getHumidity() {
        return humidity;
    }

    public long getSunrise() {
        return sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public int getSpeed() {
        return speed;
    }

    public int getPressure() {
        return pressure;
    }

    public int getLongitude() {
        return longitude;
    }
}
