package ar.com.adriabe.services.impl;

import ar.com.adriabe.daos.BillDao;
import ar.com.adriabe.daos.DaoException;
import ar.com.adriabe.daos.SettingDao;
import ar.com.adriabe.model.Bill;
import ar.com.adriabe.model.Client;
import ar.com.adriabe.model.common.annotation.IAccountableOperation;
import ar.com.adriabe.model.common.annotation.ILogableOperation;
import ar.com.adriabe.model.constant.ACCOUNTABLE_DOCUMENT;
import ar.com.adriabe.model.constant.ACTION_TYPE;
import ar.com.adriabe.services.BillingService;
import ar.com.adriabe.utilities.BillingAPrintable;
import ar.com.adriabe.utilities.BillingBPrintable;
import ar.com.adriabe.utilities.BillingPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created by Mildo on 12/2/14.
 */
@Service("billingService")
public class BillingServiceImpl implements BillingService {

    @Autowired
    BillDao billDao;

    @Autowired
    SettingDao settingDao;

    @Autowired
    private BillingPrinter billingPrinter;

    @Override
    @Transactional
    public void save(Bill bill) throws Exception {
        registerNewBill(bill);
        printBill(bill);

    }

    @ILogableOperation(desc = "Registra una nueva factura.", type = ACTION_TYPE.CREATE)
    public void registerNewBill(Bill bill) throws DaoException {
        saveBill(bill);
        Client client = bill.getClient();
        client.setBalanceBilling(client.getBalanceBilling() + bill.getTotal());
        String nextBillNumber = calculateNextBillNumber(bill.getBillNumber(), bill.getBillType());
        String nextOrderNumber = calculateNextOrderNumber(bill.getOrderNumber(), bill.getBillType());

        if ("A".compareToIgnoreCase(bill.getBillType()) == 0) {
            settingDao.setSetting(SettingDao.NRO_FACTURA_A, nextBillNumber);
            settingDao.setSetting(SettingDao.NRO_REMITO_A, nextOrderNumber);
        } else {
            settingDao.setSetting(SettingDao.NRO_FACTURA_B, nextBillNumber);
            settingDao.setSetting(SettingDao.NRO_REMITO_B, nextOrderNumber);
        }
    }

    @IAccountableOperation(desc = "Debe crear la entrada en la cuenta corriente del debito de la factura", type = ACCOUNTABLE_DOCUMENT.BILL)
    public void saveBill(Bill bill) {
        billDao.save(bill);
    }


    @Override
    @ILogableOperation(desc = "Imprime una factura.", type = ACTION_TYPE.PRINT)
    public void printBill(Bill bill) throws Exception {

        if ("A".equalsIgnoreCase(bill.getBillType())) {
            billingPrinter.print(new BillingAPrintable(bill));
        } else {
            billingPrinter.print(new BillingBPrintable(bill));
        }

    }

    @Override
    public void cancelBill(Long id) throws EntityNotFoundException {
        Bill bill = billDao.findById(id);
        bill.setCancelled(true);
        billDao.save(bill);
    }

    @Override
    public void uncancelBill(Long id) throws EntityNotFoundException {

        Bill bill = billDao.findById(id);

        bill.setCancelled(false);
        billDao.save(bill);
    }

    @Override
    public List<Bill> findUnpaidBillsByClientId(Long clientId) {
        return billDao.findUnpaidBillsByClientId(clientId);
    }

    @Override
    public Bill findBillById(long id) {
        return billDao.findById(id);
    }

    private String calculateNextBillNumber(Long lastNumber, String ivaType) throws DaoException {
        Long number = 0l;
        if ("A".compareToIgnoreCase(ivaType) == 0) {
            number = Long.valueOf(settingDao.getNextBillANumber());
        } else {
            number = Long.valueOf(settingDao.getNextBillBNumber());
        }
        Bill bill;
        while (lastNumber == number || (bill = billDao.findBillByBillNumberAndType(number, ivaType)) != null) {
            number++;
        }
        return String.valueOf(number);
    }

    private String calculateNextOrderNumber(Long lastNumber, String ivaType) throws DaoException {
        Long number = 0l;
        if ("A".compareToIgnoreCase(ivaType) == 0) {
            number = Long.valueOf(settingDao.getNextOrderANumber());
        } else {
            number = Long.valueOf(settingDao.getNextOrderBNumber());
        }


        while (lastNumber == number || billDao.findBillByOrderNumberAndType(number, ivaType) != null) {
            number++;
        }
        return String.valueOf(number);
    }


    @Override
    public List<Bill> findAll() {
        return billDao.findAll();
    }


    @Override
    public String getNextBillANumber() throws DaoException {
        return String.format("%08d", Long.valueOf(settingDao.getNextBillANumber()));
    }


    @Override
    public String getNextBillBNumber() throws DaoException {
        return String.format("%08d", Long.valueOf(settingDao.getNextBillBNumber()));
    }

    @Override
    public String getNextOrderANumber() throws DaoException {
        return String.format("%08d", Long.valueOf(settingDao.getNextOrderANumber()));
    }

    @Override
    public String getNextOrderBNumber() throws DaoException {
        return String.format("%08d", Long.valueOf(settingDao.getNextOrderBNumber()));
    }


}
