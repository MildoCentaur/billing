/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.billing.workstation.dao;

import ar.com.billing.workstation.model.Stripe;
import ar.com.billing.workstation.utilities.EurekaStringUtilities;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Mildo
 */
public class StripeDao {

    private static List<Stripe> stripes = Collections.synchronizedList(new ArrayList<Stripe>());

    /**
     * @return the products
     */
    public static List<Stripe> getStripes() {
        return stripes;
    }

    public static void addStripe(Stripe aStripe) {
       if(findStripeByName(aStripe.getName())==null){
            stripes.add(aStripe);
       }
       
    }

    protected static Stripe findStripeByName(String stripeStr, boolean clone) {
        synchronized (stripes) {
            for (Stripe stripe : stripes) {
                if (stripe.getName().equalsIgnoreCase(stripeStr)) {
                    if (clone) {
                        return stripe.clone();
                    } else {
                        return stripe;
                    }
                }
            }
        }
        return null;
    }

    public static Stripe findStripeByName(String stripe) {
        return findStripeByName(stripe, true);
    }

    public static Stripe findStripeByCodeOrName(String text) {
        boolean exist = false;
        synchronized (stripes) {
            for (Stripe stripe : stripes) {
                exist = exist || stripe.getName().equalsIgnoreCase(text);
                exist = exist || stripe.getCode().equalsIgnoreCase(text);
                if(exist){
                    return stripe;
                }
                        
            }
        }
        return null;
    }
    
    public static boolean existStripeByCodeOrName(String text) {
        return findStripeByCodeOrName(text)!=null;
    }
}
