package fr.esgi.avis.adapter;

import fr.esgi.avis.domain.mapper.JeuMapper;
import fr.esgi.avis.domain.model.Jeu;
import fr.esgi.avis.domain.usecase.ModeratorAddGameUseCase;
import fr.esgi.avis.persistance.entity.JeuEntity;
import fr.esgi.avis.persistance.repository.EditeurJpaRepository;
import fr.esgi.avis.persistance.repository.GenreJpaRepository;
import fr.esgi.avis.persistance.repository.JeuJpaRepository;
import fr.esgi.avis.persistance.repository.PlateformeJpaRepository;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ModeratorAddGameAdapter implements ModeratorAddGameUseCase.OutputPort {

    private final JeuJpaRepository jeuJpaRepository;
    private final EditeurJpaRepository editeurJpaRepository;
    private final GenreJpaRepository genreJpaRepository;
    private final PlateformeJpaRepository plateformeJpaRepository;

    public ModeratorAddGameAdapter(JeuJpaRepository jeuJpaRepository,
                                   EditeurJpaRepository editeurJpaRepository,
                                   GenreJpaRepository genreJpaRepository,
                                   PlateformeJpaRepository plateformeJpaRepository) {
        this.jeuJpaRepository = jeuJpaRepository;
        this.editeurJpaRepository = editeurJpaRepository;
        this.genreJpaRepository = genreJpaRepository;
        this.plateformeJpaRepository = plateformeJpaRepository;
    }

    @Override
    public void save(Jeu jeu) {
        JeuEntity entity = JeuMapper.INSTANCE.jeuToJeuEntity(jeu);

        if (jeu.getEditeur() != null) {
            entity.setEditeur(editeurJpaRepository.getReferenceById(jeu.getEditeur().getId()));
        }
        if (jeu.getGenre() != null) {
            entity.setGenre(genreJpaRepository.getReferenceById(jeu.getGenre().getId()));
        }
        if (jeu.getPlateformes() != null && !jeu.getPlateformes().isEmpty()) {
            entity.setPlateformes(jeu.getPlateformes().stream()
                    .map(p -> plateformeJpaRepository.getReferenceById(p.getId()))
                    .collect(Collectors.toSet()));
        }

        jeuJpaRepository.save(entity);
    }
}