package ar.com.adriabe.model;

import ar.com.adriabe.model.common.DomainObject;
import ar.com.adriabe.model.constant.ORDER_ITEM_DETAIL_STATUS;
import ar.com.adriabe.model.constant.ORDER_ITEM_STATUS;
import org.apache.commons.collections.CollectionUtils;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_item")
@JsonRootName("OrderItem")
public class OrderItem extends DomainObject {

    private static final long serialVersionUID = -5076679436655289532L;

    private double price;

    private double quantity;

    @Enumerated(EnumType.STRING)
    private ORDER_ITEM_STATUS status = ORDER_ITEM_STATUS.ORDERED;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_item_id")
    @OrderBy("id")
    private List<OrderItemDetail> packages;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> accesories;


    private boolean mainItem;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = true, nullable = false, updatable = true)
    private Product product;

    /**
     * Method 'OrderDetalle'
     */
    public OrderItem() {
        accesories = new ArrayList<OrderItem>();
        packages = new ArrayList<OrderItemDetail>();

    }

    public OrderItem(Long id) {
        setId(id);
        accesories = new ArrayList<OrderItem>();
        packages = new ArrayList<OrderItemDetail>();
    }


    public void setId(Long id) {
        super.setId(id);
    }


    @Transient
    @JsonIgnore
    public double getWeight() {
        double rta = 0;
        if (packages == null || packages.size() == 0) {
            return 0;
        }
        for (OrderItemDetail oid : packages) {
            rta = rta + oid.getWeight();
        }
        return rta;
    }

    @Transient
    @JsonIgnore
    public boolean add(OrderItemDetail orderItemDetail) {
        return packages.add(orderItemDetail);
    }

    @Transient
    @JsonIgnore
    public OrderItemDetail getPackage(int index) {
        return packages.get(index);
    }

    @Transient
    public boolean hasPackages() {
        return packages.isEmpty();
    }

    @Transient
    public int getPrepearedPackages() {
        return packages.size();
    }

    /**
     * @return the deliveredRolls
     */
    @Transient
    public int getDeliveredRolls() {
        int acum = 0;
        if (packages == null) {
            return 0;
        }
        for (OrderItemDetail detail : packages) {
            if (ORDER_ITEM_DETAIL_STATUS.DELIVERED.compareTo(detail.getStatus()) == 0) {
                acum++;
            }
        }
        return acum;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<OrderItem> getAccesories() {
        return accesories;
    }

    public void setAccesories(List<OrderItem> accesories) {
        this.accesories = accesories;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public int getRequestedPackages() {
        if (product.isMainProduct()) {
            return (int) quantity;
        }

        return packages.size() > 1 ? packages.size() : 1;
    }


    @Transient
    public Double getItemAmount() {

        return getWeight() * price;
    }

    @Transient
    public String getRatio() {

        int requestedPackages = getRequestedPackages();
        int prep = getPrepearedPackages();

        return (prep == 0 && requestedPackages == 0) ? "Acc." : (prep + "/" + requestedPackages);
    }

    @Transient
    public boolean isPuno() {
        return product.getFabric().isPuno();
    }


    public boolean isCuello() {
        return product.getFabric().isCuello();
    }

    public boolean isTira() {
        return product.getFabric().isTira();
    }

    public ORDER_ITEM_STATUS getStatus() {
        return status;
    }

    public void setStatus(ORDER_ITEM_STATUS status) {
        this.status = status;
    }

    public List<OrderItemDetail> getPackages() {
        return packages;
    }

    public void setPackages(List<OrderItemDetail> packages) {
        this.packages = packages;
    }

    @JsonProperty
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Transient
    public boolean isMainProductInItem() {
        return product.isMainProduct();
    }


    public boolean isMainItem() {
        return mainItem;
    }

    public void setMainItem(boolean mainItem) {
        this.mainItem = mainItem;
    }

    @Transient
    public boolean isFullyDelivered() {

        return status.equals(ORDER_ITEM_STATUS.DELIVERED);

    }

    @Transient
    public OrderItem getCuelloAccesoryItem() {
        if (CollectionUtils.isEmpty(accesories)) {
            return null;
        }
        for (OrderItem accesory : accesories) {
            if (accesory.isCuello()) {
                return accesory;
            }
        }
        return null;
    }

    @Transient
    public OrderItem getTiraAccesoryItem() {
        if (CollectionUtils.isEmpty(accesories)) {
            return null;
        }
        for (OrderItem accesory : accesories) {
            if (accesory.isTira()) {
                return accesory;
            }
        }
        return null;
    }

    @Transient
    public OrderItem getPunoAccesoryItem() {
        if (CollectionUtils.isEmpty(accesories)) {
            return null;
        }
        for (OrderItem accesory : accesories) {
            if (accesory.isPuno()) {
                return accesory;
            }
        }
        return null;
    }
}
