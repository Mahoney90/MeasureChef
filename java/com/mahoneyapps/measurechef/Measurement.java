package com.mahoneyapps.measurechef;

/**
 * Created by Brendan on 12/23/2015.
 */
  public class Measurement {
    // initialize parameters for Measurement constructor
    public Double quantity = 1.00;
    public String usMeasurement = "teaspoon";
    public String metricMeasurement = "mL";
    public Double metricQuantity = 5.00;


    // our Measurement constructor takes 4 arguments, set each global variable equal to
    // the argument passed
    public Measurement(Double quantity, String usMeasurement, Double metricQuantity, String metricMeasurement){
        super();
        this.quantity = quantity;
        this.usMeasurement = usMeasurement;
        this.metricQuantity = metricQuantity;
        this.metricMeasurement = metricMeasurement;
    }

    // getters and setters for our Measurement class
    public String getUsMeasurement(){
        return usMeasurement;
    }

    public void setUsMeasurement(String usMeasurement){
        this.usMeasurement = usMeasurement;
    }

    public String getMetricMeasurement(){
        return metricMeasurement;
    }

    public void setMetricMeasurement(String metricMeasurement){
        this.metricMeasurement = metricMeasurement;
    }

    public Double getMetricQuantity(){
        return metricQuantity;
    }

    public void setMetricQuantity(Double metricQuantity){
        this.metricQuantity = metricQuantity;
    }

    public Double getQuantity(){
        return quantity;
    }

    public void setQuantity(Double quantity){
        this.quantity = quantity;
    }

}
