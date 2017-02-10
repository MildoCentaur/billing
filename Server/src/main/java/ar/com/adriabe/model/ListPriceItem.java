package ar.com.adriabe.model;

import ar.com.adriabe.model.common.AuditableDomainObject;

import javax.persistence.*;


/**
 * This class represents the primary key of the Client_precios table.
 */
@Entity
@Table(name = "list_price_item")
public class ListPriceItem extends AuditableDomainObject {
    /**
     * 
     */
    private static final long serialVersionUID = 162970242301910795L;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "product_family_id")
    private ProductFamily productFamily;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "list_price_id")
    private ListPrice listPrice;

    private double price;
    
    
    public ListPriceItem() {
    }

	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

    public ProductFamily getProductFamily() {
        return productFamily;
    }
    public void setProductFamily(ProductFamily productFamily) {
        this.productFamily = productFamily;
    }

    public ListPrice getListPrice() {
        return listPrice;
    }

    public void setListPrice(ListPrice listPrice) {
        this.listPrice = listPrice;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListPriceItem)) return false;
        if (!super.equals(o)) return false;

        ListPriceItem that = (ListPriceItem) o;

        if (Double.compare(that.price, price) != 0) return false;
        if (!listPrice.equals(that.listPrice)) return false;
        if (!productFamily.equals(that.productFamily)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + productFamily.hashCode();
        result = 31 * result + listPrice.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ListPriceItem{" +
                "id=" + getId() +
                ", productFamily=" + productFamily +
                ", listPrice=" + listPrice +
                ", price=" + price +
                '}';
    }
}
