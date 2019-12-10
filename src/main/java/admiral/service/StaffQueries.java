package admiral.service;

//----------------------------------------------------------------------------------------------------------------------
// Imports

import admiral.domain.ContractorUser;
import admiral.domain.ManagerUser;
import org.springframework.stereotype.Service;

import java.util.List;

//----------------------------------------------------------------------------------------------------------------------
// Queries class for accessing the database
@Service
public class StaffQueries implements StaffFinder {

    // Link to a Contractor repository
    private StaffRepo staffRepo;

    //------------------------------------------------------------------------------------------------------------------
    // Constructor assigning the Contractor repository
    public StaffQueries(StaffRepo aRepo){
        staffRepo = aRepo;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Method to query the database, retrieving Contractor based on a Manager
    public List<ContractorUser> findContractorByManager(String searchManager){
        return staffRepo.findContractorByManager(searchManager);
    }

    //------------------------------------------------------------------------------------------------------------------
    // Method to query the database, retrieving Contractor based on a id
    public List<ContractorUser> findContractorById(String searchManager){
        return staffRepo.findContractorById(searchManager);
    }

    //------------------------------------------------------------------------------------------------------------------
    // Method to query the database, retrieving Contractor based on a id
    public int getContractorByUser(long userId){
        return staffRepo.getContractorByUser(userId);
    }

    //------------------------------------------------------------------------------------------------------------------
    // Method to query the database, retrieving Contractors
    public List<ContractorUser> findContractors(){
        return staffRepo.findContractors();
    }

    //------------------------------------------------------------------------------------------------------------------
    // Method to query the database, retrieving manager
    public List<ManagerUser> findManagers(){
        return staffRepo.findManagers();
    }
}
