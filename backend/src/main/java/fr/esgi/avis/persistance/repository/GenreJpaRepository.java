package fr.esgi.avis.persistance.repository;

import fr.esgi.avis.persistance.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreJpaRepository extends JpaRepository<GenreEntity, Long> {
}