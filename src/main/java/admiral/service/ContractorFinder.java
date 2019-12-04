package admiral.service;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import admiral.domain.ContractorUser;
import admiral.domain.TimeSheet;

import java.util.List;

//----------------------------------------------------------------------------------------------------------------------
// Interface for the retrieval of Time Sheets from the database
public interface ContractorFinder {

    //------------------------------------------------------------------------------------------------------------------
    // Retrieves Time Sheets based on search term
    public List<ContractorUser> findContractorByManager(String searchManager);
}
