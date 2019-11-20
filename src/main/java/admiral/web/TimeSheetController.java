package admiral.web;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

        // Open timesheet details html
        return "timesheet";

    }
}
