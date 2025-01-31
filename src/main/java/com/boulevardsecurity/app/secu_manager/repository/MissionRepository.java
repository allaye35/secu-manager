package com.boulevardsecurity.app.secu_manager.repository;
import com.boulevardsecurity.app.secu_manager.model.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Integer> {

    // Trouver toutes les missions affectées à un agent spécifique
    List<Mission> findByAgentAffecteId(int agentId);

    // Trouver les missions en cours
    List<Mission> findByDateDebutBeforeAndDateFinAfter(Date today1, Date today2);

    // Trouver les missions terminées
    List<Mission> findByDateFinBefore(Date today);

    // Trouver les missions planifiées après une date donnée
    List<Mission> findByDateDebutAfter(Date date);

    // Trouver les missions avec un chevauchement de dates
    List<Mission> findByDateDebutLessThanEqualAndDateFinGreaterThanEqual(Date dateFin, Date dateDebut);
}

