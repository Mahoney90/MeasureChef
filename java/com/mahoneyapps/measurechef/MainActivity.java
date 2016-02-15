package com.mahoneyapps.measurechef;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActivityToExtend {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Add a toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_to_and_from_metric, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }


    // when you click the Metric button, open the Metric activity
    public void openMetricConversion(View view) {

                // open FromUS activity when this button is clicked
                Intent openClickedConversionIntent = new Intent(MainActivity.this, FromUS.class);
                startActivity(openClickedConversionIntent);
//            }
//        });
    }

    public void openFromMetricConversion(View view) {

                // open FromMetric activity when this button is clicked
                Intent openFromMetricConversionIntent = new Intent(MainActivity.this, FromMetric.class);
                startActivity(openFromMetricConversionIntent);
            }


    public void openLiquidConversion(View view) {

                // open Liquid Conversion activity when button is clicked
                Intent openLiquidConversionIntent = new Intent(MainActivity.this, LiquidMeasurements.class);
                startActivity(openLiquidConversionIntent);
            }

    public void openTemperatureConversion(View view) {

                // open Temperature Conversion activity when clicked
                Intent openTemperatureConversion = new Intent(MainActivity.this, OvenTemperatureConversions.class);
                startActivity(openTemperatureConversion);
            }

}
