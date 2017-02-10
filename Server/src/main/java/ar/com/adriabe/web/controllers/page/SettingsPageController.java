package ar.com.adriabe.web.controllers.page;

import ar.com.adriabe.model.Setting;
import ar.com.adriabe.services.SettingService;
import ar.com.adriabe.web.model.MODULE_NAME;
import ar.com.adriabe.web.model.WebPageModel;
import ar.com.adriabe.web.model.WebPageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Mildo on 8/21/14.
 */
@Controller
public class SettingsPageController extends AbstractPageController {

    @Autowired
    SettingService settingService;

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String editSettingsPage(Model model) {
        WebPageModel webPageModel = createWebPageModel("Configuraciones");

        Map<String, List<Setting>> map = settingService.findSettingsGroupedByCategory();

        model.addAttribute("page", webPageModel);
        model.addAttribute("map", map);
        return "settings/edit-settings";
    }

    @RequestMapping(value = "/edit-settings", method = RequestMethod.POST, consumes = "application/json")
    public
    @ResponseBody
    WebPageResponse processEditSettingsPage(@RequestBody ArrayList<Setting> settings, BindingResult result, SessionStatus status) {
        WebPageResponse webPageResponse = new WebPageResponse();

        try {
            logger.info("Edit settengs");
            settingService.update(settings);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            webPageResponse.addError("No se pudo realizar la operaci�n.");
        }

        return webPageResponse;
    }

    @Override
    public MODULE_NAME getModuleName() {
        return MODULE_NAME.MODULE_SETTINGS;
    }
}
