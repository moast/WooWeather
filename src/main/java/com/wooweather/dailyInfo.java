package com.wooweather;

/**
 * Created by mostafa on 12/22/2015.
 */
public class dailyInfo {

    int humidity, pressure,speed,longitude;
    long sunrise,sunset,dtime;
    int temp_now;
    String cit,desc,icon,con;


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

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setCon(String con) {
        this.con = con;
    }

    public void setTemp_now(int temp_now) {
        this.temp_now = temp_now;
    }

    public void setCit(String cit) {
        this.cit = cit;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDtime(long dtime) {
        this.dtime = dtime;
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


    public int getTemp_now() {
        return temp_now;
    }

    public String getCon() {
        return con;
    }

    public String getDesc() {
        return desc;
    }

    public String getCit() {
        return cit;
    }

    public String getIcon() {
        return icon;
    }

    public long getDtime() {
        return dtime;
    }
}



