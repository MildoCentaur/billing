package ar.com.adriabe.repositories;

import ar.com.adriabe.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Mildo on 5/16/15.
 */

public interface BankRepository extends JpaRepository<Bank, Long> {

    @Query("select b from Bank b where b.longName like :query or b.shortName like :query or b.code like :query")
    List<Bank> findAllLike(@Param("query") String query);

}
