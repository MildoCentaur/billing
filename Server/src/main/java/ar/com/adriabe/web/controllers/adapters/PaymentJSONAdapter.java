package ar.com.adriabe.web.controllers.adapters;

import ar.com.adriabe.model.*;
import ar.com.adriabe.model.constant.TRANSFER_TYPE;
import ar.com.adriabe.services.*;
import ar.com.adriabe.web.model.json.ChequeJSON;
import ar.com.adriabe.web.model.json.PaymentJSON;
import ar.com.adriabe.web.model.json.TransferJSON;
import ar.com.adriabe.web.model.json.UnpaidElementJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mildo on 5/16/15.
 */

@Component
public class PaymentJSONAdapter {

    @Autowired
    BankService bankService;

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    ClientService clientService;

    @Autowired
    OrderService orderService;

    @Autowired
    BillingService billingService;

    public ClientPayment convertClientPaymentJSONToPayment(PaymentJSON json) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        Payment payment = new Payment();

        Client client = clientService.findClientById(json.getEntityId());


        payment.setDate(new Date());
        if (client.getClientType() == 0) {
            payment.setActivity(true);
        } else if (client.getClientType() == 2) {
            payment.setActivity(false);
        } else {
            payment.setActivity(true);
        }

        payment.setValue(json.getTotal());
        payment.setCash(json.getCash());

        for (ChequeJSON chequeJSON : json.getCheques()) {
            Cheque cheque = new Cheque();
            cheque.setAccount(chequeJSON.account);
            cheque.setBank(bankService.findById(chequeJSON.bankId));
            cheque.setClient(client);
            cheque.setCode(chequeJSON.code);
            cheque.setCrossed(chequeJSON.crossed);
            cheque.setCuitFirmante(chequeJSON.cuitSigner);
            cheque.setExpirationDate(format.parse(chequeJSON.expirationDate));
            cheque.setFirmante(chequeJSON.nameSigner);
            cheque.setValue(chequeJSON.value);
            cheque.setOthers(chequeJSON.others);
            cheque.setNumber(chequeJSON.number);
            cheque.setReceived(new Date());
            payment.addCheque(cheque);
        }


        for (TransferJSON transferJSON : json.getDeposits()) {
            Transfer transfer = new Transfer();
            transfer.setValue(transferJSON.getAmount());
            transfer.setDateRecibed(new Date());
            BankAccount account = bankAccountService.findById(transferJSON.getAccountId());
            transfer.setDestinationAccount(account.getNumber());
            transfer.setDestinationBank(bankService.findAllBanks(account.getName()).get(0));
            transfer.setSourceAccount(transferJSON.getNumber());
            transfer.setSourceBank(bankService.findAllBanks(transferJSON.getBank()).get(0));
            transfer.setTransferType(TRANSFER_TYPE.INCOMING);
            payment.addTransfer(transfer);
        }
        ClientPayment clientPayment = new ClientPayment();

        if (client.getClientType() < 2) {
            for (UnpaidElementJSON unpaidElementJSON : json.getPaying()) {
                DeliveryOrder deliveryOrder = orderService.findDeliveryOrderById(unpaidElementJSON.id);
                clientPayment.addDeliveryOrders(deliveryOrder);

            }
        } else {
            for (UnpaidElementJSON unpaidElementJSON : json.getPaying()) {
                Bill bill = billingService.findBillById(unpaidElementJSON.id);
                clientPayment.addBill(bill);

            }
        }

        clientPayment.setClient(client);
        clientPayment.setPayment(payment);
        return clientPayment;
    }
}
