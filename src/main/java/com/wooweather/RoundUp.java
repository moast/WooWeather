package com.wooweather;

/**
 * Created by mostafa on 12/21/2015.
 */
public class RoundUp {
    int finalRound;

    public void roundup(double d) {

        if (d - (int) d > 0.5) {

            double lonRound = Math.round(d);
            finalRound = (int) lonRound;
        } else {
            finalRound = (int) d;
        }


    }

}