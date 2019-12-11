package admiral.web;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import admiral.domain.ContractorUser;
import admiral.domain.ManagerUser;
import admiral.service.StaffCreator;
import admiral.service.StaffFinder;
import admiral.service.TimeSheetCreator;
import admiral.service.TimeSheetFinder;
import admiral.service.events.ContractorUpdated;
import admiral.service.events.TimeSheetMade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

//----------------------------------------------------------------------------------------------------------------------
// Controller for Time Sheet and Time Sheet processing
// Paired Programming between Dan and Dexter
@Controller
public class TimeSheetController {

    // Set Creator for database access
    private TimeSheetCreator timeSheetCreator;
    private StaffCreator staffCreator;

    // Finder for the Time Sheet queries
    private StaffFinder finder;
    private TimeSheetFinder tFinder;

    //------------------------------------------------------------------------------------------------------------------
    // Constructor setting creator
    public TimeSheetController(TimeSheetCreator iCreator, StaffCreator iStaffCreator, StaffFinder iFinder, TimeSheetFinder iTFinder) {
        timeSheetCreator = iCreator;
        staffCreator = iStaffCreator;
        finder = iFinder;
        tFinder = iTFinder;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Time sheet details page for form data entry
    @RequestMapping(path = "/Timesheet", method = RequestMethod.GET)
    public String timeSheetDetails(Model model) {

        // Gets date for form
        LocalDate currentDate = LocalDate.now();
        LocalDate weekPast = currentDate.minusDays(7);

        // Open time sheet form
        model.addAttribute("timesheetKey", new TimeSheetForm(0, false, false, 0, weekPast, currentDate, ""));
        return "timesheet";

    }

    //------------------------------------------------------------------------------------------------------------------
    // Time sheet details page; form validation, processing and receipt page
    @RequestMapping(path = "/TimesheetDetails", method = RequestMethod.POST)
    public String timeSheetProcess(@SessionAttribute("loginEmail") String loginEmail, @ModelAttribute("timesheetKey") @Valid TimeSheetForm timeSheet,
                                   BindingResult bindingResult,
                                   Model model) {

        long userId = tFinder.getUserIdByEmail(loginEmail);
        int contractorId = finder.getContractorByUser(userId);

        //--------------------------------------------------------------------------------------------------------------
        // Check that the supplied end date is later or the same as the start date
        if ((timeSheet.getStart_date() != null) & (timeSheet.getEnd_date() != null)){
            if(timeSheet.getStart_date().compareTo(timeSheet.getEnd_date()) > 0) {
                bindingResult.rejectValue("end_date", "error.end_date", "End date must be after the start date");
            }

            if((timeSheet.getNumber_of_days() < 1 && timeSheet.getWorked_saturday()) ||
                    (timeSheet.getNumber_of_days() < 1 && timeSheet.getWorked_sunday()) ||
                    (timeSheet.getNumber_of_days() < 2 && timeSheet.getWorked_saturday() && timeSheet.getWorked_sunday())) {
                bindingResult.rejectValue("number_of_days", "error.number_of_days", "Need to increase the number of days worked to reflect working the weekend");
            }

            if(timeSheet.getStart_date().isBefore(timeSheet.getEnd_date().minusDays(7))){
                  bindingResult.rejectValue("end_date", "error.end_date", "Work week cannot be longer than 7 days");
            }
        }

        //--------------------------------------------------------------------------------------------------------------
        // Validate the form, else force resubmission
        if (bindingResult.hasErrors()) {
            model.addAttribute("timesheetKey", timeSheet);
            return "timesheet";
        }

        String tempNotes = timeSheet.getNotes();

        if(timeSheet.getWorked_sunday() == true){
            tempNotes = "Worked Sunday;" + tempNotes;
        }


        if(timeSheet.getWorked_saturday() == true){
            tempNotes = "Worked Saturday;" + tempNotes;
        }



        //--------------------------------------------------------------------------------------------------------------
        // Inserts the form details to the database
        TimeSheetMade timeSheetEvent = new TimeSheetMade(
                contractorId,
                timeSheet.getNumber_of_days(),
                timeSheet.getOvertime(),
                timeSheet.getStart_date(),
                timeSheet.getEnd_date(),
                tempNotes

        );

        timeSheetCreator.makeTimeSheet(timeSheetEvent);

        // Go to the confirmation page
        return "timesheet_confirm";
    }

}
