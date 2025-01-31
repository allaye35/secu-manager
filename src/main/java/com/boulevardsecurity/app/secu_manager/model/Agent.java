package com.boulevardsecurity.app.secu_manager.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "agents")
public class Agent extends Utilisateur {

    private boolean disponibilite; // Indique si l'agent est disponible

    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Certification> certifications = new ArrayList<>();

    private String statut; // Statut de l'agent (Actif, Suspendu, etc.)

    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Absence> absences = new ArrayList<>();

    @OneToMany(mappedBy = "agentAffecte", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Mission> missions = new ArrayList<>();

    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pointe> pointages = new ArrayList<>();

    /**
     *  Effectue un pointage d'entrée et l'enregistre dans la liste des pointages.
     */
    public void pointerEntree(Date date) {
        Pointe pointage = new Pointe();
        pointage.setDateDebut(date);
        pointage.setType("Entrée");
        pointage.setAgent(this);
        pointages.add(pointage);
        System.out.println(" Agent " + getNom() + " a pointé l'entrée à : " + date);
    }

    /**
     *  Effectue un pointage de sortie et l'enregistre dans la liste des pointages.
     */
    public void pointerSortie(Date date) {
        Pointe pointage = new Pointe();
        pointage.setDateDebut(date);
        pointage.setType("Sortie");
        pointage.setAgent(this);
        pointages.add(pointage);
        System.out.println(" Agent " + getNom() + " a pointé la sortie à : " + date);
    }

    /**
     *  Enregistre une absence en s'assurant qu'elle ne soit pas déjà enregistrée.
     */
    public void enregistrerAbsence(Date date, String motif) {
        for (Absence absence : absences) {
            if (absence.getDate().equals(date)) {
                System.out.println("⚠ Absence déjà enregistrée pour la date : " + date);
                return;
            }
        }
        Absence nouvelleAbsence = new Absence();
        nouvelleAbsence.setDate(date);
        nouvelleAbsence.setMotif(motif);
        nouvelleAbsence.setAgent(this);
        absences.add(nouvelleAbsence);
        System.out.println(" Absence enregistrée : " + date + " - Motif : " + motif);
    }

    /**
     *  Vérifie si l'agent est disponible sur une période donnée en fonction de ses missions.
     */
    public boolean verifierDisponibilite(Date dateDebut, Date dateFin) {
        if (!disponibilite) {
            System.out.println(" L'agent " + getNom() + " n'est pas disponible.");
            return false;
        }
        for (Mission mission : missions) {
            if (mission.verifierChevauchement(dateDebut, dateFin)) {
                System.out.println(" Conflit avec une mission existante.");
                return false;
            }
        }
        System.out.println(" L'agent " + getNom() + " est disponible.");
        return true;
    }

    /**
     *  Planifie une mission en fonction d'une stratégie.
     */
    public void planifierMission(StrategiePlanification strategie, Mission mission) {
        strategie.planifierMission(List.of(this), mission);
        System.out.println("📅 Mission planifiée pour l'agent " + getNom());
    }
}
