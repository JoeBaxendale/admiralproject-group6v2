package admiral.service;

//----------------------------------------------------------------------------------------------------------------------
// Imports

import admiral.domain.ContractorUser;
import admiral.domain.ManagerUser;
import admiral.domain.TimeSheet;
import admiral.service.events.ContractorUpdated;
import admiral.service.events.TimeSheetMade;

import java.util.List;

//----------------------------------------------------------------------------------------------------------------------
// Interface for the Contractor repository, including all methods for the Contractor table
public interface StaffRepo {

    //------------------------------------------------------------------------------------------------------------------
    // Method to retrieve Contractors
    public List<ContractorUser> findContractors();

    //------------------------------------------------------------------------------------------------------------------
    // Method to retrieve Contractor based on id
    public List<ContractorUser> findContractorById(String searchTerm);

    //------------------------------------------------------------------------------------------------------------------
    // Method to retrieve Contractor based on manager
    public List<ContractorUser> findContractorByManager(String searchTerm);

    //------------------------------------------------------------------------------------------------------------------
    // Method to retrieve Contractor based on id
    public int getContractorByUser(long userId);

    //------------------------------------------------------------------------------------------------------------------
    // Method to update Contractors
    public void updateContractor(ContractorUpdated contractorUpdated);

    //------------------------------------------------------------------------------------------------------------------
    // Method to update Contractors
    public void updateContractorPassword(int contractorId, String newPassword);

    //------------------------------------------------------------------------------------------------------------------
    // Method to update Contractors
    public void deactivateContractor(int contractorId);

    //------------------------------------------------------------------------------------------------------------------
    // Method to retrieve Managers
    public List<ManagerUser> findManagers();

    //------------------------------------------------------------------------------------------------------------------
    // Method to retrieve Managers based on id
    public List<ManagerUser> findManagerById(int searchId);

}
