package ar.com.adriabe.auditing.model;

import ar.com.adriabe.model.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by Mildo on 12/13/14.
 */

public class AuditorAwareImpl implements AuditorAware<User> {

    @Override
    public User getCurrentAuditor() {

        return getLoggeduser();
    }

    public User getLoggeduser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()) {
            return (User) auth.getPrincipal();
        }
        return null;
    }



}