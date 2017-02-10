package ar.com.adriabe.repositories;

import ar.com.adriabe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by AJMILD1 on 23/06/14.
 */

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUsername(String username);

    List<User> findByDeleted(Boolean b);
}
