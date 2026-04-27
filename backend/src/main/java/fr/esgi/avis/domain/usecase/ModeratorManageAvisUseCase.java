package fr.esgi.avis.domain.usecase;

import fr.esgi.avis.domain.model.Avis;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModeratorManageAvisUseCase {

    public interface OutputPort {
        List<Avis> findAll();
        void deleteById(Long id);
    }

    private final OutputPort outputPort;

    public ModeratorManageAvisUseCase(OutputPort outputPort) {
        this.outputPort = outputPort;
    }

    public List<Avis> getAllAvis() {
        return outputPort.findAll();
    }

    public void deleteAvis(Long id) {
        outputPort.deleteById(id);
    }
}
