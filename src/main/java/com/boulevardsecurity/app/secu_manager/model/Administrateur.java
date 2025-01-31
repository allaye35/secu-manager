package com.boulevardsecurity.app.secu_manager.model;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Administrateur extends Utilisateur {
    public void choisirStrategiePlanification(StrategiePlanification strategie) {
        System.out.println("Stratégie choisie : " + strategie);
    }

    public void gererMission() {
        System.out.println("Gérer les missions.");
    }
}

