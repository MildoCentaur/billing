package ar.com.adriabe.services;

import ar.com.adriabe.daos.DaoException;
import ar.com.adriabe.model.Bill;

import java.util.List;

/**
 * Created by Mildo on 12/2/14.
 */
public interface BillingService {

    void save(Bill bill) throws Exception;

    List<Bill> findAll();

    public String getNextBillANumber() throws DaoException;

    public String getNextBillBNumber() throws DaoException;

    public String getNextOrderANumber() throws DaoException;

    public String getNextOrderBNumber() throws DaoException;

    public void printBill(Bill bill) throws Exception;

    void cancelBill(Long id);

    void uncancelBill(Long id);

    List<Bill> findUnpaidBillsByClientId(Long id);


    Bill findBillById(long id);
}
