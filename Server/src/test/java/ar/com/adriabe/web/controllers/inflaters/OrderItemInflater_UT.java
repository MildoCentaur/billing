package ar.com.adriabe.web.controllers.inflaters;


import ar.com.adriabe.model.*;
import ar.com.adriabe.services.ProductService;
import ar.com.adriabe.services.ServiceException;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderItemInflater_UT {

    public static final long FABRIC_ID = 10l;
    public static final long COLOR_ID = 10l;
    public static final long STRIPE_ID = 10l;
    public static final long COMBINATION_ID = 10l;
    public static final String DUMMY_PRODUCT_NAME = "dummy product name";
    public static final double DUMMY_PRICE = 22.20;
    public static final int EXPECTED_ACCESORIES_AMOUNT = 3;
    public static final String DUMMY_CODE = "DUMMY_CODE";
    @InjectMocks
    OrderItemInflater inflater;

    @Mock
    ProductService productService;

    Fabric fabric;
    Color color;
    Stripe stripe;
    StripeCombination combination;

    @Test
    public void inflateMainProductSuccess() throws ServiceException {
        OrderItem item = createOrderItem();
        Product expectedProduct = createProduct();
        when(productService.getByFabricAndColorAndStripe(fabric, color, stripe, combination)).thenReturn(expectedProduct);

        inflater.inflate(item);

        assertNotNull(item);
        assertNotNull(item.getProduct());
        assertNotNull(item.getProduct().getPrice());
        assertTrue(item.getProduct().getPrice() > 0);
        assertEquals(item.getProduct(), expectedProduct);
        assertTrue(item.isMainItem());
        assertThat(item.getAccesories()).isEmpty();
    }

    @Test
    public void inflateItemWithAccesoriesSuccess() throws ServiceException {
        OrderItem item = createOrderItem();
        OrderItem acc1 = createOrderItem();
        OrderItem acc2 = createOrderItem();
        OrderItem acc3 = createOrderItem();
        item.setAccesories(Lists.newArrayList(acc1, acc2, acc3));
        Product expectedProduct = createProduct();
        when(productService.getByFabricAndColorAndStripe(fabric, color, stripe, combination)).thenReturn(expectedProduct);

        inflater.inflate(item);

        assertNotNull(item);
        assertNotNull(item.getProduct());
        assertNotNull(item.getProduct().getPrice());
        assertTrue(item.getProduct().getPrice() > 0);
        assertEquals(item.getProduct(), expectedProduct);
        assertTrue(item.isMainItem());
        assertThat(item.getAccesories()).isNotEmpty();
        assertThat(item.getAccesories()).hasSize(EXPECTED_ACCESORIES_AMOUNT);
        for (OrderItem orderItem : item.getAccesories()) {
            assertNotNull(orderItem);
            assertNotNull(orderItem.getProduct());
            assertNotNull(orderItem.getProduct().getPrice());
            assertTrue(orderItem.getProduct().getPrice() > 0);
            assertEquals(orderItem.getProduct(), expectedProduct);
            assertFalse(orderItem.isMainItem());
            assertThat(orderItem.getAccesories()).isEmpty();
        }
    }

    @Test
    public void inflateMainProductFailsWhenQuantityLowerOrEqualToZero() throws ServiceException {
        OrderItem item = createOrderItem();
        Product expectedProduct = mock(Product.class);
        when(expectedProduct.getProductName()).thenReturn(DUMMY_PRODUCT_NAME);
        when(productService.getByFabricAndColorAndStripe(fabric, color, stripe, combination)).thenReturn(expectedProduct);
        item.setQuantity(0l);
        try {
            inflater.inflate(item);
            fail("Should have failed");
        } catch (ServiceException ex) {
            assertThat(ex).hasMessage("Cantidad de " + DUMMY_PRODUCT_NAME + " invalida.");
        }
    }

    protected Product createProduct() {
        Product expectedProduct = mock(Product.class);
        when(expectedProduct.getPrice()).thenReturn(DUMMY_PRICE);
        return expectedProduct;
    }

    protected OrderItem createOrderItem() {
        OrderItem item = new OrderItem();
        Product product = new Product();
        fabric = new Fabric(FABRIC_ID);
        color = new Color(COLOR_ID);
        stripe = new Stripe(STRIPE_ID);
        stripe.setCode(DUMMY_CODE);
        combination = new StripeCombination(COMBINATION_ID);

        product.setFabric(fabric);
        product.setColor(color);
        product.setStripe(stripe);
        product.setStripeCombination(combination);
        item.setProduct(product);
        item.setQuantity(10);


        return item;
    }

}