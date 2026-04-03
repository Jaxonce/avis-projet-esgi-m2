package fr.esgi.avis.adapter;

import fr.esgi.avis.domain.mapper.AvisMapper;
import fr.esgi.avis.domain.model.Avis;
import fr.esgi.avis.domain.usecase.UtilisateurGetAvisUseCase;
import fr.esgi.avis.persistance.repository.AvisRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UtilisateurGetAvisAdapter implements UtilisateurGetAvisUseCase.OutputPort {

    private final AvisRepository avisRepository;

    public UtilisateurGetAvisAdapter(AvisRepository avisRepository) {
        this.avisRepository = avisRepository;
    }

    @Override
    public List<Avis> findAllAvis() {
        return avisRepository.findAll().stream().map(AvisMapper.INSTANCE::avisEntityToAvis).toList();
    }
}
