package admiral.service;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import admiral.domain.ContractorUser;
import admiral.domain.ManagerUser;
import admiral.domain.TimeSheet;

import java.util.List;

//----------------------------------------------------------------------------------------------------------------------
// Interface for the retrieval of Contractor from the database
public interface StaffFinder {

    //------------------------------------------------------------------------------------------------------------------
    // Retrieves Contractor based on Manager
    public List<ContractorUser> findContractorByManager(String searchManager);

    //------------------------------------------------------------------------------------------------------------------
    // Retrieves Contractor based on ID
    public List<ContractorUser> findContractorById(String searchManager);

    //------------------------------------------------------------------------------------------------------------------
    // Retrieves Contractor based on ID
    public int getContractorByUser(long searchUserId);

    //------------------------------------------------------------------------------------------------------------------
    // Retrieves Contractors
    public List<ContractorUser> findContractors();

    //------------------------------------------------------------------------------------------------------------------
    // Retrieves Contractors
    public List<ManagerUser> findManagers();
}
