package ar.com.adriabe.adriabedelivery.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.com.adriabe.adriabedelivery.model.Order;
import ar.com.adriabe.adriabedelivery.model.OrderItem;

/**
 * Created by Ale on 03/08/2015.
 */
public class OrderReporter {
    List<Order> orders = null;
    public OrderReporter() {
        if (orders == null){
            orders = new ArrayList<>();
            Order order = new Order(123l,"Alejandro Mildiner",new ArrayList<OrderItem>());
            orders.add(order);
            order = new Order(124l,"Sergio Mildiner",new ArrayList<OrderItem>());
            orders.add(order);
            order = new Order(125l,"Alberto Mildiner",new ArrayList<OrderItem>());
            orders.add(order);
            order = new Order(126l,"jkhdsajhkdsjkdsa",new ArrayList<OrderItem>());
            orders.add(order);
        }
    }

    public List<Order> listPendingOrders() {

        return orders;
    }

    public Order getOrderById(long orderId) {
        for (Order order : orders) {
            if(order.getId() == orderId ){
                return order;
            }
        }
        return null;
    }
}
