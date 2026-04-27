package fr.esgi.avis.adapter;

import fr.esgi.avis.domain.model.Genre;
import fr.esgi.avis.domain.repository.GenreRepository;
import fr.esgi.avis.persistance.repository.GenreJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class GenreRepositoryImpl implements GenreRepository {

    private final GenreJpaRepository jpaRepository;

    public GenreRepositoryImpl(GenreJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Genre> findById(Long id) {
        return jpaRepository.findById(id).map(g -> new Genre(g.getId(), g.getNom(), java.util.List.of()));
    }
}