package ar.com.adriabe.model;

import ar.com.adriabe.model.common.AuditableDomainObject;
import ar.com.adriabe.model.common.IRegisterAccount;
import ar.com.adriabe.model.constant.ACCOUNT_TYPE;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mildo on 6/11/14.
 */
@Entity
@Table(name = "client_payments")
public class ClientPayment extends AuditableDomainObject implements IRegisterAccount {

    public static final String SU_PAGO = "Su pago: ";

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Client client;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payment payment;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "client_payment_delivery_orders",
            joinColumns = @JoinColumn(name = "client_payment_id"),
            inverseJoinColumns = @JoinColumn(name = "delivery_order_id"))
    @OrderBy("id")
    private List<DeliveryOrder> deliveryOrders;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "client_payment_bills",
            joinColumns = @JoinColumn(name = "client_payment_id"),
            inverseJoinColumns = @JoinColumn(name = "bill_id"))
    @OrderBy("id")
    private List<Bill> bills;

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    /**
     * IRegisterAccount Method's
     */
    @Override
    public boolean isDebitAccountable() {
        return false;
    }

    @Override
    public boolean isCreditAccountable() {
        return true;
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
        return SU_PAGO;
    }

    @Override
    public Double getAmountAccountable() {
        return payment.getValue();

    }

    @Override
    public Date getDateAccountable() {
        return payment.getDate();
    }

    @Override
    public Double getEntityBalanceAccountable() {
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


    public List<DeliveryOrder> getDeliveryOrders() {
        return deliveryOrders;
    }

    public void setDeliveryOrders(List<DeliveryOrder> deliveryOrders) {
        this.deliveryOrders = deliveryOrders;
    }

    public void addDeliveryOrders(DeliveryOrder deliveryOrder) {
        if (this.deliveryOrders == null) {
            this.deliveryOrders = new ArrayList<DeliveryOrder>();
        }
        this.deliveryOrders.add(deliveryOrder);
    }

    public void addBill(Bill bill) {
        if (this.bills == null) {
            this.bills = new ArrayList<Bill>();
        }
        this.bills.add(bill);
    }

    public List<Cheque> getCheques() {
        return getPayment().getCheques();
    }

    public List<Transfer> getTransfers() {
        return getPayment().getTransfers();
    }

    public Double getCash() {
        return getPayment().getCash();
    }

    public Double getValue() {
        return getPayment().getValue();
    }

}
