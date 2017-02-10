package ar.com.adriabe.daos;

import ar.com.adriabe.model.*;

import java.util.List;

/**
 * Created by Mildo on 9/5/14.
 */
public interface ListPriceDao {

    ListPrice findById(Long id);

    ListPrice findDefaultListPrice();

    double findPriceByProduct(Product product);

    ListPriceItem findDefaultListPriceItemByProductFamily(ProductFamily productFamily);

    void save(ListPriceItem listPriceItem);

    void save(ListPrice listPrice);

    List<ListPriceItem> findDefaultListPriceItemsByFabricAndStripe(Fabric fabric, Stripe stripe);
}
