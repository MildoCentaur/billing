package ar.com.adriabe.adriabedelivery.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Ale on 06/08/2015.
 */
public class Order {
    String clientName;
    Long id;
    List<OrderItem> items;

    public Order(Long id, String clientName, ArrayList<OrderItem> orderItems) {
        this.id=id;
        this.clientName=clientName;
        this.items=orderItems;
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
///////////////////////////////////////////////////////////////////////////////////////

    public int getPendingBags(){
        Random rand = new Random();
        rand.setSeed(new Date().getTime());
        return rand.nextInt(10);
    }
}
