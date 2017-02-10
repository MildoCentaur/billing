package ar.com.adriabe.web.controllers.page;

import ar.com.adriabe.model.Account;
import ar.com.adriabe.model.BankAccount;
import ar.com.adriabe.model.Client;
import ar.com.adriabe.model.constant.ACCOUNTABLE_ENTITY;
import ar.com.adriabe.model.constant.ACCOUNT_TYPE;
import ar.com.adriabe.model.constant.IVA_TYPE;
import ar.com.adriabe.services.AccountService;
import ar.com.adriabe.services.BankAccountService;
import ar.com.adriabe.services.ClientService;
import ar.com.adriabe.web.model.MODULE_NAME;
import ar.com.adriabe.web.model.WebPageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Mildo on 6/13/14.
 */
@Controller
public class ClientPageController extends AbstractPageController {
    @Autowired
    ClientService clientService;

    @Autowired
    AccountService accountService;

    @Autowired
    BankAccountService bankAccountService;

    @RequestMapping(value = "/list-clients", method = RequestMethod.GET)
    public String searchClient(Model model, @RequestParam(value = "query", defaultValue = "") String query) {

        List<Client> clients = clientService.findAllClients(query);
        WebPageModel webPageModel = createWebPageModel("Listado de Clientes");
        model.addAttribute("page", webPageModel);
        model.addAttribute("list", clients);
        return "clients/list-client";
    }

    @RequestMapping(value = "/new-client", method = RequestMethod.GET)
    public String newClient(Model model) {
        return renderAddOrEditClientForm(model, "Agregar Nuevo Cliente", new Client(), "Agregar Cliente");
    }

    @RequestMapping(value = "/edit-client", method = RequestMethod.GET)
    public String editClient(Model model, @RequestParam("id") Long id) {
        Client client = clientService.findClientById(id);
        return renderAddOrEditClientForm(model, "Editar Cliente", client, "Editar Cliente");
    }

    @RequestMapping(value = "/show-account-client", method = RequestMethod.GET)
    public String showClientAccount(Model model, @RequestParam(value = "id", required = false, defaultValue = "0") Long id) {
        Client client = null;
        List<Account> accounts = null;
        String clientName = "";

        WebPageModel webPageModel = createWebPageModel("Cuenta corriente de Clientes");
        try {
            if (id != null && id > 0) {
                client = clientService.findClientById(id);
                clientName = client.getName();
                accounts = accountService.findByAccountableIdAndAccountableEntityAndAccountType(id, ACCOUNTABLE_ENTITY.CLIENT, ACCOUNT_TYPE.ACCOUNT);
            }
        } catch (Exception ex) {
            logger.error("Client not found");
            ex.printStackTrace();

        }
        model.addAttribute("list", accounts);
        model.addAttribute("entityName", clientName);
        model.addAttribute("accountType", ACCOUNT_TYPE.ACCOUNT.getValue());
        model.addAttribute("accountableEntity", ACCOUNTABLE_ENTITY.CLIENT.getLabel());
        model.addAttribute("entityId", id);
        model.addAttribute("page", webPageModel);
        return "accountables/list-account";
    }

    @RequestMapping(value = "/new-payment-client", method = RequestMethod.GET)
    public String newPaymentClient(Model model) {
        WebPageModel webPageModel = createWebPageModel("Cobro a Clientes");
        List<BankAccount> bankAccounts = bankAccountService.findAll();
        model.addAttribute("bankAccounts", bankAccounts);
        model.addAttribute("entityName", "");
        model.addAttribute("accountType", ACCOUNT_TYPE.ACCOUNT.getValue());
        model.addAttribute("accountableEntity", ACCOUNTABLE_ENTITY.CLIENT.getLabel());
        model.addAttribute("entityId", 0);
        model.addAttribute("needToPayLabel", "Facturas inpagas");
        model.addAttribute("mainPanelLabel", "Registrar el cobro a un cliente");
        model.addAttribute("page", webPageModel);
        return "payments/new-payment";
    }

    @RequestMapping(value = "/show-movement-client", method = RequestMethod.GET)
    public String showClientMovement(Model model, @RequestParam(value = "id", required = false, defaultValue = "0") Long id) {
        Client client = null;
        List<Account> accounts = null;
        String clientName = "";

        WebPageModel webPageModel = createWebPageModel("Moviminetos de Clientes");
        try {
            if (id != null && id > 0) {
                client = clientService.findClientById(id);
                clientName = client.getName();
                accounts = accountService.findByAccountableIdAndAccountableEntityAndAccountType(id, ACCOUNTABLE_ENTITY.CLIENT, ACCOUNT_TYPE.MOVEMENT);
            }
        } catch (Exception ex) {
            logger.error("Client not found");
            ex.printStackTrace();

        }
        model.addAttribute("accountType", ACCOUNT_TYPE.MOVEMENT.getValue());
        model.addAttribute("entityName", clientName);
        model.addAttribute("accountableEntity", ACCOUNTABLE_ENTITY.CLIENT.getLabel());
        model.addAttribute("entityId", id);
        model.addAttribute("list", accounts);
        model.addAttribute("page", webPageModel);
        return "accountables/list-account";
    }

    private String renderAddOrEditClientForm(Model model, String title, Client client, String actionButtonLabel) {
        WebPageModel webPageModel = createWebPageModel(title);
        if (client == null) {
            webPageModel.addErrorMessage("Cliente no encontrado.");
        }
        model.addAttribute("page", webPageModel);
        model.addAttribute("client", client);


        model.addAttribute("conditions", IVA_TYPE.values());
        model.addAttribute("actionButtonLabel", actionButtonLabel);
        return "clients/new-client";
    }


    @Override
    public MODULE_NAME getModuleName() {
        return MODULE_NAME.MODULE_CLIENT;
    }
}
