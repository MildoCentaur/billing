package ar.com.adriabe.repositories;

import ar.com.adriabe.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Mildo on 6/18/14.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByDeleted(boolean b);

    @Query(value = "select prod from Product prod join prod.fabric fab where fab.longname like :name")
    List<Product> findLikeName(@Param("name") String name);


    @Query(value = "select prod from Product prod join prod.fabric fab join prod.stripe stripe join " +
            " prod.stripeCombination stripeCombination join prod.color color where fab.id=:fabricId " +
            " and color.id=:colorId and stripe.id=:stripeId and stripeCombination.id=:stripeCombinationId")
    Product findByFabricAndColorAndStripeAndStripeCombination(@Param("fabricId") Long fabricId,
                                                              @Param("colorId") Long colorId, @Param("stripeId") Long stripeId,
                                                              @Param("stripeCombinationId") Long stripeCombinationId);

    @Query(value = "select prod from Product prod join prod.fabric fab join prod.color color " +
            "where fab.id=:fabricId  and color.id=:colorId and prod.stripe is null and prod.stripeCombination is null")
    Product findByFabricAndColor(@Param("fabricId") Long fabricId, @Param("colorId") Long colorId);

    @Query(value = "select count(prod) from Product prod where prod.stock=0")
    Integer countEmptyStock();
}
