package ar.com.adriabe.model.common;

import ar.com.adriabe.model.Client;
import ar.com.adriabe.model.Supplier;
import ar.com.adriabe.model.constant.ACCOUNT_TYPE;

import javax.persistence.Transient;
import java.util.Date;

/**
 * Created by Mildo on 6/21/14.
 */
public interface IRegisterAccount {

    @Transient
    public boolean isDebitAccountable();
    @Transient
    public boolean isCreditAccountable();
    @Transient
    public boolean isActivityAccountable();

    @Transient
    public ACCOUNT_TYPE getAccountType();

    @Transient
    public String getConceptAccountable();
    @Transient
    public Double getAmountAccountable();
    @Transient
    public Date getDateAccountable();
    @Transient
    public Double getEntityBalanceAccountable();
    @Transient
    public Client getClientEntityAccountable();
    @Transient
    public Supplier getSupplierEntityAccountable();
}
