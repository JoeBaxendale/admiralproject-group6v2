package admiral.service;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import admiral.service.events.ContractorUpdated;

//----------------------------------------------------------------------------------------------------------------------
// Interface for the inserting of Time sheets into the DB
public interface StaffCreator {

    //------------------------------------------------------------------------------------------------------------------
    // Class to be define, saving the Time Sheet submission to the database.
    public void updateContractor(ContractorUpdated contractorUpdated);

    //------------------------------------------------------------------------------------------------------------------
    // Class to be define, saving the Time Sheet submission to the database.
    public void deactivateContractor(int contractorId);
}
