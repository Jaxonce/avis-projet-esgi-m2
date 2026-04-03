package fr.esgi.avis.adapter;

import fr.esgi.avis.domain.model.Moderateur;
import fr.esgi.avis.domain.repository.ModerateurRepository;
import fr.esgi.avis.domain.usecase.RegisterModerateurUseCase;
import org.springframework.stereotype.Component;

@Component
public class RegisterModerateurAdapter implements RegisterModerateurUseCase.OutputPort {

    private final ModerateurRepository moderateurRepository;

    public RegisterModerateurAdapter(ModerateurRepository moderateurRepository) {
        this.moderateurRepository = moderateurRepository;
    }

    @Override
    public void save(Moderateur moderateur) {
        moderateurRepository.save(moderateur);
    }
}
