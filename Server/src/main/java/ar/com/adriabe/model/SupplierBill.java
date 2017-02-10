package ar.com.adriabe.model;

import ar.com.adriabe.model.common.AuditableDomainObject;
import ar.com.adriabe.model.common.IRegisterAccount;
import ar.com.adriabe.model.constant.ACCOUNT_TYPE;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Mildo on 6/21/14.
 */
@Entity
@Table(name = "supplier_bills")
public class SupplierBill extends AuditableDomainObject implements IRegisterAccount {

    @Temporal(TemporalType.TIMESTAMP)
    private Date issueDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date registeredDate;

    private Double ivaTaxPercent3;
    private Double ivaTaxPercent10_5;
    private Double ivaTaxPercent21;
    private Double ivaTaxPercent27;
    private Double ivaTaxBBII;
    private Integer packages;
    private Long billNumber;
    private Long orderNumber;
    private Double total;
    private Double subTotal;

    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Supplier supplier;


    public Double getIvaTaxPercent3() {
        return ivaTaxPercent3;
    }

    public void setIvaTaxPercent3(Double ivaTax) {
        this.ivaTaxPercent3 = ivaTax;
    }

    public Integer getPackages() {
        return packages;
    }

    public void setPackages(Integer packages) {
        this.packages = packages;
    }

    public Long getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(Long billNumber) {
        this.billNumber = billNumber;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Interface IRegisteAccount interface Method's
     */

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
        return false;
    }

    @Override
    public ACCOUNT_TYPE getAccountType() {
        return ACCOUNT_TYPE.ACCOUNT;
    }

    @Override
    public String getConceptAccountable() {
        return "Factura N�:" + billNumber;
    }

    @Override
    public Double getAmountAccountable() {
        return total;
    }

    @Override
    public Date getDateAccountable() {
        return registeredDate;
    }

    @Override
    public Double getEntityBalanceAccountable() {
        return supplier.getBalanceBilling();
    }

    @Override
    public Client getClientEntityAccountable() {
        return null;
    }

    @Override
    public Supplier getSupplierEntityAccountable() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public Double getIvaTaxPercent10_5() {
        return ivaTaxPercent10_5;
    }

    public void setIvaTaxPercent10_5(Double ivaTaxPercent10_5) {
        this.ivaTaxPercent10_5 = ivaTaxPercent10_5;
    }

    public Double getIvaTaxPercent21() {
        return ivaTaxPercent21;
    }

    public void setIvaTaxPercent21(Double ivaTaxPercent21) {
        this.ivaTaxPercent21 = ivaTaxPercent21;
    }

    public Double getIvaTaxPercent27() {
        return ivaTaxPercent27;
    }

    public void setIvaTaxPercent27(Double ivaTaxPercent27) {
        this.ivaTaxPercent27 = ivaTaxPercent27;
    }

    public Double getIvaTaxBBII() {
        return ivaTaxBBII;
    }

    public void setIvaTaxBBII(Double ivaTaxBBII) {
        this.ivaTaxBBII = ivaTaxBBII;
    }
}
