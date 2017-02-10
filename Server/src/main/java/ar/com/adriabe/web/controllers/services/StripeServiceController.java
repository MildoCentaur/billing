package ar.com.adriabe.web.controllers.services;

import ar.com.adriabe.model.Stripe;
import ar.com.adriabe.model.StripeCombination;
import ar.com.adriabe.services.ProductService;
import ar.com.adriabe.services.ServiceException;
import ar.com.adriabe.web.model.WebPageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mildo on 6/13/14.
 */
@Controller
@RequestMapping
public class StripeServiceController extends AbstractServiceController {
    @Autowired
    ProductService productService;

    @RequestMapping(value = "/data/stripes", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Stripe> listProducts(Model model, @RequestParam(value = "query", required = false, defaultValue = "") String query) {
        return productService.findAllStripes(query);
    }

    @RequestMapping(value = "/data/stripe/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Stripe findProduct(Model model, @PathVariable("id") Long id) {
        return productService.findStripeById(id);
    }

    @RequestMapping(value = "/stripe/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    WebPageResponse processDeleteStripe(@RequestParam("id-stripe") Long id) {
        WebPageResponse webPageResponse = new WebPageResponse();
        try {
            logger.info("delete Product process initiated");
            productService.deleteStripe(id);
        } catch (Exception se) {
            logger.error(se.getMessage(), se);
            webPageResponse.addError(se.getMessage());
        }

        return webPageResponse;
    }

    @RequestMapping(value = "/add/stripe", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public
    @ResponseBody
    WebPageResponse processNewStripe(@ModelAttribute Stripe stripe, BindingResult result, SessionStatus status) {
        WebPageResponse webPageResponse = new WebPageResponse();
        try {
            logger.info("Save Product process initiated");


            productService.save(stripe);
        } catch (Exception se) {
            logger.error(se.getMessage(), se);
            webPageResponse.addError(se.getMessage());
        }

        return webPageResponse;
    }

    @RequestMapping(value = "data/product/stripes/combinations", method = RequestMethod.GET)
    public @ResponseBody
    List<StripeCombination> search(Model model,@RequestParam("stripeid") Long stripeid) {
        List<StripeCombination> result = new ArrayList<StripeCombination>();
        if (stripeid!=null && stripeid>0 ){
            Stripe stripe = productService.findStripeById(stripeid);
            if(stripe!=null){
                result = stripe.getCombinations();
            }
         }

        return result;
    }
}
