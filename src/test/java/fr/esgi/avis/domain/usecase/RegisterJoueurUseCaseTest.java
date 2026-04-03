package fr.esgi.avis.domain.usecase;

import fr.esgi.avis.domain.dto.RegisterJoueurRequest;
import fr.esgi.avis.domain.model.Joueur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegisterJoueurUseCaseTest {

    @Mock
    RegisterJoueurUseCase.OutputPort outputPort;
    @InjectMocks
    RegisterJoueurUseCase registerJoueurUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testApply_savesJoueurWithCorrectData() {
        // Arrange
        RegisterJoueurRequest request = new RegisterJoueurRequest("alice", "pass123", "alice@mail.com", LocalDate.of(1995, 3, 10));

        // Act
        registerJoueurUseCase.apply(request);

        // Assert
        ArgumentCaptor<Joueur> captor = ArgumentCaptor.forClass(Joueur.class);
        verify(outputPort).save(captor.capture());
        Joueur savedJoueur = captor.getValue();
        assertEquals("alice", savedJoueur.getPseudo());
        assertEquals("pass123", savedJoueur.getMotDePasse());
        assertEquals("alice@mail.com", savedJoueur.getEmail());
        assertEquals(LocalDate.of(1995, 3, 10), savedJoueur.getDateDeNaissance());
    }
}
