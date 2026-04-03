package fr.esgi.avis.persistance.repository;

import fr.esgi.avis.persistance.entity.JoueurEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoueurJpaRepository extends JpaRepository<JoueurEntity, Long> {}