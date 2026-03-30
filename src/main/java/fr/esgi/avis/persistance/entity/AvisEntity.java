package fr.esgi.avis.persistance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
public class AvisEntity {

    @Id
    private Long id;

    private String description;

    private LocalDateTime dateDEnvoi;

    private float note;

    private JeuEntity jeu;

    private JoueurEntity joueur;

    private ModerateurEntity moderateur;
}
