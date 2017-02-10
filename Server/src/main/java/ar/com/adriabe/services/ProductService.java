package ar.com.adriabe.services;

import ar.com.adriabe.model.*;

import java.util.List;

/**
 * Created by Mildo on 9/26/14.
 */
public interface ProductService {


    List<Fabric> findAllFabrics(String query);

    List<Stripe> findAllStripes(String query);

    List<Product> findAll(String query);

    List<ProductFamily> findAllProductFamilies(String query);


    Product findById(Long id) throws Exception;

    Fabric findFabricById(Long id);

    StripeCombination findStripeCombinationById(Long stripeCombinationId);

    Stripe findStripeById(Long id);

    void deleteProduct(Long id);

    void deleteFabric(Long id);

    void deleteStripe(Long id);


    public void save(Fabric entity);

    public void save(Product entity);

    public void save(Stripe entity);

    Product getByFabricAndColorAndStripe(Fabric fabric, Color color, Stripe stripe, StripeCombination stripeCombination) throws ServiceException;

    Product getByFabricAndColorAndStripeId(Long fabric, Long colorId, Long stripe, Long stripeCombination) throws ServiceException;

    ProductFamily findProductFamilyByProductFamilyId(Long id);

    public List<ProductFamily> findProductFamilyByFabricAndStripe(Long fabric, Long stripe);

    String incrementStock(Long fabricId, Long colorId, Long stripeId, Long stripeCombinationId, Double weight, int partida) throws ServiceException;

    Integer countProductsWithEmptyStock();
}
