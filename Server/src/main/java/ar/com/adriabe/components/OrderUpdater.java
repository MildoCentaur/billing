package ar.com.adriabe.components;

import ar.com.adriabe.model.OrderAdjustment;
import ar.com.adriabe.services.ServiceException;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Mildo on 9/14/15.
 */
public interface OrderUpdater {
    @Transactional
    void update(OrderAdjustment[] adjustments) throws ServiceException;
}
