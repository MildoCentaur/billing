package ar.com.adriabe.repositories;

import ar.com.adriabe.model.Stripe;
import ar.com.adriabe.model.StripeCombination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Mildo on 6/18/14.
 */
public interface StripeRepository extends JpaRepository<Stripe, Long> {

    List<Stripe> findByDeleted(boolean b);

    @Query(value = "select stripe from Stripe stripe where lower(stripe.name) like lower(:name)")
    List<Stripe> findLikeName(@Param("name") String name);

    @Query(value = "select stripeCombination from StripeCombination stripeCombination where id=:id")
    StripeCombination findStripeCombinationById(@Param("id") Long id);
}
