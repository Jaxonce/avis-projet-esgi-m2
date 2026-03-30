package fr.esgi.avis.persistance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
public abstract class UtilisateurEntity {

    @Id
    protected Long id;

    protected String pseudo;

    protected String email;

    protected String motDePasse;
}
