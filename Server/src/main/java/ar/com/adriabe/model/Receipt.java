package ar.com.adriabe.model;

import ar.com.adriabe.model.common.AuditableDomainObject;
import ar.com.adriabe.model.common.IRegisterAccount;
import ar.com.adriabe.model.constant.ACCOUNT_TYPE;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Mildo on 5/19/15.
 */
@Entity
@Table(name = "receipts")
public class Receipt extends AuditableDomainObject implements IRegisterAccount {
    public static final String SU_PAGO_N = "Su pago N²: ";

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private Double value; // total amount
    private String number;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Client client;

    private Double cash;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "receipt_cheques",
            joinColumns = @JoinColumn(name = "payment_id"),
            inverseJoinColumns = @JoinColumn(name = "cheque_id"))
    @OrderBy("id")
    private List<Cheque> cheques;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "receipt_transfers",
            joinColumns = @JoinColumn(name = "payment_id"),
            inverseJoinColumns = @JoinColumn(name = "transfers_id"))
    @OrderBy("id")
    private List<Transfer> transfers;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "receipt_bills",
            joinColumns = @JoinColumn(name = "receipt_id"),
            inverseJoinColumns = @JoinColumn(name = "bill_id"))
    @OrderBy("id")
    private List<Bill> bills;

    public Receipt() {
    }

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
        return false;
    }

    @Override
    public ACCOUNT_TYPE getAccountType() {
        return ACCOUNT_TYPE.ACCOUNT;
    }

    @Override
    public String getConceptAccountable() {
        return SU_PAGO_N + getNumber();

    }

    @Override
    public Double getAmountAccountable() {
        return value;
    }

    @Override
    public Date getDateAccountable() {
        return date;
    }

    @Override
    public Double getEntityBalanceAccountable() {
        return client.getBalanceBilling();
    }

    @Override
    public Client getClientEntityAccountable() {
        return client;
    }

    @Override
    public Supplier getSupplierEntityAccountable() {
        return null;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getCash() {
        return cash;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }

    public void setTransfers(List<Transfer> transfers) {
        this.transfers = transfers;
    }

    public List<Cheque> getCheques() {
        return cheques;
    }

    public void setCheques(List<Cheque> cheques) {
        this.cheques = cheques;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
