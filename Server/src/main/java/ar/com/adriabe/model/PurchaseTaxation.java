package ar.com.adriabe.model;


import ar.com.adriabe.model.constant.IVA_TYPE;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class PurchaseTaxation {

    @Temporal(TemporalType.TIMESTAMP)
    private Date issueDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date registeredDate;
    private String ivaCategory;
    private Long billNumber;
    private Long orderNumber;
    private Double total;
    private Double subTotal;
    private Double ivaTaxPercent3;
    private Double ivaTaxPercent10_5;
    private Double ivaTaxPercent21;
    private Double ivaTaxPercent27;
    private Double ivaTaxBBII;
    private String supplierName;

    public PurchaseTaxation(Date issueDate, Date registeredDate, IVA_TYPE ivaType, Long billNumber, Long orderNumber, Double total, Double subTotal, Double ivaTaxPercent3, Double ivaTaxPercent10_5, Double ivaTaxPercent21, Double ivaTaxPercent27, Double ivaTaxBBII, String supplierName) {
        this.issueDate = issueDate;
        this.registeredDate = registeredDate;
        this.ivaCategory = ivaType.getLabel();
        this.billNumber = billNumber;
        this.orderNumber = orderNumber;
        this.total = total;
        this.subTotal = subTotal;
        this.ivaTaxPercent3 = ivaTaxPercent3;
        this.ivaTaxPercent10_5 = ivaTaxPercent10_5;
        this.ivaTaxPercent21 = ivaTaxPercent21;
        this.ivaTaxPercent27 = ivaTaxPercent27;
        this.ivaTaxBBII = ivaTaxBBII;
        this.supplierName = supplierName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Double getIvaTaxBBII() {
        return ivaTaxBBII;
    }

    public void setIvaTaxBBII(Double ivaTaxBBII) {
        this.ivaTaxBBII = ivaTaxBBII;
    }

    public Double getIvaTaxPercent27() {
        return ivaTaxPercent27;
    }

    public void setIvaTaxPercent27(Double ivaTaxPercent27) {
        this.ivaTaxPercent27 = ivaTaxPercent27;
    }

    public Double getIvaTaxPercent21() {
        return ivaTaxPercent21;
    }

    public void setIvaTaxPercent21(Double ivaTaxPercent21) {
        this.ivaTaxPercent21 = ivaTaxPercent21;
    }

    public Double getIvaTaxPercent10_5() {
        return ivaTaxPercent10_5;
    }

    public void setIvaTaxPercent10_5(Double ivaTaxPercent10_5) {
        this.ivaTaxPercent10_5 = ivaTaxPercent10_5;
    }

    public Double getIvaTaxPercent3() {
        return ivaTaxPercent3;
    }

    public void setIvaTaxPercent3(Double ivaTaxPercent3) {
        this.ivaTaxPercent3 = ivaTaxPercent3;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(Long billNumber) {
        this.billNumber = billNumber;
    }

    public String getIvaCategory() {
        return ivaCategory;
    }

    public void setIvaCategory(String ivaCategory) {
        this.ivaCategory = ivaCategory;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

}
