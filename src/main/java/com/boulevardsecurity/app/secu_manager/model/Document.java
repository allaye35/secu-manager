package com.boulevardsecurity.app.secu_manager.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;

    @Lob
    private String contenu;

    public void telecharger() {
        System.out.println("Téléchargement du document de type : " + type);
    }
}




