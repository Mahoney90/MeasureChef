package com.mahoneyapps.measurechef;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class OvenTemperatureConversions extends ActivityToExtend {

    // declare a fahrenheitAdapter of type MyAdapter
    MyAdapter fahrenheitAdapter;

    // initialize an array of integers for F and C
    private static Integer[] ovenTempCelcius = new Integer[] {
            140,
            150,
            165,
            180,
            190,
            200,
            220,
            230,
            240
    };


    private static Integer[] ovenTempFarenheit = new Integer[] {
            275,
            300,
            325,
            350,
            375,
            400,
            425,
            450,
            475
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oven_temperature_conversions);

        // fahrenheitAdapter takes the context and then two arrays as parameters, the 2 integer
        // arrays we just initialized
        fahrenheitAdapter = new MyAdapter(this, ovenTempFarenheit, ovenTempCelcius);

        // initialize our ListView
        ListView FarenheitListView = (ListView) findViewById(R.id.oven_temp_list_view);

        // set the adapter on our ListView as our custom adapter
        FarenheitListView.setAdapter(fahrenheitAdapter);

        // inflate our header XML (with 2 TextViews as column headers) and add it as a HeaderView
        LayoutInflater inflater = getLayoutInflater();
        View header = inflater.inflate(R.layout.header_layout_temp, FarenheitListView, false);

        FarenheitListView.addHeaderView(header, null, false);

        // Add a toolbar to the SupportActionBar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // call method for user to do own conversion
        makeYourOwn();

        // enable option to clicked up button to get to App home, wrap in try block
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    public void makeYourOwn() {

        final EditText enterF = (EditText) findViewById(R.id.make_own_farenheit);
        final EditText enterC = (EditText) findViewById(R.id.make_own_celcius);

        // when the Edit box for Enter F is clicked...
        enterF.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (v.getText().toString().length() < 1) {
                    // is user doesn't enter a number and hits enter, show a toast
                    // encouraging user to enter number
                    Context context = getApplicationContext();
                    CharSequence text = "Please enter a number!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return false;
                } else {
                    // hide soft keyboard when user enters value
                    if ((actionId == EditorInfo.IME_ACTION_DONE) || (actionId == EditorInfo.IME_ACTION_GO)) {
                        InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(enterC.getWindowToken(), 0);
                    }
                    // retrieve user's number and convert it to Celcius for the Celcius box
                    String strFahrenheitNumber = v.getText().toString();
                    double fahrenheitNumber = Double.parseDouble(strFahrenheitNumber);
                    convertF(fahrenheitNumber);
                    return true;
                }

            }
        });

        // when the Edit box for Enter C is clicked...
        enterC.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // is user doesn't enter a number and hits enter, show a toast
                // encouraging user to enter number
                if (v.getText().toString().length() < 1){
                    Context context = getApplicationContext();
                    CharSequence text = "Please enter a number!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return false;
                } else {
                    // hide soft keyboqrd after user enters value
                    if ((actionId == EditorInfo.IME_ACTION_DONE) || (actionId == EditorInfo.IME_ACTION_GO)){
                        InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(enterC.getWindowToken(), 0);
                    }
                    // retrieve user's number and convert it to Fahrenheit for the Fahrenheit box
                    String strCelciusNumber = v.getText().toString();
                    double celciusNumber = Double.parseDouble(strCelciusNumber);
                    convertC(celciusNumber);
                    return true;
                }
            }
        });
    }


    // this method clears the Edit boxes for a user's own conversion when clicked
    public void clear(View buttonClicked) {
        EditText fahrenheitBox = (EditText) findViewById(R.id.make_own_farenheit);
        EditText celciusBox = (EditText) findViewById(R.id.make_own_celcius);

        fahrenheitBox.setText("");
        celciusBox.setText("");

    }

    public void convertF(double number) {
        // do F --> C conversion and set result in Celcius box
        double celcius = (number - 32) / 1.8;
        String celciusRounded = String.format("%.2f", celcius);

        EditText enterC = (EditText) findViewById(R.id.make_own_celcius);
        enterC.setText(celciusRounded + "°");
    }

    public void convertC(double number){
        // do C --> F conversion and set result in Fahrenheit box
        double fahrenheit = (number * 1.8) + 32;
        String fahrenheitRounded = String.format("%.2f", fahrenheit);

        EditText enterF = (EditText) findViewById(R.id.make_own_farenheit);
        enterF.setText(fahrenheitRounded + "°");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // returning our super, as defined in ActivityToExtend
        return super.onOptionsItemSelected(item);
    }
}
