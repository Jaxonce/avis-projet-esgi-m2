package fr.esgi.avis.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Plateforme {

    private Long id;
    private String nom;
    private List<Jeu> jeux = new ArrayList<>();

    public Plateforme() {
    }

    public Plateforme(String nom) {
        this.nom = nom;
    }

    public Plateforme(Long id, String nom, List<Jeu> jeux) {
        this.id = id;
        this.nom = nom;
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
        Plateforme that = (Plateforme) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Plateforme{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
