package com.wooweather;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

/**
 * Created by mostafa on 12/15/2015.
 */
public class Pop extends Activity {

    TextView humid,pressure,wind,sunr,suns,dateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popwindow);

        humid = (TextView) findViewById(R.id.humid);
        pressure = (TextView) findViewById(R.id.pressure);
        wind = (TextView) findViewById(R.id.wind);
        sunr = (TextView) findViewById(R.id.sunr);
        suns = (TextView) findViewById(R.id.suns);
        dateTime = (TextView) findViewById(R.id.dateTime);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int weight = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(weight*.8),(int)(height*.55));

        Bundle humidData = getIntent().getExtras();


        if(humidData ==  null){
            return;
        }
        String humidD = humidData.getString("humid");
        String pressureD = humidData.getString("pressure");
        String speedD = humidData.getString("speed");
        String sunriseD = humidData.getString("sunrise");
        String sunsetD = humidData.getString("sunset");
        String dateTimeD = humidData.getString("dateformat");
         humid.setText(humidD + " %");
         pressure.setText(pressureD + " hPa");
         wind.setText(speedD + " mph");
         sunr.setText(sunriseD);
         suns.setText(sunsetD);
         dateTime.setText(dateTimeD);


    }

    public void allwindy (String w1,String w2 ,String w3){
        humid.setText(w1);
        pressure.setText(w2);
        wind.setText(w3);


    }
}
