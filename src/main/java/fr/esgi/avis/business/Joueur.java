package fr.esgi.avis.business;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Joueur extends Utilisateur {

    private LocalDate dateDeNaissance;

    public Joueur(String pseudo, String motDePasse, String email, LocalDate dateDeNaissance) {
        super(pseudo, motDePasse, email);
        this.dateDeNaissance = dateDeNaissance;
    }
}
