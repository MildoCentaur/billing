package ar.com.adriabe.components;

import ar.com.adriabe.daos.OrderDao;
import ar.com.adriabe.model.OrderAdjustment;
import ar.com.adriabe.model.OrderItem;
import ar.com.adriabe.model.common.annotation.ILogableOperation;
import ar.com.adriabe.model.constant.ACTION_TYPE;
import ar.com.adriabe.services.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mildo on 9/14/15.
 */
@Component()
public class OrderUpdaterImpl implements OrderUpdater {

    OrderDao orderDao;

    @Autowired
    public OrderUpdaterImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    @ILogableOperation(desc = "Actualiza un pedido", type = ACTION_TYPE.CREATE)
    @Transactional
    public void update(OrderAdjustment[] adjustments) throws ServiceException {
        if (adjustments == null) {
            return;
        }
        List<OrderAdjustment> updates = new ArrayList<OrderAdjustment>();
        List<OrderAdjustment> deletes = new ArrayList<OrderAdjustment>();

        for (OrderAdjustment adjustment : adjustments) {
            if (adjustment.getOperation().equalsIgnoreCase("edit")) {
                updates.add(adjustment);
            } else if (adjustment.getOperation().equalsIgnoreCase("delete")) {
                deletes.add(adjustment);
            }
        }

        for (OrderAdjustment update : updates) {
            OrderItem item = orderDao.findOrderItemById(update.getOrderItemId());
            try {
                item.setQuantity(update.getQuantity());
                orderDao.save(item);
            } catch (Exception ex) {
                throw new ServiceException("No se pudo actualizar la cantidad de " + item.getProduct().getProductName());
            }
        }

        for (OrderAdjustment delete : deletes) {
            OrderItem item = orderDao.findOrderItemById(delete.getOrderItemId());
            try {
                orderDao.deleteItem(item);
            } catch (Exception ex) {
                throw new ServiceException("No se pudo eliminar el item" + item.getProduct().getProductName());
            }
        }

    }
}
