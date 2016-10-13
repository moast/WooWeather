package com.wooweather;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.inputmethodservice.Keyboard;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.startapp.android.publish.StartAppSDK;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;



public class WeatherActivity extends ActionBarActivity {

    TextView tvTe0, tvTe1, tvTe2, tvI0, tvI1, tvI2, tvC0, tvC1, tvC2, tvT, time0, time1, time2, time3, time4, time5,
            t_now, temp_6, temp_9, temp_12, temp_15, temp_18, temp_21, city_layout, description, textView,humid,co;
    private TextView tvData;
    private EditText etCity,txtSearch;
    private Exception error;
    public ImageView iv;
    public Bitmap bitmap;
    public TextView tvS;
    private ImageView imageView1, iv_weather, ivT1, ivT2, ivT3, ivT4, ivT5, ivT6;
    private TextView tvJsonItem;
    private TextView textView2;
    private TextView textView3;
    private ListView listView;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    mainT maint = new mainT();
    mainD maind = new mainD();
    mainH mainh = new mainH();
    timeZone timezone = new timeZone();
    Pop a = new Pop();
    DateFormat dfm,dfm1,dfm2;
    Date df,df1,df2;
    public String oo[] = new String[100];
    public String rr[] = new String[100];
    AbsoluteLayout abslay;




    private ExpandableListView elView, elv, elv1;
    String city;
    String items[];
    public String endpoint3, dailyE3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StartAppSDK.init(this, "200051309", true);
        setContentView(R.layout.weatherlay);

        listView = (ListView) findViewById(R.id.listView);
        txtSearch = (EditText) findViewById(R.id.txtSearch);
        abslay = (AbsoluteLayout) findViewById(R.id.abslay);

        humid = (TextView) findViewById(R.id.humid);
        t_now = (TextView) findViewById(R.id.t_now);
        temp_6 = (TextView) findViewById(R.id.temp_6);
        temp_9 = (TextView) findViewById(R.id.temp_9);
        temp_12 = (TextView) findViewById(R.id.temp_12);
        temp_15 = (TextView) findViewById(R.id.temp_15);
        temp_18 = (TextView) findViewById(R.id.temp_18);
        temp_21 = (TextView) findViewById(R.id.temp_21);
        co = (TextView) findViewById(R.id.co);
        description = (TextView) findViewById(R.id.description);
        time0 = (TextView) findViewById(R.id.time0);
        time1 = (TextView) findViewById(R.id.time1);
        time2 = (TextView) findViewById(R.id.time2);
        time3 = (TextView) findViewById(R.id.time3);
        time4 = (TextView) findViewById(R.id.time4);
        time5 = (TextView) findViewById(R.id.time5);
        ivT1 = (ImageView) findViewById(R.id.ivT1);
        ivT2 = (ImageView) findViewById(R.id.ivT2);
        ivT3 = (ImageView) findViewById(R.id.ivT3);
        ivT4 = (ImageView) findViewById(R.id.ivT4);
        ivT5 = (ImageView) findViewById(R.id.ivT5);
        ivT6 = (ImageView) findViewById(R.id.ivT6);
        city_layout = (TextView) findViewById(R.id.city_layout);
        iv_weather = (ImageView) findViewById(R.id.iv_weather);


       // AdView mAdView = (AdView) findViewById(R.id.adView);
      //  AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
     //   mAdView.loadAd(adRequest);


