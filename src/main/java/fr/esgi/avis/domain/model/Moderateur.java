package fr.esgi.avis.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Moderateur extends Utilisateur {

    private String numeroDeTelephone;

    public Moderateur(String pseudo, String motDePasse, String email, String numeroDeTelephone) {
        super(pseudo, motDePasse, email);
        this.numeroDeTelephone = numeroDeTelephone;
    }
}
