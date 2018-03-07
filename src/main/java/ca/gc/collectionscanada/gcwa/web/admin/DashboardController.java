package ca.gc.collectionscanada.gcwa.web.admin;

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
