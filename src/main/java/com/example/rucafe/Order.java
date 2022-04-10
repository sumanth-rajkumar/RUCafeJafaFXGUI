package com.example.rucafe;

import java.util.ArrayList;
import java.util.List;

/**
 * Order Class is a class that takes in a list of MenuItems with their respective data
 * @author Sumanth Rajkumar, Shantanu Jain
 */
public class Order implements Customizable {


    private ArrayList<MenuItem> list;
    public static final float taxRate = 6.625f/100;

    /**
     * Constructor that creates an Order object
     * It assigns the proper value to the instance variable list
     */
    public Order()
    {
        this.list = new ArrayList<>();
    }

    /**
     * Constructor that creates a Donut object based off the given list.
     * It assigns the proper value to the instance variable list
     */
    public Order(ArrayList<MenuItem> list) {
        this.list = list;
    }

    /**
     * Checks if the passed in object is an instance of MenuItem or not and adds to list ArrayList
     * Overrides method in Customizable interface
     * @param obj - object
     * @return true if MenuItem has been added to ArrayList, false otherwise.
     */
    @Override
    public boolean add(Object obj)
    {
        if(obj instanceof MenuItem)
        {
            MenuItem item = (MenuItem) obj;
            list.add(item);
            return true;
        }
        return false;
    }

    /**
     * Checks if the passed in object is an instance of MenuItem or not and removes from list ArrayList
     * Overrides method in Customizable interface
     * @param obj - object
     * @return true if MenuItem has been removed from ArrayList, false otherwise.
     */
    @Override
    public boolean remove(Object obj)
    {
        if(obj instanceof MenuItem)
        {
            MenuItem item = (MenuItem) obj;
            return list.remove(item);
        }
        return false;
    }

    /**
     * Gives the ArrayList of MenuItems as a List
     * @return - List of MenuItems
     */
    public List<MenuItem> getOrderItems()
    {
        return list;
    }

    /**
     * Gives the sub-total cost of all the MenuItems in an order
     * @return - double representing the subtotal
     */
    public double getOrderSubTotal()
    {
       return list.stream().mapToDouble(item -> item.getItemCost()).sum();
    }

    /**
     * Gives the sales tax amount on the subtotal
     * @return - double representing the sales tax
     */
    public double getSalesTax()
    {
        return getOrderSubTotal() * taxRate;
    }

    /**
     * Gives the total cost of an Order after sales tax is applied
     * @return - double representing the total
     */
    public double getTotal()
    {
        return getOrderSubTotal() * (1 + taxRate);
    }

    /**
     * Gives the sub-total cost, sales tax amount, total cost of Order, and MenuItems
     * @return - String representing the sub-total cost, sales tax amount, total cost of Order, and MenuItems
     */
    @Override
    public String toString()
    {

        StringBuilder builder = new StringBuilder();

        builder.append("Total=").append(String.format("%1$.2f",this.getTotal()));
        builder.append(";Tax=").append(String.format("%1$.2f",this.getSalesTax()));
        builder.append(";Price=").append(String.format("%1$.2f",this.getOrderSubTotal()));
        builder.append(";Items=").append(list);

        return builder.toString();
    }
}
