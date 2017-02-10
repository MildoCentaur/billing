/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.billing.workstation.model;

/**
 *
 * @author Mildo
 */
public class OrderItemDetail {
    
    private Double weight;
    private Long id;
    private String barcode;
    private String status;

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
