package ar.com.billing.workstation.model;

import ar.com.billing.workstation.model.constant.COLOR_TYPE;
import java.util.List;

/**
 * Created by AJMILD1 on 05/06/14.
 */

public class ProductFamily  {

    private List<Product> products;

    private COLOR_TYPE colorType;

    private Fabric fabric;

    private Stripe stripe;

    private String name;

    public ProductFamily(COLOR_TYPE type, String name,Fabric fabric,Stripe stripe){
        this.name=name;
        this.colorType=type;
        this.fabric=fabric;
        this.stripe=stripe;
    }

    public ProductFamily(){
        this.name="";
        this.colorType=COLOR_TYPE.BLANCO;
    }
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct (Product product){
        this.products.add(product);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductFamily)) return false;
        if (!super.equals(o)) return false;

        ProductFamily that = (ProductFamily) o;

        if (colorType != that.colorType) return false;
        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + colorType.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }


    public COLOR_TYPE getColorType() {
        return colorType;
    }

    public void setColorType(COLOR_TYPE colorType) {
        this.colorType = colorType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Fabric getFabric() {
        return fabric;
    }

    public void setFabric(Fabric fabric) {
        this.fabric = fabric;
    }

    public Stripe getStripe() {
        return stripe;
    }

    public void setStripe(Stripe stripe) {
        this.stripe = stripe;
    }
}
