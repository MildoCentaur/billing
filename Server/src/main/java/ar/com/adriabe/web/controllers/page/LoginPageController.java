package ar.com.adriabe.web.controllers.page;

import ar.com.adriabe.components.PasswordRecoverer;
import ar.com.adriabe.services.ServiceException;
import ar.com.adriabe.web.model.MODULE_NAME;
import ar.com.adriabe.web.model.WebPageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


@Controller("loginPageController")
public class LoginPageController extends AbstractPageController {

    @Autowired
    PasswordRecoverer recoverer;

    public LoginPageController() {

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        return "admin/login";
    }

    @RequestMapping(value = "/forgot", method = RequestMethod.GET)
    public String forgotPage(Model model) {
        return "admin/forgot-password";
    }

    @RequestMapping(value = "/forgot-password-process", method = RequestMethod.POST)
    public String forgotPasswordProcess(@RequestParam("username") String username,
                                        @RequestParam("email") String email) {
        logger.debug("I'm in the forgotPasswordCheck method");

        try {
            recoverer.recover(username, email);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return "redirect:login.html?forgot_password=1";
    }


//    @RequestMapping(value = "/index", method = RequestMethod.GET)
//    public String indexPage(Model model) {
//        return "redirect:/home.html";
//    }
//
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String defaultPage(Model model) {
//        return "redirect:/home.html";
//    }

    @RequestMapping(value = "/time", method = RequestMethod.GET)
    public
    @ResponseBody
    WebPageResponse timePage(Model model) {
        WebPageResponse response = new WebPageResponse();
        response.setDate(new Date());
        return response;
    }

    @Override
    public MODULE_NAME getModuleName() {
        return MODULE_NAME.MODULE_LOGIN;
    }
}
