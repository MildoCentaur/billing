package ar.com.adriabe.web.controllers.page;

import ar.com.adriabe.model.Account;
import ar.com.adriabe.model.constant.ACCOUNTABLE_ENTITY;
import ar.com.adriabe.model.constant.ACCOUNT_TYPE;
import ar.com.adriabe.services.AccountService;
import ar.com.adriabe.web.controllers.adapters.AccountAdapter;
import ar.com.adriabe.web.model.MODULE_NAME;
import ar.com.adriabe.web.model.WebPageResponse;
import ar.com.adriabe.web.model.json.AccountJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Mildo on 10/17/14.
 */
@Controller
public class AccountablePageController extends AbstractPageController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/show-accountable-document", method = RequestMethod.GET)
    public String showAccountableDocument(Model model, @RequestParam("id") Long id) {
        try {
            if (id == null || id < 0) {
                throw new Exception("Id invalid");
            }

            Account account = accountService.findById(id);
            if (account.getDeliveryOrder() != null) {
                return "redirect:show-delivery-order.html?id=" + account.getDeliveryOrder().getId();
            }
            if (account.getPayment() != null) {
                return "redirect:show-payment.html?id=" + account.getPayment().getId();
            }
            if (account.getBill() != null) {
                return "redirect:show-bill.html?id=" + account.getBill().getId();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "redirect:home?error=1";
    }

    @RequestMapping(value = "/data/list-account/{accountableEntity}/{accountType}/{accountableId}", method = RequestMethod.GET)
    public
    @ResponseBody
    WebPageResponse
    showAccountableDocument(Model model, @PathVariable("accountableEntity") String accountableEntity,
                            @PathVariable("accountType") int accountType,
                            @PathVariable("accountableId") Long accountableId) {
        WebPageResponse response = new WebPageResponse();
        try {
            if (accountableId == null || accountableId < 0) {
                throw new Exception("Id invalid");
            }
            if (accountableEntity == null || accountableEntity.isEmpty()) {
                throw new Exception("Accountable Entity invalid");
            }
            if (accountType == 0) {
                throw new Exception("Account type invalid");
            }

            ACCOUNTABLE_ENTITY entity = ACCOUNTABLE_ENTITY.getFromLabel(accountableEntity);
            ACCOUNT_TYPE account = ACCOUNT_TYPE.getByOrdinal(accountType);

            List<Account> list = accountService.findByAccountableIdAndAccountableEntityAndAccountType(accountableId, entity, account);
            List<AccountJSON> jsons = AccountAdapter.buildAccountJsonListFromAccountLinst(list);
            response.setAjaxResponse(jsons);

        } catch (Exception ex) {
            ex.printStackTrace();
            response.addError("Datos invalidos");
        }
        return response;
    }

    @Override
    public MODULE_NAME getModuleName() {
        return MODULE_NAME.MODULE_HOME;
    }
}
