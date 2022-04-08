package com.example.rucafe;

import java.util.ArrayList;

public class Coffee extends MenuItem implements Customizable {

    private ArrayList<String> addins;
    private String size;
    private int quantity;

    public Coffee(ArrayList<String> addins) {
        this.addins = addins;
    }

    @Override
    public boolean add(Object obj) {
        if(obj instanceof String)
        {
            String addin = (String) obj;
            return addins.add(addin);
        }
        return false;
    }

    @Override
    public boolean remove(Object obj) {

        if(obj instanceof String)
        {
            String addin = (String) obj;
            return addins.remove(addin);
        }
        return false;
    }

    @Override
    public double itemPrice() {
        return 0;
    }
}
