package admiral.service;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import admiral.service.events.ContractorUpdated;
import admiral.service.events.ManagerUpdated;

//----------------------------------------------------------------------------------------------------------------------
// Interface for the inserting of staff
public interface StaffCreator {

    //------------------------------------------------------------------------------------------------------------------
    // Updates the contractors info
    public void updateContractor(ContractorUpdated contractorUpdated);

    //------------------------------------------------------------------------------------------------------------------
    // Updates a contractors password
    public void updateContractorPassword(int contractorId, String newPassword);

    //------------------------------------------------------------------------------------------------------------------
    // Deactivates a contractor account
    public void deactivateContractor(int contractorId);

    //------------------------------------------------------------------------------------------------------------------
    // Updates the manager info
    public void updateManager(ManagerUpdated managerUpdated);

    //------------------------------------------------------------------------------------------------------------------
    // Updates a manager password
    public void updateManagerPassword(int managerId, String newPassword);

    //------------------------------------------------------------------------------------------------------------------
    // Deactivates a contractor account
    public void deactivateManager(int managerId);
}
