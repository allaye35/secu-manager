package com.boulevardsecurity.app.secu_manager.repository;

import com.boulevardsecurity.app.secu_manager.model.PlanificationManuelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanificationManuelleRepository extends JpaRepository<PlanificationManuelle, Integer> {

    // Trouver toutes les planifications associées à une mission
    List<PlanificationManuelle> findByMissionId(int missionId);

    // Trouver toutes les planifications associées à un agent spécifique
    List<PlanificationManuelle> findByAgents_Id(int agentId);

    // Vérifier si une mission a une planification manuelle existante
    boolean existsByMissionId(int missionId);

    // Trouver les planifications pour plusieurs agents
    List<PlanificationManuelle> findByAgents_IdIn(List<Integer> agentIds);

    // Supprimer toutes les planifications associées à une mission spécifique
    void deleteByMissionId(int missionId);

    // Supprimer toutes les planifications associées à un agent spécifique
    void deleteByAgents_Id(int agentId);
}
