package ar.com.adriabe.services;

import ar.com.adriabe.model.ClientPayment;
import ar.com.adriabe.model.Receipt;

/**
 * Created by Mildo on 5/21/15.
 */
public interface PaymentRegister {


    Receipt registerClientPayment(ClientPayment clientPayment) throws Exception;

    void printReceipt(Receipt receipt) throws Exception;
}
