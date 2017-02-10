package ar.com.adriabe.web.controllers.page;

import ar.com.adriabe.model.User;
import ar.com.adriabe.web.model.MODULE_NAME;
import ar.com.adriabe.web.model.WebPageModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;


/**
 * Created by Mildo on 6/13/14.
 */
public abstract class AbstractPageController {

    protected Logger logger = LogManager.getLogger(AbstractPageController.class);

    public User getLoggeduser() throws BadCredentialsException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()) {
            return (User) auth.getPrincipal();
        }
        throw new BadCredentialsException("Usuario no autenticado");
    }

    public String getLoggeduserName() throws BadCredentialsException {

        User user = getLoggeduser();

        return user.getUsername();
    }

    protected WebPageModel createWebPageModel(String pageTitle) {
        WebPageModel webPageModel = new WebPageModel();
        webPageModel.setDate(new Date());
        webPageModel.setModuleName(getModuleName());
        webPageModel.setTitle(pageTitle);
        webPageModel.setUsername(getLoggeduserName());
        return webPageModel;
    }

    public abstract MODULE_NAME getModuleName();
}
