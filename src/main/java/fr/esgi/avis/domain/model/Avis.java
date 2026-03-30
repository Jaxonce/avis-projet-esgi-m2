package fr.esgi.avis.domain.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Avis {

    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    private String description;

    @ToString.Include
    private LocalDateTime DateDenvoi;

    @ToString.Include
    private float note;

    private Jeu jeu;

    private Joueur joueur;

    private Moderateur moderateur;
}
