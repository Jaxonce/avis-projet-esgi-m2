package fr.esgi.avis.domain.usecase;

import fr.esgi.avis.domain.model.Jeu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UtilisateurGetGameUseCaseTest {

    @Mock
    UtilisateurGetGameUseCase.OutputPort outputPort;
    @InjectMocks
    UtilisateurGetGameUseCase utilisateurGetGameUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testApply_returnsJeux() {
        // Arrange
        List<Jeu> jeux = List.of(new Jeu("Zelda"), new Jeu("Mario"));
        when(outputPort.findAllJeux()).thenReturn(jeux);

        // Act
        List<Jeu> result = utilisateurGetGameUseCase.apply();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Zelda", result.get(0).getNom());
        verify(outputPort).findAllJeux();
    }

    @Test
    void testApply_emptyList() {
        // Arrange
        when(outputPort.findAllJeux()).thenReturn(List.of());

        // Act
        List<Jeu> result = utilisateurGetGameUseCase.apply();

        // Assert
        assertTrue(result.isEmpty());
    }
}
