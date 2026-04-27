package fr.esgi.avis.persistance.repository;

import fr.esgi.avis.persistance.entity.TokenBlacklistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface TokenBlacklistJpaRepository extends JpaRepository<TokenBlacklistEntity, Long> {
    boolean existsByToken(String token);
    void deleteByExpiresAtBefore(Date date);
}