package ar.com.adriabe.web.controllers.services;

import ar.com.adriabe.model.Supplier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Mildo on 6/13/14.
 */
@Controller
@RequestMapping(value = "/data")
public class SupplierServiceController extends AbstractServiceController {

    @RequestMapping(value = "/suppliers", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Supplier> listSuppliers(Model model) {
        return null;
    }

    @RequestMapping(value = "/supplier/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Supplier findSupplier(Model model, @PathVariable("id") Long id) {
        return null;
    }
}
