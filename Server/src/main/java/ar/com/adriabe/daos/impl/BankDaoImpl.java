package ar.com.adriabe.daos.impl;

import ar.com.adriabe.daos.BankDao;
import ar.com.adriabe.model.Bank;
import ar.com.adriabe.repositories.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Mildo on 5/16/15.
 */
@Component
public class BankDaoImpl implements BankDao {
    @Autowired
    BankRepository bankRepository;


    @Override
    public List<Bank> findAll(String query) {
        if (query == null || query.isEmpty()) {
            return bankRepository.findAll();
        }

        query = "%" + query + "%";
        return bankRepository.findAllLike(query);

    }

    @Override
    public Bank findById(Long id) {
        return bankRepository.findOne(id);
    }
}
