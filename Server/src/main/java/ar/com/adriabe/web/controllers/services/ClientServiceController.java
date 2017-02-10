package ar.com.adriabe.web.controllers.services;

import ar.com.adriabe.model.Client;
import ar.com.adriabe.services.ClientService;
import ar.com.adriabe.services.ServiceException;
import ar.com.adriabe.web.controllers.validators.ClientFormValidator;
import ar.com.adriabe.web.controllers.validators.KendoValidationException;
import ar.com.adriabe.web.model.WebPageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mildo on 6/13/14.
 */
@Controller
public class ClientServiceController extends AbstractServiceController {
    @Autowired
    ClientFormValidator clientFormValidator;

    @Autowired
    ClientService clientService;

    @RequestMapping(value = "/data/clients", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Client> listClients(Model model) {
        List<Client> clients = new ArrayList<Client>();
        Client client = new Client();
        client.setName("Alejandro Mildiner");
        clients.add(client);

        return clients;
    }

    @RequestMapping(value = "/data/client/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Client findClient(Model model, @PathVariable("id") Long id) {

        return clientService.findClientById(id);
    }

    @RequestMapping(value = "/client/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    WebPageResponse processDeleteClient(@RequestParam("id-client") Long idClient) {
        WebPageResponse webPageResponse = new WebPageResponse();
        try {
            logger.info("Save Client process initiated");
            clientService.delete(idClient);
        } catch (ServiceException se) {
            logger.error(se.getMessage(), se);
            webPageResponse.addError(se.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            webPageResponse.addError("Error de comunicaci?n con el Servidor.");
        }


        return webPageResponse;
    }

    @RequestMapping(value = "/add/client", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public
    @ResponseBody
    WebPageResponse processNewClient(@ModelAttribute Client client, BindingResult result, SessionStatus status) {
        WebPageResponse webPageResponse = new WebPageResponse();
        try {
            logger.info("Save Client process initiated");
            client = clientFormValidator.validateForm(client);
            clientService.save(client);
        } catch (ServiceException se) {
            logger.error(se.getMessage(), se);
            webPageResponse.addError(se.getMessage());
        } catch (KendoValidationException ve) {
            webPageResponse.setErrorListDetailList(ve.getErrorMessages());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            webPageResponse.addError("Error de comunicación con el Servidor.");
        }


        return webPageResponse;
    }
}
