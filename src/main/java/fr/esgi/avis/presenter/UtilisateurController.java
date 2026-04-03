package fr.esgi.avis.presenter;

import fr.esgi.avis.domain.dto.AvisDto;
import fr.esgi.avis.domain.dto.CreateAvisRequest;
import fr.esgi.avis.domain.dto.JeuDto;
import fr.esgi.avis.domain.dto.LoginRequest;
import fr.esgi.avis.domain.dto.LoginResponse;
import fr.esgi.avis.domain.dto.RegisterJoueurRequest;
import fr.esgi.avis.domain.mapper.AvisMapper;
import fr.esgi.avis.domain.mapper.JeuMapper;
import fr.esgi.avis.domain.usecase.RegisterJoueurUseCase;
import fr.esgi.avis.domain.usecase.UtilisateurGetAvisUseCase;
import fr.esgi.avis.domain.usecase.UtilisateurGetGameUseCase;
import fr.esgi.avis.domain.usecase.UtilisateurWriteAvisUseCase;
import fr.esgi.avis.security.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "Bearer")
public class UtilisateurController {
    private final UtilisateurGetAvisUseCase utilisateurGetAvisUseCase;
    private final UtilisateurWriteAvisUseCase utilisateurWriteAvisUseCase;
    private final RegisterJoueurUseCase registerJoueurUseCase;
    private final UtilisateurGetGameUseCase utilisateurGetGameUseCase;
    private final AuthService authService;

    public UtilisateurController(UtilisateurGetAvisUseCase utilisateurGetAvisUseCase,
                                 UtilisateurWriteAvisUseCase utilisateurWriteAvisUseCase,
                                 RegisterJoueurUseCase registerJoueurUseCase,
                                 UtilisateurGetGameUseCase utilisateurGetGameUseCase,
                                 AuthService authService) {
        this.utilisateurGetAvisUseCase = utilisateurGetAvisUseCase;
        this.utilisateurWriteAvisUseCase = utilisateurWriteAvisUseCase;
        this.registerJoueurUseCase = registerJoueurUseCase;
        this.utilisateurGetGameUseCase = utilisateurGetGameUseCase;
        this.authService = authService;
    }

    @PostMapping("/inscription")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(security = {})
    public void inscription(@RequestBody RegisterJoueurRequest request) {
        registerJoueurUseCase.apply(request);
    }

    @PostMapping("/login")
    @Operation(security = {})
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @GetMapping("/jeu")
    public List<JeuDto> getAllJeux() {
        return utilisateurGetGameUseCase.apply().stream().map(JeuMapper.INSTANCE::jeuToJeuDto).toList();
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
