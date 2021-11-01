package ma.projet.gestion.service.component;

import ma.projet.gestion.data.model.connection.Role;
import ma.projet.gestion.data.model.connection.RoleDAO;
import ma.projet.gestion.data.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Set<RoleDAO> getRoleDAOs(Set<String> stringRoles) {
        Set<RoleDAO> roleDAOs = new HashSet<>();

        if (CollectionUtils.isEmpty(stringRoles)) {
            RoleDAO userRoleDAO = roleRepository.findByName(Role.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roleDAOs.add(userRoleDAO);
        } else {
            stringRoles.forEach((String role) -> {
                if (Role.ROLE_ADMIN.toString().equals(role)) {
                    RoleDAO adminRoleDAO = roleRepository.findByName(Role.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roleDAOs.add(adminRoleDAO);
                    } else if (Role.ROLE_USER.toString().equals(role)) {
                    RoleDAO userRoleDAO = roleRepository.findByName(Role.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roleDAOs.add(userRoleDAO);
                }
            });
        }

        return roleDAOs;
    }

}
