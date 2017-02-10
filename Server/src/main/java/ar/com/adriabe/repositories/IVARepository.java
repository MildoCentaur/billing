package ar.com.adriabe.repositories;

import ar.com.adriabe.model.PurchaseTaxation;
import ar.com.adriabe.model.SupplierBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by AJMILD1 on 19/10/2015.
 */
public interface IVARepository extends JpaRepository<SupplierBill, Long> {

    @Query(value = "select new ar.com.adriabe.model.PurchaseTaxation( bill.issueDate, bill.registeredDate, supplier.IVACondition ,bill.billNumber, " +
            " bill.orderNumber, bill.total, bill.subTotal, bill.ivaTaxPercent3, bill.ivaTaxPercent10_5, bill.ivaTaxPercent21, " +
            " bill.ivaTaxPercent27,bill.ivaTaxBBII, supplier.name) from SupplierBill bill  join bill.supplier supplier " +
            " where bill.issueDate >= :startPeriod and bill.issueDate <= :endPeriod")
    List<PurchaseTaxation> findPurchaseTaxationInPeriod(@Param("startPeriod") Date startPeriod, @Param("endPeriod") Date endPeriod);


}
