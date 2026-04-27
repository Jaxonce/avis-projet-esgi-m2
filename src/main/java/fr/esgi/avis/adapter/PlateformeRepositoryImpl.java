package fr.esgi.avis.adapter;

import fr.esgi.avis.domain.model.Plateforme;
import fr.esgi.avis.domain.repository.PlateformeRepository;
import fr.esgi.avis.persistance.repository.PlateformeJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PlateformeRepositoryImpl implements PlateformeRepository {

    private final PlateformeJpaRepository jpaRepository;

    public PlateformeRepositoryImpl(PlateformeJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Plateforme> findById(Long id) {
        return jpaRepository.findById(id).map(p -> new Plateforme(p.getId(), p.getNom(), p.getDateDeSortie(), java.util.List.of()));
    }
}