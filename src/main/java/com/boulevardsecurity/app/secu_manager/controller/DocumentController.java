package com.boulevardsecurity.app.secu_manager.controller;
import com.boulevardsecurity.app.secu_manager.dto.DocumentDTO;
import com.boulevardsecurity.app.secu_manager.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
@CrossOrigin(origins = "*") // Permet l'accès depuis le frontend Angular
public class DocumentController {

    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    /**
     * Récupérer tous les documents
     */
    @GetMapping
    public ResponseEntity<List<DocumentDTO>> getAllDocuments() {
        return ResponseEntity.ok(documentService.getAllDocuments());
    }

    /**
     * Récupérer un document par son ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<DocumentDTO> getDocumentById(@PathVariable int id) {
        return ResponseEntity.ok(documentService.getDocumentById(id));
    }

    /**
     * Ajouter ou mettre à jour un document
     */
    @PostMapping
    public ResponseEntity<DocumentDTO> saveDocument(@RequestBody DocumentDTO documentDTO) {
        return ResponseEntity.ok(documentService.saveDocument(documentDTO));
    }

    /**
     * Supprimer un document par son ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable int id) {
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Rechercher des documents par type
     */
    @GetMapping("/type/{type}")
    public ResponseEntity<List<DocumentDTO>> getDocumentsByType(@PathVariable String type) {
        return ResponseEntity.ok(documentService.getDocumentsByType(type));
    }

    /**
     * Rechercher des documents par mot-clé dans le contenu
     */
    @GetMapping("/search")
    public ResponseEntity<List<DocumentDTO>> searchDocumentsByKeyword(@RequestParam String keyword) {
        return ResponseEntity.ok(documentService.searchDocumentsByKeyword(keyword));
    }
}

