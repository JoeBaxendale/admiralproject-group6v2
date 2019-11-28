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

    // Link to a Time Sheet repository
    private TimeSheetRepo timeSheetRepo;

    //------------------------------------------------------------------------------------------------------------------
    // Constructor assigning the Time Sheet repository
    public TimeSheetProcessor(TimeSheetRepo aRepo){
        timeSheetRepo = aRepo;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Method to insert Time Sheet entry into the database
    public void makeTimeSheet(TimeSheetMade timeSheet) {
        timeSheetRepo.saveTimeSheetEvent(timeSheet);
    }
}
