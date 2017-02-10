package ar.com.adriabe.repositories;

import ar.com.adriabe.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Mildo on 8/24/14.
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByDeleted(boolean deleted);

    @Query(value = "select distinct client from Client client left join client.contacts contact where lower(client.name) like lower(:name) or lower(client.nickname) like lower(:name) or lower(contact.name) like lower(:name)")
    List<Client> findLikeName(@Param("name") String name);
}
