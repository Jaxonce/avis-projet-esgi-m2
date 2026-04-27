package fr.esgi.avis.persistance.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("JOUEUR")
public class JoueurEntity extends UtilisateurEntity {

    private LocalDate dateDeNaissance;

    @OneToOne
    private AvatarEntity avatar;

    @OneToMany(mappedBy = "joueur")
    private Set<AvisEntity> avis = new HashSet<>();
}
