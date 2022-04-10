package com.example.rucafe;

import java.util.Objects;

/**
 * Donut Class is a class that defines the properties that comes with an instance of a donut
 * and the corresponding methods that go with it.
 * @author Sumanth Rajkumar, Shantanu Jain
 */
public class Donut implements MenuItem {


    private int quantity;
    private String donutType;
    private String flavor;

    private static final double CAKE_PRICE = 1.79;
    private static final double YEAST_PRICE = 1.59;
    private static final double HOLES_PRICE = 0.39;

    public static final String CAKE_TYPE = "cake donut";
    public static final String YEAST_TYPE ="yeast donut";
    public static final String HOLES_TYPE = "donut hole";

    public static final String[] DONUT_TYPES = new String[]{CAKE_TYPE,YEAST_TYPE,HOLES_TYPE};

    /**
     * Constructor that creates a Donut object based off the given strings and ints.
     * It assigns the proper values to the instance variables quantity, donutType, and flavor
     * @param donutType - a string representing the type of donut
     * @param flavor - a string representing the flavor of the donut
     * @param quantity - an int representing the number of donuts
     */
    public Donut(int quantity, String donutType, String flavor)
    {
        this.donutType = donutType;
        this.flavor=flavor;
        this.quantity= quantity;
    }

    public String getDonutType()
    {
        return donutType;
    }
    /**
     * Gives the number of donuts
     * Overrides method from MenuItem interface
     * @return - int representing the number of donuts
     */
    @Override
    public int getQuantity()
    {
        return quantity;
    }

    /**
     * Gives the unit price of the donut depending on the type.
     * Overrides method from MenuItem interface
     * @return - double representing the unit price
     */
    @Override
    public double getUnitPrice()
    {
        switch (donutType)
        {
            case CAKE_TYPE: return CAKE_PRICE;
            case YEAST_TYPE: return YEAST_PRICE;
            case HOLES_TYPE: return HOLES_PRICE;
            default: throw new RuntimeException("unknown type " + donutType);
        }
    }

    /**
     * Gives the flavor of the donut
     * @return - String representing the flavor of donut
     */
    public String getDonutFlavor()
    {
        return flavor;
    }

    /**
     * Gives the flavor of donut with the quantity
     * @return - String representing the flavor and quantity
     */
    @Override
    public String toString()
    {
        return flavor + " (" + this.quantity + ")";
    }

    /**
     * Checks if the passed in Donut object is equal to this Donut by checking
     * if each instance variable is equal
     * @param obj - object
     * @return true if instance var of passed in object is equal to the instance var of this
     * Donut otherwise false.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Donut donut = (Donut) obj;
        return quantity == donut.quantity && Objects.equals(donutType, donut.donutType) &&
            Objects.equals(flavor, donut.flavor);
    }

    /**
     * Gives the hashcode value of donut object
     * Acts as a helper to equals method
     * @return - int representing hash value
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(quantity, donutType, flavor);
    }
}
