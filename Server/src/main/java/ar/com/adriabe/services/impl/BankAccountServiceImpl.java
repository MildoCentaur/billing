package ar.com.adriabe.services.impl;

import ar.com.adriabe.model.BankAccount;
import ar.com.adriabe.repositories.BankAccountRepository;
import ar.com.adriabe.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mildo on 5/10/15.
 */
@Service
public class BankAccountServiceImpl implements BankAccountService {
    @Autowired
    BankAccountRepository bankAccountRepository;

    @Override
    public List<BankAccount> findAll() {
        return bankAccountRepository.findAll();
    }

    @Override
    public BankAccount findById(Long accountId) {
        return bankAccountRepository.getOne(accountId);
    }
}
