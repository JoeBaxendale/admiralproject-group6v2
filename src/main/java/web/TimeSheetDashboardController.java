package web;

import domain.TimeSheet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import service.TimeSheetFinder;

import java.util.List;

@Controller
public class TimeSheetDashboardController {

    private TimeSheetFinder finder;

    public TimeSheetDashboardController(TimeSheetFinder afinder){
        finder = afinder;

    }

    @GetMapping("/timeSheetDashboard")
    public String showTimeSheetDashboard(Model model){

        List<TimeSheet> pendingTimeSheets = finder.findTimeSheetsByStatus("Pending");

        model.addAttribute("pendingTimeSheets",pendingTimeSheets);

        return "timesheet_dashboard";
    }

}
