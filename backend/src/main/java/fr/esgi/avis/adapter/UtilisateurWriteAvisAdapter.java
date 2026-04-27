package fr.esgi.avis.adapter;

import fr.esgi.avis.domain.model.Avis;
import fr.esgi.avis.domain.repository.AvisRepository;
import fr.esgi.avis.domain.usecase.UtilisateurWriteAvisUseCase;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurWriteAvisAdapter implements UtilisateurWriteAvisUseCase.OutputPort {

    private final AvisRepository avisRepository;

    public UtilisateurWriteAvisAdapter(AvisRepository avisRepository) {
        this.avisRepository = avisRepository;
    }

    @Override
    public void writeAvis(Avis avis) {
        avisRepository.save(avis);
    }
}
