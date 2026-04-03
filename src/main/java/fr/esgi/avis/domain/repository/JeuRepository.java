package fr.esgi.avis.domain.repository;

import fr.esgi.avis.domain.model.Jeu;

import java.util.Optional;

public interface JeuRepository {
    Optional<Jeu> findById(Long id);
    void save(Jeu jeu);
}