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
public class LogsPageController extends AbstractPageController{

    @RequestMapping(value = "/list-logs", method = RequestMethod.GET)
    public String logsPage(Model model) {
        WebPageModel webPageModel = createWebPageModel("Actividades de usuarios");
        model.addAttribute("webPage",webPageModel);
        return "logs/list-logs";
    }



    @Override
    public MODULE_NAME getModuleName() {
        return MODULE_NAME.MODULE_LOGS;
    }
}
