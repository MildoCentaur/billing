package ar.com.adriabe.daos;

import ar.com.adriabe.model.Stripe;
import ar.com.adriabe.model.StripeCombination;

import java.util.List;

/**
 * Created by Mildo on 9/26/14.
 */
public interface StripeDao {
    public List<Stripe> findAll();


    public List<Stripe> findLikeName(String name);


    public Stripe findById(Long id);


    public void save(Stripe stripe);

    void delete(Stripe stripe);

    StripeCombination findStripeCombinationById(Long stripeCombinationId);
}
