package ar.com.adriabe.services;

import ar.com.adriabe.model.BankAccount;

import java.util.List;

/**
 * Created by Mildo on 5/10/15.
 */
public interface BankAccountService {

    List<BankAccount> findAll();

    BankAccount findById(Long accountId);
}
