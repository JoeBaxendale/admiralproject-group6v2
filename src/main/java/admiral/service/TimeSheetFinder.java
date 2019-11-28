package admiral.service;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import admiral.domain.TimeSheet;
import java.util.List;

//----------------------------------------------------------------------------------------------------------------------
// Interface for the retrieval of Time Sheets from the database
public interface TimeSheetFinder {

    //------------------------------------------------------------------------------------------------------------------
    // Retrieves Time Sheets based on search term
    public List<TimeSheet> findTimeSheetsByStatus(String searchTerm);

}
