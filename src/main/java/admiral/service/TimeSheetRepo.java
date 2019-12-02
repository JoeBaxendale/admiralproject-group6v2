package admiral.service;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import admiral.domain.TimeSheet;
import admiral.service.events.TimeSheetMade;
import java.util.List;

//----------------------------------------------------------------------------------------------------------------------
// Interface for the Time Sheet repository, including all methods for the Time Sheet table
public interface TimeSheetRepo {

    //------------------------------------------------------------------------------------------------------------------
    // Method to retive Time Sheets based on parameter
    public List<TimeSheet> findTimeSheetsByStatus(String searchTerm);

    //------------------------------------------------------------------------------------------------------------------
    // Saves a new Time Sheet to the database
    public void saveTimeSheetEvent(TimeSheetMade timeSheetMade);

    public void updateTimeSheetEntry(TimeSheet timesheet);
}
