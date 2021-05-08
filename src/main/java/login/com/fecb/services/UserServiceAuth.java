package login.com.fecb.services;

import login.com.fecb.security.UserSecurity;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserServiceAuth {
    public static UserSecurity authenticated() {
        try {
            return (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        catch (Exception e) {
            return null;
        }
    }
}
