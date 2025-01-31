package com.boulevardsecurity.app.secu_manager.repository;

import com.boulevardsecurity.app.secu_manager.model.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministrateurRepository extends JpaRepository<Administrateur, Integer> {

    // Trouver un administrateur par son email
    Optional<Administrateur> findByEmail(String email);

    // Vérifier si un administrateur existe par email
    boolean existsByEmail(String email);
}
