package ar.com.adriabe.components;

import ar.com.adriabe.daos.ProductDao;
import ar.com.adriabe.model.*;
import ar.com.adriabe.repositories.ProductFamilyRepository;
import com.google.common.collect.Lists;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BarcodeAnalyzer_UT extends TestCase {

    public static final long PRODUCT_FAMILY_ID = 10l;
    public static final long STRIPE_ID = 33l;
    public static final long FABRIC_ID = 13l;
    public static final long COMBINATION_ID = 42l;
    public static final long COMBINATION_ID2 = 44l;
    public static final double DUMMY_WEIGHT = 20.36;
    public static final String CODE_WITHOUT_DATE = "01002212036";
    private static final long COLOR_ID = 22l;
    @Mock
    ProductDao productDao;
    @Mock
    ProductFamilyRepository productFamilyRepository;

    @InjectMocks
    BarcodeAnalyzer16 analyzer;

    @Mock
    Product product;
    @Mock
    ProductFamily productFamily;
    @Mock
    Color color;

    Stripe stripe;

    StripeCombination stripeCombination;
    StripeCombination stripeCombination2;
    private Fabric fabric;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(BarcodeAnalyzer.class);
        when(productFamily.getId()).thenReturn(PRODUCT_FAMILY_ID);
        fabric = new Fabric(FABRIC_ID);
        when(productFamily.getFabric()).thenReturn(fabric);

        when(color.getId()).thenReturn(COLOR_ID);
        stripe = new Stripe();
        stripe.setId(STRIPE_ID);

        stripeCombination = new StripeCombination();
        stripeCombination.setId(COMBINATION_ID);
        stripeCombination2 = new StripeCombination();
        stripeCombination2.setId(COMBINATION_ID2);
        List<StripeCombination> combinations = Lists.newArrayList(stripeCombination, stripeCombination2);
        stripe.setCombinations(combinations);

        when(product.getProductFamily()).thenReturn(productFamily);
        when(product.getColor()).thenReturn(color);
        when(product.getStripe()).thenReturn(stripe);
        when(product.getStripeCombination()).thenReturn(stripeCombination2);

    }

    @Test
    public void generateBarcodeFromProduct() throws Exception {


        Barcode barcode = analyzer.generateBarcode(product, DUMMY_WEIGHT);

        assertNotNull(barcode);
        assertNotNull(barcode.getCode());
        assertEquals(barcode.getCode(), buildBarcode());

    }

    @Test
    public void getProductFromScannedBarcode() throws Exception {
        when(productFamilyRepository.findOne(PRODUCT_FAMILY_ID)).thenReturn(productFamily);
        when(productFamily.getStripe()).thenReturn(stripe);
        when(productDao.findProductByFabricAndColorAndStripeAndCombinationIds(FABRIC_ID, COLOR_ID, STRIPE_ID, COMBINATION_ID2)).thenReturn(product);

        Barcode barcode = analyzer.scanBarcode(buildBarcode());

        assertNotNull(barcode);
        assertNotNull(barcode.getProduct());

    }

    private String buildBarcode() {
        SimpleDateFormat format = new SimpleDateFormat("Dyy");
        String date = format.format(new Date());
        return CODE_WITHOUT_DATE + date;
    }
}