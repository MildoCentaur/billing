package ar.com.adriabe.processors.delivery.postprocess;

import ar.com.adriabe.components.InventoryAccountant;
import ar.com.adriabe.generic.Executable;
import ar.com.adriabe.generic.KendoExecutionException;
import ar.com.adriabe.model.DeliveryOrderItem;
import ar.com.adriabe.model.OrderItemDetail;
import ar.com.adriabe.processors.delivery.DeliveryOrderContext;
import ar.com.adriabe.services.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateInventoryPostProcess implements Executable<DeliveryOrderContext> {

    InventoryAccountant inventoryAccountant;

    @Autowired
    public UpdateInventoryPostProcess(InventoryAccountant inventoryAccountant) {
        this.inventoryAccountant = inventoryAccountant;
    }


    @Override
    public DeliveryOrderContext execute(DeliveryOrderContext context) throws KendoExecutionException {
        try {
            for (DeliveryOrderItem item : context.getDeliveryOrder().getItems()) {
                OrderItemDetail orderItemDetail = item.getPackages().get(0);
                Integer amount = 0 - item.getPackages().size();
                inventoryAccountant.incrementProductStock(orderItemDetail.getBarcode(), amount);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new KendoExecutionException("Error al consumir stock del producto");
        }

        return context;
    }
}
