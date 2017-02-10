package ar.com.adriabe.repositories;

import ar.com.adriabe.model.DeliveryOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrder, Long> {

    @Query(value = "select o from DeliveryOrder o join o.client client where client.name like :name and o.deleted=false and o.status<>'CANCELLED'")
    List<DeliveryOrder> findLikeName(@Param("name") String name);

    @Query(value = "select o from DeliveryOrder o where o.deleted=false and o.status<>'CANCELLED'")
    List<DeliveryOrder> findAllNotDeleted();

    @Query(value = "select o from DeliveryOrder o join o.client client where client.id= :id and o.paid=false and o.deleted=false and o.status<>'CANCELLED'")
    List<DeliveryOrder> findUnpaidDeliveryOrderByClient(@Param("id") Long id);

    @Query(value = "select sum(o.totalAmount) from DeliveryOrder o join o.client client where client.id= :id and o.deleted=false and o.status<>'CANCELLED' and o.paid=false")
    double sumAllUnpaidDeliveryOrders(@Param("id") Long id);


}
