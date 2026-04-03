package fr.esgi.avis.domain.usecase;

import fr.esgi.avis.domain.dto.CreateJeuRequest;
import fr.esgi.avis.domain.model.Jeu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ModeratorAddGameUseCaseTest {

    @Mock
    ModeratorAddGameUseCase.OutputPort outputPort;
    @InjectMocks
    ModeratorAddGameUseCase moderatorAddGameUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testApply_savesJeuWithCorrectData() {
        // Arrange
        CreateJeuRequest request = new CreateJeuRequest("Zelda", LocalDate.of(2023, 5, 12), "Action-aventure", 59.99);

        // Act
        moderatorAddGameUseCase.apply(request);

        // Assert
        ArgumentCaptor<Jeu> captor = ArgumentCaptor.forClass(Jeu.class);
        verify(outputPort).save(captor.capture());
        Jeu savedJeu = captor.getValue();
        assertEquals("Zelda", savedJeu.getNom());
        assertEquals(LocalDate.of(2023, 5, 12), savedJeu.getDateDeSortie());
        assertEquals("Action-aventure", savedJeu.getDescription());
        assertEquals(59.99, savedJeu.getPrix());
    }
}
