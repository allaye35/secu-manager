package com.boulevardsecurity.app.secu_manager.service;

import com.boulevardsecurity.app.secu_manager.dto.AdministrateurDTO;
import com.boulevardsecurity.app.secu_manager.mapper.AdministrateurMapper;
import com.boulevardsecurity.app.secu_manager.model.Administrateur;
import com.boulevardsecurity.app.secu_manager.repository.AdministrateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdministrateurService {

    private final AdministrateurRepository administrateurRepository;
    private final AdministrateurMapper administrateurMapper;

    @Autowired
    public AdministrateurService(AdministrateurRepository administrateurRepository, AdministrateurMapper administrateurMapper) {
        this.administrateurRepository = administrateurRepository;
        this.administrateurMapper = administrateurMapper;
    }

    /**
     * Récupérer tous les administrateurs.
     */
    @Transactional(readOnly = true)
    public List<AdministrateurDTO> getAllAdministrateurs() {
        return administrateurRepository.findAll().stream()
                .map(administrateurMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupérer un administrateur par son ID.
     */
    @Transactional(readOnly = true)
    public AdministrateurDTO getAdministrateurById(int id) {
        Administrateur administrateur = administrateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrateur avec ID " + id + " non trouvé"));
        return administrateurMapper.toDto(administrateur);
    }

    /**
     * Récupérer un administrateur par son email.
     */
    @Transactional(readOnly = true)
    public Optional<AdministrateurDTO> getAdministrateurByEmail(String email) {
        return administrateurRepository.findByEmail(email).map(administrateurMapper::toDto);
    }

    /**
     * Créer ou mettre à jour un administrateur.
     */
    @Transactional
    public AdministrateurDTO saveAdministrateur(AdministrateurDTO dto) {
        Administrateur administrateur = administrateurMapper.toEntity(dto);
        Administrateur savedAdministrateur = administrateurRepository.save(administrateur);
        return administrateurMapper.toDto(savedAdministrateur);
    }

    /**
     * Supprimer un administrateur par son ID.
     */
    @Transactional
    public void deleteAdministrateur(int id) {
        if (!administrateurRepository.existsById(id)) {
            throw new RuntimeException("Administrateur avec ID " + id + " non trouvé");
        }
        administrateurRepository.deleteById(id);
    }
}
