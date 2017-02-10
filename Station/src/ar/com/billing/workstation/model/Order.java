/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.billing.workstation.model;

import java.util.List;

/**
 *
 * @author Mildo
 */
public class Order {
    private String clientName;
    private Long id;
    private List<OrderItem> items;
    private String status;
    
    private int priority;

    public Order(long id) {
        this.id=id;
    }

    public int getOrderItemSize() {
        return items.size();
    }

    public boolean isOrderEmpty() {
        return items.isEmpty();
    }

    public boolean addOrderItem(OrderItem e) {
        return items.add(e);
    }

    public boolean removeOrderItem(OrderItem o) {
        return items.remove(o);
    }

    public void clearOrderItem() {
        items.clear();
    }

    public OrderItem getOrderItem(int index) {
        return items.get(index);
    }

    
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getTotalPrepearedPackages() {
        int result=0;
        for(OrderItem item : items){
            result = result + item.getOrderItemDetailsSize();
        }
        return result;
    }

    public int getTotalOrderedPackages() {
        int result=0;
        for(OrderItem item : items){
            result = result + item.getPackagesQuantity();
        }
        return result;
    }
    
}
