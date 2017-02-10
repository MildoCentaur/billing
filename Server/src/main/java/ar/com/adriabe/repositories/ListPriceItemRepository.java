package ar.com.adriabe.repositories;

import ar.com.adriabe.model.Fabric;
import ar.com.adriabe.model.ListPriceItem;
import ar.com.adriabe.model.ProductFamily;
import ar.com.adriabe.model.Stripe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Mildo on 11/29/14.
 */
public interface ListPriceItemRepository extends JpaRepository<ListPriceItem, Long> {

    @Query("select lpi from ListPriceItem lpi join lpi.listPrice lp where lpi.productFamily=:productFamily and " +
            " lower(lp.name) like '%general%' and lp.date >= " +
            " (select max(lp1.date) from ListPrice lp1 where lower(lp1.name) like '%general%')")
    ListPriceItem findDefaultListPriceItemByProductFamily(@Param("productFamily")ProductFamily productFamily);


    @Query("select lpi from ListPriceItem lpi join lpi.listPrice lp join lpi.productFamily family " +
            " where family.fabric=:fabric and family.stripe=:stripe and " +
            " lower(lp.name) like '%general%' and lp.date >= " +
            " (select max(lp1.date) from ListPrice lp1 where lower(lp1.name) like '%general%')")
    List<ListPriceItem> findDefaultListPriceItemsByFabricAndStripe(@Param("fabric") Fabric fabric, @Param("stripe")Stripe stripe);

    @Query("select lpi from ListPriceItem lpi join lpi.listPrice lp join lpi.productFamily family " +
            " where family.fabric=:fabric and family.stripe is null and lower(lp.name) like '%general%' and lp.date >= " +
            " (select max(lp1.date) from ListPrice lp1 where lower(lp1.name) like '%general%')")
    List<ListPriceItem> findDefaultListPriceItemsByFabric(@Param("fabric") Fabric fabric);
}
