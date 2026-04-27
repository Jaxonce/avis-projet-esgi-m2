package fr.esgi.avis.persistance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class AvatarEntity {

    @Id
    private Long id;

    private String nom;

    @OneToOne
    private JoueurEntity joueur;
}
