package fr.esgi.avis.adapter;

import fr.esgi.avis.domain.mapper.AvisMapper;
import fr.esgi.avis.domain.model.Avis;
import fr.esgi.avis.domain.repository.AvisRepository;
import fr.esgi.avis.domain.usecase.ModeratorManageAvisUseCase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModeratorManageAvisAdapter implements ModeratorManageAvisUseCase.OutputPort {

    private final AvisRepository avisRepository;

    public ModeratorManageAvisAdapter(AvisRepository avisRepository) {
        this.avisRepository = avisRepository;
    }

    @Override
    public List<Avis> findAll() {
        return avisRepository.findAll().stream().map(AvisMapper.INSTANCE::avisEntityToAvis).toList();
    }

    @Override
    public void deleteById(Long id) {
        avisRepository.deleteById(id);
    }
}
