package ar.com.adriabe.postprocessors;

import ar.com.adriabe.components.AuditorUserLocator;
import ar.com.adriabe.daos.AccountDao;
import ar.com.adriabe.generic.KendoExecutionException;
import ar.com.adriabe.model.Account;
import ar.com.adriabe.model.User;
import ar.com.adriabe.model.common.IRegisterAccount;
import org.joda.time.DateTime;

/**
 * Created by Mildo on 9/27/15.
 */

public abstract class AbstractAccountEntryRegistration {

    protected static final double ZERO_VALUE = 0.0;
    AccountDao accountDao;
    private AuditorUserLocator auditorUserLocator;

    public AbstractAccountEntryRegistration(AccountDao accountDao, AuditorUserLocator auditorUserLocator) {
        this.accountDao = accountDao;
        this.auditorUserLocator = auditorUserLocator;
    }

    public Account generateAccount(IRegisterAccount registerAccount) throws KendoExecutionException {
        Account account = new Account();


        setAuditableData(account);
        setDescriptiveAccountableData(account, registerAccount);
        account.setAccountType(registerAccount.getAccountType());
        setDocument(account);
        setAccountableData(account);
        accountDao.save(account);
        return account;
    }

    protected abstract void setAccountableData(Account account);

    protected abstract void setDocument(Account account);

    protected void setAuditableData(Account account) {
        User user = auditorUserLocator.locateLoggedUser();
        account.setCreatedBy(user);
        account.setCreatedDate(new DateTime());
        account.setLastModifiedBy(user);
        account.setLastModifiedDate(new DateTime());
    }

    protected void setDescriptiveAccountableData(Account account, IRegisterAccount registerAccount) {
        account.setClient(registerAccount.getClientEntityAccountable());
        account.setDate(registerAccount.getDateAccountable());
        account.setClient(registerAccount.getClientEntityAccountable());
        account.setSupplier(registerAccount.getSupplierEntityAccountable());
        account.setConcept(registerAccount.getConceptAccountable());
    }


}
