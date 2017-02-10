package ar.com.adriabe.services.impl;

import ar.com.adriabe.daos.BankDao;
import ar.com.adriabe.model.Bank;
import ar.com.adriabe.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mildo on 5/16/15.
 */
@Service
public class BankServiceImpl implements BankService {

    @Autowired
    BankDao bankDao;

    @Override
    public List<Bank> findAllBanks(String query) {

        return bankDao.findAll(query);
    }

    @Override
    public Bank findById(Long id) {
        return bankDao.findById(id);
    }
}
