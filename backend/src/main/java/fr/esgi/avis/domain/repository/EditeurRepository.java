package fr.esgi.avis.domain.repository;

import fr.esgi.avis.domain.model.Editeur;

import java.util.List;
import java.util.Optional;

public interface EditeurRepository {
    Optional<Editeur> findById(Long id);
    List<Editeur> findAll();
}