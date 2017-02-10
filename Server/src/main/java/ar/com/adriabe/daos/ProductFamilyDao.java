package ar.com.adriabe.daos;

import ar.com.adriabe.model.Fabric;
import ar.com.adriabe.model.ProductFamily;
import ar.com.adriabe.model.Stripe;
import ar.com.adriabe.model.constant.COLOR_TYPE;

import java.util.List;

/**
 * Created by Mildo on 10/8/14.
 */
public interface ProductFamilyDao {

    public ProductFamily findProductFamilyByProductFamilyId(Long id);

    public List<ProductFamily> findProductFamilyByFabricAndStripe(Long fabric, Long stripe);

    public ProductFamily findProductFamilyByFabricAndStripeAndColorType(Fabric fabric, Stripe stripe, COLOR_TYPE colorType);

    public List<ProductFamily> findProductFamilyByFabric(Long fabric);

    void save(ProductFamily productFamily);

    public List<ProductFamily> findAll();

    List<ProductFamily> findLikeName(String query);
}
