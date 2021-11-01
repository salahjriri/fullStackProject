package ma.projet.gestion.controller;

import ma.projet.gestion.service.component.UserService;
import ma.projet.gestion.service.model.PublicContent;
import ma.projet.gestion.service.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/springbootapi/app")
public class MainController {

    @Autowired
    UserService userService;

    @GetMapping(
        path = "/public",
        produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity<PublicContent> getPublicContent() {
        var publicContent = new PublicContent(
            "So this is the public content...",
            "This is an example project. This project contains ReactJs on the front-end and Spring Boot with Spring "
            + "Security and JWT authentication on the back-end. The database is PostgreSQL."
        );

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(publicContent);
    }

    @GetMapping(
        path = "/user/{userId}",
        produces = { MediaType.APPLICATION_JSON_VALUE }
    )
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<UserDTO> getUserDetails(@PathVariable Long userId) {
        UserDTO userDetailsById = userService.getUserDetailsById(userId);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userDetailsById);
    }


}
