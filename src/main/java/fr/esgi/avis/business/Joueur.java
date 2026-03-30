package fr.esgi.avis.business;

import java.time.LocalDate;
import java.util.Objects;

public class Joueur extends Utilisateur {

    private LocalDate dateDeNaissance;

    public Joueur() {
    }

    public Joueur(String pseudo, String motDePasse, String email, LocalDate dateDeNaissance) {
        super(pseudo, motDePasse, email);
        this.dateDeNaissance = dateDeNaissance;
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Joueur joueur)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        return Objects.equals(dateDeNaissance, joueur.dateDeNaissance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dateDeNaissance);
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "id=" + id +
                ", pseudo='" + pseudo + '\'' +
                ", email='" + email + '\'' +
                ", dateDeNaissance=" + dateDeNaissance +
                '}';
    }
}
