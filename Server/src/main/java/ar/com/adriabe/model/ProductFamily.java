package ar.com.adriabe.model;

/**
 * Created by AJMILD1 on 05/06/14.
 */

import ar.com.adriabe.model.common.DomainObject;
import ar.com.adriabe.model.constant.COLOR_TYPE;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonRootName;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_families")
@JsonRootName("product_family")
public class ProductFamily extends DomainObject {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "productFamily")
    private List<Product> products;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="product_family_id")
    @OrderBy("id")
    private List<ListPriceItem> prices;

    @Enumerated(EnumType.STRING)
    @Column(name = "COLOR_TYPE")
    private COLOR_TYPE colorType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Fabric fabric;

    @ManyToOne(fetch = FetchType.LAZY)
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

    @JsonManagedReference
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct (Product product){
        this.products.add(product);
    }

    public List<ListPriceItem> getPrices() {
        return prices;
    }

    public void setPrices(List<ListPriceItem> prices) {
        this.prices = prices;
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

    @Override
    public String toString() {

        return fabric.getShortname() + " " + colorType.getLabel() + " " +((stripe==null) ? "" : stripe.getName());
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

    public Double getPrice(){
        List<ListPriceItem> prices = getPrices();
        Double result = 0.0;
        if(prices == null || prices.isEmpty()){
            return result;
        }

        for (ListPriceItem price : prices) {
            if(price.getListPrice().isActive()){
                result=price.getPrice();
            }
        }

        return result;
    }
}
