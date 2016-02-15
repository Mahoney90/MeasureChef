package com.mahoneyapps.measurechef;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Brendan on 2/5/2016.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Splash activity when the App is Launched (MeasureChef background) which then immediately
        // opens up MainActivity when it is ready
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
