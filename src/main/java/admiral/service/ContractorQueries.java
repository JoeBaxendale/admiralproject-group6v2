package admiral.service;

//----------------------------------------------------------------------------------------------------------------------
// Imports

import admiral.domain.ContractorUser;
import admiral.domain.TimeSheet;
import org.springframework.stereotype.Service;

import java.util.List;

//----------------------------------------------------------------------------------------------------------------------
// Queries class for accessing the database
@Service
public class ContractorQueries implements ContractorFinder{

    // Link to a Time Sheet repository
    private ContractorRepo contractorRepo;

    //------------------------------------------------------------------------------------------------------------------
    // Constructor assigning the Time Sheet repository
    public ContractorQueries(ContractorRepo aRepo){
        contractorRepo = aRepo;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Method to query the database, retrieving Times Sheets based on a parameter
    public List<ContractorUser> findContractorByManager(String searchManager){
        return contractorRepo.findContractorByManager(searchManager);
    }
}
