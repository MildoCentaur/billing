package ar.com.adriabe.daos;

import ar.com.adriabe.model.Bill;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created by Mildo on 1/30/15.
 */
public interface BillDao {
    void save(Bill bill);

    List<Bill> findAll();

    Bill findBillByOrderNumberAndType(Long number, String ivaType);

    Bill findBillByBillNumberAndType(Long number, String ivaType);


    Bill findById(Long id) throws EntityNotFoundException;

    List<Bill> findUnpaidBillsByClientId(Long clientId);
}
