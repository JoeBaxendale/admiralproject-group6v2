package admiral.domain;

//----------------------------------------------------------------------------------------------------------------------
// Imports

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

//----------------------------------------------------------------------------------------------------------------------
//Defining mapping of the table users
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ManagerUser {

    //------------------------------------------------------------------------------------------------------------------
    // Unique identifier
    @Id
    @Column(name = "manager_id")
    private int manager_id;

    //------------------------------------------------------------------------------------------------------------------
    @Column(name = "user_id")
    private int user_id;

    //------------------------------------------------------------------------------------------------------------------
    @Column(name="first_name")
    private String firstName;

    //------------------------------------------------------------------------------------------------------------------
    @Column(name="last_name")
    private String lastName;

    //------------------------------------------------------------------------------------------------------------------
    @Column(name="email")
    private String staffEmail;

    //------------------------------------------------------------------------------------------------------------------
    @Column(name="active")
    private Boolean active;
}
