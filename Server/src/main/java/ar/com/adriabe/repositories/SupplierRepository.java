package ar.com.adriabe.repositories;

import ar.com.adriabe.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Mildo on 8/25/14.
 */
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    List<Supplier> findByDeleted(boolean deleted);

    @Query(value = "select supplier from Supplier supplier join supplier.contacts contact where lower(supplier.name) like lower(:name) or lower(supplier.nickname) like lower(:name) or lower(contact.name) like lower(:name)")
    List<Supplier> findLikeName(@Param("name") String name);
}