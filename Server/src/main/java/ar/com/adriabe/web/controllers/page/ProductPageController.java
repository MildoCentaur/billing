package ar.com.adriabe.web.controllers.page;

import ar.com.adriabe.model.Fabric;
import ar.com.adriabe.model.ListPrice;
import ar.com.adriabe.model.Product;
import ar.com.adriabe.model.Stripe;
import ar.com.adriabe.services.ColorService;
import ar.com.adriabe.services.PricingService;
import ar.com.adriabe.services.ProductService;
import ar.com.adriabe.web.controllers.adapters.KendoAdapterException;
import ar.com.adriabe.web.controllers.adapters.PriceFormAdapter;
import ar.com.adriabe.web.model.MODULE_NAME;
import ar.com.adriabe.web.model.WebPageModel;
import ar.com.adriabe.web.model.forms.ListPriceForm;
import ar.com.adriabe.web.model.forms.PriceForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AJMILD1 on 04/09/14.
 */
@Controller
public class ProductPageController extends AbstractPageController {

    @Autowired
    ProductService productService;

    @Autowired
    PricingService pricingService;

    @Autowired
    ColorService colorService;


    @RequestMapping(value = "/list-products", method = RequestMethod.GET)
    public String listProducts(Model model, @RequestParam(value = "query", defaultValue = "") String query) {

        List<Product> products = productService.findAll(query);
        WebPageModel webPageModel = createWebPageModel("Listado de Productos");
        model.addAttribute("page", webPageModel);
        model.addAttribute("list", products);
        return "product/list-product";
    }

    @RequestMapping(value = "/list-prices", method = RequestMethod.GET)
    public String listPrice(Model model) {
        List<PriceForm> list = new ArrayList<PriceForm>();
        WebPageModel webPageModel = createWebPageModel("Listado de Precios");
        String date = "";

        try {
            ListPrice listPrice = pricingService.findLatestListPrice();
            PriceFormAdapter priceFormAdapter = new PriceFormAdapter();
            list = priceFormAdapter.buildPriceFormListFromItemPriceList(listPrice.getPrices());
            SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
            date = formater.format(listPrice.getDate());
        } catch (KendoAdapterException e) {
            webPageModel.setErrorListDetailList(e.getErrorMessages());
        } catch (Exception e) {
            webPageModel.addErrorMessage("No se pudo realizar la operación");
        }

        model.addAttribute("page", webPageModel);
        model.addAttribute("list", list);
        model.addAttribute("date", date);

        return "product/list-prices";
    }

    @RequestMapping(value = "/new-list-price", method = RequestMethod.GET)
    public String newListPrice(Model model) {
        List<PriceForm> list = new ArrayList<PriceForm>();
        ListPriceForm listPriceForm = new ListPriceForm();
        WebPageModel webPageModel = createWebPageModel("Nuevo Listado de Precios");
        String date = "";

        try {
            ListPrice listPrice = pricingService.findLatestListPrice();
            PriceFormAdapter priceFormAdapter = new PriceFormAdapter();
            list = priceFormAdapter.buildPriceFormListFromItemPriceList(listPrice.getPrices());
            SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
            date = formater.format(listPrice.getDate());

        } catch (KendoAdapterException e) {
            webPageModel.setErrorListDetailList(e.getErrorMessages());
        } catch (Exception e) {
            webPageModel.addErrorMessage("No se pudo realizar la operación");
        }
        model.addAttribute("listPriceForm", listPriceForm);
        model.addAttribute("page", webPageModel);
        model.addAttribute("list", list);
        model.addAttribute("date", date);

        return "product/new-list-prices";
    }


    @RequestMapping(value = "/new-product", method = RequestMethod.GET)
    public String newProduct(Model model) {
        String title = "Agregar Nuevo Producto";
        PriceForm priceForm = new PriceForm();
        WebPageModel webPageModel = createWebPageModel(title);
        List<Fabric> fabrics = productService.findAllFabrics("");
        List<Stripe> stripes = productService.findAllStripes("");
        model.addAttribute("page", webPageModel);
        model.addAttribute("productForm", priceForm);
        model.addAttribute("fabrics", fabrics);
        model.addAttribute("stripes", stripes);


        return "product/new-product";
    }

    @Override
    public MODULE_NAME getModuleName() {
        return MODULE_NAME.MODULE_PRODUCTOS;
    }
}
