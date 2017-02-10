package ar.com.adriabe.web.controllers.adapters;

import ar.com.adriabe.model.ProductFamily;
import ar.com.adriabe.web.model.json.ProductFamilyJSON;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mildo on 1/23/15.
 */
public class ProductFamilyJSONAdapter {


    public List<ProductFamilyJSON> buildProductFamilyJSONListFromProductList(List<ProductFamily> all) {
        List<ProductFamilyJSON> result = new ArrayList<ProductFamilyJSON>();
        for (ProductFamily family : all) {
            result.add(buildProductFamilyJSONFromProduct(family));
        }
        return result;

    }

    public ProductFamilyJSON buildProductFamilyJSONFromProduct(ProductFamily family) {
        ProductFamilyJSON result = new ProductFamilyJSON();
        if(family!=null){
            result.setColorType(family.getColorType().getLabel());
            result.setFabricId(family.getFabric().getId());
            result.setFabricName(family.getFabric().getCode() + " " + family.getFabric().getShortname());
            result.setId(family.getId());
            result.setName(family.getName());
            result.setPrice(family.getPrice());
            if(family.getStripe()!=null){
                result.setStripeId(family.getStripe().getId());
                result.setStripeName(family.getStripe().getName());
            }
        }
        return result;
    }
}
