package ar.com.adriabe.web.model.json;

import org.codehaus.jackson.map.annotate.JsonRootName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mildo on 2/28/15.
 */
@JsonRootName("orders")
public class OrderJSON {

    private String clientName;
    private Long clientId;
    private Long id = 0l;
    private List<OrderItemJSON> items;
    private String status;

    private int priority;

    public int getOrderItemJSONSize() {
        return items.size();
    }

    public boolean isOrderEmpty() {
        return items.isEmpty();
    }

    public boolean addOrderItemJSON(OrderItemJSON e) {
        if (items == null) {
            items = new ArrayList<OrderItemJSON>();
        }

        return items.add(e);
    }

    public boolean removeOrderItemJSON(OrderItemJSON o) {
        return items.remove(o);
    }

    public void clearOrderItemJSON() {
        items.clear();
    }

    public OrderItemJSON getOrderItemJSON(int index) {
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

    public List<OrderItemJSON> getItems() {
        return items;
    }

    public void setItems(List<OrderItemJSON> items) {
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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
