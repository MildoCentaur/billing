package ar.com.adriabe.services;

import ar.com.adriabe.daos.DaoException;
import ar.com.adriabe.model.*;

import java.util.List;

/**
 * Created by Mildo on 11/20/14.
 */
public interface PricingService {

    double findProductPrice(Product product) throws DaoException;

    ListPrice findLatestListPrice();

    List<ListPriceItem> addAllProductFamilies(Fabric fabric, Stripe stripe, double priceWhite, double priceLight, double priceDark, double priceSpecial) throws ServiceException;


    void updateListPrice(ListPrice newListPrice);

    List<ListPriceItem> getBillableProductFamilies(Fabric fabric, Stripe stripe) throws Exception;
}
