package admiral.web;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import admiral.domain.ContractorUser;
import admiral.service.ContractorFinder;
import admiral.service.TimeSheetCreator;
import admiral.service.events.TimeSheetMade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

//----------------------------------------------------------------------------------------------------------------------
// Controller for Time Sheet and Time Sheet processing
@Controller
public class TimeSheetController {

    // Set Creator for database access
    private TimeSheetCreator timeSheetCreator;

    // Finder for the Time Sheet queries
    private ContractorFinder finder;

    //------------------------------------------------------------------------------------------------------------------
    // Constructor setting creator
    public TimeSheetController(TimeSheetCreator iCreator, ContractorFinder iFinder) {
        timeSheetCreator = iCreator;
        finder = iFinder;
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
    public String timeSheetProcess(@ModelAttribute("timesheetKey") @Valid TimeSheetForm timeSheet,
                                   BindingResult bindingResult,
                                   Model model) {

        //--------------------------------------------------------------------------------------------------------------
        // Check that the supplied end date is later or the same as the start date
        if ((timeSheet.getStart_date() != null) & (timeSheet.getEnd_date() != null)){
            if(timeSheet.getStart_date().compareTo(timeSheet.getEnd_date()) > 0) {
                bindingResult.rejectValue("end_date", "error.end_date", "End date must be after the start date");
            }
        }

        //--------------------------------------------------------------------------------------------------------------
        // Validate the form, else force resubmission
        if (bindingResult.hasErrors()) {
            model.addAttribute("timesheetKey", timeSheet);
            return "timesheet";
        }

        String tempNotes = timeSheet.getNotes();

        if(timeSheet.getWorked_sunday() != null){
            tempNotes = "Worked Sunday;" + timeSheet.getNotes();
        }
        if(timeSheet.getWorked_saturday() != null){
            tempNotes = "Worked Saturday;" + timeSheet.getNotes();
        }


        //--------------------------------------------------------------------------------------------------------------
        // Inserts the form details to the database
        TimeSheetMade timeSheetEvent = new TimeSheetMade(
                2,
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

    //------------------------------------------------------------------------------------------------------------------
    // Mangers page to manager users
    @GetMapping(path = "/Manager/{id}")
    public String contractorManager(@PathVariable("id") String managerId, Model model) {

        List<ContractorUser> contractorsUnderManager;

        if(managerId.equals("All")){
            // Creates and populates a list of TimeSheets, passes it to the dashboard page
            contractorsUnderManager = finder.findContractors();
            model.addAttribute("contractorsUnderManager",contractorsUnderManager);
        } else {
            // Creates and populates a list of TimeSheets, passes it to the dashboard page
            contractorsUnderManager = finder.findContractorByManager(managerId);
            model.addAttribute("contractorsUnderManager",contractorsUnderManager);
        }


        for(ContractorUser element: contractorsUnderManager){
            System.out.println(element.getFirstName() + element.getStaffEmail());
        }

        // Open managers page
        return "contractor_manager";
    }

    //------------------------------------------------------------------------------------------------------------------
    // Mangers page to manager users
    @GetMapping(path = "/Contractor/{id}")
    public String contractorEditor(@PathVariable("id") String contractorId, Model model) {

        List<ContractorUser> contractor = finder.findContractorById(contractorId);

        ContractorForm contractorForm = new ContractorForm(
                contractor.get(0).getManager_id(),
                contractor.get(0).getFirstName(),
                contractor.get(0).getLastName(),
                contractor.get(0).getStaffEmail(),
                contractor.get(0).getActive());

        model.addAttribute("contractorKey", contractorForm);

        // Open managers page
        return "contractor";
    }

}
