package ar.com.adriabe.processors.delivery.postprocess;

import ar.com.adriabe.daos.OrderDao;
import ar.com.adriabe.model.Order;
import ar.com.adriabe.processors.delivery.DeliveryOrderContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UpdateOrderWithDeliveryOrderPostProcess_UT extends DeliveryOrderTestSuite {


    AssignDeliveryToOriginOrderPostProcess process;

    @Mock
    OrderDao orderDao;

    @Before
    public void setUp() throws Exception {
        process = new AssignDeliveryToOriginOrderPostProcess(orderDao);
    }

    @Test
    public void assignDeliveryOrderToProperOrder() throws Exception {

        DeliveryOrderContext context = createDeliveryOrderContext();
        createOrder();

        ArgumentCaptor<Order> orderArgumentCaptor = ArgumentCaptor.forClass(Order.class);

        process.execute(context);

        verify(orderDao).saveOrUpdate(orderArgumentCaptor.capture());
        assertThat(orderArgumentCaptor.getValue().getDeliveries()).isNotEmpty();
    }

    protected void createOrder() {
        Order order = new Order(DUMMY_ORDER_ID);
        when(orderDao.findById(DUMMY_ORDER_ID)).thenReturn(order);
    }
}
