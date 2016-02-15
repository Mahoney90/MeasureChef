package com.mahoneyapps.measurechef;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Brendan on 12/14/2015.
 */
public class MyAdapter extends ArrayAdapter<Integer> {

    Context context;
    Integer[] ovenTempF;
    Integer[] ovenTempC;
    LayoutInflater ourInflater;


    // constructor for MyAdapter, takes the App's context and 2 integer arrays
    public MyAdapter(Context context, Integer[] ovenTempF, Integer[] ovenTempC) {
        // pass the layout for a row of oven conversions wehn calling the super constructor
        super(context, R.layout.oven_row,ovenTempF);
        // set global variables equal to the arguments of the constructor
        this.context = context;
        this.ovenTempF = ovenTempF;
        this.ovenTempC = ovenTempC;
    }

    // create a ViewHolder that has two TextViews
    public static class ViewHolderItem {
        TextView textViewItem1;
        TextView textViewItem2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolderItem viewHolder;
        viewHolder = new ViewHolderItem();

        // if there are no views to convert (to preserve resources), inflate our row
        if (convertView == null) {

            ourInflater = LayoutInflater.from(getContext());
            convertView = ourInflater.inflate(R.layout.oven_row, parent, false);
        }

        // else, if there are views to convert, get the Tag of the view (the example row for
        // our ListView
        else {
            viewHolder = (ViewHolderItem) convertView.getTag();
        }


            try {
                // set the 2 TextViews in our ViewHolder to the views in our row
                viewHolder.textViewItem1 = (TextView) convertView.findViewById(R.id.row_view_1);
                viewHolder.textViewItem2 = (TextView) convertView.findViewById(R.id.row_view_2);
                convertView.setTag(viewHolder);
            }
            catch(IllegalStateException e) {
                System.out.print(e.toString());
            }

        // set the text is each TextView to the value of the integer in each array at each
        // position in the array (loops through array until it is done)
        viewHolder.textViewItem1.setText(Integer.toString(ovenTempF[position]));
        viewHolder.textViewItem2.setText(Integer.toString(ovenTempC[position]));

        // set the text color of the temperatures based on the position in the Array,
        // signaling a visual change from cold to hot
        if(position == 0){
            viewHolder.textViewItem1.setTextColor(Color.parseColor("#0D47A1"));
            viewHolder.textViewItem2.setTextColor(Color.parseColor("#0D47A1"));
        } else if (position == 1){
            viewHolder.textViewItem1.setTextColor(Color.parseColor("#00695C"));
            viewHolder.textViewItem2.setTextColor(Color.parseColor("#00695C"));
        } else if (position == 2){
            viewHolder.textViewItem1.setTextColor(Color.parseColor("#8BC34A"));
            viewHolder.textViewItem2.setTextColor(Color.parseColor("#8BC34A"));
        } else if (position == 3){
            viewHolder.textViewItem1.setTextColor(Color.parseColor("#CDDC39"));
            viewHolder.textViewItem2.setTextColor(Color.parseColor("#CDDC39"));
        } else if (position == 4){
            viewHolder.textViewItem1.setTextColor(Color.parseColor("#FDD835"));
            viewHolder.textViewItem2.setTextColor(Color.parseColor("#FDD835"));
        } else if (position == 5){
            viewHolder.textViewItem1.setTextColor(Color.parseColor("#FFC107"));
            viewHolder.textViewItem2.setTextColor(Color.parseColor("#FFC107"));
        } else if (position == 6){
            viewHolder.textViewItem1.setTextColor(Color.parseColor("#FFA000"));
            viewHolder.textViewItem2.setTextColor(Color.parseColor("#FFA000"));
        } else if (position == 7){
            viewHolder.textViewItem1.setTextColor(Color.parseColor("#F57C00"));
            viewHolder.textViewItem2.setTextColor(Color.parseColor("#F57C00"));
        } else if (position == 8){
            viewHolder.textViewItem1.setTextColor(Color.parseColor("#E65100"));
            viewHolder.textViewItem2.setTextColor(Color.parseColor("#E65100"));
        } else if (position == 9){
            viewHolder.textViewItem1.setTextColor(Color.parseColor("#BF360C"));
            viewHolder.textViewItem2.setTextColor(Color.parseColor("#BF360C"));
        }

        // return our view

        return convertView;
    }}
