package com.boulevardsecurity.app.secu_manager.repository;
import com.boulevardsecurity.app.secu_manager.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {

    // Trouver un document par son type
    List<Document> findByType(String type);

    // Rechercher un document par son contenu
    List<Document> findByContenuContaining(String keyword);

    // Vérifier si un document existe par son type
    boolean existsByType(String type);

    // Trouver un document par son ID
    Optional<Document> findById(Integer id);
}
