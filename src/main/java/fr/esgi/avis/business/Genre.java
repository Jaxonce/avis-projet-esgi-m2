package fr.esgi.avis.business;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Genre {

    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @ToString.Include
    private String nom;

    private List<Jeu> jeux = new ArrayList<>();

    public Genre(String nom) {
        this.nom = nom;
    }
}
