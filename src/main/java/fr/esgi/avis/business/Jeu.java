package fr.esgi.avis.business;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Jeu {

    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @ToString.Include
    private String nom;

    @ToString.Include
    private LocalDate dateDeSortie;

    private String description;

    private Editeur editeur;

    private Classification classification;

    private List<Plateforme> plateformes = new ArrayList<>();

    private List<Genre> genres = new ArrayList<>();

    @ToString.Include
    private boolean possedeImage;

    public Jeu(String nom) {
        this.nom = nom;
    }

    public Jeu(String nom, LocalDate dateDeSortie, Editeur editeur, List<Plateforme> plateformes) {
        this.nom = nom;
        this.dateDeSortie = dateDeSortie;
        this.editeur = editeur;
        this.plateformes = plateformes;
    }

    public Jeu(String nom, LocalDate dateDeSortie, Editeur editeur, Classification classification, List<Plateforme> plateformes, List<Genre> genres) {
        this.nom = nom;
        this.dateDeSortie = dateDeSortie;
        this.editeur = editeur;
        this.classification = classification;
        this.plateformes = plateformes;
        this.genres = genres;
    }

    public Jeu(String nom, LocalDate dateDeSortie, String description, boolean possedeImage, Editeur editeur, List<Plateforme> plateformes) {
        this.nom = nom;
        this.dateDeSortie = dateDeSortie;
        this.description = description;
        this.possedeImage = possedeImage;
        this.editeur = editeur;
        this.plateformes = plateformes;
    }

    public Jeu(Long id, String nom, LocalDate dateDeSortie, String description, Editeur editeur, List<Plateforme> plateformes, boolean possedeImage) {
        this.id = id;
        this.nom = nom;
        this.dateDeSortie = dateDeSortie;
        this.description = description;
        this.editeur = editeur;
        this.plateformes = plateformes;
        this.possedeImage = possedeImage;
    }

    public Jeu(Long id, String nom, LocalDate dateDeSortie, String description, Editeur editeur, Classification classification, List<Plateforme> plateformes, List<Genre> genres, boolean possedeImage) {
        this.id = id;
        this.nom = nom;
        this.dateDeSortie = dateDeSortie;
        this.description = description;
        this.editeur = editeur;
        this.classification = classification;
        this.plateformes = plateformes;
        this.genres = genres;
        this.possedeImage = possedeImage;
    }
}
