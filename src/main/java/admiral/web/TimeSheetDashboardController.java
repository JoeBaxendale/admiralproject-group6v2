package admiral.web;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import admiral.domain.TimeSheet;
import admiral.service.TimeSheetFinder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

//----------------------------------------------------------------------------------------------------------------------
// Controller for the Time Sheet dashboard
@Controller
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
    @RequestMapping("/timeSheetDashboard")
    public String showTimeSheetDashboard(Model model){

        // Creates and populates a list of TimeSheets, passes it to the dashboard page
        List<TimeSheet> pendingTimeSheets = finder.findTimeSheetsByStatus("Pending");
        model.addAttribute("pendingTimeSheets",pendingTimeSheets);

        // Opens the dashboard html page
        return "timesheet_dashboard";
    }
}
