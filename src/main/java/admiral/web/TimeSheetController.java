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
    public String timeSheetDetails(Model model) {

        // Open timesheet form
        model.addAttribute("timesheetKey", new TimeSheetForm());
        return "timesheet";

    }

    //------------------------------------------------------------------------------------------------------------------
    // Time sheet details page
    @RequestMapping(path = "/TimesheetDetails", method = RequestMethod.POST)
    public String timeSheetProcess(@ModelAttribute("timesheetKey") @Valid TimeSheetForm timeSheet,
                                   BindingResult bindingResult,
                                   Model model) {

        //--------------------------------------------------------------------------------------------------------------
        // Validate the form, else force resubmission
        if (bindingResult.hasErrors()) {
            return "timesheet";
        }

        // Go to the confirmation page
        return "timesheet_confirm";
    }
}
