package ar.com.adriabe.processors.delivery.postprocess;

import ar.com.adriabe.daos.OrderDao;
import ar.com.adriabe.generic.Executable;
import ar.com.adriabe.generic.KendoExecutionException;
import ar.com.adriabe.model.Order;
import ar.com.adriabe.model.OrderItem;
import ar.com.adriabe.model.OrderItemDetail;
import ar.com.adriabe.model.constant.ORDER_ITEM_DETAIL_STATUS;
import ar.com.adriabe.model.constant.ORDER_ITEM_STATUS;
import ar.com.adriabe.model.constant.ORDER_STATUS;
import ar.com.adriabe.processors.delivery.DeliveryOrderContext;
import ar.com.adriabe.services.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DeliveryOrderUpdateOrderStatusPostProcess implements Executable<DeliveryOrderContext> {
    OrderDao orderDao;

    @Autowired
    public DeliveryOrderUpdateOrderStatusPostProcess(OrderDao orderDao) {
        this.orderDao = orderDao;
    }


    @Override
    public DeliveryOrderContext execute(DeliveryOrderContext context) throws KendoExecutionException {
        try {
            Order order = orderDao.findById(context.getOrder().getId());
            ORDER_STATUS orderStatus = ORDER_STATUS.DELIVERED;
            for (OrderItem orderItem : order.getItems()) {
                orderItem.setStatus(processPackages(orderItem));
                if (orderItem.getStatus().compareTo(ORDER_ITEM_STATUS.DELIVERED) != 0) {
                    orderStatus = order.getStatus();
                }
            }
            order.setStatus(orderStatus);
            orderDao.saveOrUpdate(order);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return context;
    }

    private ORDER_ITEM_STATUS processPackages(OrderItem orderItem) {
        ORDER_ITEM_STATUS result = ORDER_ITEM_STATUS.ORDERED;
        for (OrderItemDetail packageItem : orderItem.getPackages()) {
            if (packageItem.getStatus().compareTo(ORDER_ITEM_DETAIL_STATUS.PREPEARED) == 0) {
                return ORDER_ITEM_STATUS.PARTIALLY_DELIVERED;
            }
            if (packageItem.getStatus().compareTo(ORDER_ITEM_DETAIL_STATUS.DELIVERED) == 0) {
                result = ORDER_ITEM_STATUS.DELIVERED;
            }
        }
        return result;
    }
}
