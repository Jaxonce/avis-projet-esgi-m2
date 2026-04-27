package fr.esgi.avis.domain.usecase;

import org.springframework.stereotype.Component;

@Component
public class ModeratorLogoutUseCase {

    public interface OutputPort {
        void invalidateToken(String token);
    }

    private final OutputPort outputPort;

    public ModeratorLogoutUseCase(OutputPort outputPort) {
        this.outputPort = outputPort;
    }

    public void apply(String token) {
        outputPort.invalidateToken(token);
    }
}