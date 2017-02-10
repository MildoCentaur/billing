package ar.com.adriabe.web.controllers.services;

import ar.com.adriabe.components.InventoryAccountant;
import ar.com.adriabe.model.*;
import ar.com.adriabe.services.ColorService;
import ar.com.adriabe.services.PricingService;
import ar.com.adriabe.services.ProductService;
import ar.com.adriabe.services.ServiceException;
import ar.com.adriabe.web.controllers.adapters.KendoAdapterException;
import ar.com.adriabe.web.controllers.adapters.PriceFormAdapter;
import ar.com.adriabe.web.controllers.adapters.ProductFamilyJSONAdapter;
import ar.com.adriabe.web.controllers.adapters.ProductJSONAdapter;
import ar.com.adriabe.web.model.WebPageResponse;
import ar.com.adriabe.web.model.forms.ListPriceForm;
import ar.com.adriabe.web.model.forms.PriceForm;
import ar.com.adriabe.web.model.json.ProductFamilyJSON;
import ar.com.adriabe.web.model.json.ProductJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mildo on 6/13/14.
 */
@Controller
public class ProductServiceController extends AbstractServiceController {

    @Autowired
    ProductService productService;

    @Autowired
    ColorService colorService;

    @Autowired
    PricingService pricingService;

    @Autowired
    @Qualifier("inventoryAccountantImpl")
    InventoryAccountant inventoryAccountant;


    @RequestMapping(value = "/data/products", method = RequestMethod.GET)
    public
    @ResponseBody
    List<ProductJSON> listProducts(Model model, @RequestParam(value = "query", required = false, defaultValue = "") String query) throws KendoAdapterException {
        ProductJSONAdapter adapter = new ProductJSONAdapter();
        return adapter.buildProductJSONListFromProductList(productService.findAll(query));
    }

    @RequestMapping(value = "/data/product-prices", method = RequestMethod.GET)
    public
    @ResponseBody
    List<ProductFamilyJSON> listPrice(Model model, @RequestParam(value = "query", required = false, defaultValue = "") String query) throws KendoAdapterException {
        ProductFamilyJSONAdapter adapter = new ProductFamilyJSONAdapter();
        return adapter.buildProductFamilyJSONListFromProductList(productService.findAllProductFamilies(query));
    }

    @RequestMapping(value = "/data/product/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Product findProduct(Model model, @PathVariable("id") Long id) {
        Product product;
        try {
            product = productService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            product = new Product();
        }
        return product;
    }

    @RequestMapping(value = "/product/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    WebPageResponse processDeleteProduct(@RequestParam("id-product") Long id) {
        WebPageResponse webPageResponse = new WebPageResponse();
        try {
            logger.info("delete Product process initiated");
            productService.deleteProduct(id);
        } catch (Exception se) {
            logger.error(se.getMessage(), se);
            webPageResponse.addError(se.getMessage());
        }

        return webPageResponse;
    }

    @RequestMapping(value = "/add/product-family", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public
    @ResponseBody
    WebPageResponse processAddProductFamily(@ModelAttribute PriceForm form, BindingResult result, SessionStatus status) {
        WebPageResponse webPageResponse = new WebPageResponse();
        try {
            logger.info("Add new Product Family process initiated");
            if (form.getFabric() == 0) {
                throw new ServiceException("Tejido invalido.");
            }
            Fabric fabric = productService.findFabricById(form.getFabric());
            Stripe stripe = (form.getStripe() == 0) ? null : productService.findStripeById(form.getStripe());
            pricingService.addAllProductFamilies(fabric, stripe, form.getPriceWhite(), form.getPriceLight(), form.getPriceDark(), form.getPriceSpecial());
        } catch (ServiceException se) {
            logger.error(se.getMessage(), se);
            webPageResponse.addError(se.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            webPageResponse.addError("Ocurrió un error al registrar el producto.");
        }

        return webPageResponse;
    }


    @RequestMapping(value = "/list-price", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public
    @ResponseBody
    WebPageResponse processAddListPrice(@ModelAttribute ListPriceForm form, BindingResult result, SessionStatus status) {
        WebPageResponse webPageResponse = new WebPageResponse();
        try {
            logger.info("Add new Product process initiated");
            PriceFormAdapter priceFormAdapter = new PriceFormAdapter();
            ListPrice newListPrice = pricingService.findLatestListPrice();
            priceFormAdapter.buildListPriceItemsListFromListPriceForm(form, newListPrice);
            pricingService.updateListPrice(newListPrice);
        } catch (KendoAdapterException ae) {
            ae.printStackTrace();
            logger.error(ae.getMessage(), ae);
            webPageResponse.addAllErrors(ae.getErrorMessages());
        } catch (Exception se) {
            se.printStackTrace();
            logger.error(se.getMessage(), se);
            webPageResponse.addError(se.getMessage());
        }

        return webPageResponse;
    }


    @RequestMapping(value = "/update-stock", method = RequestMethod.POST)
    public
    @ResponseBody
    WebPageResponse updateProductStock(@RequestParam("barcode") String code,
                                       @RequestParam("amount") int amount) {
        WebPageResponse webPageResponse = new WebPageResponse();
        try {
            logger.info("Adds to the given barcode the given amount");
            Barcode barcode = inventoryAccountant.incrementProductStock(code, amount);
            Map<String, String> map = new HashMap<String, String>();
            map.put("barcode", barcode.getCode());
            webPageResponse.setAjaxResponse(map);
        } catch (ServiceException se) {
            se.printStackTrace();
            logger.error(se.getMessage(), se);
            webPageResponse.addError(se.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            webPageResponse.addError("Error de comunicación con el Servidor.");
        }


        return webPageResponse;
    }


    @RequestMapping(value = "/data/product/increment", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public
    @ResponseBody
    WebPageResponse processIncrementProductStock(@RequestParam(value = "stripeCombinationId", required = false, defaultValue = "0") Long stripeCombinationId,
                                                 @RequestParam("fabricId") Long fabricId,
                                                 @RequestParam("colorId") Long colorId,
                                                 @RequestParam(value = "stripeId", required = false, defaultValue = "0") Long stripeId,
                                                 @RequestParam("weight") String weightStr,
                                                 @RequestParam("partida") int partida) {
        WebPageResponse webPageResponse = new WebPageResponse();
        try {
            logger.info("Increments Product stock process initiated");
            weightStr = weightStr.replace(",", ".");
            Double weight = Double.parseDouble(weightStr);

            String barcode = productService.incrementStock(fabricId, colorId, stripeId, stripeCombinationId, weight, partida);
            Map<String, String> map = new HashMap<String, String>();
            map.put("barcode", barcode);
            webPageResponse.setAjaxResponse(map);
        } catch (ServiceException se) {
            se.printStackTrace();
            logger.error(se.getMessage(), se);
            webPageResponse.addError(se.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            webPageResponse.addError("Error de comunicaci�n con el Servidor.");
        }


        return webPageResponse;
    }


}