        listView.setVisibility(View.GONE);
        abslay.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return false;
            }
        });

        intList();


        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // listView.setVisibility(View.GONE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listView.setVisibility(View.VISIBLE);
                adapter.getFilter().filter(s);

           /*     if (s.toString().equals("")) {
                    intList();
                } else {
                    searchItem(s.toString());
                }   */
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });


        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String cf = adapter.getItem(position);
                String removeSpace = cf.replaceAll("\\s+", "");
                city = removeSpace.toString();

                String endpoint1 = "http://api.openweathermap.org/data/2.5/forecast?q=";
                String dailyE1 = "http://api.openweathermap.org/data/2.5/weather?q=";

                StringBuilder dailyE2 = new StringBuilder(dailyE1);
                StringBuilder endpoint2 = new StringBuilder(endpoint1);

                dailyE2.append(city + "&mode=json" + "&APPID=e1c649083a337011ceb4d6b1958ba87c");
                endpoint2.append(city + "&mode=json" + "&type=accurate" + "&APPID=e1c649083a337011ceb4d6b1958ba87c");

                dailyE3 = dailyE2.toString();
                endpoint3 = endpoint2.toString();
                listView.setVisibility(View.GONE);

                new dailyWeather1().execute(dailyE3);
                new openWeatherService().execute(endpoint3);
            }
        });


    }
    public void intList (){
        ChooseCity chooseCity = new ChooseCity();
        items = chooseCity.getAllCity();
       // items = new String[] {"Toronto,Ca","Paris,Fr","Rome","New York","Rio","top","New J","Pe"};
         listItems = new ArrayList<>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(this,R.layout.list_item,R.id.txtitem,listItems);
        listView.setAdapter(adapter);

    }

        public class dailyWeather1 extends AsyncTask<String,String,String[]> {


            @Override
            protected String[] doInBackground(String... params) {
                BufferedReader reader1 = null;
                HttpURLConnection connection1 = null;
                try {
                    URL end1 = new URL(dailyE3);
                    connection1 = (HttpURLConnection) end1.openConnection();
                    connection1.connect();

                    InputStream inputStream1 = connection1.getInputStream();

                    reader1 = new BufferedReader(new InputStreamReader(inputStream1));
                    StringBuilder results5 = new StringBuilder();
                    String line1 = "";

                    while ((line1 = reader1.readLine()) != null) {

                        results5.append(line1);
                    }


                    if (reader1 != null) {
                        reader1.close();
                    }

                    results5.toString();
                    try {
                        JSONObject dayy = new JSONObject(results5.toString());
                        JSONArray weatjer = dayy.getJSONArray("weather");
                        JSONObject first = weatjer.getJSONObject(0);

                        JSONObject temDay = dayy.getJSONObject("main");
                        JSONObject wind = dayy.getJSONObject("wind");
                        JSONObject coord = dayy.getJSONObject("coord");
                        JSONObject con = dayy.getJSONObject("sys");
                        int humidity = temDay.getInt("humidity");
                        int pressure = temDay.getInt("pressure");
                        int speed = wind.getInt("speed");
                        String con1 = con.getString("country");
                        int sunrise = con.getInt("sunrise");
                        int sunset = con.getInt("sunset");
                        int lon = coord.getInt("lon");


                        String cityName = dayy.getString("name");
                        String clods = first.getString("description");
                        String mIcon = first.getString("icon");
                        int dtime = dayy.getInt("dt");


                        int temDa = temDay.getInt("temp") - 273;

                        // mainD maind = new mainD();
                       // mainH mainh = new mainH();
                        dailyInfo daili = new dailyInfo();

                        daili.setTemp_now(temDa);
                        daili.setCon(con1);
                        daili.setCit(cityName);
                        daili.setDesc(clods);
                        daili.setIcon(mIcon);
                        daili.setHumidity(humidity);
                        daili.setPressure(pressure);
                        daili.setSpeed(speed);
                        daili.setSunrise(sunrise);
                        daili.setSunset(sunset);
                        daili.setLongitude(lon);
                        daili.setDtime(dtime);

                        int temDaa = daili.getTemp_now();
                        String cityName1 = daili.getCit();
                        String clods1 = daili.getDesc();
                        String mIcon1 = daili.getIcon();
                        String con2 = daili.getCon();
                        int humidity1 = daili.getHumidity();
                        int pressure1 = daili.getPressure();
                        int speed1 = daili.getSpeed();
                        long sunrise1 = daili.getSunrise();
                        long sunset1 = daili.getSunset();
                        long dtime1 = daili.getDtime();
                        int lon1 = daili.getLongitude();


                        String temDaaa = Integer.toString(temDaa);
                        String humidity2 = Integer.toString(humidity1);
                        String pressure2 = Integer.toString(pressure1);
                        String speed2 = Integer.toString(speed1);

                        double lon2 = lon1 / 15;
                        int lon22 = (int) lon2;
                        int lon3 = lon1 % 15;
                        int lon33 = lon3 * 4;

                        timeZone timezone = new timeZone();
                        timezone.resolve1(lon22,lon1);
                        timezone.resovle2(lon33);

                        String A2 = timezone.getA2();
                        String B2 = timezone.getB2();


                        df = new Date(sunrise1 * 1000L);
                        df1 = new Date(sunset1 * 1000L);
                        df2 = new Date(dtime1 * 1000L);
                        dfm = new SimpleDateFormat("HH:mm");
                        dfm.setTimeZone(TimeZone.getTimeZone("GMT" + A2+":"+B2 ));
                        dfm1 = new SimpleDateFormat("HH:mm");
                        dfm1.setTimeZone(TimeZone.getTimeZone("GMT" + A2+":"+B2));
                        dfm2 = new SimpleDateFormat("dd-MM-yyyy");
                        dfm2.setTimeZone(TimeZone.getTimeZone("GMT" + A2+":"+B2 ));
                        String sunriseFormat = dfm.format(df);
                        String sunsetFormat = dfm1.format(df1);
                        String dateFormat = dfm2.format(df2);



                      //  simplify(humidity2, pressure2, speed2, sunriseFormat, sunsetFormat, dateFormat);


                        oo[0] = cityName1;   oo[7]=humidity2;
                        oo[1] = clods1;       oo[8] = pressure2;
                        oo[2] = temDaaa;    oo[9] = speed2;
                        oo[3] = mIcon1;     oo[10] = sunriseFormat;
                        oo[4] = con2;      oo[11] =sunsetFormat;
                        oo[5] = A2;       oo[12] = dateFormat;
                        oo[6] = B2;

                        return oo;


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    error = e;
                }


                return null;
            }


            /*        } catch (JSONException e) {
                        e.printStackTrace();
                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    error = e;
                }


                return null;    */



            @Override
            protected void onPostExecute(String p[]) {

            try {


                t_now.setText(p[2] + " °C");
                    city_layout.setText(p[0] + "," + p[4]);
                    description.setText(p[1]);
                simplify(p[7],p[8],p[9],p[10],p[11],p[12]);

                   switch (p[3]) {

                        case "01d":
                            iv_weather.setImageResource(R.drawable.sunny);
                            break;
                        case "01n":
                            iv_weather.setImageResource(R.drawable.fair);
                            break;
                        case "02d":
                            iv_weather.setImageResource(R.drawable.partiaclycloudy);
                            break;
                        case "02n":
                            iv_weather.setImageResource(R.drawable.mostlyfair);
                            break;
                        case "03d":
                            iv_weather.setImageResource(R.drawable.mostlysunny);
                            break;
                        case "03n":
                            iv_weather.setImageResource(R.drawable.scatteredclouds);
                            break;
                        case "04d":
                            iv_weather.setImageResource(R.drawable.brokenclouds);
                            break;
                        case "04n":
                            iv_weather.setImageResource(R.drawable.brokenclouds);
                            break;
                        case "09d":
                            iv_weather.setImageResource(R.drawable.lightrain);
                            break;
                        case "09n":
                            iv_weather.setImageResource(R.drawable.showers);
                            break;
                        case "10d":
                            iv_weather.setImageResource(R.drawable.rainsunny);
                            break;
                        case "10n":
                            iv_weather.setImageResource(R.drawable.rainn);
                            break;

                        case "11d":
                            iv_weather.setImageResource(R.drawable.thunderd);
                            break;

                        case "11n":
                            iv_weather.setImageResource(R.drawable.thunderd);
                            break;

                        case "13d":
                            iv_weather.setImageResource(R.drawable.snowww);
                            break;

                        case "13n":
                            iv_weather.setImageResource(R.drawable.snowww);
                            break;
                        case "50d":
                            iv_weather.setImageResource(R.drawable.mist);
                            break;
                        case "50n":
                            iv_weather.setImageResource(R.drawable.mist);
                            break;

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public class openWeatherService extends AsyncTask<String, String, String[]> {

            @Override
            protected String[] doInBackground(String... params) {

                BufferedReader reader = null;
                HttpURLConnection connection = null;
                try {
                    URL end = new URL(endpoint3);
                    connection = (HttpURLConnection) end.openConnection();
                    connection.connect();

                    InputStream inputStream = connection.getInputStream();

                    reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder results1 = new StringBuilder();
                    String line = "";

                    while ((line = reader.readLine()) != null) {

                        results1.append(line);
                    }

                    results1.toString();
                    try {
                        JSONObject data = new JSONObject(results1.toString());


                        JSONObject myCity = data.getJSONObject("city");
                        JSONArray list = data.getJSONArray("list");
                        JSONObject list0 = list.getJSONObject(0);
                        JSONObject list1 = list.getJSONObject(1);
                        JSONObject list2 = list.getJSONObject(2);
                        JSONObject list3 = list.getJSONObject(3);
                        JSONObject list4 = list.getJSONObject(4);
                        JSONObject list5 = list.getJSONObject(5);

                        JSONArray weather0 = list0.getJSONArray("weather");
                        JSONArray weather1 = list1.getJSONArray("weather");
                        JSONArray weather2 = list2.getJSONArray("weather");
                        JSONArray weather3 = list3.getJSONArray("weather");
                        JSONArray weather4 = list4.getJSONArray("weather");
                        JSONArray weather5 = list5.getJSONArray("weather");


                        JSONObject weath0 = weather0.getJSONObject(0);
                        JSONObject weath1 = weather1.getJSONObject(0);
                        JSONObject weath2 = weather2.getJSONObject(0);
                        JSONObject weath3 = weather3.getJSONObject(0);
                        JSONObject weath4 = weather4.getJSONObject(0);
                        JSONObject weath5 = weather5.getJSONObject(0);

                        JSONObject main0 = list0.getJSONObject("main");
                        JSONObject main1 = list1.getJSONObject("main");
                        JSONObject main2 = list2.getJSONObject("main");
                        JSONObject main3 = list3.getJSONObject("main");
                        JSONObject main4 = list4.getJSONObject("main");
                        JSONObject main5 = list5.getJSONObject("main");

                        String my_city = myCity.getString("name");
                        String dt0 = list0.getString("dt_txt");
                        String dt1 = list1.getString("dt_txt");
                        String dt2 = list2.getString("dt_txt");

                        String clo0 = weath0.getString("description");
                        String clo1 = weath1.getString("description");
                        String clo2 = weath2.getString("description");
                        String clo3 = weath3.getString("description");
                        String clo4 = weath4.getString("description");
                        String clo5 = weath5.getString("description");

                        String icon0 = weath0.getString("icon");
                        String icon1 = weath1.getString("icon");
                        String icon2 = weath2.getString("icon");
                        String icon3 = weath3.getString("icon");
                        String icon4 = weath4.getString("icon");
                        String icon5 = weath5.getString("icon");

                        String des0 = weath0.getString("main");
                        String des1 = weath1.getString("main");
                        String des2 = weath2.getString("main");
                        String des3 = weath3.getString("main");
                        String des4 = weath4.getString("main");
                        String des5 = weath5.getString("main");


                        int temp0 = main0.getInt("temp");
                        int temp1 = main1.getInt("temp");
                        int temp2 = main2.getInt("temp");
                        int temp3 = main3.getInt("temp");
                        int temp4 = main4.getInt("temp");
                        int temp5 = main5.getInt("temp");


                        int c_temp0 = temp0 - 273;
                        int c_temp1 = temp1 - 273;
                        int c_temp2 = temp2 - 273;
                        int c_temp3 = temp3 - 273;
                        int c_temp4 = temp4 - 273;
                        int c_temp5 = temp5 - 273;

                        String dt_tx0 = list0.getString("dt_txt");
                        String dt_tx1 = list1.getString("dt_txt");
                        String dt_tx2 = list2.getString("dt_txt");
                        String dt_tx3 = list3.getString("dt_txt");
                        String dt_tx4 = list4.getString("dt_txt");
                        String dt_tx5 = list5.getString("dt_txt");

                        String dti0 = dt_tx0.substring(11, 16);
                        String dti1 = dt_tx1.substring(11, 16);
                        String dti2 = dt_tx2.substring(11, 16);
                        String dti3 = dt_tx3.substring(11, 16);
                        String dti4 = dt_tx4.substring(11, 16);
                        String dti5 = dt_tx5.substring(11, 16);



                    //    mainT maint = new mainT();


                        maint.setTemp0(c_temp0);
                        maint.setTemp1(c_temp1);
                        maint.setTemp2(c_temp2);
                        maint.setTemp3(c_temp3);
                        maint.setTemp4(c_temp4);
                        maint.setTemp5(c_temp5);

                        maint.setCondi0(clo0);
                        maint.setCondi1(clo1);
                        maint.setCondi2(clo2);
                        maint.setDt0(dt0);
                        maint.setDt1(dt1);
                        maint.setDt2(dt2);
                        maint.setIcon0(icon0);
                        maint.setIcon1(icon1);
                        maint.setIcon2(icon2);
                        maint.setIcon3(icon3);
                        maint.setIcon4(icon4);
                        maint.setIcon5(icon5);

                        int t0 = maint.getTemp0();
                        int t1 = maint.getTemp1();
                        int t2 = maint.getTemp2();
                        int t3 = maint.getTemp3();
                        int t4 = maint.getTemp4();
                        int t5 = maint.getTemp5();


                        String t_0 = Integer.toString(t0);
                        String t_1 = Integer.toString(t1);
                        String t_2 = Integer.toString(t2);
                        String t_3 = Integer.toString(t3);
                        String t_4 = Integer.toString(t4);
                        String t_5 = Integer.toString(t5);

                        String icon00 = maint.getIcon0();  String icon11 = maint.getIcon1();
                        String icon22 = maint.getIcon2();   String icon33 = maint.getIcon3();
                        String icon44 = maint.getIcon4();   String icon55 = maint.getIcon5();


                        String c0 = maint.getCondi0();
                        String c1 = maint.getCondi0();
                        String c2 = maint.getCondi0();

                        String ti0 = maint.getDt0();
                        String ti1 = maint.getDt0();
                        String ti2 = maint.getDt0();



                        rr[0] = t_0; rr[1] = t_1; rr[2] = t_2; rr[3] = t_3; rr[4] = t_4; rr[5] = t_5; rr[6] = dti0;
                        rr[7] = dti1; rr[8] = dti2; rr[9] = dti3; rr[10] = dti4; rr[11] = dti5; rr[12] = clo0;
                        rr[19] = icon00; rr[20] = icon11; rr[21] = icon22; rr[22] = icon33; rr[23] = icon44; rr[24] = icon55;

                        return rr;

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    error = e;
                }


                return null;
            }

            @Override
            protected void onPostExecute(String s[]) {

                try{
                time0.setText(s[6]);
                time1.setText(s[7]);
                time2.setText(s[8]);
                time3.setText(s[9]);
                time4.setText(s[10]);
                time5.setText(s[11]);


                temp_6.setText(s[0]);
                temp_9.setText(s[1]);
                temp_12.setText(s[2]);
                temp_15.setText(s[3]);
                temp_18.setText(s[4]);
                temp_21.setText(s[5]);


                    switch (s[19]) {
                        case "01d":
                            ivT1.setImageResource(R.drawable.sunny);
                            break;
                        case "01n":
                            ivT1.setImageResource(R.drawable.fair);
                            break;
                        case "02d":
                            ivT1.setImageResource(R.drawable.partiaclycloudy);
                            break;
                        case "02n":
                            ivT1.setImageResource(R.drawable.mostlyfair);
                            break;
                        case "03d":
                            ivT1.setImageResource(R.drawable.mostlysunny);
                            break;
                        case "03n":
                            ivT1.setImageResource(R.drawable.scatteredclouds);
                            break;
                        case "04d":
                            ivT1.setImageResource(R.drawable.brokenclouds);
                            break;
                        case "04n":
                            ivT1.setImageResource(R.drawable.brokenclouds);
                            break;
                        case "09d":
                            ivT1.setImageResource(R.drawable.lightrain);
                            break;
                        case "09n":
                            ivT1.setImageResource(R.drawable.showers);
                            break;
                        case "10d":
                            ivT1.setImageResource(R.drawable.rainsunny);
                            break;
                        case "10n":
                            ivT1.setImageResource(R.drawable.rainn);
                            break;
                        case "11d":
                            ivT1.setImageResource(R.drawable.thunderd);
                            break;

                        case "11n":
                            ivT1.setImageResource(R.drawable.thunderd);
                            break;

                        case "13d":
                            ivT1.setImageResource(R.drawable.snowww);
                            break;
                        case "13n":
                            ivT1.setImageResource(R.drawable.snowww);
                            break;
                        case "50d":
                            ivT1.setImageResource(R.drawable.mist);
                            break;
                        case "50n":
                            ivT1.setImageResource(R.drawable.mist);
                            break;
                    }
                    switch (s[20]) {

                        case "01d":
                            ivT2.setImageResource(R.drawable.sunny);
                            break;
                        case "01n":
                            ivT2.setImageResource(R.drawable.fair);
                            break;
                        case "02d":
                            ivT2.setImageResource(R.drawable.partiaclycloudy);
                            break;
                        case "02n":
                            ivT2.setImageResource(R.drawable.mostlyfair);
                            break;
                        case "03d":
                            ivT2.setImageResource(R.drawable.mostlysunny);
                            break;
                        case "03n":
                            ivT2.setImageResource(R.drawable.scatteredclouds);
                            break;
                        case "04d":
                            ivT2.setImageResource(R.drawable.brokenclouds);
                            break;
                        case "04n":
                            ivT2.setImageResource(R.drawable.brokenclouds);
                            break;
                        case "09d":
                            ivT2.setImageResource(R.drawable.lightrain);
                            break;
                        case "09n":
                            ivT2.setImageResource(R.drawable.showers);
                            break;
                        case "10d":
                            ivT2.setImageResource(R.drawable.rainsunny);
                            break;
                        case "10n":
                            ivT2.setImageResource(R.drawable.rainn);
                            break;

                        case "11d":
                            ivT2.setImageResource(R.drawable.thunderd);
                            break;

                        case "11n":
                            ivT2.setImageResource(R.drawable.thunderd);
                            break;

                        case "13d":
                            ivT2.setImageResource(R.drawable.snowww);
                            break;
                        case "13n":
                            ivT2.setImageResource(R.drawable.snowww);
                            break;
                        case "50d":
                            ivT2.setImageResource(R.drawable.mist);
                            break;
                        case "50n":
                            ivT2.setImageResource(R.drawable.mist);
                            break;
                    }

                    switch (s[21]) {

                        case "01d":
                            ivT3.setImageResource(R.drawable.sunny);
                            break;
                        case "01n":
                            ivT3.setImageResource(R.drawable.fair);
                            break;
                        case "02d":
                            ivT3.setImageResource(R.drawable.partiaclycloudy);
                            break;
                        case "02n":
                            ivT3.setImageResource(R.drawable.mostlyfair);
                            break;
                        case "03d":
                            ivT3.setImageResource(R.drawable.mostlysunny);
                            break;
                        case "03n":
                            ivT3.setImageResource(R.drawable.scatteredclouds);
                            break;
                        case "04d":
                            ivT3.setImageResource(R.drawable.brokenclouds);
                            break;
                        case "04n":
                            ivT3.setImageResource(R.drawable.brokenclouds);
                            break;
                        case "09d":
                            ivT3.setImageResource(R.drawable.lightrain);
                            break;
                        case "09n":
                            ivT3.setImageResource(R.drawable.showers);
                            break;
                        case "10d":
                            ivT3.setImageResource(R.drawable.rainsunny);
                            break;
                        case "10n":
                            ivT3.setImageResource(R.drawable.rainn);
                            break;
                        case "11d":
                            ivT3.setImageResource(R.drawable.thunderd);
                            break;
                        case "11n":
                            ivT3.setImageResource(R.drawable.thunderd);
                            break;
                        case "13d":
                            ivT3.setImageResource(R.drawable.snowww);
                            break;
                        case "13n":
                            ivT3.setImageResource(R.drawable.littlesnow);
                            break;
                        case "50d":
                            ivT2.setImageResource(R.drawable.mist);
                            break;
                        case "50n":
                            ivT2.setImageResource(R.drawable.mist);
                            break;
                    }

                    switch (s[22]) {

                        case "01d":
                            ivT4.setImageResource(R.drawable.sunny);
                            break;
                        case "01n":
                            ivT4.setImageResource(R.drawable.fair);
                            break;
                        case "02d":
                            ivT4.setImageResource(R.drawable.partiaclycloudy);
                            break;
                        case "02n":
                            ivT4.setImageResource(R.drawable.mostlyfair);
                            break;
                        case "03d":
                            ivT4.setImageResource(R.drawable.mostlysunny);
                            break;
                        case "03n":
                            ivT4.setImageResource(R.drawable.scatteredclouds);
                            break;
                        case "04d":
                            ivT4.setImageResource(R.drawable.brokenclouds);
                            break;
                        case "04n":
                            ivT4.setImageResource(R.drawable.brokenclouds);
                            break;
                        case "09d":
                            ivT4.setImageResource(R.drawable.lightrain);
                            break;
                        case "09n":
                            ivT4.setImageResource(R.drawable.showers);
                            break;
                        case "10d":
                            ivT4.setImageResource(R.drawable.rainsunny);
                            break;
                        case "10n":
                            ivT4.setImageResource(R.drawable.rainn);
                            break;

                        case "11d":
                            ivT4.setImageResource(R.drawable.thunderd);
                            break;

                        case "11n":
                            ivT4.setImageResource(R.drawable.thunderd);
                            break;

                        case "13d":
                            ivT4.setImageResource(R.drawable.snowww);
                            break;
                        case "13n":
                            ivT4.setImageResource(R.drawable.snowww);
                            break;
                        case "50d":
                            ivT4.setImageResource(R.drawable.mist);
                            break;
                        case "50n":
                            ivT4.setImageResource(R.drawable.mist);
                            break;
                    }

                    switch (s[23]) {

                        case "01d":
                            ivT5.setImageResource(R.drawable.sunny);
                            break;
                        case "01n":
                            ivT5.setImageResource(R.drawable.fair);
                            break;
                        case "02d":
                            ivT5.setImageResource(R.drawable.partiaclycloudy);
                            break;
                        case "02n":
                            ivT5.setImageResource(R.drawable.mostlyfair);
                            break;
                        case "03d":
                            ivT5.setImageResource(R.drawable.mostlysunny);
                            break;
                        case "03n":
                            ivT5.setImageResource(R.drawable.scatteredclouds);
                            break;
                        case "04d":
                            ivT5.setImageResource(R.drawable.brokenclouds);
                            break;
                        case "04n":
                            ivT5.setImageResource(R.drawable.brokenclouds);
                            break;
                        case "09d":
                            ivT5.setImageResource(R.drawable.lightrain);
                            break;
                        case "09n":
                            ivT5.setImageResource(R.drawable.showers);
                            break;
                        case "10d":
                            ivT5.setImageResource(R.drawable.rainsunny);
                            break;
                        case "10n":
                            ivT5.setImageResource(R.drawable.rainn);
                            break;

                        case "11d":
                            ivT5.setImageResource(R.drawable.thunderd);
                            break;

                        case "11n":
                            ivT5.setImageResource(R.drawable.thunderd);
                            break;

                        case "13d":
                            ivT5.setImageResource(R.drawable.snowww);
                            break;
                        case "13n":
                            ivT5.setImageResource(R.drawable.snowww);
                            break;
                        case "50d":
                            ivT5.setImageResource(R.drawable.mist);
                            break;
                        case "50n":
                            ivT5.setImageResource(R.drawable.mist);
                            break;
                    }

                    switch (s[24]) {

                        case "01d":
                            ivT6.setImageResource(R.drawable.sunny);
                            break;
                        case "01n":
                            ivT6.setImageResource(R.drawable.fair);
                            break;
                        case "02d":
                            ivT6.setImageResource(R.drawable.partiaclycloudy);
                            break;
                        case "02n":
                            ivT6.setImageResource(R.drawable.mostlyfair);
                            break;
                        case "03d":
                            ivT6.setImageResource(R.drawable.mostlysunny);
                            break;
                        case "03n":
                            ivT6.setImageResource(R.drawable.scatteredclouds);
                            break;
                        case "04d":
                            ivT6.setImageResource(R.drawable.brokenclouds);
                            break;
                        case "04n":
                            ivT6.setImageResource(R.drawable.brokenclouds);
                            break;
                        case "09d":
                            ivT6.setImageResource(R.drawable.lightrain);
                            break;
                        case "09n":
                            ivT6.setImageResource(R.drawable.showers);
                            break;
                        case "10d":
                            ivT6.setImageResource(R.drawable.rainsunny);
                            break;
                        case "10n":
                            ivT6.setImageResource(R.drawable.rainn);
                            break;

                        case "11d":
                            ivT6.setImageResource(R.drawable.thunderd);
                            break;

                        case "11n":
                            ivT6.setImageResource(R.drawable.thunderd);
                            break;

                        case "13d":
                            ivT6.setImageResource(R.drawable.snowww);
                            break;
                        case "13n":
                            ivT6.setImageResource(R.drawable.littlesnow);
                            break;
                        case "50d":
                            ivT6.setImageResource(R.drawable.mist);
                            break;
                        case "50n":
                            ivT6.setImageResource(R.drawable.mist);
                            break;
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

public void simplify(final String w1,final String w2, final String w3,final String w4, final String w5, final String w6){

   // humid.setText(w1);
    //a.allwindy(w1,w2,w3);
    try {
        iv_weather.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent x1 = new Intent(WeatherActivity.this, Pop.class);
                x1.putExtra("humid", w1);
                x1.putExtra("pressure", w2);
                x1.putExtra("speed", w3);
                x1.putExtra("sunrise", w4);
                x1.putExtra("sunset", w5);
                x1.putExtra("dateformat", w6);

                startActivity(x1);
            }
        });
    }catch (Exception e){
        e.printStackTrace();
    }

}

    }


