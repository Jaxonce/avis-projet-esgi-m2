package fr.esgi.avis.domain.repository;

import fr.esgi.avis.domain.model.Plateforme;

import java.util.Optional;

public interface PlateformeRepository {
    Optional<Plateforme> findById(Long id);
}