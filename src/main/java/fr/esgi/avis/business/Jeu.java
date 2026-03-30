package fr.esgi.avis.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Jeu {

    private Long id;
    private String nom;
    private LocalDate dateDeSortie;
    private String description;
    private Editeur editeur;
    private List<Plateforme> plateformes = new ArrayList<>();
    private boolean possedeImage;

    public Jeu() {
    }

    public Jeu(String nom) {
        this.nom = nom;
    }

    public Jeu(String nom, LocalDate dateDeSortie, Editeur editeur, List<Plateforme> plateformes) {
        this.nom = nom;
        this.dateDeSortie = dateDeSortie;
        this.editeur = editeur;
        this.plateformes = plateformes;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateDeSortie() {
        return dateDeSortie;
    }

    public void setDateDeSortie(LocalDate dateDeSortie) {
        this.dateDeSortie = dateDeSortie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Editeur getEditeur() {
        return editeur;
    }

    public void setEditeur(Editeur editeur) {
        this.editeur = editeur;
    }

    public List<Plateforme> getPlateformes() {
        return plateformes;
    }

    public void setPlateformes(List<Plateforme> plateformes) {
        this.plateformes = plateformes;
    }

    public boolean isPossedeImage() {
        return possedeImage;
    }

    public void setPossedeImage(boolean possedeImage) {
        this.possedeImage = possedeImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Jeu jeu = (Jeu) o;
        return Objects.equals(id, jeu.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Jeu{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", dateDeSortie=" + dateDeSortie +
                ", possedeImage=" + possedeImage +
                '}';
    }
}
