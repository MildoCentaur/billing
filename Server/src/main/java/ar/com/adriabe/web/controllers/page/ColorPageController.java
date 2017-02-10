package ar.com.adriabe.web.controllers.page;

import ar.com.adriabe.model.Color;
import ar.com.adriabe.model.constant.COLOR_TYPE;
import ar.com.adriabe.services.ColorService;
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
 * Created by AJMILD1 on 04/09/14.
 */
@Controller
public class ColorPageController extends AbstractPageController {

    @Autowired
    ColorService colorService;

//    @Autowired
//    ColorFormValidator colorFormValidator;

    @RequestMapping(value = "/list-colors", method = RequestMethod.GET)
    public String searchClient(Model model, @RequestParam(value = "query", defaultValue = "") String query) {

        List<Color> colors = colorService.findAllColors(query);
        WebPageModel webPageModel = createWebPageModel("Listado de Colores");
        model.addAttribute("page", webPageModel);
        model.addAttribute("list", colors);
        return "product/color/list-color";
    }

    @RequestMapping(value = "/new-color", method = RequestMethod.GET)
    public String newClient(Model model) {
        return renderAddOrEditColorForm(model, "Agregar Nuevo Color", new Color());
    }

    @RequestMapping(value = "/edit-color", method = RequestMethod.GET)
    public String editClient(Model model, @RequestParam("id") Long id) {
        Color color = colorService.findColorById(id);

        return renderAddOrEditColorForm(model, "Editar Color", color);
    }

    private String renderAddOrEditColorForm(Model model, String title, Color color) {
        WebPageModel webPageModel = createWebPageModel(title);
        if (color == null) {
            webPageModel.addErrorMessage("Color no encontrado.");
        }
        model.addAttribute("page", webPageModel);
        model.addAttribute("color", color);

        model.addAttribute("listColorTypes", COLOR_TYPE.values());
        return "product/color/new-color";
    }


    @Override
    public MODULE_NAME getModuleName() {
        return MODULE_NAME.MODULE_FABRICA;
    }
}
