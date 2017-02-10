package ar.com.adriabe.processors.delivery;

import ar.com.adriabe.generic.FailFastProcessor;
import ar.com.adriabe.processors.delivery.postprocess.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryOrderPostProcessor extends FailFastProcessor<DeliveryOrderContext> {

    @Autowired
    public void setUpdateInventoryPostProcess(UpdateInventoryPostProcess updateInventoryPostProcess) {
        this.executables.add(updateInventoryPostProcess);
    }

    @Autowired
    public void setAccountEntryRegistration(DeliveryOrderAccountEntryRegistration accountEntryRegistration) {
        this.executables.add(accountEntryRegistration);
    }

    @Autowired
    public void setUpdateOrderItemDetailPostProcess(UpdateOrderItemDetailPostProcess updateOrderItemDetailPostProcess) {
        this.executables.add(updateOrderItemDetailPostProcess);
    }


    @Autowired
    public void setAssignDeliveryToOriginOrderOrderPostProcess(AssignDeliveryToOriginOrderPostProcess assignDeliveryToOriginOrderPostProcess) {
        this.executables.add(assignDeliveryToOriginOrderPostProcess);
    }

    @Autowired
    public void setDeliveryOrderUpdateOrderStatusPostProcess(DeliveryOrderUpdateOrderStatusPostProcess deliveryOrderUpdateOrderStatusPostProcess) {
        this.executables.add(deliveryOrderUpdateOrderStatusPostProcess);
    }
}
