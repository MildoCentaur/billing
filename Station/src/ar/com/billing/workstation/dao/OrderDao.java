/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.billing.workstation.dao;

import ar.com.billing.workstation.model.Order;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Mildo
 */
public class OrderDao {
    private static List<Order> orders = Collections.synchronizedList(new ArrayList<Order>());
    
    /**
     * @return the orders
     */
    public static List<Order> getOrders() {
        return orders; 
    }
    
     /**
     * @param aOrders the orders to set
     */
    public static  void addOrder(Order aOrder) {
        if(existOrderById(aOrder.getId())){
            Order prev = findOrderById(aOrder.getId());
            orders.remove(prev);
            orders.add(aOrder);
        }else{
            orders.add(aOrder); 
        } 
    }
    
     public static Order findOrderById(long id) {
        for(Order order:orders){
            if(order.getId()==id){
                return order;
            }
        }
        return null;
    }
     
    public static boolean existOrderById(long id) {
        for(Order order:orders){
            if(order.getId()==id){
                return true;
            }
        }
        return false;
    }

}
