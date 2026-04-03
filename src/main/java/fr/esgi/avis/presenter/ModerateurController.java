package fr.esgi.avis.presenter;

import fr.esgi.avis.domain.dto.CreateJeuRequest;
import fr.esgi.avis.domain.dto.RegisterModerateurRequest;
import fr.esgi.avis.domain.usecase.ModeratorAddGameUseCase;
import fr.esgi.avis.domain.usecase.RegisterModerateurUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/moderateur")
public class ModerateurController {

    private final RegisterModerateurUseCase registerModerateurUseCase;
    private final ModeratorAddGameUseCase moderatorAddGameUseCase;

    public ModerateurController(RegisterModerateurUseCase registerModerateurUseCase,
                                ModeratorAddGameUseCase moderatorAddGameUseCase) {
        this.registerModerateurUseCase = registerModerateurUseCase;
        this.moderatorAddGameUseCase = moderatorAddGameUseCase;
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
}
