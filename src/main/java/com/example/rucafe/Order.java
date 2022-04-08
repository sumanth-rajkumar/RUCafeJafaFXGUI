package com.example.rucafe;

import java.util.ArrayList;

public class Order implements Customizable {

    private ArrayList<MenuItem> list;

    public Order(ArrayList<MenuItem> list) {
        this.list = list;
    }

    @Override
    public boolean add(Object obj) {
        if(obj instanceof MenuItem) {
            MenuItem item = (MenuItem) obj;
            list.add(item);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object obj) {
        if(obj instanceof MenuItem) {
            MenuItem item = (MenuItem) obj;
            return list.remove(item);
        }
        return false;
    }
}
