package fr.esgi.avis.persistance.repository;

import fr.esgi.avis.persistance.entity.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurJpaRepository extends JpaRepository<UtilisateurEntity, Long> {
    Optional<UtilisateurEntity> findByPseudo(String pseudo);
}
