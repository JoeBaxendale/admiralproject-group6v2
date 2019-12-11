package admiral.web;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import admiral.domain.TimeSheet;
import admiral.domain.TimeSheetPlusExtra;
import admiral.service.TimeSheetFinder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

//----------------------------------------------------------------------------------------------------------------------
// Controller for the Time Sheet dashboard

@Controller
@SessionAttributes({"TimeSheets","LoginID","userId","accessLevel"})
public class TimeSheetDashboardController {

    // Finder for the Time Sheet queries
    private TimeSheetFinder finder;

    //------------------------------------------------------------------------------------------------------------------
    // Constructor assigns the Time Sheet finder
    public TimeSheetDashboardController(TimeSheetFinder afinder){
        finder = afinder;
    }

    @GetMapping("/timesheetDashboard")
    public String decideWhichFiltersForDashboard(@SessionAttribute("loginEmail") String loginEmail,Model model){
        long userId = finder.getUserIdByEmail(loginEmail);
        long accessLevel = finder.getUserLevelFromId(userId);

        if (accessLevel == 1){
            model.addAttribute("accessLevel","Manager");
            return "redirect:/timesheetDashboard/"+ userId +"/Pending";
        }else if (accessLevel == 2){
            model.addAttribute("accessLevel","Admin");
            return "redirect:/timesheetDashboard/"+ userId +"/Approved";
        }


        return "redirect:/timesheetDashboard/0/Pending";
    }

    //------------------------------------------------------------------------------------------------------------------
    // Time Sheet dashboard to review submitted Time Sheets
    @GetMapping("/timesheetDashboard/{id}/{filterTerm}")
    public String showTimeSheetDashboard(@PathVariable("filterTerm") String filterTerm, //get the filter term from the url
                                         @PathVariable("id") long userId,
                                         @ModelAttribute("accessLevel") String accessLevel,
                                         Model model){

        System.out.println("YEEEE " + accessLevel);

        model.addAttribute("userId",userId);

        // Creates and populates a list of TimeSheets, passes it to the dashboard page
        List<TimeSheetPlusExtra> TimeSheets = finder.findTimeSheetsByStatus(filterTerm);
        List<String> managerNames = new ArrayList<>();
        for(int i=0;i< TimeSheets.size();i++) {
            String managerName = finder.findManagerByContractorId(TimeSheets.get(i).getAdmiral_role_id()); //not sure why contractorID is called admiral role id here?
            TimeSheets.get(i).setManagerFirstName(managerName.substring(0,managerName.indexOf(" ")));
            TimeSheets.get(i).setManagerSurname(managerName.substring(managerName.indexOf(" ")));
        }

        model.addAttribute("TimeSheets",TimeSheets);
        model.addAttribute("filterTerm",filterTerm);
        model.addAttribute("alteredTimeSheets", new String());
        model.addAttribute("accessLevel", accessLevel);
        // Opens the dashboard html page
        return "timesheet_dashboard";
    }

    @GetMapping("/submitChanges")
    public String submitChangesToTimeSheetStatus(@RequestParam("alteredTimeSheets") String alterData,
                                                 @ModelAttribute("TimeSheets") List<TimeSheetPlusExtra> timeSheets,  //get the altered data and the timesheets to compare the differences
                                                 @ModelAttribute("userId") String userId,
                                                 Model model){

        List<String> splitList = List.of(alterData.split(",")); //split the altered data on the comma, as the data is passed as an array in string form

        for(int j =0; j<timeSheets.size();j++) {        //look through every timesheet
            for (int i = 0; i < splitList.size(); i += 3) {     //look through every entry in the altered data. i+=3 as there are 3 entries in the list for each timesheet
                if (timeSheets.get(j).getTime_sheet_id() == Integer.parseInt(splitList.get(i).substring(splitList.get(i).indexOf("=")+1))) {    //checks if the id of the timesheet is equal to the id of the changed data

                    finder.updateTimeSheetEntry(new TimeSheet(      //run the code to update the database with the new timesheet by merging data from the old timesheet and the new altered data
                            Integer.parseInt(splitList.get(i).substring(splitList.get(i).indexOf("=")+1)),  //remove the "timesheet_id= from the data
                            timeSheets.get(i).getAdmiral_role_id(),
                            timeSheets.get(i).getNumber_days(),
                            timeSheets.get(i).getOvertime(),
                            timeSheets.get(i).getStart_date(),
                            timeSheets.get(i).getEnd_date(),
                            timeSheets.get(i).getDate_submitted(),
                            " " + splitList.get(i+1).substring(0,splitList.get(i+1).indexOf("<")),  //remove the </td> at the end of the notes
                            splitList.get(i + 2)
                    ));
                }
            }
        }

        return "redirect:/timesheetDashboard/"+ userId +"/Pending";      //redirect back to the timesheet dashboard with the pending filter after updating the database
    }
}
