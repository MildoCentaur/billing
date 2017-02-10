package ar.com.adriabe.model;

import ar.com.adriabe.model.common.AuditableDomainObject;
import ar.com.adriabe.model.common.IRegisterAccount;
import ar.com.adriabe.model.constant.ACCOUNT_TYPE;
import ar.com.adriabe.model.constant.DELIVERY_ORDER_STATUS;
import ar.com.adriabe.model.constant.IVA_TYPE;
import ar.com.adriabe.model.constant.SALE_CONDITION;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "delivery_orders")
public class DeliveryOrder extends AuditableDomainObject implements IRegisterAccount {

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_order_id")
    @OrderBy("id")
    private List<DeliveryOrderItem> items;

    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    private Double totalAmount = 0.0;

    @Enumerated(EnumType.STRING)
    private DELIVERY_ORDER_STATUS status;

    private boolean paid = false;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<DeliveryOrderItem> getItems() {
        return items;
    }

    public void setItems(List<DeliveryOrderItem> items) {
        this.items = items;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Transient
    public int getPackagesAmount() {
        int acum = 0;
        for (DeliveryOrderItem item : items) {
            acum = acum + item.getPackages().size();
        }
        return acum;
    }

    @Override
    public boolean isDebitAccountable() {
        return true;
    }

    @Override
    public boolean isCreditAccountable() {
        return false;
    }

    @Override
    public boolean isActivityAccountable() {
        return true;
    }

    @Override
    public ACCOUNT_TYPE getAccountType() {
        return ACCOUNT_TYPE.MOVEMENT;
    }

    @Override
    public String getConceptAccountable() {
        return "Orden de entrega Nº:" + getId();
    }

    @Override
    public Double getAmountAccountable() {
        return totalAmount;
    }

    @Override
    public Date getDateAccountable() {
        return date;
    }

    @Override
    public Double getEntityBalanceAccountable() {
        if (client == null) {
            return 0.0;
        }
        return client.getBalanceActivity();
    }

    @Override
    public Client getClientEntityAccountable() {
        return client;
    }

    @Override
    public Supplier getSupplierEntityAccountable() {
        return null;
    }

    public Bill createBillForPercentage(int percentage, String nextBillANumber, String nextOrderANumber, String nextBillBNumber, String nextOrderBNumber) {
        if (percentage == 0) {
            return null;
        }
        Bill bill = new Bill();
        bill.setClient(client);
        bill.setCuit(client.getCuit());
        bill.setIvaCategory(client.getIVACondition());
        if (client.getIVACondition() == IVA_TYPE.RI) {
            bill.setBillType("A");
            bill.setBillNumber(Long.parseLong(nextBillANumber));
            bill.setOrderNumber(Long.parseLong(nextOrderANumber));
        } else {
            bill.setBillType("B");
            bill.setBillNumber(Long.parseLong(nextBillBNumber));
            bill.setOrderNumber(Long.parseLong(nextOrderBNumber));
        }

        bill.setAddress(client.getAddress());
        bill.setDate(date);
        BillItem billItem;
        Double weight = 0.0;
        double ivaPercent = Double.valueOf(client.getIVACondition().getPercent().replace('%', ' ').trim());
        ivaPercent = ivaPercent / 100;
        int packages = 0;
        double subtotal = 0.0, total = 0.0, iva = 0.0;
        double price;
        for (DeliveryOrderItem item : items) {
            billItem = new BillItem();
            weight = item.getWeight();
            weight = percentage == 100 ? weight : weight / 2;
            billItem.setProductFamily(item.getProduct().getProductFamily());
            billItem.setColor(item.getProduct().getColor());
            billItem.setAmount(weight);
            billItem.setPackages(item.getPackages().size());
            price = item.getProduct().getPrice();
            price = getPriceForClientBill(percentage, bill.getBillType(), price, ivaPercent);
            billItem.setPrice(price);
            billItem.setSubtotal(weight * price);
            billItem.setTax(getTaxForClientBill(billItem.getSubtotal(), bill.getBillType(), ivaPercent));
            billItem.setTotal(getTotalForClientBill(bill.getBillType(), billItem.getSubtotal(), billItem.getTax()));
            subtotal = subtotal + billItem.getSubtotal();
            iva = iva + billItem.getTax();
            total = total + billItem.getTotal();
            packages = packages + billItem.getPackages();
            bill.addItem(billItem);
        }

        bill.setSubTotal(subtotal);
        bill.setIvaTax(iva);
        bill.setTotal(total);
        bill.setPackages(packages);
        bill.setSaleCondition(SALE_CONDITION.Contado);
        bill.setDeliveryOrder(this);

        return bill;
    }

    private Double getTotalForClientBill(String billType, Double subtotal, Double tax) {
        if (billType.compareToIgnoreCase("A") == 0) {
            return subtotal + tax;
        } else {
            return subtotal;
        }

    }

    private Double getTaxForClientBill(Double subtotal, String billType, double ivaPercent) {
        if (billType.compareToIgnoreCase("A") == 0) {
            return subtotal * ivaPercent;
        } else {
            return subtotal - subtotal / (1 + ivaPercent);
        }
    }

    private double getPriceForClientBill(int percentage, String billType, double price, double ivaPercent) {
        if (percentage == 50) {
            if (billType.compareToIgnoreCase("A") == 0) {
                return price / (1 + ivaPercent);
            } else {
                return price;
            }
        } else {
            if (billType.compareToIgnoreCase("A") == 0) {
                return price / (1 + (ivaPercent / 2));
            } else {
                return price / (1 + (ivaPercent / 2)) * (1 + ivaPercent);
            }
        }

    }

    public DELIVERY_ORDER_STATUS getStatus() {
        return status;
    }

    public void setStatus(DELIVERY_ORDER_STATUS status) {
        this.status = status;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isCancelled() {
        return status.compareTo(DELIVERY_ORDER_STATUS.CANCELLED) == 0;
    }
}
