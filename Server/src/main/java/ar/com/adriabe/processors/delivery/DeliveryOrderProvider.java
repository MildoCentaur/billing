package ar.com.adriabe.processors.delivery;

import ar.com.adriabe.web.model.json.DeliveryOrderJSON;


public class DeliveryOrderProvider {

    private DeliveryOrderJSON rawDeliveryOrder;
    private Long orderId;

    public DeliveryOrderProvider(DeliveryOrderJSON rawDeliveryOrder, Long orderId) {
        this.rawDeliveryOrder = rawDeliveryOrder;
        this.orderId = orderId;
    }


    public DeliveryOrderJSON getRawDeliveryOrder() {
        return rawDeliveryOrder;
    }

    public void setRawDeliveryOrder(DeliveryOrderJSON rawDeliveryOrder) {
        this.rawDeliveryOrder = rawDeliveryOrder;
    }


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
