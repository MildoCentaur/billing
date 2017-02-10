package ar.com.adriabe.model;

/**
 * Created by ajmild1 on 21/08/2015.
 */
public class Barcode {


    private Product product;

    private Double weight;
    private String code;

    public Barcode(Product product, String code, Double weight) {
        this.product = product;
        this.code = code;
        this.weight = weight;
    }


    public ProductFamily getProductFamily() {
        if(product ==null){
            return null;
        }
        return product.getProductFamily();
    }

    public Product getProduct() {
        return product;
    }

    public Color getColor() {
        if(product ==null){
            return null;
        }
        return product.getColor();
    }

    public Fabric getFabric() {
        if(product ==null){
            return null;
        }
        return product.getFabric();
    }

    public Stripe getStripe() {
        if(product ==null){
            return null;
        }
        return product.getStripe();
    }

    public StripeCombination getStripeCombination() {
        if(product ==null){
            return null;
        }
        return product.getStripeCombination();
    }

    public Double getWeight() {
        return weight;
    }

    public String getCode() {
        return code;
    }
}
