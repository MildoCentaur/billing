package ar.com.adriabe.processors.delivery.postprocess;

import ar.com.adriabe.model.DeliveryOrder;
import ar.com.adriabe.model.DeliveryOrderItem;
import ar.com.adriabe.processors.delivery.DeliveryOrderContext;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by AJMILD1 on 08/10/2015.
 */
public class DeliveryOrderTestSuite {
    public static final long DUMMY_ORDER_ID = 10l;
    public static final boolean DUMMY_PRINT = true;
    public static final long DUMMY_ORDER_ITEM_DETAIL_ID = 10l;


    protected DeliveryOrderContext createDeliveryOrderContext() {
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        DeliveryOrderItem item = new DeliveryOrderItem();
        item.setId(DUMMY_ORDER_ITEM_DETAIL_ID);
        List<DeliveryOrderItem> items = Lists.newArrayList(item);
        deliveryOrder.setItems(items);
        return new DeliveryOrderContext(deliveryOrder, DUMMY_PRINT, null);
    }
}
