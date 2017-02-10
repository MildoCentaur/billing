package ar.com.adriabe.daos;

import ar.com.adriabe.model.Receipt;

public interface ReceiptDao {

    Receipt save(Receipt receipt);

    Receipt findReceiptByNumber(Long number);
}
