package ma.projet.gestion.service.model;

import lombok.*;
import ma.projet.gestion.data.model.connection.Role;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {

    private Integer id;

    private Role name;

    public RoleDTO(Role name) {
        this.name = name;
    }

}
