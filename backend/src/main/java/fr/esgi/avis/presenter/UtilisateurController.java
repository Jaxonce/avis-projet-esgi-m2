package fr.esgi.avis.presenter;

import fr.esgi.avis.domain.dto.*;
import fr.esgi.avis.domain.mapper.AvisMapper;
import fr.esgi.avis.domain.mapper.JeuMapper;
import fr.esgi.avis.domain.usecase.RegisterJoueurUseCase;
import fr.esgi.avis.domain.usecase.UtilisateurGetAvisUseCase;
import fr.esgi.avis.domain.usecase.UtilisateurGetGameUseCase;
import fr.esgi.avis.domain.repository.JoueurRepository;
import fr.esgi.avis.domain.usecase.UtilisateurLogoutUseCase;
import fr.esgi.avis.domain.usecase.UtilisateurWriteAvisUseCase;
import fr.esgi.avis.security.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final JoueurRepository joueurRepository;
    private final UtilisateurGetAvisUseCase utilisateurGetAvisUseCase;
    private final UtilisateurWriteAvisUseCase utilisateurWriteAvisUseCase;
    private final RegisterJoueurUseCase registerJoueurUseCase;
    private final UtilisateurGetGameUseCase utilisateurGetGameUseCase;
    private final UtilisateurLogoutUseCase utilisateurLogoutUseCase;
    private final AuthService authService;

    public UtilisateurController(JoueurRepository joueurRepository,
                                 UtilisateurGetAvisUseCase utilisateurGetAvisUseCase,
                                 UtilisateurWriteAvisUseCase utilisateurWriteAvisUseCase,
                                 RegisterJoueurUseCase registerJoueurUseCase,
                                 UtilisateurGetGameUseCase utilisateurGetGameUseCase,
                                 UtilisateurLogoutUseCase utilisateurLogoutUseCase,
                                 AuthService authService) {
        this.joueurRepository = joueurRepository;
        this.utilisateurGetAvisUseCase = utilisateurGetAvisUseCase;
        this.utilisateurWriteAvisUseCase = utilisateurWriteAvisUseCase;
        this.registerJoueurUseCase = registerJoueurUseCase;
        this.utilisateurGetGameUseCase = utilisateurGetGameUseCase;
        this.utilisateurLogoutUseCase = utilisateurLogoutUseCase;
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

    @GetMapping("/me")
    public MeDto me() {
        String pseudo = SecurityContextHolder.getContext().getAuthentication().getName();
        return joueurRepository.findByPseudo(pseudo)
                .map(j -> new MeDto(j.getId(), j.getPseudo()))
                .orElseThrow(() -> new IllegalArgumentException("Joueur non trouvé : " + pseudo));
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        utilisateurLogoutUseCase.apply(token);
    }
}
