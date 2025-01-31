package com.boulevardsecurity.app.secu_manager.repository;

import com.boulevardsecurity.app.secu_manager.model.PlanificationOptimisee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanificationOptimiseeRepository extends JpaRepository<PlanificationOptimisee, Integer> {

    // Trouver toutes les planifications associées à une mission
    List<PlanificationOptimisee> findByMissionId(int missionId);

    // Trouver toutes les planifications associées à un agent spécifique
    List<PlanificationOptimisee> findByAgents_Id(int agentId);

    // Vérifier si une mission a une planification optimisée existante
    boolean existsByMissionId(int missionId);

    // Supprimer toutes les planifications associées à une mission spécifique
    void deleteByMissionId(int missionId);

    // Supprimer toutes les planifications associées à un agent spécifique
    void deleteByAgents_Id(int agentId);
}
