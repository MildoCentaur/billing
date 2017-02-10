package ar.com.adriabe.services;

import ar.com.adriabe.model.Bank;

import java.util.List;

public interface BankService {
    List<Bank> findAllBanks(String query);

    Bank findById(Long bankId);
}
