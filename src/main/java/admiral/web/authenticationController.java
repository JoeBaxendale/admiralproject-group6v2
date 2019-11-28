package admiral.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

//Controller for the login, register and home

@Controller
public class authenticationController {

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
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

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public RedirectView redirectWithUsingRedirectView(RedirectAttributes attributes) {
        return new RedirectView("/Timesheet");
    }
//
//    @RequestMapping(value = "/Timesheet", method = RequestMethod.GET)
//    public ModelAndView timesheet(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("timesheet"); //will open home html
//        return modelAndView;
//    }
}
