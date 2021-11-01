package ma.projet.gestion.data.repository;

import ma.projet.gestion.data.model.connection.UtilisateurDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UtilisateurDao, Long> {

    Optional<UtilisateurDao> findByUsername(String username);

    Optional<UtilisateurDao> getById(Long userId);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);


}
