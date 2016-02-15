package com.mahoneyapps.measurechef;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ActivityToExtend extends AppCompatActivity {


    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        // Inflate custom menu linking to other conversion pages
        inflater.inflate(R.menu.menu_to_and_from_metric, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // get ID of clicked view, then call the method that opens the intent to start a new activity
        // of that view

        switch (id){
            case R.id.home1:
                goHome();
                return true;

            case R.id.switch_to_from_us:
                goToUS();
                return true;

            case R.id.switch_to_liquid:
                goToLiquid();
                return true;

            case R.id.switch_to_oven_tempatures:
                goToOvenTempature();
                return true;

            case R.id.switch_to_from_metric:
                goToMetric();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // menu methods
    // new intent from current activity to the destination activity

    private void goToMetric() {
        Intent goToMetric = new Intent(this, FromMetric.class);
        startActivity(goToMetric);
    }

    private void goToOvenTempature() {
        Intent goToOven = new Intent(this, OvenTemperatureConversions.class);
        startActivity(goToOven);
    }

    private void goToLiquid() {
        Intent goToLiquid = new Intent(this, LiquidMeasurements.class);
        startActivity(goToLiquid);
    }

    private void goToUS() {
        Intent goToUs = new Intent(this, FromUS.class);
        startActivity(goToUs);
    }

    public void goHome() {
        Intent goToHome = new Intent(this, MainActivity.class);
        startActivity(goToHome);
    }
}
