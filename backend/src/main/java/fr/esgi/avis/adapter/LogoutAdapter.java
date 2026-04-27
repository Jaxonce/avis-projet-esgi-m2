package fr.esgi.avis.adapter;

import fr.esgi.avis.domain.repository.TokenBlacklistRepository;
import fr.esgi.avis.domain.usecase.ModeratorLogoutUseCase;
import fr.esgi.avis.domain.usecase.UtilisateurLogoutUseCase;
import fr.esgi.avis.security.JwtUtil;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class LogoutAdapter implements UtilisateurLogoutUseCase.OutputPort, ModeratorLogoutUseCase.OutputPort {

    private final TokenBlacklistRepository tokenBlacklistRepository;
    private final JwtUtil jwtUtil;

    public LogoutAdapter(TokenBlacklistRepository tokenBlacklistRepository, JwtUtil jwtUtil) {
        this.tokenBlacklistRepository = tokenBlacklistRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void invalidateToken(String token) {
        Date expiresAt = jwtUtil.extractExpiration(token);
        tokenBlacklistRepository.invalidate(token, expiresAt);
    }
}