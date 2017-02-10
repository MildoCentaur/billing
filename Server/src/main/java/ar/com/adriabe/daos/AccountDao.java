package ar.com.adriabe.daos;

import ar.com.adriabe.model.Account;
import ar.com.adriabe.model.constant.ACCOUNT_TYPE;

import java.util.Date;
import java.util.List;

/**
 * Created by Mildo on 10/3/14.
 */
public interface AccountDao {


    List<Account> findByClientIdAndType(Long id, ACCOUNT_TYPE movement);

    List<Account> findBySupplierIdAndType(Long id, ACCOUNT_TYPE movement);

    Account findById(Long id);

    List<Account> findIncomesMovementByDate(Date date);

    List<Account> findSuppliersAccountsByDate(Date date);

    void save(Account account);
}
