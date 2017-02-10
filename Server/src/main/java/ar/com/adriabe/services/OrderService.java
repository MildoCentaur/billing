package ar.com.adriabe.services;

import ar.com.adriabe.model.DeliveryOrder;
import ar.com.adriabe.model.Order;
import ar.com.adriabe.model.OrderItem;
import ar.com.adriabe.model.OrderItemDetail;
import ar.com.adriabe.model.constant.ORDER_STATUS;

import java.util.Date;
import java.util.List;

/**
 * Created by Mildo on 10/14/14.
 */
public interface OrderService {

    List<Order> findAllOrders(String query);

    Order findOrderById(Long id);

    void save(Order order) throws ServiceException;

    void deleteById(Long id);

    OrderItem findOrderItemById(Long id);

    List<Order> findAllPendingOrders();

    void registerOrderItemDetail(Long orderItemId, String barcode) throws InvalidDataException, ServiceException;

    public void removeProductInOrder(Long orderId, String barcode) throws InvalidDataException, ServiceException;

    void updateOrderStatus(Long orderId, ORDER_STATUS done) throws ServiceException;

    void updateOrderStatus(Order order, ORDER_STATUS status) throws ServiceException;

    List<Order> findNewOrders();

    List<Order> findOrdersInBetweenDates(Date from, Date to) throws ServiceException;

    List<Order> findOrderByStatus(ORDER_STATUS production);

    List<Order> findOrderByStatus(List<ORDER_STATUS> statusList);

    List<Order> findTodaysDeliveredOrders();

    List<DeliveryOrder> findAllDeliveryOrders();

    List<OrderItem> findItemsToDeliverByOrderOrClient(Long clientId, Long orderId);

    Integer countOrdersToDeliverByOrderOrClient(Long clientId, Long orderId);

    OrderItemDetail findOrderItemDetailById(Long id);

    DeliveryOrder findDeliveryOrderById(Long id);

    void deliverOrderPackages(DeliveryOrder deliveryOrder, boolean print) throws Exception;

    void deleteDeliveryOrderById(Long id) throws ServiceException;

    Integer countPackagesToDeliverByOrderOrClient(Long clientId, Long orderId);
}
