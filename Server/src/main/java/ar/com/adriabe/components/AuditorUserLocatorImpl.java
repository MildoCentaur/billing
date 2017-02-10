package ar.com.adriabe.components;

import ar.com.adriabe.daos.UserDao;
import ar.com.adriabe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by ajmild1 on 29/09/2015.
 */
@Component
public class AuditorUserLocatorImpl implements AuditorUserLocator {
    UserDao userDao;

    @Autowired
    public AuditorUserLocatorImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User locateLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if (auth != null && auth.isAuthenticated()) {
            if (auth.getPrincipal() instanceof User) {
                user = (User) auth.getPrincipal();
            }
        }

        return (user != null) ? user : userDao.findAnonymousUser();
    }
}
