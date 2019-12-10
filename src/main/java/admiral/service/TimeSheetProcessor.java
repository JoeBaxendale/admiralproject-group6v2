package admiral.service;

//----------------------------------------------------------------------------------------------------------------------
// Import
import admiral.service.events.TimeSheetMade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

//----------------------------------------------------------------------------------------------------------------------
// Processor class for database access
@Slf4j
@Service
public class TimeSheetProcessor implements TimeSheetCreator {

    // Paired Programming between Dan and Dexter 25/11/2019
    // Link to a Time Sheet repository
    private TimeSheetRepo timeSheetRepo;

    private NotificationProcessor notificationProcessor;

    //------------------------------------------------------------------------------------------------------------------
    // Constructor assigning the Time Sheet repository
    public TimeSheetProcessor(TimeSheetRepo aRepo, NotificationProcessor aNotificationProcessor){
        timeSheetRepo = aRepo;
        notificationProcessor = aNotificationProcessor;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Method to insert Time Sheet entry into the database. A notification will be sent to Teams after the timesheet has
    // been created.
    public void makeTimeSheet(TimeSheetMade timeSheet) {

        int time_sheet_id = timeSheetRepo.saveTimeSheetEvent(timeSheet);
        notificationProcessor.sendTimeSheetCreateNotification(time_sheet_id);

    }

}
