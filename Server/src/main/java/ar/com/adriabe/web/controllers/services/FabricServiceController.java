package ar.com.adriabe.web.controllers.services;

import ar.com.adriabe.model.Fabric;
import ar.com.adriabe.services.ProductService;
import ar.com.adriabe.services.ServiceException;
import ar.com.adriabe.web.model.WebPageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

/**
 * Created by Mildo on 6/13/14.
 */
@Controller
public class FabricServiceController extends AbstractServiceController {
    @Autowired
    ProductService fabricService;

    @RequestMapping(value = "/data/fabrics", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Fabric> listFabrics(Model model, @RequestParam(value = "query", required = false, defaultValue = "") String query) {
        return fabricService.findAllFabrics(query);
    }

    @RequestMapping(value = "/data/fabric/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Fabric findFabric(Model model, @PathVariable("id") Long id) {
        return fabricService.findFabricById(id);
    }

    @RequestMapping(value = "/fabric/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    WebPageResponse processDeleteFabric(@RequestParam("id-fabric") Long id) {
        WebPageResponse webPageResponse = new WebPageResponse();
        try {
            logger.info("delete Fabric process initiated");
            fabricService.deleteFabric(id);
        } catch (Exception se) {
            logger.error(se.getMessage(), se);
            webPageResponse.addError(se.getMessage());
        }

        return webPageResponse;
    }

    @RequestMapping(value = "/add/fabric", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public
    @ResponseBody
    WebPageResponse processNewFabric(@ModelAttribute Fabric fabric, BindingResult result, SessionStatus status) {
        WebPageResponse webPageResponse = new WebPageResponse();
        try {
            logger.info("Save Fabric process initiated");
            double cottonPercent = 100 - (fabric.getPolyesterPercent() + fabric.getLycraPercent());
            fabric.setCottonPercent(cottonPercent);
            fabricService.save(fabric);
        } catch (Exception se) {
            logger.error(se.getMessage(), se);
            webPageResponse.addError(se.getMessage());
        }

        return webPageResponse;
    }
}
