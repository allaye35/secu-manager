package com.boulevardsecurity.app.secu_manager.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "agents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private String email;
    private boolean disponibilite;
    private String statut; // Actif, Suspendu, etc.

    @Enumerated(EnumType.STRING)
    private Role role; // Rôle de l'agent (ADMIN, SUPERVISEUR, AGENT)

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "agent_permissions", joinColumns = @JoinColumn(name = "agent_id"))
    @Column(name = "permission")
    private List<String> permissions = new ArrayList<>();

    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Certification> certifications = new ArrayList<>();

    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Absence> absences = new ArrayList<>();

    @OneToMany(mappedBy = "agentAffecte", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Mission> missions = new ArrayList<>();

    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Pointe> pointages = new ArrayList<>();

    /**
     * Enregistre un pointage (Entrée ou Sortie).
     */
    public void enregistrerPointage(Date date, String type) {
        Pointe pointage = Pointe.builder()
                .dateDebut(date)
                .type(type)
                .agent(this)
                .build();
        pointages.add(pointage);
    }

    /**
     * Vérifie et enregistre une absence si elle n'existe pas déjà.
     */
    public void enregistrerAbsence(Date date, String motif) {
        boolean dejaEnregistre = absences.stream().anyMatch(absence -> absence.getDate().equals(date));
        if (dejaEnregistre) {
            throw new IllegalArgumentException("⚠ Absence déjà enregistrée pour la date : " + date);
        }
        Absence absence = Absence.builder()
                .date(date)
                .motif(motif)
                .agent(this)
                .build();
        absences.add(absence);
    }

    /**
     * Vérifie si l'agent est disponible sur une période donnée.
     */
    public boolean verifierDisponibilite(Date dateDebut, Date dateFin) {
        if (!disponibilite) return false;
        return missions.stream().noneMatch(mission -> mission.verifierChevauchement(dateDebut, dateFin));
    }

    /**
     * Associe une mission à l'agent selon une stratégie.
     */
    public void planifierMission(StrategiePlanification strategie, Mission mission) {
        strategie.planifierMission(List.of(this), mission);
    }

    /**
     * Ajoute une permission à l'agent.
     */
    public void ajouterPermission(String permission) {
        if (!permissions.contains(permission)) {
            permissions.add(permission);
        }
    }

    /**
     * Supprime une permission à l'agent.
     */
    public void supprimerPermission(String permission) {
        permissions.remove(permission);
    }
}
