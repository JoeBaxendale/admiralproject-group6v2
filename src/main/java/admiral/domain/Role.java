package admiral.domain;

//----------------------------------------------------------------------------------------------------------------------
// Imports
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

//----------------------------------------------------------------------------------------------------------------------
// Defining mapping of the table roles
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="roles")
public class Role {

    //------------------------------------------------------------------------------------------------------------------
    // Unique identifier
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleId;

    //------------------------------------------------------------------------------------------------------------------
    @Column(name="role_name")
    private String roleName;

    //------------------------------------------------------------------------------------------------------------------
    @Column(name="role_description")
    private String roleDescription;
}
