package ar.com.adriabe.services.impl;

import ar.com.adriabe.daos.*;
import ar.com.adriabe.model.*;
import ar.com.adriabe.model.common.annotation.ILogableOperation;
import ar.com.adriabe.model.constant.ACTION_TYPE;
import ar.com.adriabe.model.constant.COLOR_TYPE;
import ar.com.adriabe.services.PricingService;
import ar.com.adriabe.services.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mildo on 11/22/14.
 */
@Service("pricingService")
public class PricingServiceImpl implements PricingService {

    final Logger logger = LogManager.getLogger(PricingServiceImpl.class);
    @Autowired
    ListPriceDao listPriceDao;
    @Autowired
    ProductFamilyDao productFamilyDao;
    @Autowired
    FabricDao fabricDao;
    @Autowired
    StripeDao stripeDao;

    @Override
    public double findProductPrice(Product product) throws DaoException {
        if (product == null) {
            throw new DaoException("Producto invalido.");
        }
        return listPriceDao.findPriceByProduct(product);

    }

    @ILogableOperation(desc = "Registrar el precio de una familia de productos de un tejido", type = ACTION_TYPE.CREATE)
    private ListPriceItem addProductFamilyPrice(Fabric fabric, Stripe stripe, double price, COLOR_TYPE colorType) throws ServiceException {
        try {
            String name = fabric.getShortname() + " " + ((stripe == null) ? "" : stripe.getName());
            ProductFamily productFamily = productFamilyDao.findProductFamilyByFabricAndStripeAndColorType(fabric, stripe, colorType);
            ListPriceItem listPriceItem;

            if (productFamily == null) {
                productFamily = new ProductFamily(colorType, name, fabric, stripe);
                ListPrice listPrice = listPriceDao.findDefaultListPrice();
                listPriceItem = new ListPriceItem();
                listPriceItem.setPrice(price);
                listPriceItem.setProductFamily(productFamily);
                listPriceItem.setListPrice(listPrice);
                productFamilyDao.save(productFamily);
            } else {
                listPriceItem = listPriceDao.findDefaultListPriceItemByProductFamily(productFamily);
                listPriceItem.setPrice(price);
            }

            listPriceDao.save(listPriceItem);

            return listPriceItem;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
            throw new ServiceException("Ocurrió un error al intentar registrar el producto en la lista de precios");
        }


    }

    @Override
    public List<ListPriceItem> addAllProductFamilies(Fabric fabric, Stripe stripe, double priceWhite, double priceLight, double priceDark, double priceSpecial) throws ServiceException {
        List<ListPriceItem> list = new ArrayList<ListPriceItem>();

        list.add(addProductFamilyPrice(fabric, stripe, priceWhite, COLOR_TYPE.BLANCO));
        list.add(addProductFamilyPrice(fabric, stripe, priceLight, COLOR_TYPE.CLARO));
        list.add(addProductFamilyPrice(fabric, stripe, priceDark, COLOR_TYPE.OSCURO));
        list.add(addProductFamilyPrice(fabric, stripe, priceSpecial, COLOR_TYPE.ESPECIAL));

        return list;
    }

    @Override
    public ListPrice findLatestListPrice() {
        return listPriceDao.findDefaultListPrice();
    }

    @Override
    @Transactional()
    @ILogableOperation(desc = "Actualizando la lista de precios", type = ACTION_TYPE.UPDATE)
    public void updateListPrice(ListPrice newListPrice) {
        backupListPriceValues();
        newListPrice.setDate(new Date());
        newListPrice.setLastModifiedDate(new DateTime());
        listPriceDao.save(newListPrice);


    }


    @ILogableOperation(desc = "Generando una copia de seguridad de la lista de precios anterior", type = ACTION_TYPE.CREATE)
    private void backupListPriceValues() {
        ListPrice listPriceToBackup = findLatestListPrice();
        ListPrice backup = new ListPrice();
        List<ListPriceItem> list = new ArrayList<ListPriceItem>();
        ListPriceItem item;
        backup.setDate(listPriceToBackup.getDate());
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        backup.setName(listPriceToBackup.getName() + " hasta " + format.format(new Date()));
        backup.setActive(false);


        //listPriceDao.save(backup);

        for (ListPriceItem listPriceItem : listPriceToBackup.getPrices()) {
            item = new ListPriceItem();
            item.setPrice(listPriceItem.getPrice());
            item.setProductFamily(listPriceItem.getProductFamily());
            //item.setListPrice(backup);
            list.add(item);
        }
        backup.setPrices(list);

        backup.setDate(new Date());
        backup.setLastModifiedDate(new DateTime());
        listPriceDao.save(backup);

    }


    @Override
    @Transactional()
    @ILogableOperation(desc = "Busca las familias de productos vendibles por tejido y patron. Si no la encuentra genera el set de familias vendibles correspondiente", type = ACTION_TYPE.UPDATE)
    public List<ListPriceItem> getBillableProductFamilies(Fabric fabric, Stripe stripe) throws Exception {

        List<ListPriceItem> prices = listPriceDao.findDefaultListPriceItemsByFabricAndStripe(fabric, stripe);


        if (prices.isEmpty()) {
            throw new Exception("Producto no puede ser vendido dado que no tiene un precio asociado.");
        }

        return prices;
    }
}
