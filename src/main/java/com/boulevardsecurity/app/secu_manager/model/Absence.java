package com.boulevardsecurity.app.secu_manager.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Absence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date date; // Date de l'absence

    private String motif; // Motif de l'absence (maladie, congé, etc.)

    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = false)
    private Agent agent; // L'agent associé à cette absence

    // Méthode pour associer un agent
    public void associerAgent(Agent agent) {
        this.agent = agent;
    }

    @Override
    public String toString() {
        return "Absence{" +
                "id=" + id +
                ", date=" + date +
                ", motif='" + motif + '\'' +
                ", agent=" + (agent != null ? agent.getNom() : "Aucun agent associé") +
                '}';
    }
}
