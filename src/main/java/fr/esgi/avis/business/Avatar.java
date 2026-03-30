package fr.esgi.avis.business;

import java.util.Objects;

public class Avatar {

    private Long id;
    private String nom;
    private Utilisateur utilisateur;

    public Avatar() {
    }

    public Avatar(Long id, String nom, Utilisateur utilisateur) {
        this.id = id;
        this.nom = nom;
        this.utilisateur = utilisateur;
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

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Avatar avatar = (Avatar) o;
        return Objects.equals(id, avatar.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Avatar{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
