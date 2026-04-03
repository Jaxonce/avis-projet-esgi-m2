package fr.esgi.avis.adapter;

import fr.esgi.avis.domain.model.Joueur;
import fr.esgi.avis.domain.repository.JoueurRepository;
import fr.esgi.avis.domain.usecase.RegisterJoueurUseCase;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegisterJoueurAdapter implements RegisterJoueurUseCase.OutputPort {

    private final JoueurRepository joueurRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterJoueurAdapter(JoueurRepository joueurRepository, PasswordEncoder passwordEncoder) {
        this.joueurRepository = joueurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(Joueur joueur) {
        joueur.setMotDePasse(passwordEncoder.encode(joueur.getMotDePasse()));
        joueurRepository.save(joueur);
    }
}
