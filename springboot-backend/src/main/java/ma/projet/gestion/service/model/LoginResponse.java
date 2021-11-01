package ma.projet.gestion.service.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private Long id;

    private String type = "Bearer";

    private String jwtToken;

    private List<String> roles;

    public LoginResponse(Long id, String jwtToken, List<String> roles) {
        this.id = id;
        this.jwtToken = jwtToken;
        this.roles = roles;
    }

}
