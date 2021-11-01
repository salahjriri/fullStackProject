package ma.projet.gestion.service.component;


import ma.projet.gestion.data.model.connection.RoleDAO;

import java.util.Set;

public interface RoleService {

    Set<RoleDAO> getRoleDAOs(Set<String> stringRoles);

}
