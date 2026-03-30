package fr.esgi.avis.business;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Avatar {

    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @ToString.Include
    private String nom;

    private Utilisateur utilisateur;
}
