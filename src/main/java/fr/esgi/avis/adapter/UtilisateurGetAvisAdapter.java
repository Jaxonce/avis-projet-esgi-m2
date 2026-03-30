package fr.esgi.avis.adapter;

import fr.esgi.avis.domain.model.Avis;
import fr.esgi.avis.domain.usecase.UtilisateurGetAvisUseCase;

import java.util.List;

public class UtilisateurGetAvisAdapter implements UtilisateurGetAvisUseCase.OutputPort {

    @Override
    public List<Avis> findAllAvis() {
        return List.of();
    }
}
