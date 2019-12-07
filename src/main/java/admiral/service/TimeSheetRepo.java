package admiral.service;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import admiral.domain.TimeSheet;
import admiral.domain.TimeSheetPlusExtra;
import admiral.service.events.TimeSheetMade;
import java.util.List;

//----------------------------------------------------------------------------------------------------------------------
// Interface for the Time Sheet repository, including all methods for the Time Sheet table
public interface TimeSheetRepo {

    //------------------------------------------------------------------------------------------------------------------
    // Method to retive Time Sheets based on parameter
    public List<TimeSheetPlusExtra> findTimeSheetsByStatus(String searchTerm);

    //------------------------------------------------------------------------------------------------------------------
    // Saves a new Time Sheet to the database
    public int saveTimeSheetEvent(TimeSheetMade timeSheetMade);

    // Updates the timesheet entry
    public void updateTimeSheetEntry(TimeSheet timesheet);

    public String findManagerByContractorId(int contractorId);
}
