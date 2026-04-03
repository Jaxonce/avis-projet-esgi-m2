package fr.esgi.avis.domain.repository;

import fr.esgi.avis.domain.model.Avis;
import fr.esgi.avis.persistance.entity.AvisEntity;

import java.util.List;

public interface AvisRepository {
    List<AvisEntity> findAll();
    void save(Avis avis);
    void deleteById(Long id);
}
