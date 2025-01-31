package com.boulevardsecurity.app.secu_manager.service;

import com.boulevardsecurity.app.secu_manager.dto.UtilisateurDTO;
import com.boulevardsecurity.app.secu_manager.mapper.UtilisateurMapper;
import com.boulevardsecurity.app.secu_manager.model.Utilisateur;
import com.boulevardsecurity.app.secu_manager.model.Role;
import com.boulevardsecurity.app.secu_manager.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurMapper utilisateurMapper;

    /**
     * Constructeur avec injection des dépendances.
     * Utilisation de @Autowired sur le constructeur (facultatif depuis Spring 4.3).
     */
    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository, UtilisateurMapper utilisateurMapper) {
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurMapper = utilisateurMapper;
    }

    /**
     * Récupère tous les utilisateurs sous forme de DTO.
     */
    @Transactional(readOnly = true)
    public List<UtilisateurDTO> getAllUtilisateurs() {
        return utilisateurRepository.findAll().stream()
                .map(utilisateurMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupère un utilisateur par son ID.
     */
    @Transactional(readOnly = true)
    public UtilisateurDTO getUtilisateurById(int id) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur avec ID " + id + " non trouvé"));
        return utilisateurMapper.toDto(utilisateur);
    }

    /**
     * Sauvegarde un utilisateur (création ou mise à jour).
     */
    @Transactional
    public UtilisateurDTO saveUtilisateur(UtilisateurDTO dto) {
        Utilisateur utilisateur = utilisateurMapper.toEntity(dto);
        Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
        return utilisateurMapper.toDto(savedUtilisateur);
    }

    /**
     * Supprime un utilisateur par son ID.
     */
    @Transactional
    public void deleteUtilisateur(int id) {
        if (!utilisateurRepository.existsById(id)) {
            throw new RuntimeException("Utilisateur avec ID " + id + " non trouvé");
        }
        utilisateurRepository.deleteById(id);
    }

    /**
     * Récupère les utilisateurs par rôle.
     */
    @Transactional(readOnly = true)
    public List<UtilisateurDTO> getUtilisateursByRole(Role role) {
        return utilisateurRepository.findByRole(role).stream()
                .map(utilisateurMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupère les utilisateurs disponibles (agents uniquement).
     */
    @Transactional(readOnly = true)
    public List<UtilisateurDTO> getUtilisateursDisponibles() {
        return utilisateurRepository.findByDisponibiliteTrue().stream()
                .map(utilisateurMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Recherche des utilisateurs par nom (recherche partielle, insensible à la casse).
     */
    @Transactional(readOnly = true)
    public List<UtilisateurDTO> searchUtilisateursByNom(String nom) {
        return utilisateurRepository.findByNomContainingIgnoreCase(nom).stream()
                .map(utilisateurMapper::toDto)
                .collect(Collectors.toList());
    }
}

