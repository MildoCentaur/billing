package ar.com.adriabe.daos.impl;

import ar.com.adriabe.daos.StripeDao;
import ar.com.adriabe.model.Stripe;
import ar.com.adriabe.model.StripeCombination;
import ar.com.adriabe.repositories.StripeCombinationRepository;
import ar.com.adriabe.repositories.StripeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Mildo on 9/26/14.
 */
@Component("stripeDao")
public class StripeDaoImpl implements StripeDao {

    @Autowired
    StripeRepository stripeRepository;

    @Autowired
    StripeCombinationRepository stripeCombinationRepository;



    @Override
    public List<Stripe> findAll() {
        return stripeRepository.findByDeleted(false);
    }

    @Override
    public List<Stripe> findLikeName(String name) {
        name = '%'+name+'%';
        return stripeRepository.findLikeName(name);
    }

    @Override
    public Stripe findById(Long id) {
        return stripeRepository.findOne(id);
    }

    @Override
    public void save(Stripe stripe) {
        if(stripe.getId()>0){
            Stripe aux = stripeRepository.findOne(stripe.getId());
            for (StripeCombination stripeCombination : stripe.getCombinations()) {
                if (stripeCombination.getId() == 0) {
                    aux.addCombinations(stripeCombination);
                }
            }

            stripeRepository.save(aux);

        }else {
            stripeRepository.save(stripe);
        }
    }

    @Override
    public void delete(Stripe stripe) {
        stripeRepository.delete(stripe);
    }

    @Override
    public StripeCombination findStripeCombinationById(Long stripeCombinationId) {
        return stripeCombinationRepository.findOne(stripeCombinationId);
    }
}
