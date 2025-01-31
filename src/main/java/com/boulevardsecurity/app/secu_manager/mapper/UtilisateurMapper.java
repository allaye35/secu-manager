package com.boulevardsecurity.app.secu_manager.mapper;


import com.boulevardsecurity.app.secu_manager.dto.UtilisateurDTO;
import com.boulevardsecurity.app.secu_manager.model.Utilisateur;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurMapper {

    public UtilisateurDTO toDto(Utilisateur utilisateur) {
        UtilisateurDTO dto = new UtilisateurDTO();
        dto.setId(utilisateur.getId());
        dto.setNom(utilisateur.getNom());
        dto.setEmail(utilisateur.getEmail());
        dto.setRole(utilisateur.getRole().name());
        dto.setPermissions(utilisateur.getPermissions());

        if (utilisateur instanceof com.boulevardsecurity.app.secu_manager.model.Agent agent) {
            dto.setDisponibilite(agent.isDisponibilite());
            dto.setCertifications(agent.getCertifications());
            dto.setStatut(agent.getStatut());
        }

        return dto;
    }

    public Utilisateur toEntity(UtilisateurDTO dto) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(dto.getId());
        utilisateur.setNom(dto.getNom());
        utilisateur.setEmail(dto.getEmail());
        utilisateur.setRole(com.boulevardsecurity.app.secu_manager.model.Role.valueOf(dto.getRole()));
        utilisateur.setPermissions(dto.getPermissions());

        return utilisateur;
    }
}

