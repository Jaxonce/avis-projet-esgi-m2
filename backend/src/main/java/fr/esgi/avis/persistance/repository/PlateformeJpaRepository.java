package fr.esgi.avis.persistance.repository;

import fr.esgi.avis.persistance.entity.PlateformeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlateformeJpaRepository extends JpaRepository<PlateformeEntity, Long> {
}