package ar.com.adriabe.daos;

import ar.com.adriabe.model.Order;
import ar.com.adriabe.model.OrderItem;
import ar.com.adriabe.model.OrderItemDetail;
import ar.com.adriabe.model.constant.ORDER_STATUS;
import ar.com.adriabe.services.ServiceException;

import java.util.Date;
import java.util.List;

/**
 * Created by Mildo on 10/14/14.
 */
public interface OrderDao {
    List<Order> findAll();

    List<Order> findLikeName(String query);

    Order findById(Long id);

    void saveOrUpdate(Order order) throws ServiceException;

    void delete(Order order);

    void deleteItem(OrderItem deletedItem);

    OrderItem findOrderItemById(Long id);

    List<Order> findByStatus(List<ORDER_STATUS> status);


    List<Order> findOrdersInBetweenDates(Date from, Date to);

    List<Order> findTodaysDeliveredOrders();

    List<OrderItem> findItemsToDeliverByOrderOrClient(Long clientId, Long orderId);

    Integer countOrdersToDeliverByOrderOrClient(Long clientId, Long orderId);

    OrderItemDetail findOrderItemDetailById(Long id);

    Order findOrderContainingOrderItem(OrderItem oi);

    void save(OrderItem orderItem);

    void removeOrderItemDetail(OrderItemDetail selected);

    Order findOrderFromDeliveryOrderId(Long deliveryOrderId);

    void save(OrderItemDetail orderItemDetail);

    OrderItem findOrderItemByOrderItemDetalId(Long id);
}
