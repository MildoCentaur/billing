package ar.com.adriabe.model;

import ar.com.adriabe.model.common.AuditableDomainObject;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product extends AuditableDomainObject {

    /**
     *
     */
    private static final long serialVersionUID = -2644487083142153574L;

    private Long stock;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fabric_id")
    private Fabric fabric;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "color_id")
    private Color color;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "striped_id")
    private Stripe stripe;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "striped_combination_id")
    private StripeCombination stripeCombination;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "family_id")
    private ProductFamily productFamily;


    /**
     * Method 'Product'
     */
    public Product() {
    }

    public Product(Long id) {
        super.setId(id);
    }


    public Fabric getFabric() {
        return fabric;
    }

    public void setFabric(Fabric fabric) {
        this.fabric = fabric;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Stripe getStripe() {
        return stripe;
    }

    public StripeCombination getStripeCombination() {
        return stripeCombination;
    }

    public void setStripeCombination(StripeCombination stripeCombination) {
        this.stripeCombination = stripeCombination;
    }

    @Transient
    public String getProductFamilyName() {
        return productFamily.getName();
    }

    @JsonBackReference
    public ProductFamily getProductFamily() {
        return productFamily;
    }

    public void setProductFamily(ProductFamily productFamily) {
        this.productFamily = productFamily;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    /**
     * Delegate method's
     */

    @Transient
    public String getFullColorStr() {
        if (color == null) {
            return "Sin Color - Crudo";
        }
        return color.getName() + " (" + color.getType().getLabel() + ")";
    }

    @Transient
    @JsonProperty
    public String getColorName() {
        if (color == null) {
            return "Sin Color";
        }
        return color.getName();
    }

    @Transient
    @JsonProperty
    public String getProductName() {
        String aux;
        aux = fabric.getCode() + " " + fabric.getShortname() + " " + color.getName();

        if (stripe != null) {
            aux = aux + " " + stripe.getName();
        }

        return aux;
    }

    @Transient
    @JsonProperty
    public String getFabricName() {
        return fabric.getCode() + " " + fabric.getShortname();
    }

    @Transient
    public String getColorTypeLabel() {

        return color.getColorType();
    }

    @Transient
    @JsonProperty(required = true, value = "stripename", defaultValue = "DUMMY")
    public String getStripeName() {
        if (stripe == null) {
            return "";
        }
        return stripe.getName();
    }

    @Transient
    @Override
    public String toString() {
        String colorStr, fabricStrLong, fabricStrShort;
        String aux;

        if (fabric == null) {
            return "Producto sin tejido asociado.";
        }

        fabricStrLong = fabric.getLongname();
        fabricStrShort = fabric.getShortname();

        if (stripe == null) {
            colorStr = color.getName();
            aux = (fabricStrLong.length() + colorStr.length() > 37) ? fabricStrShort : fabricStrLong;
            aux = aux + " " + colorStr;
        } else {
            aux = (fabricStrLong.length() + stripe.getName().length() > 27) ? fabricStrShort : fabricStrLong;
            aux = aux + " " + stripe.getName() + " " + stripe.getColors() + " colores";
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append(fabric.getCode());
        buffer.append(" ");
        buffer.append(aux);

        return buffer.toString();
    }

    public Product addStock(long i) {
        this.stock = this.stock + i;
        return this;
    }

    public Double getPrice() {
        List<ListPriceItem> prices = productFamily.getPrices();
        Double result = 0.0;
        if (prices == null || prices.isEmpty()) {
            return result;
        }

        for (ListPriceItem price : prices) {
            if (price.getListPrice().isActive()) {
                result = price.getPrice();
            }
        }

        return result;
    }

    public boolean isMainProduct() {
        return fabric.isMainFabric();
    }

    protected boolean isStripe() {
        return this.stripe != null;
    }

    public void setStripe(Stripe stripe) {
        this.stripe = stripe;
    }
}