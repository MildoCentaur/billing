package ar.com.adriabe.daos.impl;

import ar.com.adriabe.daos.ProductFamilyDao;
import ar.com.adriabe.model.Fabric;
import ar.com.adriabe.model.ProductFamily;
import ar.com.adriabe.model.Stripe;
import ar.com.adriabe.model.constant.COLOR_TYPE;
import ar.com.adriabe.repositories.ProductFamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Mildo on 9/26/14.
 */
@Component("productFamilyDao")
public class ProductFamilyDaoImpl implements ProductFamilyDao {

    @Autowired
    ProductFamilyRepository productFamilyRepository;


    @Override
    public ProductFamily findProductFamilyByProductFamilyId(Long id) {
        return productFamilyRepository.findOne(id);
    }

    @Override
    public List<ProductFamily> findProductFamilyByFabricAndStripe(Long fabric, Long stripe) {
        return productFamilyRepository.findByFabricAndStripe(fabric, ((stripe == null || stripe == 0) ? 0 : stripe));
    }

    @Override
    public ProductFamily findProductFamilyByFabricAndStripeAndColorType(Fabric fabric, Stripe stripe, COLOR_TYPE colorType) {
        if (stripe == null) {
            return productFamilyRepository.findByFabricAndColorType(fabric.getId(), colorType);
        }
        return productFamilyRepository.findByFabricAndStripeAndColorType(fabric.getId(), stripe.getId(), colorType);
    }

    @Override
    public List<ProductFamily> findProductFamilyByFabric(Long fabric) {
        return productFamilyRepository.findByFabric(fabric);
    }

    @Override
    public void save(ProductFamily productFamily) {
        productFamilyRepository.save(productFamily);
    }

    @Override
    public List<ProductFamily> findAll() {
        return productFamilyRepository.findAll();
    }

    @Override
    public List<ProductFamily> findLikeName(String query) {
        return productFamilyRepository.findLikeName(query);
    }
}
