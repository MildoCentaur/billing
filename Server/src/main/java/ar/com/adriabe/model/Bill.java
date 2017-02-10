/**
 *
 */
package ar.com.adriabe.model;

import ar.com.adriabe.model.common.AuditableDomainObject;
import ar.com.adriabe.model.common.IRegisterAccount;
import ar.com.adriabe.model.constant.ACCOUNT_TYPE;
import ar.com.adriabe.model.constant.IVA_TYPE;
import ar.com.adriabe.model.constant.SALE_CONDITION;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author Mildo
 */
@Entity
@Table(name = "bills")
public class Bill extends AuditableDomainObject implements IRegisterAccount {

    private static final long serialVersionUID = 283722691108081176L;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;

    private Double ivaTax = 0.00;
    private IVA_TYPE ivaCategory;
    private Integer packages = 0;
    private Long billNumber = 0l;
    private Long orderNumber = 0l;
    private boolean cancelled = false;
    private boolean payed = false;
    private SALE_CONDITION saleCondition = SALE_CONDITION.Contado;
    private Double total = 0.00;
    private Double subTotal = 0.00;
    private String address = "";
    private String cuit;
    private String billType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id")
    @OrderBy("id")
    private List<BillItem> items;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private DeliveryOrder deliveryOrder;

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * @return the total
     */
    public Double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * @return the subTotal
     */
    public Double getSubTotal() {
        return subTotal;
    }

    /**
     * @param subTotal the subTotal to set
     */
    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the prices
     */

    public List<BillItem> getItems() {
        return items;
    }

    /**
     * @param items the prices to set
     */
    public void setItems(List<BillItem> items) {
        this.items = items;
    }

    public void addItem(BillItem item) {
        if (this.items == null) {
            this.items = new ArrayList<BillItem>();
        }
        this.items.add(item);
    }

    /**
     * @return the ivaTax
     */
    public Double getIvaTax() {
        return ivaTax;
    }

    /**
     * @param ivaTax the ivaTax to set
     */
    public void setIvaTax(Double ivaTax) {
        this.ivaTax = ivaTax;
    }

    public Integer getPackages() {
        return this.packages;
    }

    public void setPackages(Integer packages) {
        this.packages = packages;
    }

    /**
     * @return the billNumber
     */
    public Long getBillNumber() {
        return billNumber;
    }

    /**
     * @param billNumber the billNumber to set
     */
    public void setBillNumber(Long billNumber) {
        this.billNumber = billNumber;
    }

    /**
     * @return the orderNumber
     */
    public Long getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @return the cancelled
     */
    public boolean isCancelled() {
        return cancelled;
    }

    @JsonIgnore
    public String getCancelled() {
        return (cancelled) ? "SI" : "-";
    }

    /**
     * @param cancelled the cancelled to set
     */
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public SALE_CONDITION getSaleCondition() {
        return saleCondition;
    }

    public void setSaleCondition(SALE_CONDITION sale) {
        this.saleCondition = sale;
    }

    public boolean getPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    /**
     * Delegate method's
     */

    @Transient
    public String getClientFullname() {
        if (client == null) {
            return null;
        }
        return client.getFullName();
    }

    public String getBillType() {
        if (IVA_TYPE.RI.compareTo(ivaCategory) == 0) {
            return "A";
        }
        return "B";
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    @Transient
    public Long getClientId() {
        if (client == null) {
            return null;
        }
        return client.getId();
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    @Transient
    public int getItemLength() {
        return (items == null) ? 0 : items.size();
    }

    /**
     * Implements Interface IRegisterAccount method's
     */

    @Override
    @Transient
    public boolean isDebitAccountable() {
        return true;
    }

    @Override
    @Transient
    public boolean isCreditAccountable() {
        return false;
    }

    @Override
    @Transient
    public boolean isActivityAccountable() {
        return false;
    }

    @Override
    public ACCOUNT_TYPE getAccountType() {
        return ACCOUNT_TYPE.ACCOUNT;
    }

    @Override
    @Transient
    public String getConceptAccountable() {
        return "Factura Nº: " + billNumber;
    }

    @Override
    @Transient
    public Double getAmountAccountable() {
        return total;
    }

    @Override
    @Transient
    public Date getDateAccountable() {
        return date;
    }

    @Override
    @Transient
    public Double getEntityBalanceAccountable() {
        if (client == null) {
            return 0.0;
        }
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

    public IVA_TYPE getIvaCategory() {
        return ivaCategory;
    }

    public void setIvaCategory(IVA_TYPE ivaCategory) {
        this.ivaCategory = ivaCategory;
    }

    public DeliveryOrder getDeliveryOrder() {
        return deliveryOrder;
    }

    public void setDeliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }
}
