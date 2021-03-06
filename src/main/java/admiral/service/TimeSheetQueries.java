package admiral.service;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import admiral.domain.TimeSheet;
import admiral.domain.TimeSheetPlusExtra;
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
    public List<TimeSheetPlusExtra> findTimeSheetsByStatus(String searchTerm){
        return timeSheetRepo.findTimeSheetsByStatus(searchTerm);
    }

    //Method to update the database for each timesheet
    public void updateTimeSheetEntry(TimeSheet timesheet){
        timeSheetRepo.updateTimeSheetEntry(timesheet);
    };

    public String findManagerByContractorId(int contractorId){
        return timeSheetRepo.findManagerByContractorId(contractorId);
    };

    public long getUserIdByEmail(String loginEmail){
        return timeSheetRepo.getUserIdByEmail(loginEmail);
    };

    public long getUserLevelFromId(long userId){
        return timeSheetRepo.getUserLevelFromId(userId);
    }
}
