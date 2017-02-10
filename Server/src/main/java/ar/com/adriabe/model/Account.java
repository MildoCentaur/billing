package ar.com.adriabe.model;

import ar.com.adriabe.model.common.AuditableDomainObject;
import ar.com.adriabe.model.constant.ACCOUNT_TYPE;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author Mildo
 */
@Entity
@Table(name = "account")
public class Account extends AuditableDomainObject {

    /**
     *
     */
    private static final long serialVersionUID = -5783969729437179278L;

    private String concept;
    private Double credit;
    private Double debit;
    private Double balance;

    @Enumerated(EnumType.STRING)
    private ACCOUNT_TYPE accountType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Supplier supplier;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = true)
    private DeliveryOrder order;

//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="note_id", referencedColumnName="id",nullable=true)
//	private NoteDocument note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id", referencedColumnName = "id", nullable = true)
    private Bill bill;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", referencedColumnName = "id", nullable = true)
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "receipt_id", referencedColumnName = "id", nullable = true)
    private Receipt receipt;


    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Double getDebit() {
        return debit;
    }

    public void setDebit(Double debit) {
        this.debit = debit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Transient
    public String getAccountabletDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        if (date == null) {
            return "";
        }
        return format.format(date);
    }

//    @Transient
//    public String getCashierConcept(){
//        String result="";
//        if(client!=null){
//            result = client.getName();
//        }
//        if(supplier!=null){
//            result = supplier.getName();
//        }
//        return result;
//    }

    @Transient
    public String getAccountabletDateTime() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        if (date == null) {
            return "";
        }
        return format.format(date);
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public ACCOUNT_TYPE getAccountType() {
        return accountType;
    }

    public void setAccountType(ACCOUNT_TYPE accountType) {
        this.accountType = accountType;
    }


    //Relations
    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public DeliveryOrder getDeliveryOrder() {
        return this.order;
    }

    public void setDeliveryOrder(DeliveryOrder order) {
        this.order = order;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }
}
