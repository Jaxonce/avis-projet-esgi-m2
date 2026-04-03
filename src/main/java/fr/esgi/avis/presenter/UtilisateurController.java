package fr.esgi.avis.presenter;

import fr.esgi.avis.domain.dto.AvisDto;
import fr.esgi.avis.domain.mapper.AvisMapper;
import fr.esgi.avis.domain.usecase.UtilisateurGetAvisUseCase;
import fr.esgi.avis.persistance.entity.AvisEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {
    private final UtilisateurGetAvisUseCase utilisateurGetAvisUseCase;


    public UtilisateurController(UtilisateurGetAvisUseCase utilisateurGetAvisUseCase) {
        this.utilisateurGetAvisUseCase = utilisateurGetAvisUseCase;
    }

    @GetMapping("/avis")
    public List<AvisDto> getAllAvis() {
        return utilisateurGetAvisUseCase.apply().stream().map(AvisMapper.INSTANCE::avisToAvisDto).toList();
    }
}
