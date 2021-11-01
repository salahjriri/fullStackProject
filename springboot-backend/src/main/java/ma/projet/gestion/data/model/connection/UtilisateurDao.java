package ma.projet.gestion.data.model.connection;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class UtilisateurDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @ManyToMany(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleDAO> roleDAOs = new HashSet<>();

    @NotBlank
    @Size(max = 120)
    private String password;


    public UtilisateurDao(String username, String email, String password, Set<RoleDAO> roleDAOs) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roleDAOs = roleDAOs;
    }

}
