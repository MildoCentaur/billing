package ar.com.adriabe.web.model.json;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mildo on 2/28/15.
 */
public class OrderItemJSON {

    private static final double EPSILON = 0.001;
    private Long id;
    private ProductJSON product;
    private double quantity;
    private List<OrderItemDetailJSON> packages;

    private List<OrderItemJSON> accesories = new ArrayList<OrderItemJSON>();

    private String status;

    public int getOrderItemDetailJSONsSize() {
        return packages.size();
    }

    public boolean isOrderItemDetailJSONsEmpty() {
        return packages.isEmpty();
    }

    public boolean addOrderItemDetailJSONs(OrderItemDetailJSON e) {
        return packages.add(e);
    }

    public boolean removeOrderItemDetailJSONs(OrderItemDetailJSON o) {
        return packages.remove(o);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductJSON getProduct() {
        return product;
    }

    public void setProduct(ProductJSON product) {
        this.product = product;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public int getQuantityInteger() {
        return (int) quantity;
    }

    public List<OrderItemDetailJSON> getPackages() {
        return packages;
    }

    public void setPackages(List<OrderItemDetailJSON> packages) {
        this.packages = packages;
    }

    public List<OrderItemJSON> getAccesories() {
        return accesories;
    }

    public void setAccesories(List<OrderItemJSON> accesories) {
        this.accesories = accesories;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getRequestedPuno() {
        String result = "-";
        if (accesories != null && !accesories.isEmpty()) {
            for (OrderItemJSON accesory : accesories) {
                if (accesory.getProduct().isPuno() && accesory.getQuantity() > 0) {
                    result = accesory.getQuantity() + " Kg. " + ((accesory.getProduct().isStripe()) ? "R" : "");
                }
            }
        }
        return result;
    }


    public String getRequestedCuellos() {
        String result = "-";
        if (accesories != null && !accesories.isEmpty()) {
            for (OrderItemJSON accesory : accesories) {
                if (accesory.getProduct().isCuello() && accesory.getQuantity() > 0) {
                    result = accesory.getQuantityInteger() + " Ud. " + ((accesory.getProduct().isStripe()) ? "R" : "");
                }
            }
        }
        return result;
    }


    public boolean isDeleted() {
        if (quantity < EPSILON || product.getFabricId() == 0 && product.getColorId() == 0) {
            return true;
        }
        return false;
    }
}
