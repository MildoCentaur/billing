/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.billing.workstation.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mildo
 */
public class OrderItem {
    private Long id;
    private Product product;
    private double quantity;
    private List<OrderItemDetail> packages;
    
    private List<OrderItem> accesories;
    
    private String status;

    public OrderItem(long id) {
        this.id = id;
    }

    public int getOrderItemDetailsSize() {
        return packages.size();
    }

    public boolean isOrderItemDetailsEmpty() {
        return packages.isEmpty();
    }

    public boolean addOrderItemDetails(OrderItemDetail e) {
        return packages.add(e);
    }

    public boolean removeOrderItemDetails(OrderItemDetail o) {
        return packages.remove(o);
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getQuantity() {
        return quantity;
    }
    
    public int getPackagesQuantity() {
        if(product.isAccesory()){
            return 1;
        }
        return (int)quantity;
    }
    
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public List<OrderItemDetail> getPackages() {
        return packages;
    }

    public void setPackages(List<OrderItemDetail> packages) {
        this.packages = packages;
    }

    public List<OrderItem> getAccesories() {
        return accesories;
    }

    public void setAccesories(List<OrderItem> accesories) {
        this.accesories = accesories;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addOrderItemDetail(double weight, String barcode) {
        if(packages == null){
            packages = new ArrayList<OrderItemDetail>();
        }
        OrderItemDetail item = new OrderItemDetail();
        item.setBarcode(barcode);
        item.setStatus("Preparado");
        item.setWeight(weight);
        packages.add(item);
    }

    public void removeOrderItemDetail(Double weight, String barcode) {
        if(packages != null){
            OrderItemDetail remove = null;
        
            for (OrderItemDetail oid : packages){
                if(oid.getBarcode().compareTo(barcode)==0){
                    remove = oid;
                }
            }
            packages.remove(remove);
        }
    }
    
    
    
}
