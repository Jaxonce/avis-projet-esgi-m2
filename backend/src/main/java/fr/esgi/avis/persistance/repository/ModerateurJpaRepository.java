package fr.esgi.avis.persistance.repository;

import fr.esgi.avis.persistance.entity.ModerateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModerateurJpaRepository extends JpaRepository<ModerateurEntity, Long> {}
