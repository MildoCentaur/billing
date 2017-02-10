package ar.com.adriabe.web.controllers.services;

import ar.com.adriabe.model.Color;
import ar.com.adriabe.services.ColorService;
import ar.com.adriabe.services.ServiceException;
import ar.com.adriabe.web.controllers.validators.ColorFormValidator;
import ar.com.adriabe.web.controllers.validators.KendoValidationException;
import ar.com.adriabe.web.model.WebPageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
public class ColorServiceController extends AbstractServiceController {
    @Autowired
    ColorFormValidator colorFormValidator;

    @Autowired
    ColorService colorService;

    @RequestMapping(value = "/data/colors", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Color> listColors(Model model) {

        return colorService.findAllColors("");
    }

    @RequestMapping(value = "/data/color/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Color findColor(Model model, @PathVariable("id") Long id) {
        return colorService.findColorById(id);
    }

    @RequestMapping(value = "/data/color", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Color> findColor(Model model, @RequestParam("code") String code) {
        return colorService.findColorLikeCode(code);
    }

    @RequestMapping(value = "/color/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    WebPageResponse processDeleteColor(@RequestParam("id-color") Long idColor) {
        WebPageResponse webPageResponse = new WebPageResponse();
        try {
            logger.info("delete Color process initiated");
            colorService.delete(idColor);
        } catch (ServiceException se) {
            logger.error(se.getMessage(), se);
            webPageResponse.addError(se.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            webPageResponse.addError("Error de comunicaci?n con el Servidor.");
        }


        return webPageResponse;
    }

    @RequestMapping(value = "/add/color", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public
    @ResponseBody
    WebPageResponse processNewColor(@ModelAttribute Color color, BindingResult result, SessionStatus status) {
        WebPageResponse webPageResponse = new WebPageResponse();
        try {
            logger.info("Save Color process initiated");
            color = colorFormValidator.validateForm(color);
            colorService.save(color);
        } catch (DataIntegrityViolationException ie){
            String msg = ie.getMessage().toLowerCase();
            if (msg.matches("(.*)duplicate entry(.*) coordinate")){
                webPageResponse.addError("Valor de coordenada repetido.");
            }else if (msg.matches("(.*)duplicate entry(.*) code")){
                webPageResponse.addError("Valor de código repetido.");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            webPageResponse.addError("Error de comunicación con el Servidor.");
        }


        return webPageResponse;
    }
}
