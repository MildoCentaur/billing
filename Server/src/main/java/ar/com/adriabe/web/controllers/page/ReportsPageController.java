package ar.com.adriabe.web.controllers.page;

import ar.com.adriabe.web.model.MODULE_NAME;
import ar.com.adriabe.web.model.WebPageModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Mildo on 8/21/14.
 */
@Controller
public class ReportsPageController extends AbstractPageController{

    @RequestMapping(value = "/show-reports", method = RequestMethod.GET)
    public String reportsPage(Model model) {
        WebPageModel webPageModel = createWebPageModel("Informes");
        model.addAttribute("webPage",webPageModel);
        return "reports/show-reports";
    }



    @Override
    public MODULE_NAME getModuleName() {
        return MODULE_NAME.MODULE_REPORTS;
    }
}
