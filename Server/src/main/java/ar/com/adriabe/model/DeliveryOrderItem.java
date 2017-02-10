package ar.com.adriabe.model;

import ar.com.adriabe.model.common.DomainObject;

import javax.persistence.*;
import java.util.List;

/**
 * Created by AJMILD1 on 20/03/2015.
 */
@Entity
@Table(name = "delivery_order_items")
public class DeliveryOrderItem extends DomainObject {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_order_item_id")
    @OrderBy("id")
    private List<OrderItemDetail> packages;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<OrderItemDetail> getPackages() {
        return packages;
    }

    public void setPackages(List<OrderItemDetail> packages) {
        this.packages = packages;
    }

    @Transient
    public Double getWeight() {
        Double sum = 0.0;
        for (OrderItemDetail aPackage : packages) {
            sum = sum + aPackage.getWeight();
        }
        return sum;
    }

    @Transient
    public Double getItemAmount() {
        return getWeight() * product.getPrice();
    }
}
