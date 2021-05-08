package login.com.fecb.resources;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class SecurityResource {

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public Principal currentUserName(Principal principal) {
        return principal;
    }
}
