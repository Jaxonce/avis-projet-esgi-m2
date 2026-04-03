package fr.esgi.avis.domain.repository;

import fr.esgi.avis.domain.model.Jeu;

import java.util.List;
import java.util.Optional;

public interface JeuRepository {
    List<Jeu> findAll();
    Optional<Jeu> findById(Long id);
    void save(Jeu jeu);
}