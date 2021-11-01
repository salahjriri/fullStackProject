package ma.projet.gestion.service.model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    private String username;

    private String email;

    private String password;

    private Set<RoleDTO> roles = new HashSet<>();

   

}
