package ar.com.adriabe.repositories;

import ar.com.adriabe.model.Order;
import ar.com.adriabe.model.constant.ORDER_STATUS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Mildo on 10/14/14.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select o from Order o join o.client client where client.name like :name")
    List<Order> findLikeName(@Param("name") String name);

    @Query(value = "select o from Order o where o.status in (:statusList)")
    List<Order> findByStatus(@Param("statusList") List<ORDER_STATUS> statusList);

    @Query(value = "select o from Order o where o.orderedDate>:from and o.orderedDate<:to")
    List<Order> findOrdersInBetweenDates(@Param("from") Date from, @Param("to") Date to);

    @Query(value = "select o from Order o where o.status = :delivered and o.deliveredDate > :time")
    List<Order> findDeliveredOrders(@Param("delivered") ORDER_STATUS delivered, @Param("time") Date time);

    @Query(value = "select o from Order o left join o.items item left join item.accesories acc where item.id=:id or acc.id=:id")
    Order findOrderByOrderItemId(@Param("id") Long id);

    @Query(value = "select o from Order o join o.items item join item.packages package where package.id in " +
            "(select deliveryPackage.id from DeliveryOrder delivery join delivery.items deliveryItem " +
            "join deliveryItem.packages deliveryPackage where delivery.id = :deliveryOrderId)")
    Order findOrderByDeliveryOrderId(@Param("deliveryOrderId") Long deliveryOrderId);

}
