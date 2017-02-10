package ar.com.adriabe.daos.impl;

import ar.com.adriabe.daos.OrderDao;
import ar.com.adriabe.model.Order;
import ar.com.adriabe.model.OrderItem;
import ar.com.adriabe.model.OrderItemDetail;
import ar.com.adriabe.model.constant.ORDER_STATUS;
import ar.com.adriabe.repositories.OrderItemDetailRepository;
import ar.com.adriabe.repositories.OrderItemRepository;
import ar.com.adriabe.repositories.OrderRepository;
import ar.com.adriabe.services.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Mildo on 10/14/14.
 */
@Component("orderDao")
public class OrderDaoImpl implements OrderDao {


    OrderRepository orderRepository;
    OrderItemRepository orderItemRepository;
    OrderItemDetailRepository orderItemDetailRepository;

    @Autowired
    public OrderDaoImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository, OrderItemDetailRepository orderItemDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderItemDetailRepository = orderItemDetailRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findLikeName(String query) {
        return orderRepository.findLikeName(query);
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findOne(id);
    }

    @Override
    public void saveOrUpdate(Order order) throws ServiceException {
        if (order.getId() == null || order.getId() == 0) {
            List<OrderItem> list = order.getItems();
            order.setItems(null);
            orderRepository.saveAndFlush(order);
            order.setItems(list);
        }
        orderRepository.save(order);

    }

    @Override
    public void delete(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public void deleteItem(OrderItem deletedItems) {
        orderItemRepository.delete(deletedItems);
    }

    @Override
    public OrderItem findOrderItemById(Long id) {
        return orderItemRepository.findOne(id);
    }

    @Override
    public List<Order> findByStatus(List<ORDER_STATUS> status) {
        return orderRepository.findByStatus(status);
    }

    @Override
    public List<Order> findOrdersInBetweenDates(Date from, Date to) {
        return orderRepository.findOrdersInBetweenDates(from, to);

    }

    @Override
    public List<Order> findTodaysDeliveredOrders() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);

        return orderRepository.findDeliveredOrders(ORDER_STATUS.DELIVERED, calendar.getTime());
    }

    @Override
    public Order findOrderContainingOrderItem(OrderItem oi) {
        return orderRepository.findOrderByOrderItemId(oi.getId());
    }

    @Override
    public void save(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    @Override
    public void removeOrderItemDetail(OrderItemDetail selected) {
        orderItemDetailRepository.delete(selected);
    }

    @Override
    public Order findOrderFromDeliveryOrderId(Long deliveryOrderId) {
        return orderRepository.findOrderByDeliveryOrderId(deliveryOrderId);
    }

    @Override
    public void save(OrderItemDetail orderItemDetail) {
        orderItemDetailRepository.save(orderItemDetail);
    }

    @Override
    public OrderItem findOrderItemByOrderItemDetalId(Long orderItemDetailId) {
        return orderItemRepository.findOrderItemByOrderItemDetailId(orderItemDetailId);
    }

    @Override
    public List<OrderItem> findItemsToDeliverByOrderOrClient(Long clientId, Long orderId) {
        return orderItemRepository.findItemsToDeliverByOrderOrClient(clientId, orderId);
    }

    @Override
    public Integer countOrdersToDeliverByOrderOrClient(Long clientId, Long orderId) {
        return orderItemRepository.countOrdersToDeliverByOrderOrClient(clientId, orderId);
    }

    @Override
    public OrderItemDetail findOrderItemDetailById(Long id) {
        return orderItemDetailRepository.findOne(id);
    }


}
