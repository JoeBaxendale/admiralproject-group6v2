package admiral.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="admiral_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admiral_role_id")
    private int roleId;

    @Column(name="admiral_role_name")
    private String roleName;

    @Column(name="admiral_role_description")
    private String roleDescription;
}
