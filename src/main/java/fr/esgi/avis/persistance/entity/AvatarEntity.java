package fr.esgi.avis.persistance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
public class AvatarEntity {

    @Id
    private Long id;

    private String nom;


    private JoueurEntity joueur;
}
