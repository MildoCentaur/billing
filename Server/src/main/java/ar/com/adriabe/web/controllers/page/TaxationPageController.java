package ar.com.adriabe.web.controllers.page;

import ar.com.adriabe.components.taxation.IVAPurchaseTaxReporter;
import ar.com.adriabe.components.taxation.IVATaxContext;
import ar.com.adriabe.model.PurchaseTaxation;
import ar.com.adriabe.model.constant.EurekaConstants;
import ar.com.adriabe.web.model.MODULE_NAME;
import ar.com.adriabe.web.model.WebPageModel;
import ar.com.adriabe.web.model.WebPageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Mildo on 8/21/14.
 */
@Controller
public class TaxationPageController extends AbstractPageController{

    private IVAPurchaseTaxReporter ivaPurchaseTaxReporter;

    @Autowired
    public TaxationPageController(IVAPurchaseTaxReporter ivaPurchaseTaxReporter) {
        this.ivaPurchaseTaxReporter = ivaPurchaseTaxReporter;
    }

    @RequestMapping(value = "/list-buy-tax", method = RequestMethod.GET)
    public String listBuyTax(Model model) {
        WebPageModel webPageModel = createWebPageModel("Listado de IVA Compras");
        SimpleDateFormat format = new SimpleDateFormat(EurekaConstants.PATTERN_READABLE_SHORT);
        model.addAttribute("webPage",webPageModel);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        Date date = new Date();
        model.addAttribute("dateFrom", format.format(date));
        model.addAttribute("dateTo", format.format(calendar.getTime()));
        model.addAttribute("purchases", new ArrayList<PurchaseTaxation>());
        return "taxation/list-buy-tax";
    }

    @RequestMapping(value = "/list-buy-tax-process", method = RequestMethod.POST)
    public @ResponseBody WebPageResponse listBuyTaxProcess(@RequestParam("startPeriod") Date startPeriod,@RequestParam("endPeriod") Date endPeriod) {
        WebPageResponse  response = new WebPageResponse();
        IVATaxContext context = new IVATaxContext();
        context.setStartPeriod(startPeriod);
        context.setEndPeriod(endPeriod);
        List<PurchaseTaxation> list = ivaPurchaseTaxReporter.report(context);
        response.setAjaxResponse(list);
        return response;
    }

    @RequestMapping(value = "/list-sell-tax", method = RequestMethod.GET)
    public String listSellTax(Model model) {
        WebPageModel webPageModel = createWebPageModel("Listado de IVA Ventas");
        model.addAttribute("webPage",webPageModel);
        return "taxation/list-sell-tax";
    }

    @RequestMapping(value = "/list-sell-tax-process", method = RequestMethod.POST)
    public @ResponseBody WebPageResponse listSellTaxProcess(@RequestParam("startPeriod") Date startPeriod,@RequestParam("endPeriod") Date endPeriod) {
        WebPageResponse  response = new WebPageResponse();
        IVATaxContext context = new IVATaxContext();
        context.setStartPeriod(startPeriod);
        context.setEndPeriod(endPeriod);
       // List<SalesTaxation> list = ivaSalesTaxReporter.report(context);
       // response.setAjaxResponse(list);
        return response;
    }

    @Override
    public MODULE_NAME getModuleName() {
        return MODULE_NAME.TAXATION;
    }
}
