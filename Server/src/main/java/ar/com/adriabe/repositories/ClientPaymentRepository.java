package ar.com.adriabe.repositories;

import ar.com.adriabe.model.ClientPayment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Mildo on 5/16/15.
 */
public interface ClientPaymentRepository extends JpaRepository<ClientPayment, Long> {
}
