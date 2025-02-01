package com.boulevardsecurity.app.secu_manager.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    private Date dateFin;

    @Lob
    private String description;

    private String statut; // Ex: "Planifiée", "En cours", "Terminée"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id", nullable = false)
    private Agent agentAffecte;

    /**
     * Planifie la mission en appliquant une stratégie de planification
     */
    public void planifier(StrategiePlanification strategie) {
        if (strategie != null) {
            strategie.planifierMission((List<Agent>) agentAffecte, this);
            this.statut = "Planifiée"; // Mise à jour du statut après la planification
        } else {
            throw new IllegalArgumentException("Aucune stratégie de planification fournie.");
        }
    }

    /**
     * Affecte un agent à la mission
     */
    public void affecterAgent(Agent agent) {
        if (agent == null) {
            throw new IllegalArgumentException("L'agent ne peut pas être null.");
        }
        this.agentAffecte = agent;
    }

    /**
     * Vérifie si cette mission chevauche une autre mission en comparant les dates
     */
    public boolean verifierChevauchement(Date dateDebut, Date dateFin) {
        return (this.dateDebut.before(dateFin) && this.dateFin.after(dateDebut));
    }

    @Override
    public String toString() {
        return "Mission{" +
                "id=" + id +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", description='" + description + '\'' +
                ", statut='" + statut + '\'' +
                ", agentAffecte=" + (agentAffecte != null ? agentAffecte.getNom() : "Aucun agent") +
                '}';
    }
}
