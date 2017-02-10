package ar.com.adriabe.services.status.updater;

import ar.com.adriabe.model.Order;
import ar.com.adriabe.services.ServiceException;

/**
 * Created by Mildo on 7/19/15.
 */
public interface OrderStatusUpdater {

    Order updateStatus(Order order) throws ServiceException;
}
