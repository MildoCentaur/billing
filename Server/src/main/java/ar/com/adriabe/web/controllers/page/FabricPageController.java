package ar.com.adriabe.web.controllers.page;

import ar.com.adriabe.model.Fabric;
import ar.com.adriabe.services.ProductService;
import ar.com.adriabe.web.model.MODULE_NAME;
import ar.com.adriabe.web.model.WebPageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class FabricPageController extends AbstractPageController {

    @Autowired
    ProductService productService;


    @RequestMapping(value = "/list-fabrics", method = RequestMethod.GET)
    public String  searchFabric(Model model,@RequestParam(value = "query", defaultValue = "") String query) {

        List<Fabric> fabrics = productService.findAllFabrics(query);
        WebPageModel webPageModel = createWebPageModel("Listado de Tejidos");
        model.addAttribute("page",webPageModel);
        model.addAttribute("list",fabrics);
        return "product/fabric/list-fabric";
    }

    @RequestMapping(value = "/new-fabric", method = RequestMethod.GET)
    public String  newFabric(Model model) {
        return renderAddOrEditFabricForm(model, new Fabric());
    }

    @RequestMapping(value = "/edit-fabric", method = RequestMethod.GET)
    public String  editFabric(Model model,@RequestParam("id") Long id) {
        Fabric fabric = productService.findFabricById(id);

        return renderAddOrEditFabricForm(model,  fabric);
    }

    private String renderAddOrEditFabricForm(Model model, Fabric fabric) {
        WebPageModel webPageModel = createWebPageModel("");
        String actionButton="";
        String panelHeader="";
        if(fabric == null){
            webPageModel.addErrorMessage("Tejido no encontrado.");
        }else if(fabric.getId()==0){
            webPageModel.setTitle( "Agregar Nuevo Tejido");
            actionButton="Agregar Tejido";
            panelHeader="Formulario de alta de Tejidos";
        }else{
            webPageModel.setTitle( "Editar Tejido");
            actionButton="Editar Tejido";
            panelHeader="Formulario de modificaci�n de Tejidos";
        }
        model.addAttribute("page",webPageModel);
        model.addAttribute("fabric",fabric);
        model.addAttribute("actionButton",actionButton);
        model.addAttribute("panelHeader",panelHeader);

        return "product/fabric/new-fabric";
    }


    @Override
    public MODULE_NAME getModuleName() {
        return MODULE_NAME.MODULE_FABRICA;
    }
}
