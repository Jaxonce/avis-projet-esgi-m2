package fr.esgi.avis.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Joueur extends Utilisateur {

    private LocalDate dateDeNaissance;

    private Avatar avatar;

    private List<Avis> avis = new ArrayList<>();

    public Joueur(String pseudo, String motDePasse, String email, LocalDate dateDeNaissance) {
        super(pseudo, motDePasse, email);
        this.dateDeNaissance = dateDeNaissance;
    }
}
