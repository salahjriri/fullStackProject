package ma.projet.gestion.util;

import ma.projet.gestion.data.model.connection.RoleDAO;
import ma.projet.gestion.data.model.connection.UtilisateurDao;
import ma.projet.gestion.service.model.RoleDTO;
import ma.projet.gestion.service.model.UserDTO;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ModelConverterUtil {

    public UserDTO getUserDTO(UtilisateurDao savedUser) {
        return new UserDTO(
            savedUser.getId(),
            savedUser.getUsername(),
            savedUser.getEmail(),
            savedUser.getPassword(),
            getRoleDTOs(savedUser.getRoleDAOs())
        );
    }

    private Set<RoleDTO> getRoleDTOs(Set<RoleDAO> roleDAOs) {
        return roleDAOs.stream()
            .map(this::getRoleDTO)
            .collect(Collectors.toSet());
    }

    private RoleDTO getRoleDTO(RoleDAO roleDAO) {
        return new RoleDTO(
            roleDAO.getId(),
            roleDAO.getName()
        );
    }

}
