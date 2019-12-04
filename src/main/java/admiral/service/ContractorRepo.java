package admiral.service;

//----------------------------------------------------------------------------------------------------------------------
// Imports

import admiral.domain.ContractorUser;
import admiral.domain.TimeSheet;
import admiral.service.events.TimeSheetMade;

import java.util.List;

//----------------------------------------------------------------------------------------------------------------------
// Interface for the Time Sheet repository, including all methods for the Time Sheet table
public interface ContractorRepo {

    //------------------------------------------------------------------------------------------------------------------
    // Method to retive Time Sheets based on parameter
    public List<ContractorUser> findContractorByManager(String searchTerm);

}
