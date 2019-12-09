package admiral.service;

//----------------------------------------------------------------------------------------------------------------------
// Import
import admiral.service.events.ContractorUpdated;
import admiral.service.events.TimeSheetMade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

//----------------------------------------------------------------------------------------------------------------------
// Processor class for database access
@Slf4j
@Service
public class StaffProcessor implements StaffCreator {

    // Link to a Time Sheet repository
    private StaffRepo staffRepo;

    //------------------------------------------------------------------------------------------------------------------
    // Constructor assigning the Time Sheet repository
    public StaffProcessor(StaffRepo aRepo){
        staffRepo = aRepo;
    }

    //------------------------------------------------------------------------------------------------------------------
    // update contractor information
    public void updateContractor(ContractorUpdated contractorUpdated) {
        staffRepo.updateContractor(contractorUpdated);
    }

    //------------------------------------------------------------------------------------------------------------------
    // Deactivates contractor by id
    public void deactivateContractor(int contractorId) {
        staffRepo.deactivateContractor(contractorId);

    }

}
