package ar.com.adriabe.repositories;

import ar.com.adriabe.model.Fabric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Mildo on 6/18/14.
 */
public interface FabricRepository extends JpaRepository<Fabric, Long> {

    List<Fabric> findByDeleted(boolean b);

    @Query(value = "select fab from Fabric fab where lower(fab.shortname) like lower(:name)")
    List<Fabric> findLikeName(@Param("name") String name);

}
