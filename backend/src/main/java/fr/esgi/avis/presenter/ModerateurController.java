package fr.esgi.avis.presenter;

import fr.esgi.avis.domain.dto.AvisDto;
import fr.esgi.avis.domain.dto.CreateJeuRequest;
import fr.esgi.avis.domain.dto.EditeurDto;
import fr.esgi.avis.domain.dto.GenreDto;
import fr.esgi.avis.domain.dto.LoginRequest;
import fr.esgi.avis.domain.dto.LoginResponse;
import fr.esgi.avis.domain.dto.PlateformeDto;
import fr.esgi.avis.domain.dto.ReferentialDto;
import fr.esgi.avis.domain.dto.RegisterModerateurRequest;
import fr.esgi.avis.domain.repository.EditeurRepository;
import fr.esgi.avis.domain.repository.GenreRepository;
import fr.esgi.avis.domain.repository.PlateformeRepository;
import fr.esgi.avis.domain.mapper.JeuMapper;
import fr.esgi.avis.domain.mapper.AvisMapper;
import fr.esgi.avis.domain.usecase.ModeratorAddGameUseCase;
import fr.esgi.avis.domain.usecase.ModeratorLogoutUseCase;
import fr.esgi.avis.domain.usecase.ModeratorManageAvisUseCase;
import fr.esgi.avis.domain.usecase.RegisterModerateurUseCase;
import fr.esgi.avis.security.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/moderateur")
@SecurityRequirement(name = "Bearer")
public class ModerateurController {

    private final RegisterModerateurUseCase registerModerateurUseCase;
    private final ModeratorAddGameUseCase moderatorAddGameUseCase;
    private final ModeratorManageAvisUseCase moderatorManageAvisUseCase;
    private final ModeratorLogoutUseCase moderatorLogoutUseCase;
    private final AuthService authService;
    private final EditeurRepository editeurRepository;
    private final GenreRepository genreRepository;
    private final PlateformeRepository plateformeRepository;

    public ModerateurController(RegisterModerateurUseCase registerModerateurUseCase,
                                ModeratorAddGameUseCase moderatorAddGameUseCase,
                                ModeratorManageAvisUseCase moderatorManageAvisUseCase,
                                ModeratorLogoutUseCase moderatorLogoutUseCase,
                                AuthService authService,
                                EditeurRepository editeurRepository,
                                GenreRepository genreRepository,
                                PlateformeRepository plateformeRepository) {
        this.registerModerateurUseCase = registerModerateurUseCase;
        this.moderatorAddGameUseCase = moderatorAddGameUseCase;
        this.moderatorManageAvisUseCase = moderatorManageAvisUseCase;
        this.moderatorLogoutUseCase = moderatorLogoutUseCase;
        this.authService = authService;
        this.editeurRepository = editeurRepository;
        this.genreRepository = genreRepository;
        this.plateformeRepository = plateformeRepository;
    }

    @PostMapping("/login")
    @Operation(security = {})
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/inscription")
    @ResponseStatus(HttpStatus.CREATED)
    public void inscription(@RequestBody RegisterModerateurRequest request) {
        registerModerateurUseCase.apply(request);
    }

    @PostMapping("/jeu")
    @ResponseStatus(HttpStatus.CREATED)
    public void addJeu(@RequestBody CreateJeuRequest request) {
        moderatorAddGameUseCase.apply(request);
    }

    @GetMapping("/avis")
    public List<AvisDto> getAllAvis() {
        return moderatorManageAvisUseCase.getAllAvis().stream().map(AvisMapper.INSTANCE::avisToAvisDto).toList();
    }

    @DeleteMapping("/avis/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAvis(@PathVariable Long id) {
        moderatorManageAvisUseCase.deleteAvis(id);
    }

    @GetMapping("/referentiel")
    public ReferentialDto getReferentiel() {
        List<EditeurDto> editeurs = editeurRepository.findAll().stream().map(JeuMapper.INSTANCE::editeurToEditeurDto).toList();
        List<GenreDto> genres = genreRepository.findAll().stream().map(JeuMapper.INSTANCE::genreToGenreDto).toList();
        List<PlateformeDto> plateformes = plateformeRepository.findAll().stream().map(JeuMapper.INSTANCE::plateformeToPlateformeDto).toList();
        return new ReferentialDto(editeurs, genres, plateformes);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        moderatorLogoutUseCase.apply(token);
    }
}
