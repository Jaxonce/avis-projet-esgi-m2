package fr.esgi.avis.adapter;

import fr.esgi.avis.domain.model.Joueur;
import fr.esgi.avis.domain.repository.JoueurRepository;
import fr.esgi.avis.domain.usecase.RegisterJoueurUseCase;
import org.springframework.stereotype.Component;

@Component
public class RegisterJoueurAdapter implements RegisterJoueurUseCase.OutputPort {

    private final JoueurRepository joueurRepository;

    public RegisterJoueurAdapter(JoueurRepository joueurRepository) {
        this.joueurRepository = joueurRepository;
    }

    @Override
    public void save(Joueur joueur) {
        joueurRepository.save(joueur);
    }
}
