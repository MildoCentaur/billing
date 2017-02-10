package ar.com.adriabe.daos.impl;

import ar.com.adriabe.daos.BillDao;
import ar.com.adriabe.model.Bill;
import ar.com.adriabe.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created by Mildo on 1/30/15.
 */
@Component
public class BillDaoImpl implements BillDao {

    @Autowired
    BillRepository billRepository;

    @Override
    public void save(Bill bill) {
        billRepository.save(bill);
    }

    @Override
    public List<Bill> findAll() {
        return billRepository.findAll();
    }

    @Override
    public Bill findBillByOrderNumberAndType(Long number, String billType) {
        return billRepository.findBillByOrderNumberAndType(number, billType);
    }

    @Override
    public Bill findBillByBillNumberAndType(Long number, String billType) {
        return billRepository.findBillByBillNumberAndType(number, billType);
    }

    @Override
    public Bill findById(Long id) throws EntityNotFoundException {
        Bill bill = billRepository.findOne(id);
        if (bill == null) {
            throw new EntityNotFoundException();
        }
        return bill;
    }

    @Override
    public List<Bill> findUnpaidBillsByClientId(Long clientId) {
        return billRepository.findUnpaidBillsByClientId(clientId);
    }
}
