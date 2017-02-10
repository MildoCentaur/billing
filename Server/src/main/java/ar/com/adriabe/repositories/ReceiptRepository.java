package ar.com.adriabe.repositories;

import ar.com.adriabe.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Mildo on 5/20/15.
 */

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    @Query(value = "select r from Receipt r where r.number = :number")
    Receipt findByNumber(@Param("number") Long number);
}
