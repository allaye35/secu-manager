package com.boulevardsecurity.app.secu_manager.repository;

import com.boulevardsecurity.app.secu_manager.model.Utilisateur;
import com.boulevardsecurity.app.secu_manager.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    // Trouver un utilisateur par son email
    Optional<Utilisateur> findByEmail(String email);

    // Trouver tous les utilisateurs par rôle
    List<Utilisateur> findByRole(Role role);

    // Trouver tous les utilisateurs disponibles (agents uniquement)
    List<Utilisateur> findByDisponibiliteTrue();

    // Trouver un utilisateur par son nom
    List<Utilisateur> findByNomContainingIgnoreCase(String nom);

    // Trouver tous les utilisateurs avec un statut spécifique
    List<Utilisateur> findByStatut(String statut);
}

