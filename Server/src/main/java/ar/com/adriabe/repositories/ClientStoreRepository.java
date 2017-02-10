package ar.com.adriabe.repositories;

import ar.com.adriabe.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Mildo on 9/5/14.
 */
public interface ClientStoreRepository extends JpaRepository<Store, Long> {

}
