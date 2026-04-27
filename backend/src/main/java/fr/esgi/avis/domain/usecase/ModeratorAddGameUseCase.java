package fr.esgi.avis.domain.usecase;

import fr.esgi.avis.domain.dto.CreateJeuRequest;
import fr.esgi.avis.domain.model.Editeur;
import fr.esgi.avis.domain.model.Genre;
import fr.esgi.avis.domain.model.Jeu;
import fr.esgi.avis.domain.model.Plateforme;
import fr.esgi.avis.domain.repository.EditeurRepository;
import fr.esgi.avis.domain.repository.GenreRepository;
import fr.esgi.avis.domain.repository.PlateformeRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModeratorAddGameUseCase {

    public interface OutputPort {
        void save(Jeu jeu);
    }

    private final OutputPort outputPort;
    private final EditeurRepository editeurRepository;
    private final GenreRepository genreRepository;
    private final PlateformeRepository plateformeRepository;

    public ModeratorAddGameUseCase(OutputPort outputPort,
                                   EditeurRepository editeurRepository,
                                   GenreRepository genreRepository,
                                   PlateformeRepository plateformeRepository) {
        this.outputPort = outputPort;
        this.editeurRepository = editeurRepository;
        this.genreRepository = genreRepository;
        this.plateformeRepository = plateformeRepository;
    }

    public void apply(CreateJeuRequest request) {
        Editeur editeur = editeurRepository.findById(request.editeurId())
                .orElseThrow(() -> new IllegalArgumentException("Éditeur non trouvé : " + request.editeurId()));

        Genre genre = genreRepository.findById(request.genreId())
                .orElseThrow(() -> new IllegalArgumentException("Genre non trouvé : " + request.genreId()));

        List<Plateforme> plateformes = request.plateformeIds().stream()
                .map(id -> plateformeRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Plateforme non trouvée : " + id)))
                .toList();

        Jeu jeu = new Jeu();
        jeu.setNom(request.nom());
        jeu.setDateDeSortie(request.dateDeSortie());
        jeu.setDescription(request.description());
        jeu.setPrix(request.prix());
        jeu.setEditeur(editeur);
        jeu.setGenre(genre);
        jeu.setPlateformes(plateformes);

        outputPort.save(jeu);
    }
}