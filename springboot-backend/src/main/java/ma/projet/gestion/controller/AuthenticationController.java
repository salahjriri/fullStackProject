package ma.projet.gestion.controller;

import lombok.extern.log4j.Log4j2;
import ma.projet.gestion.service.component.UserService;
import ma.projet.gestion.service.model.LoginRequest;
import ma.projet.gestion.service.model.LoginResponse;
import ma.projet.gestion.service.model.RegistrationRequest;
import ma.projet.gestion.service.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/springbootapi/auth")
public class AuthenticationController {

    @Autowired
    UserService userService;

    @PutMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        log.info("Incoming login: {}", loginRequest.toString());
        LoginResponse loginResponse = userService.loginUser(loginRequest);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(loginResponse);
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody RegistrationRequest registrationRequest) {
        log.info("Incoming user registration: {}", registrationRequest.toString());
        UserDTO savedUser = userService.registerUser(registrationRequest);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(savedUser);
    }

}
