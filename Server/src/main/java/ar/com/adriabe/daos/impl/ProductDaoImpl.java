package ar.com.adriabe.daos.impl;

import ar.com.adriabe.daos.DaoException;
import ar.com.adriabe.daos.ProductDao;
import ar.com.adriabe.model.*;
import ar.com.adriabe.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Mildo on 9/26/14.
 */
@Component("productDao")
public class ProductDaoImpl implements ProductDao {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findByDeleted(false);
    }

    @Override
    public List<Product> findLikeName(String name) {
        name = '%' + name + '%';
        return productRepository.findLikeName(name);
    }

    @Override
    public Product findById(Long id) throws Exception {
        Product product = productRepository.findOne(id);
        if (product == null) {
            throw new Exception("Producto no encontrado id:" + id);
        }
        return product;
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product findProductByFabricAndColorAndStripeAndCombination(Fabric fabric, Color color, Stripe stripe, StripeCombination stripeCombination) {
        if (stripe == null || stripeCombination == null) {
            return productRepository.findByFabricAndColor(fabric.getId(), color.getId());
        }
        return productRepository.findByFabricAndColorAndStripeAndStripeCombination(fabric.getId(), color.getId(), stripe.getId(), stripeCombination.getId());
    }


    @Override
    public Product findProductByFabricAndColorAndStripeAndCombinationIds(Long fabric, Long color, Long stripe, Long stripeCombination) throws DaoException {
        Product product = null;
        if (stripe == null || stripeCombination == null || stripe == 0 || stripeCombination == 0) {
            product = productRepository.findByFabricAndColor(fabric, color);
        } else {
            product = productRepository.findByFabricAndColorAndStripeAndStripeCombination(fabric, color, stripe, stripeCombination);
        }
        if (product == null) {
            throw new DaoException("Producto no encontrado");
        }
        return product;
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Integer countEmptyStock() {
        return productRepository.countEmptyStock();
    }


}
