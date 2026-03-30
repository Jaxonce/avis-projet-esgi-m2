package fr.esgi.avis.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type_utilisateur", discriminatorType = DiscriminatorType.STRING)
public abstract class UtilisateurEntity {

    @Id
    protected Long id;

    protected String pseudo;

    protected String email;

    protected String motDePasse;
}
