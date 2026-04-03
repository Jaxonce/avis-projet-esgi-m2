package fr.esgi.avis.domain.usecase;

import fr.esgi.avis.domain.dto.CreateJeuRequest;
import fr.esgi.avis.domain.model.Jeu;
import org.springframework.stereotype.Component;

@Component
public class ModeratorAddGameUseCase {

    public interface OutputPort {
        void save(Jeu jeu);
    }

    private final OutputPort outputPort;

    public ModeratorAddGameUseCase(OutputPort outputPort) {
        this.outputPort = outputPort;
    }

    public void apply(CreateJeuRequest request) {
        Jeu jeu = new Jeu();
        jeu.setNom(request.nom());
        jeu.setDateDeSortie(request.dateDeSortie());
        jeu.setDescription(request.description());
        jeu.setPrix(request.prix());
        outputPort.save(jeu);
    }
}
