package fr.esgi.avis.domain.usecase;

import fr.esgi.avis.domain.dto.RegisterModerateurRequest;
import fr.esgi.avis.domain.model.Moderateur;
import org.springframework.stereotype.Component;

@Component
public class RegisterModerateurUseCase {

    public interface OutputPort {
        void save(Moderateur moderateur);
    }

    private final OutputPort outputPort;

    public RegisterModerateurUseCase(OutputPort outputPort) {
        this.outputPort = outputPort;
    }

    public void apply(RegisterModerateurRequest request) {
        Moderateur moderateur = new Moderateur(request.pseudo(), request.motDePasse(), request.email(), request.numeroDeTelephone());
        outputPort.save(moderateur);
    }
}
