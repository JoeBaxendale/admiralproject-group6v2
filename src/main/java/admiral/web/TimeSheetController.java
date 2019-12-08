package admiral.web;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import admiral.domain.ContractorUser;
import admiral.domain.ManagerUser;
import admiral.service.StaffCreator;
import admiral.service.StaffFinder;
import admiral.service.TimeSheetCreator;
import admiral.service.events.ContractorUpdated;
import admiral.service.events.TimeSheetMade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

//----------------------------------------------------------------------------------------------------------------------
// Controller for Time Sheet and Time Sheet processing
@Controller
public class TimeSheetController {

    // Set Creator for database access
    private TimeSheetCreator timeSheetCreator;
    private StaffCreator staffCreator;

    // Finder for the Time Sheet queries
    private StaffFinder finder;

    //------------------------------------------------------------------------------------------------------------------
    // Constructor setting creator
    public TimeSheetController(TimeSheetCreator iCreator, StaffCreator iStaffCreator, StaffFinder iFinder) {
        timeSheetCreator = iCreator;
        staffCreator = iStaffCreator;
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
        System.out.println("------------------------------------------------------------>"+ timeSheet.getWorked_saturday() + timeSheet.getWorked_sunday());

        if(timeSheet.getWorked_sunday() == true){
            tempNotes = "Worked Sunday;" + tempNotes;
        }


        if(timeSheet.getWorked_saturday() == true){
            tempNotes = "Worked Saturday;" + tempNotes;
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
        List<ManagerUser> managers = finder.findManagers();

        if(managerId.equals("All")){
            // Creates and populates a list of TimeSheets, passes it to the dashboard page
            contractorsUnderManager = finder.findContractors();
            contractorsUnderManager = getManagerNames(contractorsUnderManager, managers);
            model.addAttribute("contractorsUnderManager",contractorsUnderManager);
        } else {
            // Creates and populates a list of TimeSheets, passes it to the dashboard page
            contractorsUnderManager = finder.findContractorByManager(managerId);
            contractorsUnderManager = getManagerNames(contractorsUnderManager, managers);
            model.addAttribute("contractorsUnderManager",contractorsUnderManager);
        }

        // Open managers page
        model.addAttribute("managersKey",managers);
        model.addAttribute("searchKey",managerId);
        return "contractor_manager";
    }

    public List<ContractorUser> getManagerNames(List<ContractorUser> iContractors, List<ManagerUser> iManagers){

        List<ContractorUser> temp = iContractors;

        for(ContractorUser element: temp){
            for(ManagerUser managerElement: iManagers){
                if(element.getManager_id() == managerElement.getManager_id()){
                    element.setManager_name(managerElement.getFirstName() + " " + managerElement.getLastName());
                }
            }
        }
        return temp;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Mangers page to manager users
    @GetMapping(path = "/Contractor/{id}")
    public String contractorEditor(@PathVariable("id") String contractorId, Model model) {

        List<ContractorUser> contractor = finder.findContractorById(contractorId);

        StaffForm staffForm = new StaffForm(
                contractor.get(0).getManager_id(),
                contractor.get(0).getFirstName(),
                contractor.get(0).getLastName(),
                contractor.get(0).getStaffEmail(),
                contractor.get(0).getActive()
                );

        model.addAttribute("contractorKey", staffForm);
        model.addAttribute("contractorId", contractorId);

        // Open managers page
        return "contractor";
    }

    //------------------------------------------------------------------------------------------------------------------
    // Mangers page to manager users
    @RequestMapping(path = "/ContractorUpdate/{id}", method = RequestMethod.POST)
    public RedirectView contractorDetails(@PathVariable("id") String contractorId,
                                          @ModelAttribute("contractorKey") @Valid StaffForm contractorForm,
                                          BindingResult bindingResult, Model model) {

        //--------------------------------------------------------------------------------------------------------------
        // Validate the form, else force resubmission
        if (bindingResult.hasErrors()) {
            model.addAttribute("contractorKey", contractorForm);
            return new RedirectView("/Contractor/ ${contractorId}");
        }

        ContractorUpdated contractorUpdated = new ContractorUpdated(
                Integer.parseInt(contractorId),
                contractorForm.getManager_id(),
                contractorForm.getFirst_name(),
                contractorForm.getLast_name(),
                contractorForm.getEmail(),
                contractorForm.getActive());

        staffCreator.updateContractor(contractorUpdated);

        // Open managers page
        return new RedirectView("/Manager/All");
    }

}
