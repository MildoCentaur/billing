package ar.com.adriabe.repositories;

import ar.com.adriabe.model.OrderItemDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Mildo on 3/28/15.
 */
public interface OrderItemDetailRepository extends JpaRepository<OrderItemDetail, Long> {


}
