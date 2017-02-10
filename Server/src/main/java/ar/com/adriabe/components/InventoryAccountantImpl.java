package ar.com.adriabe.components;


import ar.com.adriabe.daos.ProductDao;
import ar.com.adriabe.model.Barcode;
import ar.com.adriabe.model.Product;
import ar.com.adriabe.services.InvalidDataException;
import ar.com.adriabe.services.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class InventoryAccountantImpl implements InventoryAccountant {

    public static final String INVALID_BARCODE = "Error en la lectura del c?digo de barras.";
    public static final String PRODUCT_NOT_FOUND = "Producto no encontrado.";
    protected final Logger logger = LogManager.getLogger(InventoryAccountantImpl.class);
    BarcodeAnalyzer barcodeAnalizer;
    ProductDao productDao;

    @Autowired
    public InventoryAccountantImpl(@Qualifier("barcodeAnalyzer") BarcodeAnalyzer barcodeAnalizer, ProductDao productDao) {
        this.barcodeAnalizer = barcodeAnalizer;
        this.productDao = productDao;
    }

    @Override
    public Barcode incrementProductStock(String code, long amount) throws ServiceException {
        Barcode barcode = null;
        try {
            barcode = barcodeAnalizer.scanBarcode(code);
            Product product = barcode.getProduct();
            product.addStock(amount);
            productDao.save(product);
            return barcode;
        } catch (InvalidDataException e) {
            e.printStackTrace();
            logger.error("C?digo de barras invalido");
            throw new ServiceException(INVALID_BARCODE);
        } catch (ServiceException e) {
            e.printStackTrace();
            logger.error("Producto no encontrado.");
            throw new ServiceException(PRODUCT_NOT_FOUND);
        }
    }

}
