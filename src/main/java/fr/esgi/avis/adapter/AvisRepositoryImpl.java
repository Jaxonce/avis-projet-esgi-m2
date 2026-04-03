package fr.esgi.avis.adapter;

import fr.esgi.avis.domain.mapper.AvisMapper;
import fr.esgi.avis.domain.model.Avis;
import fr.esgi.avis.domain.repository.AvisRepository;
import fr.esgi.avis.persistance.entity.AvisEntity;
import fr.esgi.avis.persistance.repository.AvisJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AvisRepositoryImpl implements AvisRepository {

    private final AvisJpaRepository avisJpaRepository;

    public AvisRepositoryImpl(AvisJpaRepository avisRepository) {
        this.avisJpaRepository = avisRepository;
    }

    @Override
    public List<AvisEntity> findAll() {
        return avisJpaRepository.findAll();
    }

    @Override
    public void save(Avis avis) {
        avisJpaRepository.save(AvisMapper.INSTANCE.avisToAvisEntity(avis));
    }
}
