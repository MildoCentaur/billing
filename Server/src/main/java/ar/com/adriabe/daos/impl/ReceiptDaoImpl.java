package ar.com.adriabe.daos.impl;

import ar.com.adriabe.daos.ReceiptDao;
import ar.com.adriabe.model.Receipt;
import ar.com.adriabe.repositories.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Mildo on 5/19/15.
 */
@Component
public class ReceiptDaoImpl implements ReceiptDao {

    @Autowired
    ReceiptRepository receiptRepository;

    @Override
    public Receipt save(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    @Override
    public Receipt findReceiptByNumber(Long number) {
        return receiptRepository.findByNumber(number);
    }
}
