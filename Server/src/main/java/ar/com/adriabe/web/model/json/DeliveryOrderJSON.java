package ar.com.adriabe.web.model.json;

import java.util.List;

/**
 * Created by ajmild1 on 26/03/2015.
 */
public class DeliveryOrderJSON {

    private Long clientId;

    private Long id;

    private Long orderId;

    private String clientName;

    private boolean print = true;

    private List<DeliveryOrderItemJSON> items;

    public List<DeliveryOrderItemJSON> getItems() {
        return items;
    }

    public void setItems(List<DeliveryOrderItemJSON> items) {
        this.items = items;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPrint() {
        return print;
    }

    public void setPrint(boolean print) {
        this.print = print;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
