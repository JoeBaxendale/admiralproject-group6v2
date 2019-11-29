package data;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

//----------------------------------------------------------------------------------------------------------------------
// Maps the Staff entity for database access
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="admiral_staff")
public class Staff {

    //------------------------------------------------------------------------------------------------------------------
    // Unique identifier
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admiral_staff_id")
    private int staffId;

    //------------------------------------------------------------------------------------------------------------------
    @Column(name="admiral_staff_email")
    private String staffEmail;

    //------------------------------------------------------------------------------------------------------------------
    @Column(name="password")
    private String password;

    //------------------------------------------------------------------------------------------------------------------
    @Column(name="first_name")
    private String firstName;

    //------------------------------------------------------------------------------------------------------------------
    @Column(name="last_name")
    private String lastName;
}
