package ar.com.adriabe.processors.delivery.postprocess;

import ar.com.adriabe.daos.OrderDao;
import ar.com.adriabe.generic.Executable;
import ar.com.adriabe.generic.KendoExecutionException;
import ar.com.adriabe.model.DeliveryOrder;
import ar.com.adriabe.model.Order;
import ar.com.adriabe.processors.delivery.DeliveryOrderContext;
import ar.com.adriabe.services.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ajmild1 on 03/10/2015.
 */
@Component
public class AssignDeliveryToOriginOrderPostProcess implements Executable<DeliveryOrderContext> {
    OrderDao orderDao;


    @Autowired
    public AssignDeliveryToOriginOrderPostProcess(OrderDao orderDao) {
        this.orderDao = orderDao;
    }


    @Override
    public DeliveryOrderContext execute(DeliveryOrderContext context) throws KendoExecutionException {
        Order order = orderDao.findById(context.getOrder().getId());
        DeliveryOrder deliveryOrder = context.getDeliveryOrder();
        order.addDeliveryOrder(deliveryOrder);
        try {
            orderDao.saveOrUpdate(order);
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new KendoExecutionException("Error al asociar la orden de entrega con el pedido.");
        }
        return context;
    }
}
