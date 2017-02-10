package ar.com.adriabe.repositories;

import ar.com.adriabe.model.ClientContact;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by Mildo on 9/5/14.
 */
public interface ClientContactRepository extends JpaRepository<ClientContact, Long> {

}
