package ma.projet.gestion.service.model;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {

    @NotBlank
    @Size(
        min = 3,
        max = 20
    )
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(
        min = 6,
        max = 40
    )
    private String password;

    private Set<String> roles;

}
