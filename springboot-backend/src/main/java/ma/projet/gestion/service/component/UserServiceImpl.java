package ma.projet.gestion.service.component;

import lombok.extern.log4j.Log4j2;
import ma.projet.gestion.data.model.connection.Role;
import ma.projet.gestion.data.model.connection.RoleDAO;
import ma.projet.gestion.data.model.connection.UtilisateurDao;
import ma.projet.gestion.data.repository.UserRepository;
import ma.projet.gestion.exception.RegistrationException;
import ma.projet.gestion.exception.ResourceNotFoundException;
import ma.projet.gestion.security.component.JwtTokenUtil;
import ma.projet.gestion.security.service.UserDetailsImpl;
import ma.projet.gestion.service.model.LoginRequest;
import ma.projet.gestion.service.model.LoginResponse;
import ma.projet.gestion.service.model.RegistrationRequest;
import ma.projet.gestion.service.model.UserDTO;
import ma.projet.gestion.util.ModelConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    RoleService roleService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ModelConverterUtil modelConverterUtil;

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtTokenUtil.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());


        return new LoginResponse(
            userDetails.getId(),
            jwtToken,
            roles
        );
    }

    @Override
    public UserDTO registerUser(RegistrationRequest registrationRequest) {
        if (registrationRequest.getRoles().contains(Role.ROLE_ADMIN.toString())) {
            throw new RegistrationException("Admin can not be registered this way!");
        }

        if (userRepository.existsByUsername(registrationRequest.getUsername())) {
            throw new RegistrationException("This username is already exists!");
        }

        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new RegistrationException("Error: Email is already in use!");
        }

        String encodedPassword = encoder.encode(registrationRequest.getPassword());
        Set<RoleDAO> roleDAOs = roleService.getRoleDAOs(registrationRequest.getRoles());

        UtilisateurDao userDAO = new UtilisateurDao(registrationRequest.getUsername(), registrationRequest.getEmail(),encodedPassword,
            roleDAOs
        );

        UtilisateurDao savedUser = userRepository.save(userDAO);

        return modelConverterUtil.getUserDTO(savedUser);
    }

    @Override
    public UserDTO getUserDetailsById(Long userId) {
        UtilisateurDao userDAO = userRepository.getById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        return modelConverterUtil.getUserDTO(userDAO);
    }




}
