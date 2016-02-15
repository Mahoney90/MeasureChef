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

public class FromMetric extends ActivityToExtend {

    private static final String TAG = FromUS.class.getSimpleName();
    // declare a new MyCustomAdapter called dataAdapter
    private MyCustomAdapter dataAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.from_metric);

        // create a Toolbar, set the title, and then set it as the SupportAcionBar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("From Metric to U.S.");
        setSupportActionBar(myToolbar);

//        generate the ListView form ArrayList
        displayListView();

        // Enable up button so user can click to get home (wrap in try block, throws exception)
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    private void displayListView() {
//        ArrayList of measurements
        ArrayList<Measurement> measurementList = new ArrayList<Measurement>();

        // add Measurement objects to our ArrayList
        Measurement measurement = new Measurement(1.00, "mL", .20, "teaspoons");
        measurementList.add(measurement);
        Measurement measurement2 = new Measurement(1.00, "mL", .066666, "Tablespoons");
        measurementList.add(measurement2);
        Measurement measurement3 = new Measurement(1.00, "mL", .033333, "Fluid Oz");
        measurementList.add(measurement3);
        Measurement measurement4 = new Measurement(1.00, "mL", .004166, "Cups");
        measurementList.add(measurement4);
        Measurement measurement5 = new Measurement(1.00, "mL", .002127, "Pints");
        measurementList.add(measurement5);
        Measurement measurement6 = new Measurement(1.00, "Liters", .263157, "Gallons");
        measurementList.add(measurement6);
        Measurement measurement7 = new Measurement(1.00, "Liters", 1.05263, "Quarts");
        measurementList.add(measurement7);
        Measurement measurement8 = new Measurement(1.00, "g", .035714, "oz");
        measurementList.add(measurement8);
        Measurement measurement9 = new Measurement(1.00, "g", .002202, "lbs");
        measurementList.add(measurement9);

        // find ListView view
        ListView metricListView = (ListView) findViewById(R.id.from_metric_list_view);

        // initialize dataAdapter, the view for the adapter is the view for each row, taking our
        // newly formed ArrayList as an argument
        dataAdapter = new MyCustomAdapter(this, R.layout.metric_row_layout, measurementList);

        // setting the adapter on our Listview to be our dataAdapter
        metricListView.setAdapter(dataAdapter);

        metricListView.onSaveInstanceState();

    }


    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // returning our super, as defined in ActivityToExtend
        return super.onOptionsItemSelected(item);
   }

    private class MyCustomAdapter extends ArrayAdapter<Measurement> {
        // Declare measurementList
        private ArrayList<Measurement> measurementList;

        public MyCustomAdapter(Context context, int textViewResourceId, ArrayList<Measurement> measurementList){
            super(context, textViewResourceId, measurementList);
            this.measurementList = new ArrayList<>();
            // add measurementList (ArrayList of Measurements) to our ArrayList variable within
            // the adapter
            this.measurementList.addAll(measurementList);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // stores the position of the item in view
            final Measurement measurement = getItem(position);

            if (convertView == null) {
                // if convertView is null, inflate our row layout
                LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                convertView = layoutInflater.inflate(R.layout.metric_row_layout, parent, false);
            }

            // assign variables to our important widgets
            final EditText editText = (EditText) convertView.findViewById(R.id.converting_measurement_edit);
            final TextView textView = (TextView) convertView.findViewById(R.id.converting_measurement_text);
            final TextView textView2 = (TextView) convertView.findViewById(R.id.converted_measurement_edit);
            TextView textView3 = (TextView) convertView.findViewById(R.id.converted_measurement_text);

            // get the QUANTITY of the view and store it in our EditText
            String s = String.valueOf(measurement.quantity);
            editText.setText(s);

            // set TextView equal to the Metric (since it's the FromMetric class) Measurement of
            // the position in view
            textView.setText(measurement.getUsMeasurement());

            // set the changing/converted TextView to be formatted to 2 decimal places
            String s2 = String.format("%.2f", measurement.getMetricQuantity());
            textView2.setText(s2);

            // again, using a getter on the view position to set a TextView
            textView3.setText(measurement.getMetricMeasurement());

            // When the user clicks an EditText....
            editText.setOnEditorActionListener(new TextView.OnEditorActionListener(){

                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    // select all the text when clicked
                    editText.setSelectAllOnFocus(true);

                    // when the done checkmark is clicked, get the new value and pass it to multiply,
                    // close the soft keyboard
                    if((actionId == EditorInfo.IME_ACTION_DONE) || (actionId == EditorInfo.IME_ACTION_GO)){
                        String newResult = v.getText().toString();
                        double newNumber = Double.parseDouble(newResult);
                        multiply(newNumber);
                        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);

                        return true;
                    }

                    return false;
                }

                private void multiply(double newNumber) {

                    // based on the position of the view clicked, we will multiply the new user value
                    // by the necessary conversion number -- the POSITION is tied to the multiplier

                    // update the new TextView value in column 3
                    switch(position){
                        case 0:
                            double answer = newNumber / 5.0;
                            String answerStr = String.format("%.2f", answer);
                            textView2.setText(answerStr);
                            break;

                        case 1:
                            double answer1 = newNumber / 15.0;
                            String answerStr1 = String.format("%.2f", answer1);
                            textView2.setText(answerStr1);
                            break;

                        case 2:
                            double answer2 = newNumber / 30.0;
                            String answerStr2 = String.format("%.2f", answer2);
                            textView2.setText(answerStr2);
                            break;

                        case 3:
                            double answer3 = newNumber / 40.0;
                            String strAnswer3 = String.format("%.2f", answer3);
                            textView2.setText(strAnswer3);
                            break;

                        case 4:
                            double answer4 = newNumber / 470.0;
                            String strAnswer4 = String.format("%.2f", answer4);
                            textView2.setText(strAnswer4);
                            break;

                        case 5:
                            double answer5 = newNumber / 3.8;
                            String strAnswer5 = String.format("%.2f", answer5);
                            textView2.setText(strAnswer5);
                            break;

                        case 6:
                            double answer6 = newNumber / .95;
                            String strAnswer6 = String.format("%.2f",answer6);
                            textView2.setText(strAnswer6);
                            break;

                        case 7:
                            double answer7 = newNumber / 28.0;
                            String strAnswer7 = String.format("%.2f", answer7);
                            textView2.setText(strAnswer7);
                            break;

                        case 8:
                            double answer8 = newNumber / 453.6;
                            String strAnswer8 = String.format("%.2f", answer8);
                            textView2.setText(strAnswer8);
                            break;

                    }

                }

            });

            // return our view that was initially clicked
            return convertView;
        }
    }

}