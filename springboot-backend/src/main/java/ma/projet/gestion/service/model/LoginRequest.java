package ma.projet.gestion.service.model;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
