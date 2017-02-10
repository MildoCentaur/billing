package ar.com.adriabe.web.controllers.page;

import ar.com.adriabe.model.*;
import ar.com.adriabe.model.common.IRegisterAccount;
import ar.com.adriabe.model.constant.ACCOUNTABLE_ENTITY;
import ar.com.adriabe.services.BankService;
import ar.com.adriabe.services.ClientService;
import ar.com.adriabe.services.PaymentRegister;
import ar.com.adriabe.services.PaymentService;
import ar.com.adriabe.web.controllers.adapters.PaymentJSONAdapter;
import ar.com.adriabe.web.model.MODULE_NAME;
import ar.com.adriabe.web.model.WebPageResponse;
import ar.com.adriabe.web.model.json.PaymentJSON;
import ar.com.adriabe.web.model.json.UnpaidElementJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mildo on 5/8/15.
 */
@Controller
public class PaymentPageController extends AbstractPageController {

    @Autowired
    PaymentJSONAdapter paymentAdapter;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private BankService bankService;
    @Autowired
    private PaymentRegister paymentRegister;

    @Autowired
    private ClientService clientService;


    @RequestMapping(value = "/data/list-unpaids/{accountableEntity}/{accountableId}", method = RequestMethod.GET)
    public
    @ResponseBody
    WebPageResponse showAccountableDocument(Model model, @PathVariable("accountableEntity") String accountableEntity,
                                            @PathVariable("accountableId") Long accountableId) {

        WebPageResponse response = new WebPageResponse();
        try {
            if (accountableId == null || accountableId < 0) {
                throw new Exception("Id invalid");
            }
            if (accountableEntity == null || accountableEntity.isEmpty()) {
                throw new Exception("Accountable Entity invalid");
            }

            ACCOUNTABLE_ENTITY entity = ACCOUNTABLE_ENTITY.getFromLabel(accountableEntity);
            List<IRegisterAccount> list = null;
            if (ACCOUNTABLE_ENTITY.CLIENT.compareTo(entity) == 0) {
                Client client = clientService.findClientById(accountableId);
                list = paymentService.findDocumentsToBePaidById(client);
            } else {

            }


            List<UnpaidElementJSON> jsons = new ArrayList<UnpaidElementJSON>();
            for (IRegisterAccount iRegisterAccount : list) {
                jsons.add(UnpaidElementJSON.createFromIRegisterAccount(iRegisterAccount));
            }
            response.setAjaxResponse(jsons);

        } catch (Exception ex) {
            ex.printStackTrace();
            response.addError("Datos invalidos");
        }
        return response;
    }

    @RequestMapping(value = "/register-payment", method = RequestMethod.POST, consumes = "application/json")
    public
    @ResponseBody
    WebPageResponse registerPayment(@RequestBody PaymentJSON paymentJSON, BindingResult result, SessionStatus status) {

        WebPageResponse response = new WebPageResponse();
        try {

            SupplierPayment paymentSupplier = null;
            ClientPayment clientPayment;

            if (paymentJSON.getEntityType().compareToIgnoreCase(ACCOUNTABLE_ENTITY.CLIENT.getLabel()) == 0) {
                clientPayment = paymentAdapter.convertClientPaymentJSONToPayment(paymentJSON);
                Receipt receipt = paymentRegister.registerClientPayment(clientPayment);
                paymentRegister.printReceipt(receipt);


            } else {

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            response.addError(ex.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/search-bank", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Bank> searchBanks(Model model, @RequestParam(value = "query", defaultValue = "") String query) {
        List<Bank> banks = bankService.findAllBanks(query);

        return banks;
    }

    @Override
    public MODULE_NAME getModuleName() {
        return MODULE_NAME.MODULE_HOME;
    }

    public BankService getBankService() {
        return bankService;
    }

    public void setBankService(BankService bankService) {
        this.bankService = bankService;
    }
}