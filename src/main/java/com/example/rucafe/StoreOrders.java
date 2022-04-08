package com.example.rucafe;

import java.util.ArrayList;

public class StoreOrders implements Customizable {

    private ArrayList<Order> list;

    public StoreOrders(ArrayList<Order> list) {
        this.list = list;
    }

    @Override
    public boolean add(Object obj) {
        if(obj instanceof Order) {
            Order order = (Order) obj;
            list.add(order);
        }
        return true;
    }

    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Order) {
            Order order = (Order) obj;
            return list.remove(order);
        }
        return false;
    }

}
