package com.boulevardsecurity.app.secu_manager.service;
import com.boulevardsecurity.app.secu_manager.dto.DocumentDTO;
import com.boulevardsecurity.app.secu_manager.mapper.DocumentMapper;
import com.boulevardsecurity.app.secu_manager.model.Document;
import com.boulevardsecurity.app.secu_manager.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;

    @Autowired
    public DocumentService(DocumentRepository documentRepository, DocumentMapper documentMapper) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
    }

    /**
     * Récupérer tous les documents.
     */
    @Transactional(readOnly = true)
    public List<DocumentDTO> getAllDocuments() {
        return documentRepository.findAll().stream()
                .map(documentMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupérer un document par son ID.
     */
    @Transactional(readOnly = true)
    public DocumentDTO getDocumentById(int id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document avec ID " + id + " non trouvé"));
        return documentMapper.toDto(document);
    }

    /**
     * Créer ou mettre à jour un document.
     */
    @Transactional
    public DocumentDTO saveDocument(DocumentDTO dto) {
        Document document = documentMapper.toEntity(dto);
        Document savedDocument = documentRepository.save(document);
        return documentMapper.toDto(savedDocument);
    }

    /**
     * Supprimer un document par son ID.
     */
    @Transactional
    public void deleteDocument(int id) {
        if (!documentRepository.existsById(id)) {
            throw new RuntimeException("Document avec ID " + id + " non trouvé");
        }
        documentRepository.deleteById(id);
    }

    /**
     * Rechercher des documents par type.
     */
    @Transactional(readOnly = true)
    public List<DocumentDTO> getDocumentsByType(String type) {
        return documentRepository.findByType(type).stream()
                .map(documentMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Rechercher des documents contenant un mot-clé dans le contenu.
     */
    @Transactional(readOnly = true)
    public List<DocumentDTO> searchDocumentsByKeyword(String keyword) {
        return documentRepository.findByContenuContaining(keyword).stream()
                .map(documentMapper::toDto)
                .collect(Collectors.toList());
    }
}
