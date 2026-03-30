package fr.esgi.avis.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Editeur {

    private Long id;
    private String nom;
    private String logo;
    private List<Jeu> jeux = new ArrayList<>();

    public Editeur() {
    }

    public Editeur(String nom, String logo) {
        this.nom = nom;
        this.logo = logo;
    }

    public Editeur(Long id, String nom, String logo, List<Jeu> jeux) {
        this.id = id;
        this.nom = nom;
        this.logo = logo;
        this.jeux = jeux;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<Jeu> getJeux() {
        return jeux;
    }

    public void setJeux(List<Jeu> jeux) {
        this.jeux = jeux;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Editeur editeur = (Editeur) o;
        return Objects.equals(id, editeur.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Editeur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}
