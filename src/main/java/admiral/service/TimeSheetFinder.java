package admiral.service;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import admiral.domain.TimeSheet;
import admiral.domain.TimeSheetPlusExtra;

import java.util.List;

//----------------------------------------------------------------------------------------------------------------------
// Interface for the retrieval of Time Sheets from the database
public interface TimeSheetFinder {

    //------------------------------------------------------------------------------------------------------------------
    // Retrieves Time Sheets based on search term
    public List<TimeSheetPlusExtra> findTimeSheetsByStatus(String searchTerm);

    //Updates a timesheet in the database
    public void updateTimeSheetEntry(TimeSheet timesheet);

    //Gets the manager name based on the contractorId
    //included this in timesheet repositories as it applies to the timesheet data
    public String findManagerByContractorId(int contractorId);

    public long getUserIdByEmail(String loginEmail);

    public long getUserLevelFromId(long userId);

}
