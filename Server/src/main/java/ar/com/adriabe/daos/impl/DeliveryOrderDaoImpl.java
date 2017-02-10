package ar.com.adriabe.daos.impl;

import ar.com.adriabe.daos.DeliveryOrderDao;
import ar.com.adriabe.model.DeliveryOrder;
import ar.com.adriabe.model.DeliveryOrderItem;
import ar.com.adriabe.model.OrderItemDetail;
import ar.com.adriabe.model.constant.DELIVERY_ORDER_STATUS;
import ar.com.adriabe.model.constant.ORDER_ITEM_DETAIL_STATUS;
import ar.com.adriabe.repositories.DeliveryOrderItemRepository;
import ar.com.adriabe.repositories.DeliveryOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component("deliveryOrderDao")
public class DeliveryOrderDaoImpl implements DeliveryOrderDao {

    @Autowired
    DeliveryOrderRepository deliveryOrderRepository;

    @Autowired
    DeliveryOrderItemRepository deliveryOrderItemRepository;

//    @Autowired
//    OrderItemDetailRepository orderItemDetailRepository;

    @Override
    public List<DeliveryOrder> findAllDeliveryOrders() {
        return deliveryOrderRepository.findAllNotDeleted();
    }

    @Override
    public DeliveryOrder findById(Long id) {
        return deliveryOrderRepository.findOne(id);
    }

    @Override
    public DeliveryOrder save(DeliveryOrder deliveryOrder) {
        return deliveryOrderRepository.save(deliveryOrder);

    }

    @Override
    public List<DeliveryOrder> findUnpaidDeliveryOrderByClient(Long clientId) {

        return deliveryOrderRepository.findUnpaidDeliveryOrderByClient(clientId);
    }

    @Override
    public double sumAllUnpaidDeliveryOrders(Long clientId) {
        return deliveryOrderRepository.sumAllUnpaidDeliveryOrders(clientId);
    }

    @Override
    public void delete(DeliveryOrder order) {
        order.setDeleted(true);
        order.setStatus(DELIVERY_ORDER_STATUS.CANCELLED);
        for (DeliveryOrderItem deliveryOrderItem : order.getItems()) {
            for (OrderItemDetail detail : deliveryOrderItem.getPackages()) {
                detail.setStatus(ORDER_ITEM_DETAIL_STATUS.PREPEARED);
            }
        }
        deliveryOrderRepository.save(order);
    }


}
