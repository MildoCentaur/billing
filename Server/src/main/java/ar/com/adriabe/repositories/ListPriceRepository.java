package ar.com.adriabe.repositories;

import ar.com.adriabe.model.ListPrice;
import ar.com.adriabe.model.ListPriceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Mildo on 8/24/14.
 */
public interface ListPriceRepository extends JpaRepository<ListPrice, Long> {

    List<ListPrice> findByDeleted(boolean deleted);

    @Query("select lp from ListPrice lp where lower(lp.name) like '%general%' and lp.active=true and lp.date >= " +
            "(select max(lp1.date) from ListPrice lp1 where lower(lp1.name) like '%general%')")
    ListPrice findLatestAndNameLikeGeneral();

    @Query("select lpi from ListPriceItem lpi join lpi.productFamily pf join pf.products prod " +
            " where prod.id = :productId  and lpi.id in " +
            " (select lpi.id from ListPrice lp join lp.prices lpi " +
                       " where lp.active=true and lp.date>=(select max(lp1.date) from ListPrice lp1))")
    ListPriceItem findPriceByProduct(@Param("productId")Long productId);
}
