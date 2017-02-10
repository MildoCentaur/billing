package ar.com.adriabe.model;

import ar.com.adriabe.model.common.AuditableDomainObject;
import ar.com.adriabe.model.common.IRegisterAccount;
import ar.com.adriabe.model.constant.ACCOUNT_TYPE;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * Created by Mildo on 6/11/14.
 */
@Entity
@Table(name = "supplier_payments")
public class SupplierPayment extends AuditableDomainObject implements IRegisterAccount {

    public static final String SU_PAGO_N = "Comprobante de pago Nº: ";
    public static final String SU_PAGO = "Orden de Pago Nº: ";

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Supplier supplier;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payment payment;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "supplier_payment_bills",
            joinColumns = @JoinColumn(name = "supplier_payment_id"),
            inverseJoinColumns = @JoinColumn(name = "bill_id"))
    @OrderBy("id")
    private List<SupplierBill> bills;


    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<SupplierBill> getBills() {
        return bills;
    }

    public void setBills(List<SupplierBill> bills) {
        this.bills = bills;
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
        return payment.isActivity();
    }

    @Override
    public ACCOUNT_TYPE getAccountType() {
        return ACCOUNT_TYPE.ACCOUNT;
    }

    @Override
    public String getConceptAccountable() {
        if (!payment.isActivity()) {
            return SU_PAGO_N + payment.getNumber();
        }
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
        if (payment.isActivity()) {
            return supplier.getBalanceActivity();
        } else {
            return supplier.getBalanceBilling();
        }
    }

    @Override
    public Client getClientEntityAccountable() {
        return null;
    }

    @Override
    public Supplier getSupplierEntityAccountable() {
        return supplier;
    }
}
