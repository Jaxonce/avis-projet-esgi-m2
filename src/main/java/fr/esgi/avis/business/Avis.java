package fr.esgi.avis.business;

import java.time.LocalDateTime;
import java.util.Objects;

public class Avis {

    private Long id;
    private String contenu;
    private LocalDateTime dateDeCreation;
    private float note;
    private Jeu jeu;
    private Joueur joueur;
    private Moderateur moderateur;

    public Avis() {
    }

    public Avis(Long id, String contenu, LocalDateTime dateDeCreation, float note, Jeu jeu, Joueur joueur, Moderateur moderateur) {
        this.id = id;
        this.contenu = contenu;
        this.dateDeCreation = dateDeCreation;
        this.note = note;
        this.jeu = jeu;
        this.joueur = joueur;
        this.moderateur = moderateur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public LocalDateTime getDateDeCreation() {
        return dateDeCreation;
    }

    public void setDateDeCreation(LocalDateTime dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public Jeu getJeu() {
        return jeu;
    }

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public Moderateur getModerateur() {
        return moderateur;
    }

    public void setModerateur(Moderateur moderateur) {
        this.moderateur = moderateur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Avis avis = (Avis) o;
        return Objects.equals(id, avis.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Avis{" +
                "id=" + id +
                ", note=" + note +
                ", dateDeCreation=" + dateDeCreation +
                '}';
    }
}
