package ar.com.adriabe.services.status.updater;


import ar.com.adriabe.daos.DeliveryOrderDao;
import ar.com.adriabe.daos.OrderDao;
import ar.com.adriabe.model.Order;
import ar.com.adriabe.model.OrderItem;
import ar.com.adriabe.model.constant.ORDER_ITEM_STATUS;
import ar.com.adriabe.model.constant.ORDER_STATUS;
import ar.com.adriabe.services.ServiceException;
import ar.com.adriabe.services.generics.Executor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Scope("stereotype")
public class OrderStatusUpdaterImpl implements OrderStatusUpdater {

    private OrderDao orderDao;

    private DeliveryOrderDao deliveryOrderDao;

    private HashMap<ORDER_STATUS, Executor> transitions;

    //    @Autowired
    public OrderStatusUpdaterImpl() {
        transitions = new HashMap<ORDER_STATUS, Executor>();
//        this.orderDao = orderDao;
//        this.deliveryOrderDao = deliveryOrderDao;
    }

//    @Autowired
//    public void setWaitingTransition(@Qualifier("waitingTransitionExecutor") WaitingTransitionExecutor waitingTransitionExecutor){
//        transitions.put(ORDER_STATUS.WAITING,waitingTransitionExecutor);
//    }

    @Override
    public Order updateStatus(Order order) throws ServiceException {
        boolean flag;
        switch (order.getStatus()) {
            case WAITING:
                flag = false;
                for (OrderItem orderItem : order.getItems()) {
                    flag = flag || ORDER_ITEM_STATUS.PARTIALLY_PREPARED.equals(orderItem.getStatus());
                    for (OrderItem item : orderItem.getAccesories()) {
                        flag = flag || ORDER_ITEM_STATUS.PARTIALLY_PREPARED.equals(item.getStatus());
                    }
                }
                if (flag) {
                    order.setStatus(ORDER_STATUS.WORKING);
                }
                return order;

            case WORKING:
                order.setStatus(ORDER_STATUS.DONE);
                break;
            case DONE:
                flag = order.getUncancelledDeliveries().size() > 0;
                for (OrderItem orderItem : order.getItems()) {
                    flag = flag || ORDER_ITEM_STATUS.PARTIALLY_DELIVERED.equals(orderItem.getStatus());
                    for (OrderItem item : orderItem.getAccesories()) {
                        flag = flag || ORDER_ITEM_STATUS.PARTIALLY_DELIVERED.equals(item.getStatus());
                    }
                }
                if (flag) {
                    order.setStatus(ORDER_STATUS.PARTIALLY_DELIVERED);
                }
                break;
            case PARTIALLY_DELIVERED:
                flag = order.getUncancelledDeliveries().size() > 0;

                for (OrderItem orderItem : order.getItems()) {
                    flag = flag && ORDER_ITEM_STATUS.DELIVERED.equals(orderItem.getStatus());
                    for (OrderItem item : orderItem.getAccesories()) {
                        flag = flag && ORDER_ITEM_STATUS.DELIVERED.equals(item.getStatus());
                    }
                }

                if (flag) {
                    order.setStatus(ORDER_STATUS.DELIVERED);
                }
                break;
            default:
                throw new ServiceException("El estado del pedido no puede ser actualizado.");

        }
        return order;
    }

}
