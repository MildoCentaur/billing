package ar.com.adriabe.model;

import ar.com.adriabe.model.common.AuditableDomainObject;
import ar.com.adriabe.model.constant.TRANSFER_TYPE;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by AJMILD1 on 13/06/14.
 */
@Entity
@Table(name = "transfers")
public class Transfer extends AuditableDomainObject{
    @Temporal(TemporalType.DATE)
    private Date dateTransferred;
    @Temporal(TemporalType.DATE)
    private Date dateRecibed;
    private String sourceAccount;
    @ManyToOne(fetch = FetchType.LAZY)
    private Bank sourceBank;
    private String destinationAccount;
    @ManyToOne  (fetch = FetchType.LAZY)
    private Bank destinationBank;
    @Enumerated(EnumType.ORDINAL)
    private TRANSFER_TYPE transferType;
    private Double value;

    public Date getDateTransferred() {
        return dateTransferred;
    }

    public void setDateTransferred(Date dateTransferred) {
        this.dateTransferred = dateTransferred;
    }

    public Date getDateRecibed() {
        return dateRecibed;
    }

    public void setDateRecibed(Date dateRecibed) {
        this.dateRecibed = dateRecibed;
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Bank getSourceBank() {
        return sourceBank;
    }

    public void setSourceBank(Bank sourceBank) {
        this.sourceBank = sourceBank;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public Bank getDestinationBank() {
        return destinationBank;
    }

    public void setDestinationBank(Bank destinationBank) {
        this.destinationBank = destinationBank;
    }

    public TRANSFER_TYPE getTransferType() {
        return transferType;
    }

    public void setTransferType(TRANSFER_TYPE transferType) {
        this.transferType = transferType;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
