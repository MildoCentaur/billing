package ar.com.adriabe.processors.delivery;

import ar.com.adriabe.components.OperationLogRegistrar;
import ar.com.adriabe.daos.DeliveryOrderDao;
import ar.com.adriabe.generic.AbstractAuditableRegistrar;
import ar.com.adriabe.generic.KendoExecutionException;
import ar.com.adriabe.model.DeliveryOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Mildo on 9/27/15.
 */
@Component
public class DeliveryOrderRegistrar extends AbstractAuditableRegistrar<DeliveryOrderContext, DeliveryOrderProvider> {

    DeliveryOrderDao deliveryOrderDao;


    @Autowired
    public DeliveryOrderRegistrar(DeliveryOrderPostProcessor deliveryOrderPostProcessor,
                                  DeliveryOrderBuilder deliveryOrderBuilder,
                                  DeliveryOrderDao deliveryOrderDao,
                                  OperationLogRegistrar operationLogRegistrar) {
        super(deliveryOrderPostProcessor, deliveryOrderBuilder, operationLogRegistrar);

        this.deliveryOrderDao = deliveryOrderDao;
    }


    protected DeliveryOrderContext doRegister(DeliveryOrderContext context) throws KendoExecutionException {
        try {
            DeliveryOrder deliveryOrder = context.getDeliveryOrder();
            setAuditableEntityData(deliveryOrder);
            deliveryOrderDao.save(deliveryOrder);
            setResultMessage("Delivery Order id:" + deliveryOrder.getId());
            return context;
        } catch (Exception e) {
            e.printStackTrace();
            throw new KendoExecutionException("Error al guardar la orden de entrega.");
        }
    }

    @Override
    public String getOperationTitle() {
        return "Registro de orden de entrega:";

    }

    @Override
    public String getOperationIntention() {
        return " Almaceno la orden," +
                " genera el asiento contable," +
                " imprime el ticket," +
                " actualizo estado del pedido" +
                " y remuevo del stock";
    }


}