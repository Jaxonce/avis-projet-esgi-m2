package fr.esgi.avis.persistance.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
public class ModerateurEntity extends UtilisateurEntity {

    private String numeroDeTelephone;

}
