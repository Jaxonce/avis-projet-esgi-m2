package fr.esgi.avis.domain.usecase;

import fr.esgi.avis.domain.dto.CreateJeuRequest;
import fr.esgi.avis.domain.model.Editeur;
import fr.esgi.avis.domain.model.Genre;
import fr.esgi.avis.domain.model.Jeu;
import fr.esgi.avis.domain.model.Plateforme;
import fr.esgi.avis.domain.repository.EditeurRepository;
import fr.esgi.avis.domain.repository.GenreRepository;
import fr.esgi.avis.domain.repository.PlateformeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ModeratorAddGameUseCaseTest {

    @Mock
    ModeratorAddGameUseCase.OutputPort outputPort;
    @Mock
    EditeurRepository editeurRepository;
    @Mock
    GenreRepository genreRepository;
    @Mock
    PlateformeRepository plateformeRepository;

    @InjectMocks
    ModeratorAddGameUseCase moderatorAddGameUseCase;

    AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void testApply_savesJeuWithCorrectData() {
        // Arrange
        Editeur editeur = new Editeur(1L, "Nintendo", List.of());
        Genre genre = new Genre(2L, "Action", List.of());
        Plateforme plateforme = new Plateforme(3L, "Nintendo Switch", LocalDate.of(2017, 3, 3), List.of());

        when(editeurRepository.findById(1L)).thenReturn(Optional.of(editeur));
        when(genreRepository.findById(2L)).thenReturn(Optional.of(genre));
        when(plateformeRepository.findById(3L)).thenReturn(Optional.of(plateforme));

        CreateJeuRequest request = new CreateJeuRequest("Zelda", LocalDate.of(2023, 5, 12), "Action-aventure", 59.99, 1L, 2L, List.of(3L));

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
        assertEquals(editeur, savedJeu.getEditeur());
        assertEquals(genre, savedJeu.getGenre());
        assertEquals(List.of(plateforme), savedJeu.getPlateformes());
    }

    @Test
    void testApply_throwsWhenEditeurNotFound() {
        when(editeurRepository.findById(99L)).thenReturn(Optional.empty());

        CreateJeuRequest request = new CreateJeuRequest("Zelda", LocalDate.of(2023, 5, 12), "desc", 59.99, 99L, 1L, List.of(1L));

        assertThrows(IllegalArgumentException.class, () -> moderatorAddGameUseCase.apply(request));
        verify(outputPort, never()).save(any());
    }

    @Test
    void testApply_throwsWhenGenreNotFound() {
        when(editeurRepository.findById(1L)).thenReturn(Optional.of(new Editeur(1L, "Nintendo", List.of())));
        when(genreRepository.findById(99L)).thenReturn(Optional.empty());

        CreateJeuRequest request = new CreateJeuRequest("Zelda", LocalDate.of(2023, 5, 12), "desc", 59.99, 1L, 99L, List.of(1L));

        assertThrows(IllegalArgumentException.class, () -> moderatorAddGameUseCase.apply(request));
        verify(outputPort, never()).save(any());
    }

    @Test
    void testApply_throwsWhenPlateformeNotFound() {
        when(editeurRepository.findById(1L)).thenReturn(Optional.of(new Editeur(1L, "Nintendo", List.of())));
        when(genreRepository.findById(2L)).thenReturn(Optional.of(new Genre(2L, "Action", List.of())));
        when(plateformeRepository.findById(99L)).thenReturn(Optional.empty());

        CreateJeuRequest request = new CreateJeuRequest("Zelda", LocalDate.of(2023, 5, 12), "desc", 59.99, 1L, 2L, List.of(99L));

        assertThrows(IllegalArgumentException.class, () -> moderatorAddGameUseCase.apply(request));
        verify(outputPort, never()).save(any());
    }
}