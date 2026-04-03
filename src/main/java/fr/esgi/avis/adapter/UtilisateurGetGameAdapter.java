package fr.esgi.avis.adapter;

import fr.esgi.avis.domain.model.Jeu;
import fr.esgi.avis.domain.repository.JeuRepository;
import fr.esgi.avis.domain.usecase.UtilisateurGetGameUseCase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UtilisateurGetGameAdapter implements UtilisateurGetGameUseCase.OutputPort {

    private final JeuRepository jeuRepository;

    public UtilisateurGetGameAdapter(JeuRepository jeuRepository) {
        this.jeuRepository = jeuRepository;
    }

    @Override
    public List<Jeu> findAllJeux() {
        return jeuRepository.findAll();
    }
}
