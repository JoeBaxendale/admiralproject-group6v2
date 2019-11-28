package admiral.web;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import admiral.service.TimeSheetCreator;
import admiral.service.TimeSheetQueries;
import admiral.service.events.TimeSheetMade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.validation.Valid;
import java.util.Date;

//----------------------------------------------------------------------------------------------------------------------
// Controller
@Controller
public class TimeSheetController {

    private TimeSheetCreator timeSheetCreator;

    //------------------------------------------------------------------------------------------------------------------
    // Constructor
    public TimeSheetController(TimeSheetCreator iCreator) {
        timeSheetCreator = iCreator;
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

        System.out.println("------------------------------------------->");
        System.out.println("------------------------------------------->"+ timeSheet.getNumber_of_days());
        //--------------------------------------------------------------------------------------------------------------
        // Validate the form, else force resubmission
        if (bindingResult.hasErrors()) {
            return "timesheet";
        }

        TimeSheetMade timeSheetEvent = new TimeSheetMade(
                Integer.parseInt(timeSheet.getNumber_of_days()),
                Integer.parseInt(timeSheet.getOvertime()),
                timeSheet.getStart_date(),
                timeSheet.getEnd_date(),
                timeSheet.getNotes()

        );

        timeSheetCreator.makeTimeSheet(timeSheetEvent);

        // Go to the confirmation page
        return "timesheet_confirm";
    }



}
