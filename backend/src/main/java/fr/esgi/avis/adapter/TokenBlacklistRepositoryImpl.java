package fr.esgi.avis.adapter;

import fr.esgi.avis.domain.repository.TokenBlacklistRepository;
import fr.esgi.avis.persistance.entity.TokenBlacklistEntity;
import fr.esgi.avis.persistance.repository.TokenBlacklistJpaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenBlacklistRepositoryImpl implements TokenBlacklistRepository {

    private final TokenBlacklistJpaRepository jpaRepository;

    public TokenBlacklistRepositoryImpl(TokenBlacklistJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void invalidate(String token, Date expiresAt) {
        TokenBlacklistEntity entity = new TokenBlacklistEntity();
        entity.setToken(token);
        entity.setExpiresAt(expiresAt);
        jpaRepository.save(entity);
    }

    @Override
    public boolean isInvalidated(String token) {
        return jpaRepository.existsByToken(token);
    }

    @Scheduled(fixedRate = 3_600_000)
    public void cleanExpiredTokens() {
        jpaRepository.deleteByExpiresAtBefore(new Date());
    }
}