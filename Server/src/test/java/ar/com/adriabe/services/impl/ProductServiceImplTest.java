package ar.com.adriabe.services.impl;


import ar.com.adriabe.model.OrderItem;
import ar.com.adriabe.processors.delivery.DeliveryOrderRegistrar;
import ar.com.adriabe.services.OrderService;
import ar.com.adriabe.services.ProductService;
import ar.com.adriabe.services.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/application-context.xml"})
public class ProductServiceImplTest {

    @Autowired
    ProductService productService;


    @Autowired
    OrderService orderService;

    @Autowired
    DeliveryOrderRegistrar deliveryOrderRegistrar;

    @Test
    public void testIncrementStock() throws Exception {

        String barcode1 = productService.incrementStock(1l, 4l, 2l, 57l, 1234.00, 1);

        deliveryOrderRegistrar.register(null);


    }

    @Test
    public void testedsasdaadsA() {
        List<OrderItem> itemsToDeliverByOrderOrClient = orderService.findItemsToDeliverByOrderOrClient(1l, 1l);
        assertNotNull(itemsToDeliverByOrderOrClient);

    }

    @Test
    public void test2() {


        try {
            String barcode4 = productService.incrementStock(1l, 4l, 1l, null, 10.21, 1);
            fail("deberia ");
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        try {
            String barcode5 = productService.incrementStock(1l, 4l, null, 2l, 10.21, 1);
            String barcode3 = productService.incrementStock(1l, 4l, null, null, 10.21, 1);
            assertEquals(barcode3, barcode5);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }
}