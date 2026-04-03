package fr.esgi.avis.domain.usecase;

import fr.esgi.avis.domain.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.mockito.Mockito.*;

class UtilisateurGetAvisUseCaseTest {
    @Mock
    UtilisateurGetAvisUseCase.OutputPort outputPort;
    @InjectMocks
    UtilisateurGetAvisUseCase utilisateurGetAvisUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testApply() {

        //Arrange - Given
        when(outputPort.findAllAvis()).thenReturn(List.of(new Avis(Long.valueOf(1), "description", LocalDateTime.of(2026, Month.APRIL, 3, 10, 8, 48), Double.valueOf(0), new Jeu(Long.valueOf(1), "nom", LocalDate.of(2026, Month.APRIL, 3), "description", new Editeur(Long.valueOf(1), "nom", List.of()), new Classification(Long.valueOf(1), "nom", "couleurRGB", List.of()), List.of(new Plateforme(Long.valueOf(1), "nom", LocalDate.of(2026, Month.APRIL, 3), List.of())), new Genre(Long.valueOf(1), "nom", List.of()), true, Double.valueOf(0), "image"), new Joueur("pseudo", "motDePasse", "email", null), new Moderateur("pseudo", "motDePasse", "email", null))));

        //Act - When
        List<Avis> result = utilisateurGetAvisUseCase.apply();

        //Assert - Then
        Assertions.assertEquals(List.of(new Avis(Long.valueOf(1), "description", LocalDateTime.of(2026, Month.APRIL, 3, 10, 8, 48), Double.valueOf(0), new Jeu(Long.valueOf(1), "nom", LocalDate.of(2026, Month.APRIL, 3), "description", new Editeur(Long.valueOf(1), "nom", List.of()), new Classification(Long.valueOf(1), "nom", "couleurRGB", List.of()), List.of(new Plateforme(Long.valueOf(1), "nom", LocalDate.of(2026, Month.APRIL, 3), List.of())), new Genre(Long.valueOf(1), "nom", List.of()), true, Double.valueOf(0), "image"), new Joueur("pseudo", "motDePasse", "email", null), new Moderateur("pseudo", "motDePasse", "email", null))), result);
    }
}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme