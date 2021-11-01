package ma.projet.gestion.service.component;


import ma.projet.gestion.service.model.*;

public interface UserService {

    LoginResponse loginUser(LoginRequest loginRequest);

    UserDTO registerUser(RegistrationRequest registrationRequest);

    UserDTO getUserDetailsById(Long userId);


}
