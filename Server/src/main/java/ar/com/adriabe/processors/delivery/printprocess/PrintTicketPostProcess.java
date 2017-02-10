package ar.com.adriabe.processors.delivery.printprocess;

import ar.com.adriabe.daos.DaoException;
import ar.com.adriabe.daos.SettingDao;
import ar.com.adriabe.generic.Executable;
import ar.com.adriabe.generic.KendoExecutionException;
import ar.com.adriabe.model.constant.EurekaConstants;
import ar.com.adriabe.processors.delivery.DeliveryOrderContext;
import ar.com.adriabe.utilities.TicketPrintable;
import ar.com.adriabe.utilities.TicketPrinter;
import ar.com.adriabe.utilities.TicketPrinterRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by AJMILD1 on 09/10/2015.
 */

@Component
public class PrintTicketPostProcess implements Executable<DeliveryOrderContext> {

    SettingDao settingDao;

    @Autowired
    public PrintTicketPostProcess(SettingDao settingDao) {
        this.settingDao = settingDao;
    }


    @Override
    public DeliveryOrderContext execute(DeliveryOrderContext target) throws KendoExecutionException {
        TicketPrinter printer;
        try {
            if (target.isPrint()) {

                if (EurekaConstants.DEBUG) {
                    printer = new TicketPrinter();
                } else {
                    String ticketPrinterUrl = settingDao.getTicketPrinterUrl();
                    int ticketPrinterPort = settingDao.getTicketPrinterPort();
                    printer = new TicketPrinterRemote(ticketPrinterUrl, ticketPrinterPort);
                }
                printer.print(new TicketPrintable(target.getDeliveryOrder()));
            }
            return target;
        } catch (DaoException e) {
            e.printStackTrace();
            throw new KendoExecutionException("Error al buscar las propiedades de conexion con la ticketeadora");
        } catch (Exception e) {
            e.printStackTrace();
            throw new KendoExecutionException("Error en la conexion con la ticketeadora");
        }
    }
}
