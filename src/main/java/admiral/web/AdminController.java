package admiral.web;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import admiral.domain.ContractorUser;
import admiral.domain.ManagerUser;
import admiral.service.StaffCreator;
import admiral.service.StaffFinder;
import admiral.service.TimeSheetCreator;
import admiral.service.events.ContractorUpdated;
import admiral.service.events.ManagerUpdated;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

//----------------------------------------------------------------------------------------------------------------------
// Controller for Time Sheet and Time Sheet processing
@SessionAttributes({"accessLevel"})
@Controller
public class AdminController {

    // Set Creator for database access
    private StaffCreator staffCreator;

    // Finder for the Time Sheet queries
    private StaffFinder finder;

    private PasswordEncoder passwordEncoder;

    //------------------------------------------------------------------------------------------------------------------
    // Constructor setting creator
    public AdminController(TimeSheetCreator iCreator, StaffCreator iStaffCreator, StaffFinder iFinder, PasswordEncoder iPasswordEncoder) {
        staffCreator = iStaffCreator;
        finder = iFinder;
        passwordEncoder = iPasswordEncoder;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Mangers page to manager users
    @GetMapping(path = "/ContractorDash/{id}")
    public String contractorManager(@PathVariable("id") String managerId,@SessionAttribute("accessLevel")String accessLevel, Model model) {

        if(!accessLevel.equals("Admin")){
            return "AccessError";
        }

        List<ContractorUser> contractorsUnderManager;
        List<ManagerUser> managers = finder.findManagers();

        if(managerId.equals("All")){
            // Creates and populates a list of TimeSheets, passes it to the dashboard page
            contractorsUnderManager = finder.findContractors();
            contractorsUnderManager = getManagerNames(contractorsUnderManager, managers);
            model.addAttribute("contractorsUnderManager",contractorsUnderManager);
        } else {
            // Creates and populates a list of TimeSheets, passes it to the dashboard page
            contractorsUnderManager = finder.findContractorByManager(managerId);
            contractorsUnderManager = getManagerNames(contractorsUnderManager, managers);
            model.addAttribute("contractorsUnderManager",contractorsUnderManager);
        }

        // Open managers page
        model.addAttribute("managersKey",managers);
        model.addAttribute("searchKey",managerId);
        return "contractor_dashboard";
    }

    public List<ContractorUser> getManagerNames(List<ContractorUser> iContractors, List<ManagerUser> iManagers){

        List<ContractorUser> temp = iContractors;

        for(ContractorUser element: temp){
            for(ManagerUser managerElement: iManagers){
                if(element.getManager_id() == managerElement.getManager_id()){
                    element.setManager_name(managerElement.getFirstName() + " " + managerElement.getLastName());
                }
            }
        }
        return temp;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Mangers page to manager users
    @GetMapping(path = "/Contractor/{id}")
    public String contractorEditor(@PathVariable("id") String contractorId,@SessionAttribute("accessLevel")String accessLevel, Model model) {

        if(!accessLevel.equals("Admin")){
            return "AccessError";
        }

        List<ContractorUser> contractor = finder.findContractorById(contractorId);
        List<ManagerUser> managers = finder.findManagers();

        StaffForm staffForm = new StaffForm(
                contractor.get(0).getManager_id(),
                contractor.get(0).getFirstName(),
                contractor.get(0).getLastName(),
                contractor.get(0).getStaffEmail());

        model.addAttribute("contractorKey", staffForm);
        model.addAttribute("managersKey", managers);
        model.addAttribute("contractorId", contractorId);
        model.addAttribute("managerId", contractor.get(0).getManager_id());

        // Open managers page
        return "contractor";
    }

    //------------------------------------------------------------------------------------------------------------------
    // Mangers page to manager users
    @RequestMapping(path = "/ContractorUpdate/{id}", method = RequestMethod.POST)
    public String contractorDetails(@PathVariable("id") String contractorId,
                                    @SessionAttribute("accessLevel")String accessLevel,
                                    @ModelAttribute("contractorKey") @Valid StaffForm contractorForm,
                                    BindingResult bindingResult, Model model) {


        if(!accessLevel.equals("Admin")){
            return "AccessError";
        }

        //--------------------------------------------------------------------------------------------------------------
        // Validate the form, else force resubmission
        if (bindingResult.hasErrors()) {
            model.addAttribute("contractorKey", contractorForm);
            model.addAttribute("contractorId", contractorId);
            return "contractor";
        }

        ContractorUpdated contractorUpdated = new ContractorUpdated(
                Integer.parseInt(contractorId),
                contractorForm.getManager_id(),
                contractorForm.getFirst_name(),
                contractorForm.getLast_name(),
                contractorForm.getEmail());

        staffCreator.updateContractor(contractorUpdated);

        // Open managers page
        return "redirect:/ContractorDash/All";
    }

    //------------------------------------------------------------------------------------------------------------------
    // Mangers page to manager users
    @RequestMapping(path = "/SetPasswordContractor/{id}", method = RequestMethod.GET)
    public String setContractorPassword(@PathVariable("id") String contractorId,@SessionAttribute("accessLevel")String accessLevel, Model model) {

        if(!accessLevel.equals("Admin")){
            return "AccessError";
        }

        model.addAttribute("passKey", new PasswordForm());
        model.addAttribute("contractorId", contractorId);

        // Open managers page
        return "contractor_password";
    }

    //------------------------------------------------------------------------------------------------------------------
    // Mangers page to manager users
    @RequestMapping(path = "/PasswordContractorDetails/{id}", method = RequestMethod.POST)
    public String ContractorPasswordProcess(@PathVariable("id") String contractorId,
                                            @SessionAttribute("accessLevel")String accessLevel,
                                            @ModelAttribute("passKey") @Valid PasswordForm passwordForm,
                                            BindingResult bindingResult, Model model) {


        if(!accessLevel.equals("Admin")){
            return "AccessError";
        }

        //--------------------------------------------------------------------------------------------------------------
        // Check that the supplied end date is later or the same as the start date
        if ((passwordForm.getPassword1() != null) & (passwordForm.getPassword2() != null)){
            if(!passwordForm.getPassword1().equals(passwordForm.getPassword2())) {
                bindingResult.rejectValue("password2", "error.password2", "Passwords must match");
            }
        }

        //--------------------------------------------------------------------------------------------------------------
        // Validate the form, else force resubmission
        if (bindingResult.hasErrors()) {
            model.addAttribute("passKey", passwordForm);
            model.addAttribute("contractorId", contractorId);
            return "contractor_password";
        }


        // Breaks without encrypting
        String tempPassword = passwordEncoder.encode(passwordForm.getPassword1());
        staffCreator.updateContractorPassword(Integer.parseInt(contractorId), tempPassword);

        // Open managers page
        return "redirect:/ContractorDash/All";
    }

    //------------------------------------------------------------------------------------------------------------------
    // Disables a contractor account
    @RequestMapping(path = "/DeactivateContractor/{id}", method = RequestMethod.GET)
    public String deactivateContractor(@PathVariable("id") String contractorId,@SessionAttribute("accessLevel")String accessLevel, Model model) {


        if(!accessLevel.equals("Admin")){
            return "AccessError";
        }

        staffCreator.deactivateContractor(Integer.parseInt(contractorId));

        // Open managers page
        return "redirect:/ContractorDash/All";
    }

    //------------------------------------------------------------------------------------------------------------------
    // Mangers page to manager users
    @GetMapping(path = "/ManagerDash")
    public String managerManager(Model model,@SessionAttribute("accessLevel")String accessLevel) {

        if(!accessLevel.equals("Admin")){
            return "AccessError";
        }

        // Creates and populates a list of TimeSheets, passes it to the dashboard page
        List<ManagerUser> managers = finder.findManagers();
        model.addAttribute("managers",managers);

        // Open managers page
        return "manager_dashboard";
    }

    //------------------------------------------------------------------------------------------------------------------
    // Mangers page to manager users
    @GetMapping(path = "/Manager/{id}")
    public String managerEditor(@PathVariable("id") String managerId,@SessionAttribute("accessLevel")String accessLevel, Model model) {

        if(!accessLevel.equals("Admin")){
            return "AccessError";
        }

        List<ManagerUser> manager = finder.findManagerById(Integer.parseInt(managerId));

        StaffForm staffForm = new StaffForm(
                manager.get(0).getManager_id(),
                manager.get(0).getFirstName(),
                manager.get(0).getLastName(),
                manager.get(0).getStaffEmail());

        model.addAttribute("managerKey", staffForm);
        model.addAttribute("managerId", managerId);

        // Open managers page
        return "manager";
    }

    //------------------------------------------------------------------------------------------------------------------
    // Mangers page to manager users
    @RequestMapping(path = "/ManagerUpdate/{id}", method = RequestMethod.POST)
    public String managerDetails(@PathVariable("id") String managerId,
                                 @SessionAttribute("accessLevel")String accessLevel,
                                 @ModelAttribute("managerKey") @Valid StaffForm managerForm,
                                 BindingResult bindingResult, Model model) {

        if(!accessLevel.equals("Admin")){
            return "AccessError";
        }

        //--------------------------------------------------------------------------------------------------------------
        // Validate the form, else force resubmission
        if (bindingResult.hasErrors()) {
            model.addAttribute("managerKey", managerForm);
            model.addAttribute("managerId", managerId);
            return "manager";
        }

        ManagerUpdated managerUpdated = new ManagerUpdated(
                managerForm.getManager_id(),
                managerForm.getFirst_name(),
                managerForm.getLast_name(),
                managerForm.getEmail());

        staffCreator.updateManager(managerUpdated);

        // Open managers page
        return "redirect:/ManagerDash";
    }

    //------------------------------------------------------------------------------------------------------------------
    // Set the managers password
    @RequestMapping(path = "/SetPasswordManager/{id}", method = RequestMethod.GET)
    public String setManagersPassword(@PathVariable("id") String managerId,@SessionAttribute("accessLevel")String accessLevel, Model model) {

        if(!accessLevel.equals("Admin")){
            return "AccessError";
        }

        model.addAttribute("passKey", new PasswordForm());
        model.addAttribute("managerId", managerId);

        // Open managers page
        return "manager_password";
    }

    //------------------------------------------------------------------------------------------------------------------
    // Mangers page to manager users
    @RequestMapping(path = "/PasswordManagerDetails/{id}", method = RequestMethod.POST)
    public String ManagerPasswordProcess(@PathVariable("id") String managerId,
                                         @SessionAttribute("accessLevel")String accessLevel,
                                         @ModelAttribute("passKey") @Valid PasswordForm passwordForm,
                                         BindingResult bindingResult, Model model) {


        if(!accessLevel.equals("Admin")){
            return "AccessError";
        }

        //--------------------------------------------------------------------------------------------------------------
        // Check that the supplied end date is later or the same as the start date
        if ((passwordForm.getPassword1() != null) & (passwordForm.getPassword2() != null)){
            if(!passwordForm.getPassword1().equals(passwordForm.getPassword2())) {
                bindingResult.rejectValue("password2", "error.password2", "Passwords must match");
            }
        }

        //--------------------------------------------------------------------------------------------------------------
        // Validate the form, else force resubmission
        if (bindingResult.hasErrors()) {
            model.addAttribute("passKey", passwordForm);
            model.addAttribute("managerId", managerId);
            return "manager_password";
        }


        // Breaks without encrypting
        String tempPassword = passwordEncoder.encode(passwordForm.getPassword1());
        staffCreator.updateManagerPassword(Integer.parseInt(managerId), tempPassword);

        // Open managers page
        return "redirect:/ManagerDash";
    }


    //------------------------------------------------------------------------------------------------------------------
    // Mangers page to manager users
    @RequestMapping(path = "/DeactivateManager/{id}", method = RequestMethod.GET)
    public String deactivateManager(@PathVariable("id") String managerId, @SessionAttribute("accessLevel")String accessLevel,Model model) {


        if(!accessLevel.equals("Admin")){
            return "AccessError";
        }

        staffCreator.deactivateManager(Integer.parseInt(managerId));

        // Open managers page
        return "redirect:/ManagerDash";
    }
}

