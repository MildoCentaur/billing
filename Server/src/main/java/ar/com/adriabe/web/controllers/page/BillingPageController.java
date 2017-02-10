package ar.com.adriabe.web.controllers.page;

import ar.com.adriabe.daos.DaoException;
import ar.com.adriabe.model.Bill;
import ar.com.adriabe.model.constant.IVA_TYPE;
import ar.com.adriabe.services.BillingService;
import ar.com.adriabe.web.controllers.adapters.BillFormAdapter;
import ar.com.adriabe.web.controllers.adapters.KendoAdapterException;
import ar.com.adriabe.web.model.MODULE_NAME;
import ar.com.adriabe.web.model.WebPageModel;
import ar.com.adriabe.web.model.WebPageResponse;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

/**
 * Created by Mildo on 8/21/14.
 */
@Controller("billingPageController")
public class BillingPageController extends AbstractPageController{

    @Autowired
    private BillingService billingService;


    @Autowired
    private BillFormAdapter billFormAdapter;

    @RequestMapping(value = "/billing", method = RequestMethod.GET)
    public String newBill(Model model) throws DaoException {
        WebPageModel webPageModel = createWebPageModel("Facturar");
        model.addAttribute("ivaCategories", IVA_TYPE.values());
        model.addAttribute("bill", new Bill());
        model.addAttribute("billANumber",billingService.getNextBillANumber() );
        model.addAttribute("billBNumber",billingService.getNextBillBNumber());
        model.addAttribute("orderANumber",billingService.getNextOrderANumber());
        model.addAttribute("orderBNumber",billingService.getNextOrderBNumber());
        model.addAttribute("isShowBill", false);
        model.addAttribute("billType", "X");
        model.addAttribute("page",webPageModel);
        return "billing/new-bill";
    }

    @RequestMapping(value = "/add/bill", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public
    @ResponseBody
    WebPageResponse processNewBill(@ModelAttribute Bill bill, BindingResult result, SessionStatus status) {

        WebPageResponse webPageResponse = new WebPageResponse();
        try {
            logger.info("Save Bill process initiated");
            bill = billFormAdapter.adapter(bill);
            billingService.save(bill);
        } catch (KendoAdapterException ve) {
            webPageResponse.setErrorListDetailList(ve.getErrorMessages());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            webPageResponse.addError(StringEscapeUtils.escapeHtml("No se pudo realizar la operación."));
        }


        return webPageResponse;
    }

    @RequestMapping(value = "print-bill", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public
    @ResponseBody
    WebPageResponse processNewBill(@RequestParam("billId") Long id, BindingResult result, SessionStatus status) {

        WebPageResponse webPageResponse = new WebPageResponse();
        try {
            logger.info("re print bill process initiated");
            Bill bill = billingService.findBillById(id);
            billingService.printBill(bill);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            webPageResponse.addError(StringEscapeUtils.escapeHtml("No se pudo realizar la operación."));
        }


        return webPageResponse;
    }

    @RequestMapping(value = "/list-bills", method = RequestMethod.GET)
    public String listBills(Model model) {
        WebPageModel webPageModel = createWebPageModel("Listado de facturas");
        List<Bill> list = billingService.findAll();
        model.addAttribute("list",list);
        model.addAttribute("page",webPageModel);
        return "billing/list-bill";
    }

    @RequestMapping(value = "/show-bill", method = RequestMethod.GET)
    public String showBill(Model model, @RequestParam("id") Long id) {
        WebPageModel webPageModel = createWebPageModel("Facturar");
        model.addAttribute("ivaCategories", IVA_TYPE.values());
        Bill bill = billingService.findBillById(id);
        model.addAttribute("bill", bill);
        model.addAttribute("isShowBill", true);
        model.addAttribute("billType", bill.getBillType());

        model.addAttribute("page", webPageModel);
        return "billing/new-bill";
    }

    @RequestMapping(value = "/cancel/bill", method = RequestMethod.POST)
    public @ResponseBody WebPageResponse cancelBill (@RequestParam("id") Long id) {
        WebPageResponse webPageResponse = new WebPageResponse();
        try {
            logger.info("Save Bill process initiated");
            billingService.cancelBill(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            webPageResponse.addError(StringEscapeUtils.escapeHtml("No se pudo realizar la operación."));
        }
        return webPageResponse;
    }

    @RequestMapping(value = "/reestablish/bill", method = RequestMethod.POST)
    public
    @ResponseBody
    WebPageResponse uncancelBill(@RequestParam("id") Long id) {
        WebPageResponse webPageResponse = new WebPageResponse();
        try {
            logger.info("uncancel Bill process initiated");
            billingService.uncancelBill(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            webPageResponse.addError(StringEscapeUtils.escapeHtml("No se pudo realizar la operación."));
        }
        return webPageResponse;
    }

    @Override
    public MODULE_NAME getModuleName() {
        return MODULE_NAME.MODULE_BILLING;
    }

    public BillingService getBillingService() {
        return billingService;
    }

    public void setBillingService(BillingService billingService) {
        this.billingService = billingService;
    }
}
