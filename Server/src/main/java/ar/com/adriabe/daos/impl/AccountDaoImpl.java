package ar.com.adriabe.daos.impl;

import ar.com.adriabe.daos.AccountDao;
import ar.com.adriabe.model.Account;
import ar.com.adriabe.model.constant.ACCOUNT_TYPE;
import ar.com.adriabe.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Mildo on 10/3/14.
 */
@Component("accountDao")
public class AccountDaoImpl implements AccountDao {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Account> findByClientIdAndType(Long id, ACCOUNT_TYPE accountType) {
        return accountRepository.findByClientIdAndActivity(id, accountType);

    }

    @Override
    public List<Account> findBySupplierIdAndType(Long id, ACCOUNT_TYPE accountType) {
        return accountRepository.findBySupplierIdAndActivity(id, accountType);
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findOne(id);
    }

    @Override
    public List<Account> findIncomesMovementByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date today = calendar.getTime();
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.add(Calendar.DATE, -1);
        Date yesterday = calendar.getTime();
        return accountRepository.findIncomesMovementByDate(yesterday, today);
    }

    @Override
    public List<Account> findSuppliersAccountsByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date today = calendar.getTime();
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        calendar.add(Calendar.DATE, -1);
        Date yesterday = calendar.getTime();
        return accountRepository.findByDateAndSupplierType(yesterday, today);
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }


}
