package ar.com.adriabe.model;

import ar.com.adriabe.model.common.DomainObject;

import javax.persistence.*;

@Entity
@Table(name = "bill_items")
public class BillItem extends DomainObject {

    /**
     *
     */
    private static final long serialVersionUID = 7497937363052593644L;
    private Integer packages;
    private Double amount;
    private Double price;
    private Double subtotal;
    private Double total;
    private Double tax;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_family_id", referencedColumnName = "id")
    private ProductFamily productFamily;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id", referencedColumnName = "id")
    private Color color;


    public Integer getPackages() {
        return packages;
    }

    public void setPackages(Integer packages) {
        this.packages = packages;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subTotal) {
        this.subtotal = subTotal;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Transient
    public String getProductDescription() {
        if (productFamily == null || color==null) {
            return "Producto inexistente.";
        }

        return productFamily.getName() + " " + color.getName();
    }


    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public void setProductFamily(ProductFamily productFamily) {
        this.productFamily = productFamily;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    public ProductFamily  getProductFamily() {
        return this.productFamily ;
    }

    public Color getColor() {
        return this.color;
    }
}
