package ar.com.adriabe.web.controllers.adapters;

import ar.com.adriabe.model.Product;
import ar.com.adriabe.web.model.json.ProductJSON;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mildo on 1/23/15.
 */
@Component
public class ProductJSONAdapter {


    public List<ProductJSON> buildProductJSONListFromProductList(List<Product> products) throws KendoAdapterException {
        List<ProductJSON> result = new ArrayList<ProductJSON>();
        if (products == null || products.isEmpty()) {
            return result;
        }
        for (Product product : products) {
            result.add(buildProductJSONFromProduct(product));
        }
        return result;
    }

    public ProductJSON buildProductJSONFromProduct(Product product) {
        ProductJSON result = new ProductJSON();
        if (product == null) {
            return result;
        }
        result.setColor(product.getColorName());
        result.setColorId(product.getColor().getId());
        result.setColorCode(product.getColor().getCode());
        result.setColorCoordinate(product.getColor().getCoordinate());
        result.setColorType(product.getColorTypeLabel());
        result.setCuello(product.getFabric().isCuello());
        result.setTiras(product.getFabric().isTira());
        result.setFabric(product.getFabricName());
        result.setFabricId(product.getFabric().getId());
        result.setFabricCode(product.getFabric().getCode());
        result.setId(product.getId());
        result.setPrice(product.getPrice());
        result.setProductFamilyId(product.getProductFamily().getId());
        result.setProductFamilyName(product.getProductFamily().getName());
        result.setPuno(product.getFabric().isPuno());
        result.setStock(product.getStock());
        if (product.getStripe() != null) {
            result.setStripe(product.getStripeName());
            result.setStripeCode(product.getStripe().getCode());
            String[] combination = new String[6];
            combination[0] = product.getStripeCombination().getColorFingerA();
            combination[1] = product.getStripeCombination().getColorFingerB();
            combination[2] = product.getStripeCombination().getColorFingerC();
            combination[3] = product.getStripeCombination().getColorFingerD();
            combination[4] = product.getStripeCombination().getColorFingerE();
            combination[5] = product.getStripeCombination().getColorFingerF();
            result.setStripeCombination(combination);
            result.setStripeId(product.getStripe().getId());
            result.setStripeCombinationIndex(product.getStripe().getCombinationIndex(product.getStripeCombination()));
            result.setStripeCombinationId(product.getStripeCombination().getId());
            result.setStripeCombinationName(product.getStripeCombination().getName());
            result.setStripeFormula(product.getStripe().getListingFormula());
        }

        return result;
    }
}
