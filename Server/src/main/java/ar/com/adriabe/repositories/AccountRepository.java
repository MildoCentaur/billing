package ar.com.adriabe.repositories;

import ar.com.adriabe.model.Account;
import ar.com.adriabe.model.constant.ACCOUNT_TYPE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Mildo on 10/3/14.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("select account from Account account join account.supplier supplier where supplier.id=:id and account.accountType=:accountType")
    List<Account> findBySupplierIdAndActivity(@Param("id") Long id, @Param("accountType") ACCOUNT_TYPE accountType);

    @Query("select account from Account account join account.client client where client.id=:id and account.accountType=:accountType")
    List<Account> findByClientIdAndActivity(@Param("id") Long id, @Param("accountType") ACCOUNT_TYPE accountType);

    @Query("select account from Account account join account.supplier where account.date>=:startDate and account.date<=:toDate")
    List<Account> findByDateAndSupplierType(@Param("startDate") Date startDate, @Param("toDate") Date toDate);

    @Query("select account from Account account join account.client join account.payment where account.date>=:startDate and account.date<=:toDate")
    List<Account> findIncomesMovementByDate(@Param("startDate") Date startDate, @Param("toDate") Date toDate);
}
