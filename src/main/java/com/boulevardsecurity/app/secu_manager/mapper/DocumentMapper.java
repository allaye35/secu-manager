package com.boulevardsecurity.app.secu_manager.mapper;


import com.boulevardsecurity.app.secu_manager.dto.DocumentDTO;
import com.boulevardsecurity.app.secu_manager.model.Document;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapper {
    public DocumentDTO toDto(Document document) {
        return new DocumentDTO(
                document.getId(),
                document.getType(),
                document.getContenu()
        );
    }

    public Document toEntity(DocumentDTO dto) {
        return Document.builder()
                .id(dto.getId())
                .type(dto.getType())
                .contenu(dto.getContenu())
                .build();
    }
}