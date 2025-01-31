package com.boulevardsecurity.app.secu_manager.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pointe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut;

    private String type; // Type de la pointe (par exemple, "entrée" ou "sortie")

    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = false)
    private Agent agent;

    // Méthode pour vérifier la présence d'un agent
    public boolean verifierPresence() {
        return this.agent != null; // Vérifie si l'agent est associé à cette pointe
    }

    // Méthode pour associer un agent à une pointe
    public void associerAgent(Agent agent) {
        this.agent = agent;
    }

    @Override
    public String toString() {
        return "Pointe{" +
                "id=" + id +
                ", dateDebut=" + dateDebut +
                ", type='" + type + '\'' +
                ", agent=" + (agent != null ? agent.getNom() : "Aucun agent associé") +
                '}';
    }
}
