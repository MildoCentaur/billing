package ar.com.adriabe.repositories;

import ar.com.adriabe.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by AJMILD1 on 23/06/14.
 */

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {


}
