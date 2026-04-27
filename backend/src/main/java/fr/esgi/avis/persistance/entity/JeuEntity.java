package fr.esgi.avis.persistance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class JeuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private LocalDate dateDeSortie;

    private String description;

    @ManyToOne
    private EditeurEntity editeur;

    @ManyToOne
    private ClassificationEntity classification;

    @ManyToMany
    private Set<PlateformeEntity> plateformes = new HashSet<>();

    @ManyToOne
    private GenreEntity genre;

    private float prix;

    private String image;

    private boolean possedeImage;
}
