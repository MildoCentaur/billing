package ar.com.adriabe.daos.impl;

import ar.com.adriabe.daos.ListPriceDao;
import ar.com.adriabe.model.*;
import ar.com.adriabe.repositories.ListPriceItemRepository;
import ar.com.adriabe.repositories.ListPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Mildo on 9/5/14.
 */
@Component("listPriceDao")
public class ListPriceDaoImpl implements ListPriceDao {

    @Autowired
    ListPriceRepository listPriceRepository;

    @Autowired
    ListPriceItemRepository listPriceItemRepository;

    @Override
    public ListPrice findById(Long id) {

        return listPriceRepository.findOne(id);
    }

    @Override
    public ListPrice findDefaultListPrice() {
        ListPrice listPrice = listPriceRepository.findLatestAndNameLikeGeneral();

        return listPrice;

    }

    @Override
    public double findPriceByProduct(Product product) {
        ListPriceItem item = listPriceRepository.findPriceByProduct(product.getId());

        return item.getPrice();
    }

    @Override
    public ListPriceItem findDefaultListPriceItemByProductFamily(ProductFamily productFamily) {
        return listPriceItemRepository.findDefaultListPriceItemByProductFamily(productFamily);
    }

    @Override
    public void save(ListPriceItem listPriceItem) {
        listPriceItemRepository.save(listPriceItem);
    }

    @Override
    public void save(ListPrice listPrice) {
        listPriceRepository.save(listPrice);
    }

    @Override
    public List<ListPriceItem> findDefaultListPriceItemsByFabricAndStripe(Fabric fabric, Stripe stripe) {
        if (stripe == null) {
            return listPriceItemRepository.findDefaultListPriceItemsByFabric(fabric);
        }
        return listPriceItemRepository.findDefaultListPriceItemsByFabricAndStripe(fabric, stripe);
    }
}
