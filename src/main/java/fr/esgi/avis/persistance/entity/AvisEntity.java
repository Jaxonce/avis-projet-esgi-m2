package fr.esgi.avis.persistance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDateTime;

@Entity
public class AvisEntity {

    @Id
    private Long id;

    private String description;

    private LocalDateTime dateDEnvoi;

    private float note;

    @ManyToOne
    private JeuEntity jeu;

    @ManyToOne
    private JoueurEntity joueur;

    @ManyToOne
    private ModerateurEntity moderateur;
}
