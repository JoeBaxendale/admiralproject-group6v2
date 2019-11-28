package admiral.service;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import admiral.domain.TimeSheet;
import org.springframework.stereotype.Service;
import java.util.List;

//----------------------------------------------------------------------------------------------------------------------
// Queries class for accessing the database
@Service
public class TimeSheetQueries implements TimeSheetFinder{

    // Link to a Time Sheet repository
    private TimeSheetRepo timeSheetRepo;

    //------------------------------------------------------------------------------------------------------------------
    // Constructor assigning the Time Sheet repository
    public TimeSheetQueries(TimeSheetRepo aRepo){
        timeSheetRepo = aRepo;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Method to query the database, retrieving Times Sheets based on a parameter
    public List<TimeSheet> findTimeSheetsByStatus(String searchTerm){
        return timeSheetRepo.findTimeSheetsByStatus(searchTerm);
    }
}
