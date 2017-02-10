package ar.com.adriabe.web.model.json;

import org.codehaus.jackson.map.annotate.JsonRootName;

/**
 * Created by Mildo on 1/23/15.
 */
@JsonRootName("product-family")
public class ProductFamilyJSON {

    private Long id;

    private Double price;
    private String colorType;


    private Long fabricId;
    private String fabricName;

    private Long stripeId;
    private String stripeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getColorType() {
        return colorType;
    }

    public void setColorType(String colorType) {
        this.colorType = colorType;
    }

    public Long getFabricId() {
        return fabricId;
    }

    public void setFabricId(Long fabricId) {
        this.fabricId = fabricId;
    }

    public String getFabricName() {
        return fabricName;
    }

    public void setFabricName(String fabricName) {
        this.fabricName = fabricName;
    }

    public Long getStripeId() {
        return stripeId;
    }

    public void setStripeId(Long stripeId) {
        this.stripeId = stripeId;
    }

    public String getStripeName() {
        return stripeName;
    }

    public void setStripeName(String stripeName) {
        this.stripeName = stripeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
