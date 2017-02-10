package ar.com.adriabe.adriabedelivery.services;

import java.util.ArrayList;
import java.util.List;

import ar.com.adriabe.adriabedelivery.model.Order;
import ar.com.adriabe.adriabedelivery.model.OrderItem;

/**
 * Created by Ale on 03/08/2015.
 */
public class PackagesReporter {

    List<OrderItem> items = null;
    public PackagesReporter() {
        if (items == null){
            items = new ArrayList<OrderItem>();
            OrderItem item = new OrderItem();
        }
    }


}
