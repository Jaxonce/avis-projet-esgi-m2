package fr.esgi.avis.business;

import java.util.Objects;

public class Moderateur extends Utilisateur {

    private String numeroDeTelephone;

    public Moderateur() {
    }

    public Moderateur(String pseudo, String motDePasse, String email, String numeroDeTelephone) {
        super(pseudo, motDePasse, email);
        this.numeroDeTelephone = numeroDeTelephone;
    }

    public String getNumeroDeTelephone() {
        return numeroDeTelephone;
    }

    public void setNumeroDeTelephone(String numeroDeTelephone) {
        this.numeroDeTelephone = numeroDeTelephone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Moderateur that)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        return Objects.equals(numeroDeTelephone, that.numeroDeTelephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numeroDeTelephone);
    }

    @Override
    public String toString() {
        return "Moderateur{" +
                "id=" + id +
                ", pseudo='" + pseudo + '\'' +
                ", email='" + email + '\'' +
                ", numeroDeTelephone='" + numeroDeTelephone + '\'' +
                '}';
    }
}
