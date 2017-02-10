package ar.com.adriabe.processors.delivery;

import ar.com.adriabe.model.DeliveryOrder;
import ar.com.adriabe.model.Order;

/**
 * Created by Mildo on 9/27/15.
 */
public class DeliveryOrderContext {

    private DeliveryOrder deliveryOrder;
    private boolean print;
    private Order order;

    public DeliveryOrderContext(DeliveryOrder deliveryOrder, boolean print, Order order) {
        this.deliveryOrder = deliveryOrder;
        this.print = print;
        this.order = order;
    }


    public DeliveryOrder getDeliveryOrder() {
        return deliveryOrder;
    }

    public void setDeliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }

    public boolean isPrint() {
        return print;
    }

    public void setPrint(boolean print) {
        this.print = print;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
