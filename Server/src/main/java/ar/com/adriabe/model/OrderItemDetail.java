/**
 *
 */
package ar.com.adriabe.model;

import ar.com.adriabe.model.common.DomainObject;
import ar.com.adriabe.model.constant.ORDER_ITEM_DETAIL_STATUS;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;


/**
 * @author Mildo
 */
@Entity
@Table(name = "order_item_detail")
public class OrderItemDetail extends DomainObject {

    /**
     *
     */
    private static final long serialVersionUID = -5073820592483427110L;

    @Enumerated(EnumType.STRING)
    private ORDER_ITEM_DETAIL_STATUS status = ORDER_ITEM_DETAIL_STATUS.PREPEARED;

    private double weight;
    private String barcode;

    public OrderItemDetail() {

    }

    public void setId(Long id) {
        super.setId(id);
    }

    public ORDER_ITEM_DETAIL_STATUS getStatus() {
        return status;
    }

    public void setStatus(ORDER_ITEM_DETAIL_STATUS status) {
        this.status = status;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
