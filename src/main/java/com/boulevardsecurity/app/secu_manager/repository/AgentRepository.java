package com.boulevardsecurity.app.secu_manager.repository;
import com.boulevardsecurity.app.secu_manager.model.Agent;
import com.boulevardsecurity.app.secu_manager.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Integer> {

    // Trouver un agent par son email
    Optional<Agent> findByEmail(String email);

    // Trouver tous les agents disponibles
    List<Agent> findByDisponibiliteTrue();

    // Trouver les agents par statut
    List<Agent> findByStatut(String statut);

    // Trouver les agents par rôle (en principe, toujours "AGENT")
    List<Agent> findByRole(Role role);

    // Trouver les agents affectés à un lieu spécifique
    List<Agent> findByLieuAffectation(String lieuAffectation);

    // Trouver les agents ayant une certaine certification
    List<Agent> findByCertificationsContaining(String certification);

    // Trouver les agents ayant une certaine disponibilité pour une période donnée
    List<Agent> findByDisponibiliteTrueAndStatutNot(String statut);

    // Trouver les agents ayant travaillé dans une période donnée
    List<Agent> findByMissionsContaining(String mission);

    // Trouver les agents ayant été absents dans une période donnée
    List<Agent> findByAbsencesContaining(String absence);

    // Trouver les agents embauchés après une date spécifique
    List<Agent> findByDateEmbaucheAfter(Date date);
}
