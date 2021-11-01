package ma.projet.gestion.data.repository;

import ma.projet.gestion.data.model.connection.Role;
import ma.projet.gestion.data.model.connection.RoleDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleDAO, Long> {

    Optional<RoleDAO> findByName(Role name);

}
