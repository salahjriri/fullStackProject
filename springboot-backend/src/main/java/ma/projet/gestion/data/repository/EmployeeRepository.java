package ma.projet.gestion.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.projet.gestion.data.model.employer.EmployeeDao;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDao, Long>{

}
