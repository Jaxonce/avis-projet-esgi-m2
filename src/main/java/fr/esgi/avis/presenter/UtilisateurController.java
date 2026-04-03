package fr.esgi.avis.presenter;

import fr.esgi.avis.domain.dto.AvisDto;
import fr.esgi.avis.domain.dto.CreateAvisRequest;
import fr.esgi.avis.domain.dto.RegisterJoueurRequest;
import fr.esgi.avis.domain.mapper.AvisMapper;
import fr.esgi.avis.domain.usecase.RegisterJoueurUseCase;
import fr.esgi.avis.domain.usecase.UtilisateurGetAvisUseCase;
import fr.esgi.avis.domain.usecase.UtilisateurWriteAvisUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {
    private final UtilisateurGetAvisUseCase utilisateurGetAvisUseCase;
    private final UtilisateurWriteAvisUseCase utilisateurWriteAvisUseCase;
    private final RegisterJoueurUseCase registerJoueurUseCase;

    public UtilisateurController(UtilisateurGetAvisUseCase utilisateurGetAvisUseCase,
                                 UtilisateurWriteAvisUseCase utilisateurWriteAvisUseCase,
                                 RegisterJoueurUseCase registerJoueurUseCase) {
        this.utilisateurGetAvisUseCase = utilisateurGetAvisUseCase;
        this.utilisateurWriteAvisUseCase = utilisateurWriteAvisUseCase;
        this.registerJoueurUseCase = registerJoueurUseCase;
    }

    @PostMapping("/inscription")
    @ResponseStatus(HttpStatus.CREATED)
    public void inscription(@RequestBody RegisterJoueurRequest request) {
        registerJoueurUseCase.apply(request);
    }

    @GetMapping("/avis")
    public List<AvisDto> getAllAvis() {
        return utilisateurGetAvisUseCase.apply().stream().map(AvisMapper.INSTANCE::avisToAvisDto).toList();
    }

    @PostMapping("/avis")
    @ResponseStatus(HttpStatus.CREATED)
    public void writeAvis(@RequestBody CreateAvisRequest request) {
        utilisateurWriteAvisUseCase.apply(request);
    }
}
