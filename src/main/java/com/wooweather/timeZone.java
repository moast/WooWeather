package com.wooweather;

/**
 * Created by mostafa on 12/20/2015.
 */
public class timeZone {
    String a2 ;
    String b2 ;
    int ww;
    String wws1,wws;



    public void resolve1 (int a1,int log1) {

        if ( log1 >= 2 && log1 < 22) {
                int a11 = a1 + 1;
                String w11 = Integer.toString(a11);
                a2 = "+" + w11;
            }
            else {
         if (a1>0){
                String w1 = Integer.toString(a1);
                a2 = "+" + w1;

        }else if(a1< 0){
            a2 = Integer.toString(a1);


        }else if( a1 == 0){
            String w2 = Integer.toString(a1);
             a2 = "+" + w2;

        }}
        setA2(a2);
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public String getA2() {

        return a2;
    }

    public void resovle2 (int b1){

        if(b1<0){
            int sx = b1*(-1);
            if(sx>=1 && sx<=9){
            String v1 =  Integer.toString(sx);
            b2 = "0"+v1;}
            else{
                b2 = Integer.toString(sx);
            }
        }else if(b1==0){
            b2 = Integer.toString(b1);
        }
        else{
            int sx1 = b1*(1);
            if(sx1>=1 && sx1<=9){
                String v2 = Integer.toString(sx1);
            b2 = "0"+v2;}
            else{
                b2 = Integer.toString(b1);
            }
        }
        accurateSun(b2);

    }


    public void accurateSun (String ss) {
        int s = Integer.valueOf(ss);


        if (s >= 0 && s < 30) {
            ww = 0;
             wws = Integer.toString(ww);
             wws1 = "0"+wws;


        } else if (s == 30) {
            ww = 30;
            wws1 = Integer.toString(ww);


        } else if (s > 30 && s < 60) {
            ww = 59;
             wws1 = Integer.toString(ww);


        }


        setB2(wws1);


    }

    public void setB2(String b2) {
        this.b2 = b2;
    }

    public String getB2() {
        return b2;
    }
}


