package ar.com.adriabe.services.impl;

import ar.com.adriabe.daos.AccountDao;
import ar.com.adriabe.daos.DaoException;
import ar.com.adriabe.model.Account;
import ar.com.adriabe.model.constant.ACCOUNTABLE_ENTITY;
import ar.com.adriabe.model.constant.ACCOUNT_TYPE;
import ar.com.adriabe.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Mildo on 10/17/14.
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDao accountDao;


    @Override
    public Account findById(Long id) {
        return accountDao.findById(id);
    }

    @Override
    public List<Account> findByAccountableIdAndAccountableEntityAndAccountType(Long accountableId, ACCOUNTABLE_ENTITY accountableEntity, ACCOUNT_TYPE accountType) throws DaoException {

        if (ACCOUNTABLE_ENTITY.CLIENT.compareTo(accountableEntity) == 0) {
            return accountDao.findByClientIdAndType(accountableId, accountType);
        } else if (ACCOUNTABLE_ENTITY.SUPPLIER.compareTo(accountableEntity) == 0) {
            return accountDao.findBySupplierIdAndType(accountableId, accountType);
        }

        throw new DaoException("Entidad invalida.");
    }

    @Override
    public List<Account> findIncomesMovementByDate(Date date) {
        return accountDao.findIncomesMovementByDate(date);

    }

    @Override
    public List<Account> findSuppliersMovementByDate(Date date) {
        return accountDao.findSuppliersAccountsByDate(date);
    }
}
