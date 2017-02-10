package ar.com.adriabe.web.controllers.page;

import ar.com.adriabe.model.Account;
import ar.com.adriabe.services.AccountService;
import ar.com.adriabe.web.model.MODULE_NAME;
import ar.com.adriabe.web.model.WebPageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

/**
 * Created by Mildo on 11/22/14.
 */
@Controller
public class CashierPageController extends AbstractPageController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/cashier", method = RequestMethod.GET)
    public String showCashier(Model model) {

        Double totalSum = 0d, totalOutcome = 0d, totalIncome = 0d;

        List<Account> incomes = accountService.findIncomesMovementByDate(new Date());

        List<Account> outcomes = accountService.findSuppliersMovementByDate(new Date());
        for (Account income : incomes) {
            totalIncome = totalIncome + income.getCredit();
        }
        for (Account outcome : outcomes) {
            totalOutcome = totalOutcome + outcome.getDebit();
        }
        totalSum = totalIncome + totalOutcome;
        WebPageModel webPageModel = createWebPageModel("Movimientos de Caja");
        model.addAttribute("page", webPageModel);
        model.addAttribute("outcomes", outcomes);
        model.addAttribute("incomes", incomes);
        model.addAttribute("totalSum", totalSum);
        model.addAttribute("totalOutcome", totalOutcome);
        model.addAttribute("totalIncome", totalIncome);
        return "cashier/cashier";
    }

    @Override
    public MODULE_NAME getModuleName() {
        return MODULE_NAME.MODULE_CASHIER;
    }
}
