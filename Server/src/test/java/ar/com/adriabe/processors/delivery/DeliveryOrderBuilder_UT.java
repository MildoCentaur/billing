package ar.com.adriabe.processors.delivery;

import ar.com.adriabe.daos.ClientDao;
import ar.com.adriabe.daos.OrderDao;
import ar.com.adriabe.daos.ProductDao;
import ar.com.adriabe.generic.KendoExecutionException;
import ar.com.adriabe.model.Client;
import ar.com.adriabe.model.Order;
import ar.com.adriabe.model.OrderItemDetail;
import ar.com.adriabe.web.model.json.DeliveryOrderItemJSON;
import ar.com.adriabe.web.model.json.DeliveryOrderJSON;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeliveryOrderBuilder_UT {
    public static final long ORDER_ID = 10l;
    public static final long DUMMY_CLIENT_ID = 10l;
    public static final String DUMMY_BARCODE = "DUMMY_BARCODE";
    public static final double DUMMY_PRODUCT_WEIGHT = 20.2;
    public static final double DUMMY_PRODUCT_PRICE = 103.3;
    public static final long DUMMY_PRODUCT_ID = 10l;
    public static final long DUMMY_ORDER_ITEM_DETAIL_ID = 10l;
    public static final String DUMMY_STATUS = "DUMMY_STATUS";
    private static final long DUMMY_ORDER_ID = 10l;
    DeliveryOrderBuilder builder;

    @Mock
    ClientDao clientDao;
    @Mock
    ProductDao productDao;
    @Mock
    OrderDao orderDao;

    Client expectedClient;
    Order order;
    OrderItemDetail orderItemDetail;

    @Before
    public void setUp() throws Exception {
        builder = new DeliveryOrderBuilder(clientDao, productDao, orderDao);
    }

    @Test
    public void contextHasExpectedClientWhenBuilderIsExecuted() throws KendoExecutionException {
        DeliveryOrderProvider provider = createProvider();

        when(orderDao.findById(DUMMY_ORDER_ID)).thenReturn(order);
        when(clientDao.findById(DUMMY_CLIENT_ID)).thenReturn(expectedClient);
        when(orderDao.findOrderItemDetailById(DUMMY_ORDER_ITEM_DETAIL_ID)).thenReturn(orderItemDetail);

        DeliveryOrderContext context = builder.build(provider);

        assertThat(context.getDeliveryOrder().getClient()).isEqualTo(expectedClient);
    }

    @Test
    public void contextHasExpectedOrderWhenBuilderIsExecuted() throws KendoExecutionException {
        DeliveryOrderProvider provider = createProvider();

        when(orderDao.findById(DUMMY_ORDER_ID)).thenReturn(order);
        when(clientDao.findById(DUMMY_CLIENT_ID)).thenReturn(expectedClient);
        when(orderDao.findOrderItemDetailById(DUMMY_ORDER_ITEM_DETAIL_ID)).thenReturn(orderItemDetail);

        DeliveryOrderContext context = builder.build(provider);

        assertThat(context.getOrder()).isEqualTo(order);
    }

    @Test
    public void deliveryOrderContainsExpectedAmountOfItemsWhenBuilderIsExecuted() throws KendoExecutionException {
        DeliveryOrderProvider provider = createProvider();

        when(orderDao.findById(DUMMY_ORDER_ID)).thenReturn(order);
        when(clientDao.findById(DUMMY_CLIENT_ID)).thenReturn(expectedClient);
        when(orderDao.findOrderItemDetailById(DUMMY_ORDER_ITEM_DETAIL_ID)).thenReturn(orderItemDetail);

        DeliveryOrderContext context = builder.build(provider);

        assertThat(context.getDeliveryOrder().getItems()).hasSize(1);
    }

    private DeliveryOrderProvider createProvider() {
        DeliveryOrderJSON json = new DeliveryOrderJSON();
        json.setClientId(DUMMY_CLIENT_ID);
        DeliveryOrderItemJSON item = new DeliveryOrderItemJSON();
        item.setProductBarcode(DUMMY_BARCODE);
        item.setProductWeight(DUMMY_PRODUCT_WEIGHT);
        item.setProductPrice(DUMMY_PRODUCT_PRICE);
        item.setProductId(DUMMY_PRODUCT_ID);
        item.setOrderItemDetailId(DUMMY_ORDER_ITEM_DETAIL_ID);
        item.setStatus(DUMMY_STATUS);
        List<DeliveryOrderItemJSON> items = Lists.newArrayList(item);
        json.setItems(items);
        expectedClient = new Client(DUMMY_CLIENT_ID);
        order = new Order(DUMMY_ORDER_ID);
        orderItemDetail = new OrderItemDetail();
        return new DeliveryOrderProvider(json, DUMMY_ORDER_ID);
    }

}
