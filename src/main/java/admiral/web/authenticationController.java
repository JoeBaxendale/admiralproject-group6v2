package admiral.web;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import admiral.domain.User;
import admiral.service.UserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import admiral.domain.UserInfo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

import javax.validation.Valid;

//----------------------------------------------------------------------------------------------------------------------
// Controller for the login, register and home
@Controller
public class authenticationController {

    @Autowired
    UserService userService;

    //------------------------------------------------------------------------------------------------------------------
    // Login mapping
    @RequestMapping(value = {"/login", "", "/"}, method = RequestMethod.GET)
    public ModelAndView login(HttpSession httpSession) {
        // If this is a non-logged in user (anonymousUser) then got to the login screen
        ModelAndView modelAndView = new ModelAndView();
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString() == "anonymousUser") {
            modelAndView.setViewName("login"); //will open login html
        } else {
            // Authenticated user. Get the details and find the correct URL to redirect to
            UserInfo loggedInUser = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            httpSession.setAttribute("loginEmail", loggedInUser.getUsername());

            // Get the target path to redirect to based on the role
            String targetPath;
            targetPath = determineTargetPath(loggedInUser.getRole());

            // Set the redirect path
            modelAndView.setViewName("redirect:" + targetPath);
        }
        return modelAndView;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Register mapping
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User(0,"","","","",3,true);
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
            modelAndView.addObject("user", user);
            modelAndView.setViewName("register");
            return  modelAndView;
        }
        //        we will save the user, if no binding errors
        else if (userService.isUserAlreadyPresent(user)) {
            modelAndView.addObject("Message", "user already exists");
            modelAndView.addObject("user", user);
            modelAndView.setViewName("register");
            return  modelAndView;
        }
        else {
            //save the user registration form
            userService.saveUser(user);
            modelAndView.addObject("Message", "user created successfully");
        }

        modelAndView.addObject("user", user);
        modelAndView.setViewName("login");
        return  modelAndView;
    }
    //------------------------------------------------------------------------------------------------------------------
    // Get the require target URL path to redirect to based on a user's role. No match will return the login error page
    protected String determineTargetPath(String role){

        String url;

        // Check user role and decide the redirect URL
        switch (role) {
            case "Admin" :
                url = "/timesheetDashboard";
                break;
            case "Manager" :
                url = "/timesheetDashboard";
                break;
            case "Contractor" :
                url = "/Timesheet";
                break;
            default :
                url = "/login?error=true";
                break;
        }
        return url;
    }

}
