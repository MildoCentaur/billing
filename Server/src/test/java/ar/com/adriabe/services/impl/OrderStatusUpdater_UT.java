package ar.com.adriabe.services.impl;

import ar.com.adriabe.daos.DeliveryOrderDao;
import ar.com.adriabe.daos.OrderDao;
import ar.com.adriabe.model.Order;
import ar.com.adriabe.model.OrderItem;
import ar.com.adriabe.model.OrderItemDetail;
import ar.com.adriabe.model.constant.ORDER_ITEM_DETAIL_STATUS;
import ar.com.adriabe.model.constant.ORDER_ITEM_STATUS;
import ar.com.adriabe.model.constant.ORDER_STATUS;
import ar.com.adriabe.services.status.updater.OrderStatusUpdater;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class OrderStatusUpdater_UT {


    @Mock
    OrderDao orderDao;

    @Mock
    DeliveryOrderDao deliveryOrderDao;

    @InjectMocks
    OrderStatusUpdater updater;

    List<OrderItemDetail> details1;
    List<OrderItemDetail> details2;
    List<OrderItemDetail> details3;
    OrderItemDetail detail11;
    OrderItemDetail detail21;
    OrderItemDetail detail31;
    OrderItem item1;
    OrderItem item2;
    OrderItem item3;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(OrderStatusUpdater.class);
    }

    @Test
    public void updateOrderFromWaitingToWorkingWhenOneItemHasAtLeastOnePackage() throws Exception {
        Order order = createOrderInInitialStatus();


        Order expected = updater.updateStatus(order);

        assertEquals(ORDER_STATUS.WORKING, expected.getStatus());
        for (OrderItem orderItem : order.getItems()) {
            assertEquals(ORDER_ITEM_STATUS.PARTIALLY_PREPARED, orderItem.getStatus());
        }
    }

    @Test
    public void orderNotUpdatedFromWaitingToWorkingWhenNoItemHasAtLeastOnePackage() throws Exception {
        Order order = createOrder();

        Order expected = updater.updateStatus(order);

        assertEquals(ORDER_STATUS.WAITING, expected.getStatus());
        for (OrderItem orderItem : order.getItems()) {
            assertEquals(ORDER_ITEM_STATUS.ORDERED, orderItem.getStatus());
        }
    }

    @Test
    public void updateOrderFromWorkingToDoneWhenOneItemHasAtLeastOnePackage() throws Exception {
        Order order = createOrderInWorkingStatus();


        Order expected = updater.updateStatus(order);

        assertEquals(ORDER_STATUS.DONE, expected.getStatus());
        for (OrderItem orderItem : order.getItems()) {
            assertEquals(ORDER_ITEM_STATUS.ORDERED, orderItem.getStatus());
        }
    }

    protected Order createOrderInWorkingStatus() {
        Order order = createOrderInInitialStatus();
        order.setStatus(ORDER_STATUS.WORKING);

        return order;
    }

    @Test
    public void updateOrderToDoneFailWhenOrderStatusDifferentThanWorking() throws Exception {
        Order order = createOrder();

        Order expected = updater.updateStatus(order);

        assertEquals(ORDER_STATUS.WAITING, expected.getStatus());
        for (OrderItem orderItem : order.getItems()) {
            assertEquals(ORDER_ITEM_STATUS.ORDERED, orderItem.getStatus());
        }
    }

    private Order createOrderInInitialStatus() {
        Order order = createOrder();
        item1.setPackages(details1);
        item2.setPackages(details2);
        item3.setPackages(details3);

        return order;
    }

    private Order createOrder() {
        Order order = new Order();
        order.setStatus(ORDER_STATUS.WAITING);
        item1 = new OrderItem();
        item2 = new OrderItem();
        item3 = new OrderItem();
        detail11 = new OrderItemDetail();
        detail11.setStatus(ORDER_ITEM_DETAIL_STATUS.PREPEARED);
        detail21 = new OrderItemDetail();
        detail11.setStatus(ORDER_ITEM_DETAIL_STATUS.PREPEARED);
        detail31 = new OrderItemDetail();
        detail31.setStatus(ORDER_ITEM_DETAIL_STATUS.PREPEARED);
        details1 = Lists.newArrayList(detail11);
        details2 = Lists.newArrayList(detail21);
        details3 = Lists.newArrayList(detail31);
        List<OrderItem> items = Lists.newArrayList(item1, item2, item3);
        order.setItems(items);
        return order;
    }
}