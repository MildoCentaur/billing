package ar.com.adriabe.daos;

import ar.com.adriabe.model.*;

import java.util.List;

/**
 * Created by Mildo on 9/26/14.
 */
public interface ProductDao {
    List<Product> findAll();

    List<Product> findLikeName(String name);

    Product findById(Long id) throws Exception;

    void save(Product product);

    Product findProductByFabricAndColorAndStripeAndCombination(Fabric fabric, Color color, Stripe stripe, StripeCombination stripeCombination);

    Product findProductByFabricAndColorAndStripeAndCombinationIds(Long fabric, Long color, Long stripe, Long stripeCombination) throws DaoException;

    void delete(Product product);

    Integer countEmptyStock();
}
