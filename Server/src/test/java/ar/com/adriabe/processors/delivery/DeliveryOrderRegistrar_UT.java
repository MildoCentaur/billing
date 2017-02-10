package ar.com.adriabe.processors.delivery;

import ar.com.adriabe.components.OperationLogProvider;
import ar.com.adriabe.components.OperationLogRegistrar;
import ar.com.adriabe.daos.DeliveryOrderDao;
import ar.com.adriabe.generic.KendoExecutionException;
import ar.com.adriabe.model.DeliveryOrder;
import ar.com.adriabe.web.model.json.DeliveryOrderItemJSON;
import ar.com.adriabe.web.model.json.DeliveryOrderJSON;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeliveryOrderRegistrar_UT {

    public static final long DUMMY_CLIENT_ID = 10l;
    public static final String DUMMY_COLOR_NAME = "DUMMY_COLOR_NAME";
    public static final String DUMMY_FABRIC_NAME = "DUMMY_FABRIC_NAME";
    public static final long DUMMY_PRODUCT_ID = 10l;
    public static final String DUMMY_BARCODE_NAME = "DUMMY_BARCODE_NAME";
    public static final String DUMMY_PRODUCT_NAME = "DUMMY_PRODUCT_NAME";
    public static final double DUMMY_PRODUCT_PRICE = 100.2;
    public static final double DUMMY_PRODUCT_WEIGHT = 20.9;
    public static final String DUMMY_STATUS = "DUMMY_STATUS";
    public static final long DUMMY_ORDER_ITEM_ID_1 = 1l;
    public static final long DUMMY_ORDER_ITEM_ID_2 = 2l;
    public static final double DUMMY_TOTAL_AMOUNT = 1234.56;
    public static final long DELIVERY_ORDER_SUCCESS_ID = 10l;
    private static final Long DUMMY_ORDER_ID = 10l;
    DeliveryOrderRegistrar registrar;
    DeliveryOrderProvider deliveryOrderProviderSuccess;
    @Mock
    private DeliveryOrderPostProcessor postProcessor;
    @Mock
    private DeliveryOrderBuilder deliveryOrderBuilder;
    @Mock
    private DeliveryOrderDao deliveryOrderDao;
    @Mock
    private OperationLogRegistrar operationLogRegistrar;
    private DeliveryOrderContext deliveryOrderContextSuccess;

    @Before
    public void setUp() throws KendoExecutionException {

        registrar = new DeliveryOrderRegistrar(postProcessor, deliveryOrderBuilder, deliveryOrderDao, operationLogRegistrar);
        deliveryOrderProviderSuccess = createDeliveryOrderProvider();
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        DeliveryOrder deliveryOrderSuccess = new DeliveryOrder();
        deliveryOrderSuccess.setId(DELIVERY_ORDER_SUCCESS_ID);
        /*Client client = new Client();
        client.setId(DUMMY_CLIENT_ID);
        deliveryOrder.setClient(client);
        deliveryOrder.setDate(new Date());
        deliveryOrder.setStatus(DELIVERY_ORDER_STATUS.WAITING);
        deliveryOrder.setTotalAmount(DUMMY_TOTAL_AMOUNT);
        deliveryOrder.setPaid(false);
        DeliveryOrderItem item = new DeliveryOrderItem();
        List<DeliveryOrderItem> items = Lists.newArrayList(item);
        deliveryOrder.setItems(items);*/
        deliveryOrderContextSuccess = new DeliveryOrderContext(deliveryOrder, true, null);
        when(deliveryOrderBuilder.build(deliveryOrderProviderSuccess)).thenReturn(deliveryOrderContextSuccess);
        when(deliveryOrderDao.save(deliveryOrder)).thenReturn(deliveryOrderSuccess);
    }

    @Test
    public void registerDeliveryOrderUsingDeliveryOrderBuilder() throws Exception {

        registrar.register(deliveryOrderProviderSuccess);

        verify(deliveryOrderBuilder).build(deliveryOrderProviderSuccess);
    }


    @Test
    public void saveDeliveryOrderWhenRegisteringADeliveryOrder() throws Exception {

        registrar.register(deliveryOrderProviderSuccess);

        verify(deliveryOrderDao).save(any(DeliveryOrder.class));


    }

    @Test
    public void executePostProcessorsWhenRegisteringADeliveryOrder() throws Exception {

        registrar.register(deliveryOrderProviderSuccess);

        verify(postProcessor).process(deliveryOrderContextSuccess);
    }

    @Test
    public void AuditLogRegisteredWhenRegisteringADeliveryOrderSuccess() throws Exception {


        ArgumentCaptor<OperationLogProvider> captor = ArgumentCaptor.forClass(OperationLogProvider.class);
        registrar.register(deliveryOrderProviderSuccess);

        verify(operationLogRegistrar).register(captor.capture());

        assertNull(captor.getValue().getMessage());
        assertTrue(captor.getValue().isCompleted());
    }

    private DeliveryOrderProvider createDeliveryOrderProvider() {
        DeliveryOrderJSON rawDeliveryOrder = new DeliveryOrderJSON();
        rawDeliveryOrder.setClientId(DUMMY_CLIENT_ID);
        DeliveryOrderItemJSON item = createDeliveryOrderItemJSON(DUMMY_ORDER_ITEM_ID_1);
        DeliveryOrderItemJSON item2 = createDeliveryOrderItemJSON(DUMMY_ORDER_ITEM_ID_2);
        List<DeliveryOrderItemJSON> items = Lists.newArrayList(item, item2);
        rawDeliveryOrder.setItems(items);
        return new DeliveryOrderProvider(rawDeliveryOrder, DUMMY_ORDER_ID);
    }

    private DeliveryOrderItemJSON createDeliveryOrderItemJSON(Long orderItemId) {
        DeliveryOrderItemJSON item = new DeliveryOrderItemJSON();
        item.setColorName(DUMMY_COLOR_NAME);
        item.setFabricName(DUMMY_FABRIC_NAME);
        item.setOrderItemDetailId(orderItemId);
        item.setProductBarcode(DUMMY_BARCODE_NAME);
        item.setProductId(DUMMY_PRODUCT_ID);
        item.setProductName(DUMMY_PRODUCT_NAME);
        item.setProductPrice(DUMMY_PRODUCT_PRICE);
        item.setProductWeight(DUMMY_PRODUCT_WEIGHT);
        item.setStatus(DUMMY_STATUS);
        return item;
    }
}
