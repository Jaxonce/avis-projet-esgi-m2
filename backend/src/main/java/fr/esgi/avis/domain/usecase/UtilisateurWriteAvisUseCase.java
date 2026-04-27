package fr.esgi.avis.domain.usecase;

import fr.esgi.avis.domain.dto.CreateAvisRequest;
import fr.esgi.avis.domain.model.Avis;
import fr.esgi.avis.domain.repository.JeuRepository;
import fr.esgi.avis.domain.repository.JoueurRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UtilisateurWriteAvisUseCase {

    public interface OutputPort {
        void writeAvis(Avis avis);
    }

    private final OutputPort outputPort;
    private final JeuRepository jeuRepository;
    private final JoueurRepository joueurRepository;

    public UtilisateurWriteAvisUseCase(OutputPort outputPort, JeuRepository jeuRepository, JoueurRepository joueurRepository) {
        this.outputPort = outputPort;
        this.jeuRepository = jeuRepository;
        this.joueurRepository = joueurRepository;
    }

    public void apply(CreateAvisRequest request) {
        var jeu = jeuRepository.findById(request.jeuId())
                .orElseThrow(() -> new IllegalArgumentException("Jeu non trouvé : " + request.jeuId()));
        var joueur = joueurRepository.findById(request.joueurId())
                .orElseThrow(() -> new IllegalArgumentException("Joueur non trouvé : " + request.joueurId()));

        Avis avis = new Avis();
        avis.setDescription(request.description());
        avis.setNote(request.note());
        avis.setDateDenvoi(LocalDateTime.now());
        avis.setJeu(jeu);
        avis.setJoueur(joueur);

        outputPort.writeAvis(avis);
    }
}
