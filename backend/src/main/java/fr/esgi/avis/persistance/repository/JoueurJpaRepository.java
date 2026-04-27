package fr.esgi.avis.persistance.repository;

import fr.esgi.avis.persistance.entity.JoueurEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JoueurJpaRepository extends JpaRepository<JoueurEntity, Long> {
    Optional<JoueurEntity> findByPseudo(String pseudo);
}