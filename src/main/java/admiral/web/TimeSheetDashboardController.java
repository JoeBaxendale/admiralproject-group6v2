package admiral.web;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import admiral.domain.TimeSheet;
import admiral.service.TimeSheetFinder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//----------------------------------------------------------------------------------------------------------------------
// Controller for the Time Sheet dashboard

@Controller
@SessionAttributes("TimeSheets")
public class TimeSheetDashboardController {

    // Finder for the Time Sheet queries
    private TimeSheetFinder finder;

    //------------------------------------------------------------------------------------------------------------------
    // Constructor assigns the Time Sheet finder
    public TimeSheetDashboardController(TimeSheetFinder afinder){
        finder = afinder;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Time Sheet dashboard to review submitted Time Sheets
    @GetMapping("/timesheetDashboard/{filterTerm}")
    public String showTimeSheetDashboard(@PathVariable("filterTerm") String filterTerm, Model model){ //get the filter term from the url

        // Creates and populates a list of TimeSheets, passes it to the dashboard page
        List<TimeSheet> TimeSheets = finder.findTimeSheetsByStatus(filterTerm);
        model.addAttribute("TimeSheets",TimeSheets);
        model.addAttribute("filterTerm",filterTerm);
        model.addAttribute("alteredTimeSheets", new String());

        // Opens the dashboard html page
        return "timesheet_dashboard";
    }

    @GetMapping("/submitChanges")
    public String submitChangesToTimeSheetStatus(@RequestParam("alteredTimeSheets") String alterData,
                                                 @ModelAttribute("TimeSheets") List<TimeSheet> timeSheets,
                                                 Model model){


        List<String> splitList = List.of(alterData.split(","));

        for(int j =0; j<timeSheets.size();j++) {
            for (int i = 0; i < splitList.size(); i += 3) {
                if (timeSheets.get(j).getTime_sheet_id() == Integer.parseInt(splitList.get(i).substring(splitList.get(i).indexOf("=")+1))) {

                    finder.updateTimeSheetEntry(new TimeSheet(
                            Integer.parseInt(splitList.get(i).substring(splitList.get(i).indexOf("=")+1)),
                            timeSheets.get(i).getAdmiral_role_id(),
                            timeSheets.get(i).getNumber_days(),
                            timeSheets.get(i).getOvertime(),
                            timeSheets.get(i).getStart_date(),
                            timeSheets.get(i).getEnd_date(),
                            timeSheets.get(i).getDate_submitted(),
                            " " + splitList.get(i+1).substring(0,splitList.get(i+1).indexOf("<")),
                            splitList.get(i + 2)
                    ));
                }
            }
        }

        return "redirect:/timesheetDashboard/Pending";
    }
}
