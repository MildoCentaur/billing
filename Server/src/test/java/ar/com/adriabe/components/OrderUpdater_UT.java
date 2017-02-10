package ar.com.adriabe.components;

import ar.com.adriabe.daos.OrderDao;
import ar.com.adriabe.model.OrderAdjustment;
import ar.com.adriabe.model.OrderItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderUpdater_UT {

    public static final long DUMMY_ORDER_ITEM_ID = 10l;
    OrderUpdater updater;

    @Mock
    OrderDao orderDao;

    @Mock
    OrderItem item;

    @Before
    public void setUp() throws Exception {
        updater = new OrderUpdaterImpl(orderDao);
        when(orderDao.findOrderItemById(DUMMY_ORDER_ITEM_ID)).thenReturn(item);
    }


    @Test
    public void updaterDeletesFromOrder() throws Exception {
        OrderAdjustment[] adjustments = CreateOrderAdjustmentsForDelete();

        updater.update(adjustments);

        verify(orderDao).deleteItem(any(OrderItem.class));

    }

    protected OrderAdjustment[] CreateOrderAdjustmentsForDelete() {
        OrderAdjustment adjustment = new OrderAdjustment();
        adjustment.setOperation("delete");
        adjustment.setOrderItemId(DUMMY_ORDER_ITEM_ID);
        return new OrderAdjustment[]{adjustment};
    }

    @Test
    public void updaterUpdatesItemQuantityFromOrder() throws Exception {
        OrderAdjustment[] adjustments = CreateOrderAdjustmentsForEdit();

        updater.update(adjustments);

        verify(orderDao).save(item);
        verify(item).setQuantity(anyDouble());
    }

    protected OrderAdjustment[] CreateOrderAdjustmentsForEdit() {
        OrderAdjustment adjustment = new OrderAdjustment();
        adjustment.setOperation("edit");
        adjustment.setOrderItemId(DUMMY_ORDER_ITEM_ID);
        adjustment.setQuantity(10d);
        return new OrderAdjustment[]{adjustment};
    }
}