package admiral.web;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import admiral.domain.User;
import admiral.service.UserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

//----------------------------------------------------------------------------------------------------------------------
// Controller for the login, register and home
@Controller
public class authenticationController {

    @Autowired
    UserService userService;

    //------------------------------------------------------------------------------------------------------------------
    // Login mapping
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login"); //will open login html
        return modelAndView;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Register mapping
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register"); //will open register html
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUser(@Valid User user, BindingResult bindingResult,
                                     ModelMap modelMap){
        ModelAndView modelAndView = new ModelAndView();
        //Check for the validation
        if (bindingResult.hasErrors()){
            modelAndView.addObject("sucessMessage", "Please correct the errors in form!");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        //        we will save the user, if no binding errors
        else if (userService.isUserAlreadyPresent(user)) {
            modelAndView.addObject("Message", "user already exists");
        }
        else {
            //save the user registration form
            userService.saveUser(user);
            modelAndView.addObject("Message", "user created successfully");
        }
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return  modelAndView;
    }
}
