package fr.esgi.avis.domain.usecase;

import fr.esgi.avis.domain.model.Jeu;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UtilisateurGetGameUseCase {

    public interface OutputPort {
        List<Jeu> findAllJeux();
    }

    private final OutputPort outputPort;

    public UtilisateurGetGameUseCase(OutputPort outputPort) {
        this.outputPort = outputPort;
    }

    public List<Jeu> apply() {
        return outputPort.findAllJeux();
    }
}
