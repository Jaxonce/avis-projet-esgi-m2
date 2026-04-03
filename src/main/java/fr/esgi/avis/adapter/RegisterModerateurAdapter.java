package fr.esgi.avis.adapter;

import fr.esgi.avis.domain.model.Moderateur;
import fr.esgi.avis.domain.repository.ModerateurRepository;
import fr.esgi.avis.domain.usecase.RegisterModerateurUseCase;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegisterModerateurAdapter implements RegisterModerateurUseCase.OutputPort {

    private final ModerateurRepository moderateurRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterModerateurAdapter(ModerateurRepository moderateurRepository, PasswordEncoder passwordEncoder) {
        this.moderateurRepository = moderateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(Moderateur moderateur) {
        moderateur.setMotDePasse(passwordEncoder.encode(moderateur.getMotDePasse()));
        moderateurRepository.save(moderateur);
    }
}
