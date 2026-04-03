package fr.esgi.avis.domain.usecase;

import fr.esgi.avis.domain.model.Avis;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ModeratorManageAvisUseCaseTest {

    @Mock
    ModeratorManageAvisUseCase.OutputPort outputPort;
    @InjectMocks
    ModeratorManageAvisUseCase moderatorManageAvisUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAvis_returnsAvis() {
        // Arrange
        Avis avis1 = new Avis();
        avis1.setDescription("Très bon jeu");
        Avis avis2 = new Avis();
        avis2.setDescription("Pas terrible");
        when(outputPort.findAll()).thenReturn(List.of(avis1, avis2));

        // Act
        List<Avis> result = moderatorManageAvisUseCase.getAllAvis();

        // Assert
        assertEquals(2, result.size());
        verify(outputPort).findAll();
    }

    @Test
    void testDeleteAvis_callsOutputPort() {
        // Act
        moderatorManageAvisUseCase.deleteAvis(1L);

        // Assert
        verify(outputPort).deleteById(1L);
    }

    @Test
    void testDeleteAvis_withDifferentId() {
        // Act
        moderatorManageAvisUseCase.deleteAvis(42L);

        // Assert
        verify(outputPort).deleteById(42L);
        verify(outputPort, never()).deleteById(1L);
    }
}
