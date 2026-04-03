package fr.esgi.avis.persistance.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("MODERATEUR")
public class ModerateurEntity extends UtilisateurEntity {

    private String numeroDeTelephone;

}
