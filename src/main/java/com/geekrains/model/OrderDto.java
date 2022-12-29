package com.geekrains.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderDto {
    private String productName;
    private double cost;
    private int quantity;
    private String graphicalRepresentationOfAFullPrice;

    public OrderDto() {
    }

    public OrderDto(String productName, double cost, int quantity) {
        this.productName = productName;
        this.cost = cost;
        this.quantity = quantity;
    }

    public String makeGraphicalRepresentationOfAFullPrice(){
        if(quantity>1){
            return Integer.toString(quantity) + " x " + Double.toString(cost) + " = " + Double.toString(round(quantity*cost,1));
        }else{
            return Double.toString(cost);
        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getGraphicalRepresentationOfAFullPrice() {
        return graphicalRepresentationOfAFullPrice;
    }

    public void setGraphicalRepresentationOfAFullPrice(String graphicalRepresentationOfAFullPrice) {
        this.graphicalRepresentationOfAFullPrice = graphicalRepresentationOfAFullPrice;
    }

}
