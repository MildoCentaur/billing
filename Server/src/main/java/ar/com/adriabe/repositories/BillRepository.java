package ar.com.adriabe.repositories;

import ar.com.adriabe.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Mildo on 1/31/15.
 */
public interface BillRepository extends JpaRepository<Bill, Long> {

    @Query("select bill from Bill bill where bill.orderNumber=:number and bill.billType=:billType")
    Bill findBillByOrderNumberAndType(@Param("number") Long number, @Param("billType") String billType);

    @Query("select bill from Bill bill where bill.billNumber=:number and bill.billType=:billType")
    Bill findBillByBillNumberAndType(@Param("number") Long number, @Param("billType") String billType);

    @Query("select bill from Bill bill where bill.client.id=:id and bill.payed=true order by bill.date desc")
    List<Bill> findUnpaidBillsByClientId(@Param("id") Long id);
}
