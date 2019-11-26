package admiral.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//Controller for the login, register and home

@Controller
public class authenticationController {

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login"); //will open login html
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register"); //will open register html
        return modelAndView;
    }

    @RequestMapping(value = "/timeSheetDashboard", method = RequestMethod.GET)
    public ModelAndView timesheet_dashboard(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("timesheet_dashboard"); //will open home html
        return modelAndView;
    }
}
