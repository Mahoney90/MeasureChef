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

public class FromUS extends ActivityToExtend {

    private static final String TAG = FromUS.class.getSimpleName();
    // declare a new MyCustomAdapter called dataAdapter
    private MyCustomAdapter dataAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_us);

        // create a Toolbar and then set it as the SupportAcionBar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
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
        Measurement measurement = new Measurement(1.00, "teaspoon", 5.0, "mL");
        measurementList.add(measurement);
        Measurement measurement2 = new Measurement(1.00, "Tablespoon", 15.0, "mL");
        measurementList.add(measurement2);
        Measurement measurement3 = new Measurement(1.00, "Fluid oz", 30.0, "mL");
        measurementList.add(measurement3);
        Measurement measurement4 = new Measurement(1.00, "Cup", 240.0, "mL");
        measurementList.add(measurement4);
        Measurement measurement5 = new Measurement(1.00, "Pint", 470.0, "mL");
        measurementList.add(measurement5);
        Measurement measurement6 = new Measurement(1.00, "Gallon", 3.8, "Liters");
        measurementList.add(measurement6);
        Measurement measurement7 = new Measurement(1.00, "Quart", .95, "Liters");
        measurementList.add(measurement7);
        Measurement measurement8 = new Measurement(1.00, "oz", 28.0, "g");
        measurementList.add(measurement8);
        Measurement measurement9 = new Measurement(1.00, "lb", 454.0, "g");
        measurementList.add(measurement9);

//        initialize our ListView
        ListView metricListView = (ListView) findViewById(R.id.from_us_list_view);

        // initialize dataAdapter, the view for the adapter is the view for each row, taking our
        // newly formed ArrayList as an argument
        dataAdapter = new MyCustomAdapter(this, R.layout.from_us_row_layout, measurementList);

        // setting the adapter on our Listview to be our dataAdapter
        metricListView.setAdapter(dataAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.menu_to_and_from_metric, menu);
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


//    public void goHome() {
//        Intent goToHome = new Intent(this, MainActivity.class);
//        startActivity(goToHome);
//    }

    private class MyCustomAdapter extends ArrayAdapter<Measurement> {
        // Declare measurementList
        private ArrayList<Measurement> measurementList;

        public MyCustomAdapter(Context context, int textViewResourceId, ArrayList<Measurement> measurementList) {
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

                convertView = layoutInflater.inflate(R.layout.from_us_row_layout, parent, false);
            }

            // assign variables to our important widgets
            final EditText editText = (EditText) convertView.findViewById(R.id.converting_measurement_edit_2);
            final TextView textView = (TextView) convertView.findViewById(R.id.converting_measurement_text_2);
            final TextView textView2 = (TextView) convertView.findViewById(R.id.converted_measurement_edit_2);
            TextView textView3 = (TextView) convertView.findViewById(R.id.converted_measurement_text_2);

            // get the QUANTITY of the view and store it in our EditText
            String s = String.valueOf(measurement.quantity);
            editText.setText(s);

            // set TextView equal to the US Measurement of the position in view
            textView.setText(measurement.getUsMeasurement());

            // set the changing/converted TextView to be formatted to 2 decimal places
            String s2 = String.format("%.2f", measurement.getMetricQuantity());
            textView2.setText(s2);

            // again, using a getter on the view position to set a TextView
            textView3.setText(measurement.getMetricMeasurement());

            // When the user clicks an EditText....
            editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    // select all the text when clicked
                    editText.setSelectAllOnFocus(true);

                    // when the done checkmark is clicked, get the new value and pass it to multiply,
                    // close the soft keyboard
                        if ((actionId == EditorInfo.IME_ACTION_DONE) || (actionId == EditorInfo.IME_ACTION_GO)) {
                            String newResult = v.getText().toString();
                            double newNumber = Double.parseDouble(newResult);
                            multiply(newNumber);
                            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);

                            return true;
                        }
                        return false;
                    }


                private void multiply(double newNumber) {
                    // based on the position of the view clicked, we will multiply the new user value
                    // by the necessary conversion number -- the POSITION is tied to the multiplier

                    // update the new TextView value in column 3
                    String measure = measurement.getUsMeasurement();
                    switch (measure) {
                        case "teaspoon":
                            double answer = newNumber * 5.0;
                            String answerStr = String.valueOf(answer);
                            textView2.setText(answerStr);
                            break;

                        case "Tablespoon":
                            double answer1 = newNumber * 15.0;
                            String answerStr1 = String.valueOf(answer1);
                            textView2.setText(answerStr1);
                            break;

                        case "Fluid oz":
                            double answer2 = newNumber * 30.0;
                            String answerStr2 = String.valueOf(answer2);
                            textView2.setText(answerStr2);
                            break;

                        case "Cup":
                            double answer3 = newNumber * 40.0;
                            String strAnswer3 = String.valueOf(answer3);
                            textView2.setText(strAnswer3);
                            break;

                        case "Pint":
                            double answer4 = newNumber * 470.0;
                            String strAnswer4 = String.valueOf(answer4);
                            textView2.setText(strAnswer4);
                            break;

                        case "Gallon":
                            double answer5 = newNumber * 3.8;
                            String strAnswer5 = String.valueOf(answer5);
                            textView2.setText(strAnswer5);
                            break;

                        case "Quart":
                            double answer6 = newNumber * .95;
                            String strAnswer6 = String.valueOf(answer6);
                            textView2.setText(strAnswer6);
                            break;

                        case "oz":
                            double answer7 = newNumber * 28.0;
                            String strAnswer7 = String.valueOf(answer7);
                            textView2.setText(strAnswer7);
                            break;

                        case "lb":
                            double answer8 = newNumber * 453.6;
                            String strAnswer8 = String.valueOf(answer8);
                            textView2.setText(strAnswer8);
                            break;

                    }
                    // check to see if the value entered != 1
                    makeSingularOrPlural(newNumber);
                }

                private void makeSingularOrPlural(double newNumber) {

                    // is the user enters a non-1 value, changes the US Measurement text to be plural
                    if (newNumber != 1.00) {
                        String measure = measurement.getUsMeasurement();
                        switch (measure) {
                            case "teaspoon":
                                measure = "teaspoons";
                                textView.setText(measure);
                                break;

                            case "Tablespoon":
                                measure = "Tablespoons";
                                textView.setText(measure);
                                break;

                            case "Fluid oz":
                                break;

                            case "Cup":
                                measure = "Cups";
                                textView.setText(measure);
                                break;

                            case "Pint":
                                measure = "teaspoons";
                                textView.setText(measure);
                                break;

                            case "Gallon":
                                measure = "teaspoons";
                                textView.setText(measure);
                                break;

                            case "Quart":
                                measure = "teaspoons";
                                textView.setText(measure);
                                break;

                            case "oz":
                                measure = "teaspoons";
                                textView.setText(measure);
                                break;

                            case "lb":
                                measure = "teaspoons";
                                textView.setText(measure);
                                break;
                        }
                    }

                }
            });

            // return view of the initially clicked widget
            return convertView;
        }
    }
}