package com.boulevardsecurity.app.secu_manager.repository;
import com.boulevardsecurity.app.secu_manager.model.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ContratRepository extends JpaRepository<Contrat, Integer> {

    // Trouver tous les contrats d'un agent spécifique
    List<Contrat> findByAgentId(int agentId);

    // Trouver les contrats actifs (en cours)
    List<Contrat> findByDateDebutBeforeAndDateFinAfter(Date today1, Date today2);

    // Trouver les contrats terminés
    List<Contrat> findByDateFinBefore(Date today);

    // Trouver les contrats signés après une certaine date
    List<Contrat> findByDateDebutAfter(Date date);

    // Trouver les contrats en cours d'un agent donné
    List<Contrat> findByAgentIdAndDateFinAfter(int agentId, Date today);
}
