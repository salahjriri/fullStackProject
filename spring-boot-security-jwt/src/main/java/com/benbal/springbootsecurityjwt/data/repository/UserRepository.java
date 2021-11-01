package com.benbal.springbootsecurityjwt.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.benbal.springbootsecurityjwt.data.model.UtilisateurDao;

@Repository
public interface UserRepository extends JpaRepository<UtilisateurDao, Long> {

    Optional<UtilisateurDao> findByUsername(String username);

    Optional<UtilisateurDao> getById(Long userId);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Long countAllByUsernameNotNull();

}
