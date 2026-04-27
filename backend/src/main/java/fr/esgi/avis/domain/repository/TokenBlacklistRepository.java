package fr.esgi.avis.domain.repository;

import java.util.Date;

public interface TokenBlacklistRepository {
    void invalidate(String token, Date expiresAt);
    boolean isInvalidated(String token);
}