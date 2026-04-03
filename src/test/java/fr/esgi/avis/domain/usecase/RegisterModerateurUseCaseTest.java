package fr.esgi.avis.domain.usecase;

import fr.esgi.avis.domain.dto.RegisterModerateurRequest;
import fr.esgi.avis.domain.model.Moderateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegisterModerateurUseCaseTest {

    @Mock
    RegisterModerateurUseCase.OutputPort outputPort;
    @InjectMocks
    RegisterModerateurUseCase registerModerateurUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testApply_savesModerateurWithCorrectData() {
        // Arrange
        RegisterModerateurRequest request = new RegisterModerateurRequest("modo1", "pass123", "modo@mail.com", "0600000000");

        // Act
        registerModerateurUseCase.apply(request);

        // Assert
        ArgumentCaptor<Moderateur> captor = ArgumentCaptor.forClass(Moderateur.class);
        verify(outputPort).save(captor.capture());
        Moderateur savedModerateur = captor.getValue();
        assertEquals("modo1", savedModerateur.getPseudo());
        assertEquals("pass123", savedModerateur.getMotDePasse());
        assertEquals("modo@mail.com", savedModerateur.getEmail());
        assertEquals("0600000000", savedModerateur.getNumeroDeTelephone());
    }
}
