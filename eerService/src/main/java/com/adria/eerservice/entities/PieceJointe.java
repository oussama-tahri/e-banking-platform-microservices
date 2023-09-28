package com.adria.eerservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Objects;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class PieceJointe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;  // Exemple : "Carte Nationale", "Passeport", etc.

    @Column(nullable = false)
    private String nom;   // Exemple : "Carte Nationale_recto", "Passeport_verso", etc.

    @Lob
    @Column(nullable = false)
    private byte[] contenu;
    @ManyToOne
    private EER eer;

    // Constructeurs, getters et setters

    // Écrivez les constructeurs, getters et setters ici...

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PieceJointe that = (PieceJointe) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

