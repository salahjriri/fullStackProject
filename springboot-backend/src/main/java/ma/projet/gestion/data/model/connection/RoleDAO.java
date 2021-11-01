package ma.projet.gestion.data.model.connection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class RoleDAO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Role name;

    public RoleDAO(Role name) {
        this.name = name;
    }

}
