package fr.esgi.avis.persistance.repository;

import fr.esgi.avis.persistance.entity.EditeurEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditeurJpaRepository extends JpaRepository<EditeurEntity, Long> {
}