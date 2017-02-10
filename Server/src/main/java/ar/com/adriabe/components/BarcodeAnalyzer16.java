package ar.com.adriabe.components;

import ar.com.adriabe.daos.DaoException;
import ar.com.adriabe.daos.ProductDao;
import ar.com.adriabe.model.Barcode;
import ar.com.adriabe.model.Product;
import ar.com.adriabe.model.ProductFamily;
import ar.com.adriabe.model.Stripe;
import ar.com.adriabe.repositories.ProductFamilyRepository;
import ar.com.adriabe.services.InvalidDataException;
import ar.com.adriabe.services.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ajmild1 on 21/08/2015.
 */
@Component("barcodeAnalyzer")
public class BarcodeAnalyzer16 implements BarcodeAnalyzer {

    public static final int CODE_LENGTH = 16;
    private ProductDao productDao;

    private ProductFamilyRepository productFamilyRepository;

    @Autowired
    public BarcodeAnalyzer16(ProductDao productDao, ProductFamilyRepository productFamilyRepository) {
        this.productDao = productDao;
        this.productFamilyRepository = productFamilyRepository;
    }


    @Override
    public Barcode generateBarcode(Product product, Double weight) {
        String code = generateCode(product, weight);
        Barcode barcode = new Barcode(product, code, weight);
        return barcode;
    }

    private String generateCode(Product product, Double weight) {
        SimpleDateFormat format = new SimpleDateFormat("Dyy");
        String date = format.format(new Date());
        long productFamilyId = product.getProductFamily().getId();
        long colorId = product.getColor().getId();
        int index = 0;
        if (product.getStripe() != null) {
            index = product.getStripe().getCombinationIndex(product.getStripeCombination());
        }
        long days = Integer.valueOf(date);
        Locale locale = Locale.ENGLISH;

        String barcode = String.format(locale, "%03d%03d%1d%04d%05d", productFamilyId, colorId, index, (int) (weight * 100), days);

        return barcode;
    }

    @Override
    public Barcode scanBarcode(String code) throws InvalidDataException, ServiceException {
        if (code.isEmpty() || code.length() < CODE_LENGTH) {
            throw new InvalidDataException();
        }
        Long familyId = Long.parseLong(code.substring(0, 3));
        Long colorId = Long.parseLong(code.substring(3, 6));
        int stripeCombinationIndex = Integer.parseInt(code.substring(6, 7));
        Double weight = Long.parseLong(code.substring(7, 11)) / 100.0;
        ProductFamily productFamily = productFamilyRepository.findOne(familyId);
        if (productFamily == null) {
            throw new ServiceException("Familia de producto no encontrada.");
        }
        Long fabricId = productFamily.getFabric().getId();
        Stripe stripe = productFamily.getStripe();


        Long stripeId = null;
        Long combinationId = null;
        if (stripe != null) {
            combinationId = stripe.getCombinationIdByIndex(stripeCombinationIndex);
            stripeId = stripe.getId();
        }

        try {
            Product product = productDao.findProductByFabricAndColorAndStripeAndCombinationIds(fabricId, colorId, stripeId, combinationId);
            Barcode barcode = new Barcode(product, code, weight);
            return barcode;
        } catch (DaoException e) {
            throw new ServiceException("Producto invalido.");
        }


    }

}
