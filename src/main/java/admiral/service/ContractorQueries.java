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

    // Link to a Contractor repository
    private ContractorRepo contractorRepo;

    //------------------------------------------------------------------------------------------------------------------
    // Constructor assigning the Contractor repository
    public ContractorQueries(ContractorRepo aRepo){
        contractorRepo = aRepo;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Method to query the database, retrieving Contractor based on a Manager
    public List<ContractorUser> findContractorByManager(String searchManager){
        return contractorRepo.findContractorByManager(searchManager);
    }

    //------------------------------------------------------------------------------------------------------------------
    // Method to query the database, retrieving Contractor based on a id
    public List<ContractorUser> findContractorById(String searchManager){
        return contractorRepo.findContractorById(searchManager);
    }

    //------------------------------------------------------------------------------------------------------------------
    // Method to query the database, retrieving Contractors
    public List<ContractorUser> findContractors(){
        return contractorRepo.findContractors();
    }
}
