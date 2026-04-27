package fr.esgi.avis.domain.repository;

import fr.esgi.avis.domain.model.Genre;

import java.util.Optional;

public interface GenreRepository {
    Optional<Genre> findById(Long id);
}