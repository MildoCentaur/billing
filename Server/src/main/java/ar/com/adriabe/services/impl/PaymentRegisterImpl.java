package ar.com.adriabe.services.impl;

import ar.com.adriabe.model.Client;
import ar.com.adriabe.model.ClientPayment;
import ar.com.adriabe.model.Receipt;
import ar.com.adriabe.model.common.annotation.ILogableOperation;
import ar.com.adriabe.model.constant.ACTION_TYPE;
import ar.com.adriabe.services.BillingService;
import ar.com.adriabe.services.PaymentRegister;
import ar.com.adriabe.services.PaymentService;
import ar.com.adriabe.utilities.BillingPrinter;
import ar.com.adriabe.utilities.ReceiptPrintable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Mildo on 5/21/15.
 */
@Component
public class PaymentRegisterImpl implements PaymentRegister {

    private static final int FIFTY_PERCENTAGE = 50;
    private static final int HOUNDRED_PERCENTAGE = 100;
    @Autowired
    private BillingPrinter billingPrinter;
    @Autowired
    private BillingService billingService;
    @Autowired
    private PaymentService paymentService;

    @Override
    @ILogableOperation(desc = "Registra el pago correspondiente.", type = ACTION_TYPE.CREATE)
    @Transactional
    public Receipt registerClientPayment(ClientPayment clientPayment) throws Exception {

        Receipt receipt;
        Client client = clientPayment.getClient();
        int percentage = client.getClientType() == 1 ? FIFTY_PERCENTAGE : (client.getClientType() == 2 ? HOUNDRED_PERCENTAGE : 0);
        paymentService.markPayingDeliveredOrders(clientPayment);
        clientPayment = paymentService.saveClientPayment(clientPayment);


        if (percentage > 0) {
            receipt = paymentService.generateClientPaymentReceipt(clientPayment, percentage);
            paymentService.saveClientPaymentReceipt(receipt);
            return receipt;
        }

        return null;

    }


    @Override
    public void printReceipt(Receipt receipt) throws Exception {
        if (receipt != null) {
            billingPrinter.print(new ReceiptPrintable(receipt));
        }

    }
}
