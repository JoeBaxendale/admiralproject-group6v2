package admiral.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

//----------------------------------------------------------------------------------------------------------------------
// Details to be returned for a user. Intended to be used for providing logged in user details.
// It implements the Spring Security UserDetails class so the user information of the logged in user will be available
// to the application
@Data
@NoArgsConstructor
public class UserInfo implements UserDetails{

    private int userId;
    // Username is the email address in our case
    private String username;
    private String password;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private boolean accountNonLocked;
    private Set<GrantedAuthority> authorities;
    private String role;

    private String firstName;
    private String lastName;
    //------------------------------------------------------------------------------------------------------------------
    // Return a contractor id for the user if they are a contractor.
    // If the user isn't a contractor this will contain zero;
    private int contractorId;
    //------------------------------------------------------------------------------------------------------------------
    // Return a manager id for the user if they are a manager.
    // If the user isn't a manager this will contain zero;
    private int managerId;




    UserInfo(String username, String password, boolean enabled,
             boolean accountNonExpired, boolean credentialsNonExpired,
             boolean accountNonLocked,
             String role,
             int userId, String emailAddress,
             String firstName, String lastName,
             int contractorId, int managerId) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.authorities = Set.of(new SimpleGrantedAuthority(role));
        this.firstName = firstName;
        this.lastName = lastName;
        this.contractorId = contractorId;
        this.managerId = managerId;
    }



    // Provide a setter for role that will set the authorities collection
    public void setRole(String role) {
        this.role = role;
        this.authorities = Set.of(new SimpleGrantedAuthority(role));
    }



}