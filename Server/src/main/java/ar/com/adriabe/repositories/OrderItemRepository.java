package ar.com.adriabe.repositories;

import ar.com.adriabe.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("select distinct oi from OrderItem oi where " +
            " oi.id in (select oi2.id from Order o join o.items oi2 join oi2.packages pks1 where (o.client.id=:clientId or o.id=:orderId) and pks1.status='PREPEARED')" +
            " or oi.id in (select acc.id from Order o3 join o3.items oi3 join oi3.accesories acc join acc.packages where o3.client.id=:clientId or o3.id=:orderId)")
    List<OrderItem> findItemsToDeliverByOrderOrClient(@Param("clientId") Long clientId, @Param("orderId") Long orderId);


    @Query(value = "select count(distinct o) from Order o join o.items oi join oi.packages pks where (o.client.id=:clientId or o.id=:orderId)")
    Integer countOrdersToDeliverByOrderOrClient(@Param("clientId") Long clientId, @Param("orderId") Long orderId);

    @Query(value = "select item from OrderItem item join item.packages package where package.id = :orderItemDetailId")
    OrderItem findOrderItemByOrderItemDetailId(@Param("orderItemDetailId") Long orderItemDetailId);
}
