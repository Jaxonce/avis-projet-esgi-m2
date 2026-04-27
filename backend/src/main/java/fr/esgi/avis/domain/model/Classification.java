package fr.esgi.avis.domain.model;

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
public class Classification {

    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @ToString.Include
    private String nom;

    @ToString.Include
    private String couleurRGB;

    private List<Jeu> jeux = new ArrayList<>();

    public Classification(String nom) {
        this.nom = nom;
    }

    public Classification(String nom, String couleurRGB) {
        this.nom = nom;
        this.couleurRGB = couleurRGB;
    }
}
