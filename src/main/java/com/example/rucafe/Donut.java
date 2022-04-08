package com.example.rucafe;

public class Donut extends MenuItem {

    private int quantity;
    private String flavor;
    private String donutType;
    private static final double CAKE_PRICE = 1.79;
    private static final double YEAST_PRICE = 1.59;
    private static final double HOLES_PRICE = 0.39;
    public Donut(String flavor, String donutType)
    {
        this.donutType = donutType;
        this.flavor = flavor;
    }
    public Donut(String donutType)
    {
        this.donutType = donutType;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public double itemPrice() {
        if(this.donutType.equalsIgnoreCase("Cake donut"))
        {
            return CAKE_PRICE * quantity;
        }
        if(this.donutType.equalsIgnoreCase("Yeast donut"))
        {
            return YEAST_PRICE * quantity;
        }
        if(this.donutType.equalsIgnoreCase("Donut holes"))
        {
            return HOLES_PRICE * quantity;
        }
        return 0.0;
    }

    @Override
    public String toString() {
        return donutType;
    }
}
