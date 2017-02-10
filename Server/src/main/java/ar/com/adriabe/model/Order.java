package ar.com.adriabe.model;

import ar.com.adriabe.model.common.AuditableDomainObject;
import ar.com.adriabe.model.constant.ORDER_ITEM_DETAIL_STATUS;
import ar.com.adriabe.model.constant.ORDER_STATUS;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonRootName;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@JsonRootName("order")
public class Order extends AuditableDomainObject {
    /**
     *
     */
    private static final long serialVersionUID = 4186764108370092673L;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderedDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date estimatedDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date prepearedDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveredDate;

    private Double totalAmount = 0.0;

    // Cantidad de rollos ordenados y cantidad entregados
    private int totalOrderedRolls;
    private int totalPreparedRolls;


    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @OrderBy("id")
    private List<OrderItem> items;

    @Enumerated(EnumType.STRING)
    private ORDER_STATUS status = ORDER_STATUS.WAITING;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @OrderBy("id")
    private List<DeliveryOrder> deliveries;

    /**
     * Method 'Order'
     */
    public Order() {
        this.setId(0l);
        items = new ArrayList<OrderItem>();
        orderedDate = new Date();
        totalOrderedRolls = 0;
    }

    public Order(Long id) {
        super.setId(id);
        items = new ArrayList<OrderItem>();
        orderedDate = new Date();
    }


    /**
     * Method 'getTotalDeliver'
     *
     * @return int
     */
    @Transient
    public int getTotalDeliveredRolls() {
        int acum = 0;
        for (OrderItem item : items) {
            for (OrderItemDetail detail : item.getPackages()) {
                acum = acum + ((ORDER_ITEM_DETAIL_STATUS.DELIVERED.compareTo(detail.getStatus()) == 0) ? 1 : 0);
            }
        }
        return acum;
    }


    public int getItemListSize() {
        return ((isItemListEmpty())) ? 0 : items.size();
    }

    public boolean isItemListEmpty() {
        return items == null || items.isEmpty();
    }

    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }

    public Date getEstimatedDate() {
        return estimatedDate;
    }

    public void setEstimatedDate(Date estimatedDate) {
        this.estimatedDate = estimatedDate;
    }

    public Date getPrepearedDate() {
        return prepearedDate;
    }

    public void setPrepearedDate(Date prepearedDate) {
        this.prepearedDate = prepearedDate;
    }

    public Date getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(Date deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalOrderedRolls() {
        return totalOrderedRolls;
    }

    public void setTotalOrderedRolls(int totalOrderedRolls) {
        this.totalOrderedRolls = totalOrderedRolls;
    }

    public int getTotalPreparedRolls() {
        return totalPreparedRolls;
    }

    public void setTotalPreparedRolls(int totalPrepearedRolls) {
        this.totalPreparedRolls = totalPrepearedRolls;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    @JsonIgnore
    public ORDER_STATUS getStatus() {
        return status;
    }

    public void setStatus(ORDER_STATUS status) {
        this.status = status;
    }

    @Transient
    public String getStatusLabel() {
        return status.getLabel();
    }

    public List<DeliveryOrder> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<DeliveryOrder> deliveries) {
        this.deliveries = deliveries;
    }

    @Transient
    public String getOrderedDateStr() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(orderedDate);
    }

    @Transient
    public int getPackages() {
        int accum = 0;
        for (OrderItem item : items) {
            accum = accum + item.getRequestedPackages();
        }
        return accum;
    }

    @Transient
    public String getRatio() {
        int requestedPackages = 0;
        int prepearedPackages = 0;
        int accesoryReqPackages;
        int accesoryPrepPackages;
        for (OrderItem item : items) {
            requestedPackages = requestedPackages + item.getRequestedPackages();
            prepearedPackages = prepearedPackages + item.getPrepearedPackages();
            if (item.getAccesories() != null && item.getAccesories().size() > 0) {
                for (OrderItem accesory : item.getAccesories()) {
                    accesoryPrepPackages = accesory.getPrepearedPackages();
                    prepearedPackages = prepearedPackages + accesoryPrepPackages;
                    accesoryReqPackages = accesory.getRequestedPackages();
                    requestedPackages = requestedPackages + accesoryReqPackages;
                }
            }
        }

        if (prepearedPackages != 0 && requestedPackages == 0) {
            requestedPackages = prepearedPackages;
        }


        return (prepearedPackages == 0 && requestedPackages == 0) ? "Acc." : (prepearedPackages + "/" + requestedPackages);
    }


    @Transient
    public void recalculateValues() {
        Double accum = 0d;
        int requested = 0, prepeared = 0;
        for (OrderItem item : items) {
            accum = accum + item.getWeight() * item.getPrice();
            requested = item.getRequestedPackages() + requested;
            prepeared = item.getPrepearedPackages() + prepeared;
        }
        totalAmount = accum;
        totalOrderedRolls = requested;
        totalPreparedRolls = prepeared;
    }

    @Transient
    public boolean isFullyDelivered() {
        boolean result = true;
        for (OrderItem item : items) {
            result = result && item.isFullyDelivered();
        }
        return result;
    }

    public List<DeliveryOrder> getUncancelledDeliveries() {
        List<DeliveryOrder> uncancelledDeliveries = new ArrayList<DeliveryOrder>();
        for (DeliveryOrder delivery : deliveries) {
            if (!delivery.isCancelled()) {
                uncancelledDeliveries.add(delivery);
            }
        }
        return uncancelledDeliveries;
    }

    public void addDeliveryOrder(DeliveryOrder deliveryOrder) {
        if (deliveries == null) {
            deliveries = new ArrayList<DeliveryOrder>();
        }
        deliveries.add(deliveryOrder);
    }
}
