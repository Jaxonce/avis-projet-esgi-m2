package fr.esgi.avis.domain.usecase;

import fr.esgi.avis.domain.dto.RegisterJoueurRequest;
import fr.esgi.avis.domain.model.Joueur;
import org.springframework.stereotype.Component;

@Component
public class RegisterJoueurUseCase {

    public interface OutputPort {
        void save(Joueur joueur);
    }

    private final OutputPort outputPort;

    public RegisterJoueurUseCase(OutputPort outputPort) {
        this.outputPort = outputPort;
    }

    public void apply(RegisterJoueurRequest request) {
        Joueur joueur = new Joueur(request.pseudo(), request.motDePasse(), request.email(), request.dateDeNaissance());
        outputPort.save(joueur);
    }
}
