package ar.com.adriabe.services;

import ar.com.adriabe.model.DeliveryOrder;
import ar.com.adriabe.model.OrderItemDetail;

import java.util.List;

/**
 * Created by Mildo on 4/25/15.
 */
public interface DeliveryOrderService {

    void saveDeliveryOrder(DeliveryOrder deliveryOrder, boolean print) throws Exception;


    void updateDeliveredItemsStatus(List<OrderItemDetail> delivered);
}
