package ar.com.adriabe.model;

import ar.com.adriabe.model.common.Accountable;
import ar.com.adriabe.model.common.AuditableDomainObject;
import ar.com.adriabe.model.constant.IVA_TYPE;

import javax.persistence.*;
import java.util.List;

/**
 * Created by AJMILD1 on 13/06/14.
 */
@Entity
@Table(name="suppliers")
public class Supplier extends AuditableDomainObject implements Accountable {

    private String name;
    private String address;
    private String localidad;
    private String state;
    private String country;
    private Double credit;
    private String postalCode;
    private String nickname;
    private String email;
    @Enumerated(EnumType.ORDINAL)
    private IVA_TYPE IVACondition;
    private String cuit;
    private String phone;
    private String fax;
    private String nextel;
    private Double balanceActivity =0.0;
    private Double balanceBilling =0.0;
    private String rubro;
    private String comments;

    private int supplierType;

    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name="supplier_id")
    @OrderBy("id")
    private List<SupplierContact> contacts;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public IVA_TYPE getIVACondition() {
        return IVACondition;
    }

    public void setIVACondition(IVA_TYPE IVACondition) {
        this.IVACondition = IVACondition;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getNextel() {
        return nextel;
    }

    public void setNextel(String nextel) {
        this.nextel = nextel;
    }

    public Double getBalanceActivity() {
        return balanceActivity;
    }

    public void setBalanceActivity(Double balance) {
        this.balanceActivity = balance;
    }

    public Double getBalanceBilling() {
        return balanceBilling;
    }

    public void setBalanceBilling(Double balanceBilling) {
        this.balanceBilling = balanceBilling;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public List<SupplierContact> getContacts() {
        return contacts;
    }

    public void setContacts(List<SupplierContact> contacts) {
        this.contacts = contacts;
    }

    /**
     * Interface IAccountable Method's
     */

    @Override
    public Double getAccountableBalance() {
        return balanceBilling;
    }

    @Override
    public Double getAccountableActivityBalance() {
        return balanceActivity;
    }

    @Transient
    public String getFullName() {
        if (nickname!=null && !nickname.isEmpty())
            return name + " (" + nickname + ")";

        return name;
    }

    public int getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(int supplierType) {
        this.supplierType = supplierType;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}

