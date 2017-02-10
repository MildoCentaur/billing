package ar.com.adriabe.services;

import ar.com.adriabe.daos.DaoException;
import ar.com.adriabe.model.Client;
import ar.com.adriabe.model.ClientPayment;
import ar.com.adriabe.model.Receipt;
import ar.com.adriabe.model.common.IRegisterAccount;

import java.util.List;

/**
 * Created by Mildo on 5/8/15.
 */
public interface PaymentService {

    List<IRegisterAccount> findDocumentsToBePaidById(Client id);

    public ar.com.adriabe.model.Receipt generateClientPaymentReceipt(ClientPayment clientPayment, int percentage) throws DaoException;

    public ClientPayment saveClientPayment(ClientPayment payment) throws Exception;

    public Receipt saveClientPaymentReceipt(Receipt receipt);

    String getNextReceiptNumber() throws DaoException;

    void calculateNextReceiptNumber(Long lastNumber) throws DaoException;

    void markPayingDeliveredOrders(ClientPayment clientPayment);
}
