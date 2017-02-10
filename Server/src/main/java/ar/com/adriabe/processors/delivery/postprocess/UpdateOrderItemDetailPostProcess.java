package ar.com.adriabe.processors.delivery.postprocess;

import ar.com.adriabe.daos.OrderDao;
import ar.com.adriabe.generic.Executable;
import ar.com.adriabe.generic.KendoExecutionException;
import ar.com.adriabe.model.DeliveryOrderItem;
import ar.com.adriabe.model.OrderItemDetail;
import ar.com.adriabe.model.constant.ORDER_ITEM_DETAIL_STATUS;
import ar.com.adriabe.processors.delivery.DeliveryOrderContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateOrderItemDetailPostProcess implements Executable<DeliveryOrderContext> {

    OrderDao orderDao;

    @Autowired
    public UpdateOrderItemDetailPostProcess(OrderDao orderDao) {
        this.orderDao = orderDao;
    }


    @Override
    public DeliveryOrderContext execute(DeliveryOrderContext target) throws KendoExecutionException {
        for (DeliveryOrderItem deliveryOrderItem : target.getDeliveryOrder().getItems()) {
            for (OrderItemDetail detail : deliveryOrderItem.getPackages()) {
                detail.setStatus(ORDER_ITEM_DETAIL_STATUS.DELIVERED);
                orderDao.save(detail);
            }
        }
        return target;
    }
}
