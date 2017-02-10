package ar.com.adriabe.services;

import ar.com.adriabe.daos.DaoException;
import ar.com.adriabe.model.Account;
import ar.com.adriabe.model.constant.ACCOUNTABLE_ENTITY;
import ar.com.adriabe.model.constant.ACCOUNT_TYPE;

import java.util.Date;
import java.util.List;

/**
 * Created by Mildo on 10/17/14.
 */
public interface AccountService {

    public Account findById(Long id);

    public List<Account> findByAccountableIdAndAccountableEntityAndAccountType(Long accountableId, ACCOUNTABLE_ENTITY accountableEntity, ACCOUNT_TYPE accountType) throws DaoException;

    List<Account> findIncomesMovementByDate(Date date);

    List<Account> findSuppliersMovementByDate(Date date);
}
