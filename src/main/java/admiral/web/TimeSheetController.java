package admiral.web;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

//----------------------------------------------------------------------------------------------------------------------
// Controller
@Controller
public class TimeSheetController {

    //------------------------------------------------------------------------------------------------------------------
    // Constructor
    public TimeSheetController() {
        // for finders
    }

    //------------------------------------------------------------------------------------------------------------------
    // Time sheet details page
    @RequestMapping(path = "/Timesheet", method = RequestMethod.GET)
    public String timeSheetDetails(@ModelAttribute("timesheetKey") @Valid TimeSheetForm donor, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            System.out.println("----------------------------------> Fail");
            return "timesheet";
        }

        // Open timesheet details html
        System.out.println("----------------------------------> Success");
        model.addAttribute("timesheetKey", new TimeSheetForm());
        return "timesheet";

    }

    //------------------------------------------------------------------------------------------------------------------
    // Time sheet details page
    //@RequestMapping(path = "/Timesheet/Process", method = RequestMethod.GET)
    //public String timeSheetProcess(Model model) {

        // Open timesheet details html
    //    return "timesheet";

    //}
}
