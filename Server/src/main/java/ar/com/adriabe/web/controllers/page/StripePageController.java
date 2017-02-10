package ar.com.adriabe.web.controllers.page;

import ar.com.adriabe.model.Stripe;
import ar.com.adriabe.services.ProductService;
import ar.com.adriabe.web.model.MODULE_NAME;
import ar.com.adriabe.web.model.WebPageModel;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by AJMILD1 on 04/09/14.
 */
@Controller
public class StripePageController extends AbstractPageController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/list-stripes", method = RequestMethod.GET)
    public String listStripe(Model model, @RequestParam(value = "query", defaultValue = "") String query) {

        List<Stripe> stripes = productService.findAllStripes(query);
        WebPageModel webPageModel = createWebPageModel("Listado de patrones de rayado ");
        model.addAttribute("page", webPageModel);
        model.addAttribute("list", stripes);
        return "product/stripe/list-stripe";
    }

    @RequestMapping(value = "/new-stripe", method = RequestMethod.GET)
    public String newStripe(Model model) {
        return renderAddOrEditStripeForm(model, "<label>Agregar nuevo patr&oacute;n de rayado<label>", new Stripe());
    }

    @RequestMapping(value = "/edit-stripe", method = RequestMethod.GET)
    public String editStripe(Model model, @RequestParam("id") Long id) {
        Stripe stripe = productService.findStripeById(id);
        stripe.getCombinations();
        return renderAddOrEditStripeForm(model, "Editar patr&oacute;n de rayado", stripe);
    }

    private String renderAddOrEditStripeForm(Model model, String title, Stripe stripe) {
        WebPageModel webPageModel = createWebPageModel(title);
        if (stripe == null) {
            webPageModel.addErrorMessage("Rayado no encontrado.");
        }
        model.addAttribute("page", webPageModel);
        model.addAttribute("stripe", stripe);

        return "product/stripe/new-stripe";
    }


    @Override
    public MODULE_NAME getModuleName() {
        return MODULE_NAME.MODULE_FABRICA;
    }
}
