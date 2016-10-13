package com.wooweather;

/**
 * Created by mostafa on 12/16/2015.
 */
public class mainD {

    int temp_now;
    String cit,desc,icon,con;

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
}
