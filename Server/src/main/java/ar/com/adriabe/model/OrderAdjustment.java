package ar.com.adriabe.model;

/**
 * Created by Mildo on 9/14/15.
 */
public class OrderAdjustment {

    private Long orderItemId = 0l;
    private Long orderId = 0l;
    private Double quantity = 0.0;
    private String operation = "";

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }
}
