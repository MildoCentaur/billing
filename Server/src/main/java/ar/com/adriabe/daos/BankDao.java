package ar.com.adriabe.daos;

import ar.com.adriabe.model.Bank;

import java.util.List;


public interface BankDao {
    List<Bank> findAll(String query);

    Bank findById(Long id);
}
