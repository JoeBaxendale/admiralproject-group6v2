package admiral.service;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import admiral.service.events.TimeSheetMade;

//----------------------------------------------------------------------------------------------------------------------
// Interface for the inserting of Time sheets into the DB
public interface TimeSheetCreator {

    //------------------------------------------------------------------------------------------------------------------
    // Class to be define, saving the Time Sheet submission to the database
    public void makeTimeSheet(TimeSheetMade timeSheet);
}
