package fr.esgi.avis.domain.usecase;

import fr.esgi.avis.domain.model.Avis;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UtilisateurGetAvisUseCase {

    private final OutputPort outputPort;

    public UtilisateurGetAvisUseCase(OutputPort outputPort) {
        this.outputPort = outputPort;
    }

    public interface OutputPort {
        List<Avis> findAllAvis();
    }

    public List<Avis> apply()  {
        return outputPort.findAllAvis();
    }
}
