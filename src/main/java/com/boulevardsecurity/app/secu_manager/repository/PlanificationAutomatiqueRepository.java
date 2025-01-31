package com.boulevardsecurity.app.secu_manager.repository;
import com.boulevardsecurity.app.secu_manager.model.PlanificationAutomatique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlanificationAutomatiqueRepository extends JpaRepository<PlanificationAutomatique, Integer> {

    // Trouver toutes les planifications associées à une mission spécifique
    List<PlanificationAutomatique> findByMissionId(int missionId);

    // Trouver toutes les planifications impliquant un agent spécifique
    List<PlanificationAutomatique> findByAgents_Id(int agentId);

    // Vérifier si une mission a déjà une planification
    boolean existsByMissionId(int missionId);
}
