package fr.esgi.avis.persistance.repository;

import fr.esgi.avis.persistance.entity.AvisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvisRepository extends JpaRepository<AvisEntity, String> {
}
