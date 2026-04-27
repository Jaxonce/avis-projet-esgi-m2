package fr.esgi.avis.persistance.repository;

import fr.esgi.avis.persistance.entity.JeuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JeuJpaRepository extends JpaRepository<JeuEntity, Long> {}