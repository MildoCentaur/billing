package ar.com.adriabe.accounting;

import ar.com.adriabe.daos.AccountDao;
import ar.com.adriabe.model.*;
import ar.com.adriabe.model.common.IRegisterAccount;
import ar.com.adriabe.model.common.annotation.IAccountableOperation;
import ar.com.adriabe.model.constant.ACCOUNT_TYPE;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by AJMILD1 on 03/02/2015.
 */
@Aspect
public class ServiceAccountAware {


    public static final double ZERO_VALUE = 0.0;
    @Autowired
    AccountDao accountDao;

    private Account account;

    @Before(value = "@annotation(ar.com.adriabe.model.common.annotation.IAccountableOperation) && @annotation(iAccountableOperation)")
    public void logBefore(JoinPoint joinPoint, IAccountableOperation iAccountableOperation) throws Exception {

        account = new Account();

        setAuditableData();
        IRegisterAccount registerAccount = getiRegisterAccountElement(joinPoint);

        if (registerAccount.isActivityAccountable()) {
            account.setAccountType(ACCOUNT_TYPE.MOVEMENT);
        } else {
            account.setAccountType(ACCOUNT_TYPE.ACCOUNT);
        }

        account.setDebit(ZERO_VALUE);
        if (registerAccount.isDebitAccountable()) {
            account.setDebit(registerAccount.getAmountAccountable());
        }

        account.setCredit(ZERO_VALUE);
        if (registerAccount.isCreditAccountable()) {
            account.setCredit(registerAccount.getAmountAccountable());
        }


        account.setDate(registerAccount.getDateAccountable());
        account.setClient(registerAccount.getClientEntityAccountable());
        account.setSupplier(registerAccount.getSupplierEntityAccountable());

        switch (iAccountableOperation.type()) {
            case BILL:
                account.setBill((Bill) registerAccount);
                break;
            case CREDIT_NOTE:
                break;
            case DEBIT_NOTE:
                break;
            case ORDER_DELIVERY:
                account.setDeliveryOrder((DeliveryOrder) registerAccount);
                break;
            case CLIENT_PAYMENT:
                account.setPayment(((ClientPayment) registerAccount).getPayment());
                break;
            case RECEIPT:
                account.setReceipt((Receipt) registerAccount);
                break;
            case SUPPLIER_PAYMENT:
                account.setPayment(((SupplierPayment) registerAccount).getPayment());
                break;
        }
    }

    private void setAuditableData() {
        User user = getAuditableUser();
        account.setCreatedBy(user);
        account.setCreatedDate(new DateTime());
        account.setLastModifiedBy(user);
        account.setLastModifiedDate(new DateTime());
    }

    private IRegisterAccount getiRegisterAccountElement(JoinPoint joinPoint) throws Exception {
        IRegisterAccount registerAccount = null;
        if (joinPoint.getArgs().length > 2) {
            throw new Exception("Los métodos para registrar entradas en la cuenta solo deben tener un objeto IRegisterAccount y un boolean para print.");
        }
        for (Object obj : joinPoint.getArgs()) {
            if (obj != null && obj instanceof IRegisterAccount) {
                registerAccount = (IRegisterAccount) obj;
            }
        }
        return registerAccount;
    }


    @AfterReturning(returning = "result", pointcut = "@annotation(ar.com.adriabe.model.common.annotation.IAccountableOperation)")
    public void logAfter(JoinPoint joinPoint, Object result) throws Exception {
        IRegisterAccount registerAccount = getiRegisterAccountElement(joinPoint);
        account.setBalance(registerAccount.getEntityBalanceAccountable());
        account.setConcept(registerAccount.getConceptAccountable());
        accountDao.save(account);

    }

    private User getAuditableUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        String username = "SYSTEM";
        if (auth != null && auth.isAuthenticated()) {
            if (auth.getPrincipal() instanceof User) {
                user = (User) auth.getPrincipal();
            }
        }
        return user;
    }

}