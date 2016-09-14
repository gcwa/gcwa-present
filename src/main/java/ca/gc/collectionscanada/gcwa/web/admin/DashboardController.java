package ca.gc.collectionscanada.gcwa.web.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("adminDashboardController")
public class DashboardController {

    @RequestMapping("/admin")
    public ModelAndView index() {
        return new ModelAndView("redirect:/admin/dashboard");
    }
}
