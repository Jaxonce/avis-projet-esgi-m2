package fr.esgi.avis.adapter;

import fr.esgi.avis.persistance.entity.AvisEntity;
import fr.esgi.avis.domain.repository.AvisRepository;
import fr.esgi.avis.persistance.repository.AvisJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AvisRepositoryImpl implements AvisRepository {

    private final AvisJpaRepository avisRepository;

    public AvisRepositoryImpl(AvisJpaRepository avisRepository) {
        this.avisRepository = avisRepository;
    }

    @Override
    public List<AvisEntity> findAll() {
        return avisRepository.findAll();
    }
}
