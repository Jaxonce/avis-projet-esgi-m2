package fr.esgi.avis.persistance.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MODERATEUR")
public class ModerateurEntity extends UtilisateurEntity {

    private String numeroDeTelephone;

}
