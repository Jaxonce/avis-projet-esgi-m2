package fr.esgi.avis.adapter;

import fr.esgi.avis.domain.model.Jeu;
import fr.esgi.avis.domain.repository.JeuRepository;
import fr.esgi.avis.domain.usecase.ModeratorAddGameUseCase;
import org.springframework.stereotype.Component;

@Component
public class ModeratorAddGameAdapter implements ModeratorAddGameUseCase.OutputPort {

    private final JeuRepository jeuRepository;

    public ModeratorAddGameAdapter(JeuRepository jeuRepository) {
        this.jeuRepository = jeuRepository;
    }

    @Override
    public void save(Jeu jeu) {
        jeuRepository.save(jeu);
    }
}
