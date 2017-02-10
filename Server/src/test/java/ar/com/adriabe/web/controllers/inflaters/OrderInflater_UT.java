package ar.com.adriabe.web.controllers.inflaters;

import ar.com.adriabe.daos.ClientDao;
import ar.com.adriabe.model.Client;
import ar.com.adriabe.model.Order;
import ar.com.adriabe.model.OrderItem;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderInflater_UT {

    public static final String DUMMY_ADDRESS = "DUMMY_ADDRESS";
    public static final long ID_CLIENT = 10l;
    OrderInflater inflater;
    @Mock
    ClientDao clientDao;

    @Mock
    OrderItemInflater orderItemInflater;

    @Test
    public void inflateOrderWhenOrderIsNotNull() throws Exception {
        inflater = new OrderInflater(clientDao, orderItemInflater);
        Order order = createOrderWith2Items();
        Client expectedClient = new Client(ID_CLIENT);
        expectedClient.setAddress(DUMMY_ADDRESS);
        when(clientDao.findById(ID_CLIENT)).thenReturn(expectedClient);
        inflater.inflate(order);

        assertNotNull(order.getClient());
        assertEquals(order.getClient(), expectedClient);

        verify(orderItemInflater, times(2)).inflate(any(OrderItem.class));

    }

    @Test
    public void inflateOrderFailWhenClientIsNotFound() throws Exception {
        inflater = new OrderInflater(clientDao, orderItemInflater);
        Order order = createOrderWith2Items();
        Client expectedClient = new Client(ID_CLIENT);
        expectedClient.setAddress(DUMMY_ADDRESS);
        when(clientDao.findById(ID_CLIENT)).thenReturn(null);
        try {
            inflater.inflate(order);
            fail("Should have failed");
        } catch (Exception ex) {
            assertThat(ex).hasMessage("Cliente no encontrado.");
        }
    }


    protected Order createOrderWith2Items() {
        Order order = new Order();
        Client client = new Client(ID_CLIENT);

        order.setClient(client);
        OrderItem item1 = new OrderItem();
        OrderItem item2 = new OrderItem();
        List<OrderItem> items = Lists.newArrayList(item1, item2);
        order.setItems(items);
        return order;
    }


}