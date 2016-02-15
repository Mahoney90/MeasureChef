package com.mahoneyapps.measurechef;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class LiquidMeasurements extends ActivityToExtend {

    // create an ArrayList called liquidMeasurementList
    ArrayList<Measurement> liquidMeasurementList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liquid_measurements);

        // add Liquid Measurements to the array
        addLiquidMeasurementsToArray();

        // set liquidAdapter as the adapter on our ListView
        setAdapter();

        // create a Toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // enable the user to hit the up button to return home
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public class LiquidAdapter extends ArrayAdapter<Measurement> {

        // LiquidAdapter constructor, takes our ArrayList as an argument
        public LiquidAdapter(Context context, int resource, ArrayList<Measurement> liquidObjects) {
            super(context, resource, liquidObjects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // if we don't have a row view yet, inflate our row view
            if (convertView == null){
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.metric_row_layout, parent, false);
            }

            // returns position of the view
            final Measurement measurementPosition = getItem(position);

            // initialize our views and call getters to set the values
            final EditText editText = (EditText) convertView.findViewById(R.id.converting_measurement_edit);
            double quantity = measurementPosition.getQuantity();
            String strQuantity = String.valueOf(quantity);
            editText.setText(strQuantity);

            final TextView newNumberView = (TextView) convertView.findViewById(R.id.converted_measurement_edit);
            double quantity1 = measurementPosition.getMetricQuantity();
            String strQuantity1 = String.format("%.2f", measurementPosition.getMetricQuantity());
            newNumberView.setText(strQuantity1);

            TextView convertingLiquidMeasure = (TextView) convertView.findViewById(R.id.converting_measurement_text);
            String quantity2 = measurementPosition.getUsMeasurement();
            convertingLiquidMeasure.setText(quantity2);

            TextView convertedLiquidMeasure = (TextView) convertView.findViewById(R.id.converted_measurement_text);
            String quantity3 = measurementPosition.getMetricMeasurement();
            convertedLiquidMeasure.setText(quantity3);

            // set a listener for when the user clicks on the EditText
            editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    // select all text when the Edit box is clicked
                    editText.setSelectAllOnFocus(true);
                    // when the user hits enter, retrieve the new value and pass it to the MULTIPLY method
                    if ((actionId == EditorInfo.IME_ACTION_DONE) || (actionId == EditorInfo.IME_ACTION_GO)){
                        String editStr = v.getText().toString();
                        double editDouble = Double.parseDouble(editStr);
                        multiply(editDouble);

                        // hide the soft keyboard when user hits enter
                        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);

                        return true;
                    }

                    return false;
                }

                private void multiply(double number) {
                    // retrieve the value in the appropriate column, then call the corresponding
                    // calculation based on the US measurement in that row
                    String liquidMeasurementToConvert = measurementPosition.getUsMeasurement();

                    switch (liquidMeasurementToConvert){
                        case "fluid oz":
                            double newQuantity = number / 8.0;
                            String newQuantityRounded = String.format("%.2f", newQuantity);
                            String newQuantityStr = String.valueOf(newQuantityRounded);
                            newNumberView.setText(newQuantityStr);
                            break;

                        case "Pint":
                            double pintQuantity = number * 2.0;
                            String pintQuantityStr = String.valueOf(pintQuantity);
                            newNumberView.setText(pintQuantityStr);
                            break;

                        case "Quart":
                            double quartQuantity = number * 2.0;
                            String quartQuantityStr = String.valueOf(quartQuantity);
                            newNumberView.setText(quartQuantityStr);
                            break;

                        case "Gallon":
                            double gallonQuantity = number * 4.0;
                            String gallonQuantityStr = String.valueOf(gallonQuantity);
                            newNumberView.setText(gallonQuantityStr);
                            break;
                    }
                    
                    double moreThanOne = measurementPosition.getQuantity();

//                    makeSingular(moreThanOne);
                }

//                private void makeSingular(double moreThanOne) {
//
//                }
            });
            // return our view
            return convertView;
        }
    }

    private void setAdapter() {
        // initialize our ListView and set our custom adapter as the adapter that populates the
        // ListView with our ArrayList
        ListView listView = (ListView) findViewById(R.id.liquid_list_view);
        LiquidAdapter liquidAdapter = new LiquidAdapter(this, R.id.liquid_list_view, liquidMeasurementList);
        listView.setAdapter(liquidAdapter);

    }

    private void addLiquidMeasurementsToArray() {
        // create new Measurement objects and add them to our ArrayList
        Measurement measurement1 = new Measurement(8.00, "fluid oz", 1.00, "Cups");
        liquidMeasurementList.add(measurement1);
        Measurement measurement2 = new Measurement(1.00, "Pint", 2.00, "Cups");
        liquidMeasurementList.add(measurement2);
        Measurement measurement3 = new Measurement(1.00, "Quart", 2.00, "Pints");
        liquidMeasurementList.add(measurement3);
        Measurement measurement4 = new Measurement(1.00, "Gallon", 4.00, "Quarts");
        liquidMeasurementList.add(measurement4);
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

        // call super to populate options menu from ActivityToExtend
        return super.onOptionsItemSelected(item);
    }
}
