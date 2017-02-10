package ar.com.adriabe.repositories;

import ar.com.adriabe.model.DeliveryOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Mildo on 3/31/15.
 */
public interface DeliveryOrderItemRepository extends JpaRepository<DeliveryOrderItem, Long> {
}
