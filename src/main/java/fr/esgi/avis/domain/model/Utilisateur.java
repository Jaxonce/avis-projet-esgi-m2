package fr.esgi.avis.domain.model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public abstract class Utilisateur {

    @EqualsAndHashCode.Include
    @ToString.Include
    protected Long id;

    @ToString.Include
    protected String pseudo;

    @ToString.Include
    protected String email;

    protected String motDePasse;

    protected Utilisateur(String pseudo, String motDePasse, String email) {
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;
        this.email = email;
    }
}
