package fr.esgi.avis.persistance.entity;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class JoueurEntity extends UtilisateurEntity {

    private LocalDate dateDeNaissance;

    private AvatarEntity avatar;

    private List<AvisEntity> avis = new ArrayList<>();
}
