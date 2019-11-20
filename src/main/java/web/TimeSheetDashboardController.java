package web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import service.TimeSheetFinder;

@Controller
public class TimeSheetDashboardController {

    private TimeSheetFinder finder;

    public TimeSheetDashboardController(TimeSheetFinder afinder){
        finder = afinder;

    }

    @GetMapping("/timeSheetDashboard")
    public String showTimeSheetDashboard(Model model){
        return "timesheet_dashboard";
    }

}
