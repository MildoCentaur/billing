package ar.com.adriabe.processors.delivery.printprocess;

import ar.com.adriabe.daos.DaoException;
import ar.com.adriabe.daos.SettingDao;
import ar.com.adriabe.generic.Executable;
import ar.com.adriabe.generic.KendoExecutionException;
import ar.com.adriabe.model.Bill;
import ar.com.adriabe.model.Client;
import ar.com.adriabe.model.DeliveryOrder;
import ar.com.adriabe.processors.delivery.DeliveryOrderContext;
import ar.com.adriabe.services.BillingService;
import ar.com.adriabe.services.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by AJMILD1 on 09/10/2015.
 */
@Component
public class PrintBillPostProcess implements Executable<DeliveryOrderContext> {

    private SettingDao settingDao;
    private BillingService billingService;


    @Autowired
    public PrintBillPostProcess(SettingDao settingDao, BillingService billingService) {
        this.settingDao = settingDao;
        this.billingService = billingService;
    }


    @Override
    public DeliveryOrderContext execute(DeliveryOrderContext target) throws KendoExecutionException {

        DeliveryOrder deliveryOrder = target.getDeliveryOrder();
        Client client = deliveryOrder.getClient();
        int percentage = client.getClientType() == 1 ? OrderServiceImpl.FIFTY_PERCENTAGE : (client.getClientType() == 2 ? OrderServiceImpl.HOUNDRED_PERCENTAGE : 0);
        if (percentage == 0) {
            return target;
        }
        try {
            Bill bill = deliveryOrder.createBillForPercentage(percentage, billingService.getNextBillANumber(), billingService.getNextOrderANumber(), billingService.getNextBillBNumber(), billingService.getNextOrderBNumber());
            if (bill != null) {
                billingService.save(bill);
            }

            return target;
        } catch (DaoException e) {
            e.printStackTrace();
            throw new KendoExecutionException("Error al registrar la factura.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new KendoExecutionException("Error al imprimir la factura.");
        }
    }
}
