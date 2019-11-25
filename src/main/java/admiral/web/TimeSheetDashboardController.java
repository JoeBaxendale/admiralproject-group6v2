package admiral.web;

import admiral.DatabaseConnection;
import admiral.domain.TimeSheet;
import admiral.service.TimeSheetFinder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Connection;
import java.util.List;

@Controller
public class TimeSheetDashboardController {

    private TimeSheetFinder finder;

    public TimeSheetDashboardController(TimeSheetFinder afinder){
        finder = afinder;

    }

    @RequestMapping("/timeSheetDashboard")
    public String showTimeSheetDashboard(Model model){

        List<TimeSheet> pendingTimeSheets = finder.findTimeSheetsByStatus("Pending");

        model.addAttribute("pendingTimeSheets",pendingTimeSheets);

        return "timesheet_dashboard";
    }

}
