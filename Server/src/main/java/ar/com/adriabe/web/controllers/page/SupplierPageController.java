package ar.com.adriabe.web.controllers.page;

import ar.com.adriabe.model.Account;
import ar.com.adriabe.model.BankAccount;
import ar.com.adriabe.model.Supplier;
import ar.com.adriabe.model.constant.ACCOUNTABLE_ENTITY;
import ar.com.adriabe.model.constant.ACCOUNT_TYPE;
import ar.com.adriabe.model.constant.IVA_TYPE;
import ar.com.adriabe.services.AccountService;
import ar.com.adriabe.services.BankAccountService;
import ar.com.adriabe.services.SupplierService;
import ar.com.adriabe.web.controllers.validators.KendoValidationException;
import ar.com.adriabe.web.controllers.validators.SupplierFormValidator;
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

import java.util.List;

/**
 * Created by Mildo on 8/25/14.
 */
@Controller
public class SupplierPageController  extends AbstractPageController{
    @Autowired
    SupplierService supplierService;

    @Autowired
    AccountService accountService;

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    SupplierFormValidator supplierFormValidator;

    @RequestMapping(value = "/list-suppliers", method = RequestMethod.GET)
    public String  searchSupplier(Model model,@RequestParam(value = "query", defaultValue = "") String query) {

        List<Supplier> suppliers = supplierService.findAllSuppliers(query);
        WebPageModel webPageModel = createWebPageModel("Listado de Proveedores");
        model.addAttribute("page",webPageModel);
        model.addAttribute("list",suppliers);
        return "suppliers/list-supplier";
    }

    @RequestMapping(value = "/new-supplier", method = RequestMethod.GET)
    public String  newSupplier(Model model) {

        return renderAddOrEditSupplierForm(model,"Agregar Nuevo Proveedor", new Supplier(),"Agregar Proveedor");
    }

    private String renderAddOrEditSupplierForm(Model model, String title, Supplier supplier,String actionButtonLabel) {
        WebPageModel webPageModel = createWebPageModel(title);
        if(supplier == null){
            webPageModel.addErrorMessage("Proveedor no encontrado.");
        }
        model.addAttribute("page",webPageModel);
        model.addAttribute("supplier",supplier);


        model.addAttribute("conditions", IVA_TYPE.values());
        model.addAttribute("actionButtonLabel",actionButtonLabel);
        return "suppliers/new-supplier";
    }

    @RequestMapping(value = "/edit-supplier", method = RequestMethod.GET)
    public String  editSupplier(Model model,@RequestParam("id") Long id) {
        Supplier supplier= supplierService.findSupplierById(id);
        return renderAddOrEditSupplierForm(model,"Editar Proveedor",supplier,"Editar Proveedor");
    }

    @RequestMapping(value = "/new-supplier", method = RequestMethod.POST)
    public @ResponseBody
    WebPageResponse processNewSupplier(Model model,@RequestParam(value = "supplier") Supplier supplier) {
        WebPageResponse webPageResponse = new WebPageResponse();
        try {
            logger.info("Save Supplier process initiated");
            supplier = supplierFormValidator.validateForm(supplier);
            supplierService.save(supplier);
        }catch (KendoValidationException ve){
            logger.error(ve.getMessage(),ve);
            webPageResponse.setErrorListDetailList(ve.getErrorMessages());
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            webPageResponse.addError(e.getMessage());
        }
        model.addAttribute("page",webPageResponse);

        return webPageResponse;
    }

    @RequestMapping(value = "/new-payment-supplier", method = RequestMethod.GET)
    public String newPaymentClient(Model model) {
        WebPageModel webPageModel = createWebPageModel("Pago a proveedores");
        List<BankAccount> bankAccounts = bankAccountService.findAll();
        model.addAttribute("bankAccounts", bankAccounts);
        model.addAttribute("entityName", "");
        model.addAttribute("accountType", ACCOUNT_TYPE.ACCOUNT.getValue());
        model.addAttribute("accountableEntity", ACCOUNTABLE_ENTITY.SUPPLIER.getLabel());
        model.addAttribute("entityId", 0);
        model.addAttribute("needToPayLabel", "Mercadería inpaga.");
        model.addAttribute("mainPanelLabel", "Generar ordenes de pago");
        model.addAttribute("page", webPageModel);
        return "payments/new-payment";
    }

    @RequestMapping(value = "/show-account-supplier", method = RequestMethod.GET)
    public String  showSupplierAccount(Model model,@RequestParam(value="id",required = false,defaultValue = "0") Long id) {
        Supplier supplier = null;
        List<Account> accounts = null;
        String supplierName = "";

        WebPageModel webPageModel = createWebPageModel("Cuenta corriente de Proveedores");
        try {
            if (id != null && id > 0) {
                supplier = supplierService.findSupplierById(id);
                supplierName=supplier.getName();
                accounts = accountService.findByAccountableIdAndAccountableEntityAndAccountType(id,ACCOUNTABLE_ENTITY.SUPPLIER,ACCOUNT_TYPE.ACCOUNT);
            }
        }catch (Exception ex){
            logger.error("Supplier not found");
            ex.printStackTrace();

        }
        model.addAttribute("list",accounts);
        model.addAttribute("accountType", ACCOUNT_TYPE.ACCOUNT.getValue());
        model.addAttribute("accountableEntity", ACCOUNTABLE_ENTITY.SUPPLIER.getLabel());
        model.addAttribute("entityName",supplierName);
        model.addAttribute("entityId",id);
        model.addAttribute("page",webPageModel);
        return "accountables/list-account";
    }

    @RequestMapping(value = "/show-movement-supplier", method = RequestMethod.GET)
    public String  showSupplierMovement(Model model,@RequestParam(value="id",required = false,defaultValue = "0") Long id) {
        Supplier supplier = null;
        List<Account> accounts = null;
        String supplierName = "";

        WebPageModel webPageModel = createWebPageModel("Moviminetos de Proveedores");
        try {
            if (id != null && id > 0) {
                supplier = supplierService.findSupplierById(id);
                supplierName=supplier.getName();
                accounts = accountService.findByAccountableIdAndAccountableEntityAndAccountType(id,ACCOUNTABLE_ENTITY.SUPPLIER,ACCOUNT_TYPE.MOVEMENT);
            }
        }catch (Exception ex){
            logger.error("Supplier not found");
            ex.printStackTrace();

        }
        model.addAttribute("list",accounts);
        model.addAttribute("accountType", ACCOUNT_TYPE.MOVEMENT.getValue());
        model.addAttribute("accountableEntity", ACCOUNTABLE_ENTITY.SUPPLIER.getLabel());
        model.addAttribute("entityName",supplierName);
        model.addAttribute("entityId",id);
        model.addAttribute("page",webPageModel);
        return "accountables/list-account";
    }
    
    @Override
    public MODULE_NAME getModuleName() {
        return MODULE_NAME.MODULE_SUPPLIER;
    }
}
