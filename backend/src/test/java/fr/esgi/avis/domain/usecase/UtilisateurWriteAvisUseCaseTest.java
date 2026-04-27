package fr.esgi.avis.domain.usecase;

import fr.esgi.avis.domain.dto.CreateAvisRequest;
import fr.esgi.avis.domain.model.Jeu;
import fr.esgi.avis.domain.model.Joueur;
import fr.esgi.avis.domain.model.Avis;
import fr.esgi.avis.domain.repository.JeuRepository;
import fr.esgi.avis.domain.repository.JoueurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UtilisateurWriteAvisUseCaseTest {

    @Mock
    UtilisateurWriteAvisUseCase.OutputPort outputPort;
    @Mock
    JeuRepository jeuRepository;
    @Mock
    JoueurRepository joueurRepository;
    @InjectMocks
    UtilisateurWriteAvisUseCase utilisateurWriteAvisUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testApply_success() {
        // Arrange
        Jeu jeu = new Jeu("Zelda");
        Joueur joueur = new Joueur("alice", "pass", "alice@mail.com", LocalDate.of(1995, 1, 1));
        CreateAvisRequest request = new CreateAvisRequest("Super jeu !", 9.0, 1L, 1L);

        when(jeuRepository.findById(1L)).thenReturn(Optional.of(jeu));
        when(joueurRepository.findById(1L)).thenReturn(Optional.of(joueur));

        // Act
        utilisateurWriteAvisUseCase.apply(request);

        // Assert
        ArgumentCaptor<Avis> captor = ArgumentCaptor.forClass(Avis.class);
        verify(outputPort).writeAvis(captor.capture());
        Avis savedAvis = captor.getValue();
        assertEquals("Super jeu !", savedAvis.getDescription());
        assertEquals(9.0, savedAvis.getNote());
        assertEquals(jeu, savedAvis.getJeu());
        assertEquals(joueur, savedAvis.getJoueur());
        assertNotNull(savedAvis.getDateDenvoi());
    }

    @Test
    void testApply_jeuNotFound_throwsException() {
        // Arrange
        CreateAvisRequest request = new CreateAvisRequest("Super jeu !", 9.0, 99L, 1L);
        when(jeuRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> utilisateurWriteAvisUseCase.apply(request));
        verify(outputPort, never()).writeAvis(any());
    }

    @Test
    void testApply_joueurNotFound_throwsException() {
        // Arrange
        Jeu jeu = new Jeu("Zelda");
        CreateAvisRequest request = new CreateAvisRequest("Super jeu !", 9.0, 1L, 99L);
        when(jeuRepository.findById(1L)).thenReturn(Optional.of(jeu));
        when(joueurRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> utilisateurWriteAvisUseCase.apply(request));
        verify(outputPort, never()).writeAvis(any());
    }
}
