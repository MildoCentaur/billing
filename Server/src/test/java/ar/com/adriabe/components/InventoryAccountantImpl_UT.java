package ar.com.adriabe.components;

import ar.com.adriabe.daos.ProductDao;
import ar.com.adriabe.model.Barcode;
import ar.com.adriabe.model.Product;
import ar.com.adriabe.services.InvalidDataException;
import ar.com.adriabe.services.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InventoryAccountantImpl_UT {

    public static final long DUMMY_AMOUNT = 10l;
    public static final String DUMMY_BARCODE = "DUMMY_BARCODE";
    public static final long DUMMY_PRODUCT_ID = 10l;
    public static final int DUMMY_INITIAL_STOCK = 0;
    public static final double DUMMY_WEIGHT = 20.2;
    InventoryAccountant inventoryAccountant;

    @Mock
    private ProductDao productDao;

    @Mock
    private BarcodeAnalyzer barcodeAnalizer;

    @Before
    public void setUp() throws Exception {
        inventoryAccountant = new InventoryAccountantImpl(barcodeAnalizer, productDao);
    }

    @Test
    public void productStockIncreasesWhenIncrementProductStockIsExecuted() throws Exception {
        //Given
        Product dummyProduct = new Product(DUMMY_PRODUCT_ID);
        dummyProduct.setStock(DUMMY_INITIAL_STOCK);
        Barcode expectedBarcode = new Barcode(dummyProduct, DUMMY_BARCODE, DUMMY_WEIGHT);
        when(barcodeAnalizer.scanBarcode(DUMMY_BARCODE)).thenReturn(expectedBarcode);

        //When
        Barcode barcode = inventoryAccountant.incrementProductStock(DUMMY_BARCODE, DUMMY_AMOUNT);
        //Should
        assertNotNull(barcode);
        assertEquals(barcode.getProduct(), dummyProduct);
        assertEquals(barcode.getProduct().getStock(), DUMMY_INITIAL_STOCK + DUMMY_AMOUNT);
    }


    @Test
    public void productNotFoundMessageIsThrownWhenProductBarcodeCouldNotBeFound() throws Exception {
        //Given
        Product dummyProduct = new Product(DUMMY_PRODUCT_ID);
        dummyProduct.setStock(DUMMY_INITIAL_STOCK);

        when(barcodeAnalizer.scanBarcode(DUMMY_BARCODE)).thenThrow(new InvalidDataException(), new ServiceException(""));

        //When
        try {
            Barcode barcode = inventoryAccountant.incrementProductStock(DUMMY_BARCODE, DUMMY_AMOUNT);
            fail("Should have failed");
        } catch (ServiceException e) {
            assertThat(e).hasMessage(InventoryAccountantImpl.INVALID_BARCODE);
        }
        try {
            Barcode barcode = inventoryAccountant.incrementProductStock(DUMMY_BARCODE, DUMMY_AMOUNT);
            fail("Should have failed");
        } catch (ServiceException e) {
            assertThat(e).hasMessage(InventoryAccountantImpl.PRODUCT_NOT_FOUND);
        }

        //Should
        assertEquals(dummyProduct.getStock(), DUMMY_INITIAL_STOCK);
    }


}
