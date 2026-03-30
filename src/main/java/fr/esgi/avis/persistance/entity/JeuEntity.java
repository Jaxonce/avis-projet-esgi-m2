package fr.esgi.avis.persistance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class JeuEntity {

    @Id
    private Long id;

    private String nom;

    private LocalDate dateDeSortie;

    private String description;

    private EditeurEntity editeur;

    private ClassificationEntity classification;

    private List<PlateformeEntity> plateformes = new ArrayList<>();

    private GenreEntity genre;

    private float prix;

    private String image;

    private boolean possedeImage;
}
