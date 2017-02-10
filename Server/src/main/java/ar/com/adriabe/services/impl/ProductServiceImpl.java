package ar.com.adriabe.services.impl;

import ar.com.adriabe.daos.*;
import ar.com.adriabe.model.*;
import ar.com.adriabe.model.common.annotation.ILogableOperation;
import ar.com.adriabe.model.constant.ACTION_TYPE;
import ar.com.adriabe.services.ProductService;
import ar.com.adriabe.services.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Mildo on 9/26/14.
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {


    final Logger logger = LogManager.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductFamilyDao productFamilyDao;
    @Autowired
    private FabricDao fabricDao;
    @Autowired
    private ColorDao colorDao;
    @Autowired
    private StripeDao stripeDao;

    @Override
    public List<Product> findAll(String query) {
        if (query == null || query.isEmpty()) {
            return productDao.findAll();
        }
        return productDao.findLikeName(query);

    }

    @Override
    public List<ProductFamily> findAllProductFamilies(String query) {
        if (query == null || query.isEmpty()) {
            return productFamilyDao.findAll();
        }
        return productFamilyDao.findLikeName(query);
    }

    @Override
    public Product findById(Long id) throws Exception {
        return productDao.findById(id);
    }


    @Override
    public void deleteProduct(Long id) {
        Product product = null;
        try {
            product = productDao.findById(id);
            productDao.delete(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFabric(Long id) {
        Fabric fabric = fabricDao.findById(id);
        fabricDao.delete(fabric);
    }

    @Override
    public void deleteStripe(Long id) {
        Stripe stripe = stripeDao.findById(id);
        stripeDao.delete(stripe);
    }

    @Override
    public List<Fabric> findAllFabrics(String query) {
        if (query == null || query.isEmpty()) {
            return fabricDao.findAll();
        }
        return fabricDao.findLikeName(query);
    }

    @Override
    public Fabric findFabricById(Long id) {
        return fabricDao.findById(id);

    }

    @Override
    public StripeCombination findStripeCombinationById(Long stripeCombinationId) {
        if (stripeCombinationId == null || stripeCombinationId == 0) {
            return null;
        }
        return stripeDao.findStripeCombinationById(stripeCombinationId);
    }

    @Override
    public List<Stripe> findAllStripes(String query) {
        if (query == null || query.isEmpty()) {
            return stripeDao.findAll();
        }
        return stripeDao.findLikeName(query);
    }

    @Override
    public Stripe findStripeById(Long id) {
        if (id == null || id == 0) {
            return null;
        }
        return stripeDao.findById(id);
    }

    @Override
    @Transactional
    @ILogableOperation(desc = "Registra un tejido", type = ACTION_TYPE.CREATE)
    public void save(Fabric entity) {
        fabricDao.save(entity);
    }

    @Override
    @Transactional
    @ILogableOperation(desc = "Registra un producto", type = ACTION_TYPE.CREATE)
    public void save(Product entity) {
        productDao.save(entity);
    }

    @Override
    @Transactional
    @ILogableOperation(desc = "Registra un patron de rayado", type = ACTION_TYPE.CREATE)
    public void save(Stripe entity) {
        if (entity.getCombinations() != null) {
            List<StripeCombination> deleted = new ArrayList<StripeCombination>();
            for (StripeCombination stripeCombination : entity.getCombinations()) {
                if (stripeCombination.getName() == null)
                    deleted.add(stripeCombination);
            }
            entity.getCombinations().removeAll(deleted);
        }
        stripeDao.save(entity);
    }


    @Override
    public ProductFamily findProductFamilyByProductFamilyId(Long id) {
        return productFamilyDao.findProductFamilyByProductFamilyId(id);
    }

    @Override
    @Transactional
    @ILogableOperation(desc = "Busca un producto por: tejido, color, patrón y combinación de rayado. Si no lo encuentra lo crea.", type = ACTION_TYPE.CREATE)
    public Product getByFabricAndColorAndStripe(Fabric fabric, Color color, Stripe stripe, StripeCombination stripeCombination) throws ServiceException {
        return getByFabricAndColorAndStripeId(fabric.getId(), color.getId(), (stripe == null) ? null : stripe.getId(), (stripe == null) ? null : stripeCombination.getId());
    }

    @Override
    @Transactional
    public Product getByFabricAndColorAndStripeId(Long fabric, Long colorId, Long stripe, Long stripeCombination) throws ServiceException {
        logger.debug("start getByFabricAndColorAndStripeId.");
        Product product = null;
        if (fabric == null || colorId == null || fabric == 0 || colorId == 0) {
            return null;
        }
        //lookup for the product
        logger.debug("Busca un producto por: tejido, color, patrón y combinación de rayado. Si no lo encuentra lo crea.");
        try {
            product = productDao.findProductByFabricAndColorAndStripeAndCombinationIds(fabric, colorId, stripe, stripeCombination);

        } catch (DaoException e) {
            e.printStackTrace();
            List<ProductFamily> productFamilies = findProductFamilyByFabricAndStripe(fabric, stripe);
            Fabric fab = findFabricById(fabric);
            Stripe stripePattern = findStripeById(stripe);
            if (productFamilies == null || productFamilies.isEmpty()) {
                String aux = ((fab == null) ? "" : fab.getShortname()) + " " + ((stripePattern == null) ? "" : stripePattern.getName());
                throw new ServiceException("Tipo de producto fuera de la lista de precios " + aux);
            }

            product = createProduct(colorId, stripe, stripeCombination, productFamilies, fab, stripePattern);
        }

        return product;
    }

    protected Product createProduct(Long colorId, Long stripe, Long stripeCombination, List<ProductFamily> productFamilies, Fabric fab, Stripe stripePattern) throws ServiceException {
        try {

            Product product;
            product = new Product();
            product.setStripeCombination(findStripeCombinationById(stripeCombination));
            product.setStripe(stripePattern);
            product.setStock(0);
            product.setFabric(fab);
            Color color = colorDao.findAllColorsById(colorId);
            product.setColor(color);
            for (ProductFamily productFamily : productFamilies) {
                if (productFamily.getColorType().compareTo(color.getType()) == 0) {
                    if (stripe == null || stripe == 0 || stripe != null && productFamily.getStripe().getId() == stripe) {
                        product.setProductFamily(productFamily);
                    }
                }
            }
            productDao.save(product);
            return product;
        } catch (Exception ex) {
            String aux = ((fab == null) ? "" : fab.getShortname()) + " " + ((stripePattern == null) ? "" : stripePattern.getName());
            throw new ServiceException("Error al registrar producto " + aux);
        }
    }

    @Override
    public List<ProductFamily> findProductFamilyByFabricAndStripe(Long fabric, Long stripe) {
        List<ProductFamily> result = null;

        if (fabric == null) {
            return null;
        }
        if (stripe == null || stripe == 0) {
            result = productFamilyDao.findProductFamilyByFabric(fabric);
        } else {
            result = productFamilyDao.findProductFamilyByFabricAndStripe(fabric, stripe);
        }


        return result;
    }

    @Override
    @Transactional
    @ILogableOperation(desc = "Incrementa el sctock de un producto y genera su codigo de barras", type = ACTION_TYPE.UPDATE)
    public String incrementStock(Long fabricId, Long colorId, Long stripeId, Long stripeCombinationId, Double weight, int partida) throws ServiceException {
        if (fabricId == null || fabricId == 0l || colorId == null || colorId == 0l || weight > 99.9) {
            throw new ServiceException("Parametros invalidos");
        }

        Fabric fabric = findFabricById(fabricId);
        Color color = colorDao.findAllColorsById(colorId);
        Stripe stripe = null;
        StripeCombination combination = null;
        if (stripeId != null && stripeCombinationId != null && stripeId > 0 && stripeCombinationId > 0) {
            stripe = findStripeById(stripeId);
            combination = findStripeCombinationById(stripeCombinationId);
        }

        Product product = getByFabricAndColorAndStripe(fabric, color, stripe, combination);


        save(product.addStock(1l));
        int index = 0;
        if (stripe != null) {
            index = product.getStripe().getCombinationIndex(product.getStripeCombination());
        }


        SimpleDateFormat format = new SimpleDateFormat("Dyy");
        String date = format.format(new Date());
        Long productFamilyId = product.getProductFamily().getId();
        long days = Integer.valueOf(date);
        Locale locale = Locale.ENGLISH;
        String barcode = String.format(locale, "%03d%03d%1d%04d%05d", productFamilyId, colorId, index, (int) (weight * 100), days);

        return barcode;

    }

    @Override
    public Integer countProductsWithEmptyStock() {
        return productDao.countEmptyStock();
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public ProductFamilyDao getProductFamilyDao() {
        return productFamilyDao;
    }

    public void setProductFamilyDao(ProductFamilyDao productFamilyDao) {
        this.productFamilyDao = productFamilyDao;
    }

    public FabricDao getFabricDao() {
        return fabricDao;
    }

    public void setFabricDao(FabricDao fabricDao) {
        this.fabricDao = fabricDao;
    }

    public ColorDao getColorDao() {
        return colorDao;
    }

    public void setColorDao(ColorDao colorDao) {
        this.colorDao = colorDao;
    }

    public StripeDao getStripeDao() {
        return stripeDao;
    }

    public void setStripeDao(StripeDao stripeDao) {
        this.stripeDao = stripeDao;
    }

}
