package com.boulevardsecurity.app.secu_manager.repository;

import com.boulevardsecurity.app.secu_manager.model.Pointe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointeRepository extends JpaRepository<Pointe, Integer> {
    // Ajouter des méthodes de recherche spécifiques si nécessaire
}
