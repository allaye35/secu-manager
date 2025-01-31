package com.boulevardsecurity.app.secu_manager.mapper;

import com.boulevardsecurity.app.secu_manager.dto.AdministrateurDTO;
import com.boulevardsecurity.app.secu_manager.model.Administrateur;
import org.springframework.stereotype.Component;

@Component
public class AdministrateurMapper {
    public AdministrateurDTO toDto(Administrateur administrateur) {
        return new AdministrateurDTO(
                administrateur.getId(),
                administrateur.getNom(),
                administrateur.getEmail()
        );
    }

    public Administrateur toEntity(AdministrateurDTO dto) {
        return Administrateur.builder()
                .id(dto.getId())
                .nom(dto.getNom())
                .email(dto.getEmail())
                .build();
    }
}
