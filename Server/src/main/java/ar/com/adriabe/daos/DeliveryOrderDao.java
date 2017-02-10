package ar.com.adriabe.daos;

import ar.com.adriabe.model.DeliveryOrder;

import java.util.List;

public interface DeliveryOrderDao {

    public List<DeliveryOrder> findAllDeliveryOrders();

    DeliveryOrder findById(Long id);

    DeliveryOrder save(DeliveryOrder deliveryOrder);

    List<DeliveryOrder> findUnpaidDeliveryOrderByClient(Long accountableId);

    double sumAllUnpaidDeliveryOrders(Long clientId);

    void delete(DeliveryOrder order);
}
