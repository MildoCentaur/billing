package ar.com.adriabe.services.impl;

import ar.com.adriabe.daos.DeliveryOrderDao;
import ar.com.adriabe.daos.OrderDao;
import ar.com.adriabe.daos.SettingDao;
import ar.com.adriabe.model.DeliveryOrder;
import ar.com.adriabe.model.OrderItemDetail;
import ar.com.adriabe.model.common.annotation.ILogableOperation;
import ar.com.adriabe.model.constant.ACTION_TYPE;
import ar.com.adriabe.processors.delivery.DeliveryOrderContext;
import ar.com.adriabe.processors.delivery.postprocess.DeliveryOrderAccountEntryRegistration;
import ar.com.adriabe.services.DeliveryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Mildo on 4/25/15.
 */
@Service("deliveryOrderService")
public class DeliveryOrderServiceImpl implements DeliveryOrderService {

    @Autowired
    DeliveryOrderDao deliveryOrderDao;

    @Autowired
    OrderDao orderDao;

    @Autowired
    SettingDao settingDao;

    @Autowired
    DeliveryOrderAccountEntryRegistration deliveryOrderAccountEntryRegistration;

    @Override
    public void saveDeliveryOrder(DeliveryOrder deliveryOrder, boolean print) throws Exception {
        deliveryOrderDao.save(deliveryOrder);

        DeliveryOrderContext context = new DeliveryOrderContext(deliveryOrder, false, null);
        deliveryOrderAccountEntryRegistration.execute(context);
//        TicketPrinter printer;
//        if (EurekaConstants.DEBUG) {
//            printer = new TicketPrinter();
//        } else {
//            printer = new TicketPrinterRemote(settingDao.getTicketPrinterUrl(), settingDao.getTicketPrinterPort());
//        }

//        if(true){
//            printer.print(new TicketPrintable(deliveryOrder));

//        }
    }

    @Override
    @ILogableOperation(desc = "Actualiza el estado de los paquetes entregados-rechazados", type = ACTION_TYPE.CREATE)
    @Transactional
    public void updateDeliveredItemsStatus(List<OrderItemDetail> delivered) {
        for (OrderItemDetail orderItemDetail : delivered) {
            orderDao.save(orderItemDetail);
        }

    }

}
