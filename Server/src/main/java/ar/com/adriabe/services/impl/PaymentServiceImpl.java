package ar.com.adriabe.services.impl;

import ar.com.adriabe.daos.*;
import ar.com.adriabe.model.*;
import ar.com.adriabe.model.common.IRegisterAccount;
import ar.com.adriabe.model.common.annotation.IAccountableOperation;
import ar.com.adriabe.model.constant.ACCOUNTABLE_DOCUMENT;
import ar.com.adriabe.services.BillingService;
import ar.com.adriabe.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Mildo on 5/8/15.
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    DeliveryOrderDao deliveryOrderDao;

    @Autowired
    ClientPaymentDao clientPaymentDao;


    @Autowired
    BillingService billingService;

    @Autowired
    SettingDao settingDao;

    @Autowired
    ReceiptDao receiptDao;


    @Override
    public List<IRegisterAccount> findDocumentsToBePaidById(Client client) {
        List<IRegisterAccount> list = new ArrayList<IRegisterAccount>();

        if (client.getClientType() < 2) {
            List<DeliveryOrder> deliveryOrders = deliveryOrderDao.findUnpaidDeliveryOrderByClient(client.getId());
            for (DeliveryOrder deliveryOrder : deliveryOrders) {
                list.add(deliveryOrder);
            }
        } else {
            List<Bill> bills = billingService.findUnpaidBillsByClientId(client.getId());
            for (Bill bill : bills) {
                list.add(bill);
            }
        }


        return list;
    }


    @IAccountableOperation(desc = "Registra el pago y crea un entrada en la cuentas correspondientes", type = ACCOUNTABLE_DOCUMENT.CLIENT_PAYMENT)
    public ClientPayment saveClientPayment(ClientPayment payment) throws Exception {

        Client client = payment.getClient();

        client.setBalanceActivity(client.getBalanceActivity() - payment.getValue());

        return clientPaymentDao.save(payment);
    }


    @IAccountableOperation(desc = "Registra el pago y crea un entrada en la cuentas correspondientes", type = ACCOUNTABLE_DOCUMENT.RECEIPT)
    public Receipt saveClientPaymentReceipt(Receipt receipt) {
        Client client = receipt.getClient();


        client.setBalanceBilling(client.getBalanceBilling() - receipt.getValue());
        return receiptDao.save(receipt);
    }

    //Por ahora solo se hace para los clientes que tienen type 2
    public Receipt generateClientPaymentReceipt(ClientPayment clientPayment, int percentage) throws DaoException {

        Client client = clientPayment.getClient();
        List<Bill> unpaid = billingService.findUnpaidBillsByClientId(clientPayment.getClient().getId());
        List<Bill> paying = selectPayingBills(unpaid, client, clientPayment);


        Receipt receipt = new Receipt();
        receipt.setNumber(getNextReceiptNumber());
        receipt.setClient(client);
        receipt.setBills(paying);
        Double receiptAmount = clientPayment.getValue();
        receipt.setValue(receiptAmount);
        receipt.setCash(clientPayment.getCash());

        List<Cheque> cheques = new ArrayList<Cheque>();
        for (Cheque cheque : clientPayment.getCheques()) {
            cheques.add(cheque);
        }
        receipt.setCheques(cheques);
        List<Transfer> transfers = new ArrayList<Transfer>();
        for (Transfer transfer : clientPayment.getTransfers()) {
            transfers.add(transfer);
        }
        receipt.setTransfers(transfers);
        receipt.setDate(new Date());


        return receipt;
    }

    private List<Bill> selectPayingBills(List<Bill> unpaid, Client client, ClientPayment clientPayment) {
        double totalCheques = 0;
        double totalDebt = 0;
        double totalTransferencias = 0;
        List<Bill> result = new ArrayList<Bill>();
        for (Transfer transfer : clientPayment.getTransfers()) {
            totalTransferencias = totalTransferencias + transfer.getValue();
        }
        for (Cheque cheque : clientPayment.getCheques()) {
            totalCheques = totalCheques + cheque.getValue();
        }
        for (Bill bill : unpaid) {
            totalDebt = totalDebt + bill.getTotal();
        }

        double difference = totalDebt - client.getBalanceBilling();
        double payingAmount = (client.getClientType() == 1) ? (clientPayment.getValue() / 2) : clientPayment.getValue();


        double total = payingAmount + difference; /// en el futuro al incluir los clientes intermedios esto va a cambiar
        Iterator<Bill> billIterator = unpaid.iterator();
        Bill actual = null;

        while (total > 0 && billIterator.hasNext()) {
            if (total > 0) {
                actual = billIterator.next();
                total = total - actual.getTotal();
                actual.setPayed(true);
                result.add(actual);
            }
        }


        return result;
    }

    @Override
    public String getNextReceiptNumber() throws DaoException {
        return String.format("%08d", Long.valueOf(settingDao.getNextReceiptNumber()));
    }

    @Override
    public void calculateNextReceiptNumber(Long lastNumber) throws DaoException {
        Long number = 0l;
        number = Long.valueOf(settingDao.getNextReceiptNumber());
        Receipt receipt;
        while (lastNumber == number || (receipt = receiptDao.findReceiptByNumber(number)) != null) {
            number++;
        }

        settingDao.setSetting(SettingDao.NRO_RECIBO, String.valueOf(number));

    }

    @Override
    public void markPayingDeliveredOrders(ClientPayment clientPayment) {

        double amount = clientPayment.getValue();
        double totalDebt = sumAllUnpaidDeliveryOrders(clientPayment.getClient().getId());

        double difference = totalDebt - clientPayment.getClient().getBalanceActivity();
        if (difference > 0) {
            amount = amount + difference;
        }

        for (DeliveryOrder deliveryOrder : clientPayment.getDeliveryOrders()) {
            amount = amount - deliveryOrder.getTotalAmount();
            if (amount > 0) {
                deliveryOrder.setPaid(true);
            }
        }


    }

    private double sumAllUnpaidDeliveryOrders(Long clientId) {
        return deliveryOrderDao.sumAllUnpaidDeliveryOrders(clientId);

    }
}


