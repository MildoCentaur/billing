package ar.com.adriabe.services;

import ar.com.adriabe.model.Order;

import java.util.Date;

/**
 * Created by Mildo on 3/1/15.
 */

public class OrderPrioritizer {

    public int prioritize(Order aOrder) {
        Date date = new Date();
        Date orderdered = aOrder.getOrderedDate();

        return (int) ((date.getTime() - orderdered.getTime()) / 3600000);
    }
}
