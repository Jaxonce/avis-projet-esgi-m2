package fr.esgi.avis.adapter;

import fr.esgi.avis.domain.model.Editeur;
import fr.esgi.avis.domain.repository.EditeurRepository;
import fr.esgi.avis.persistance.repository.EditeurJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class EditeurRepositoryImpl implements EditeurRepository {

    private final EditeurJpaRepository jpaRepository;

    public EditeurRepositoryImpl(EditeurJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Editeur> findById(Long id) {
        return jpaRepository.findById(id).map(e -> new Editeur(e.getId(), e.getNom(), java.util.List.of()));
    }
}