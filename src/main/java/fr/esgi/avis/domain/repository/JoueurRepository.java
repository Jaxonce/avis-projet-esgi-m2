package fr.esgi.avis.domain.repository;

import fr.esgi.avis.domain.model.Joueur;

import java.util.Optional;

public interface JoueurRepository {
    Optional<Joueur> findById(Long id);
    void save(Joueur joueur);
}