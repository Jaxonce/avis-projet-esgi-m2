package fr.esgi.avis.domain.dto;

import java.time.LocalDate;
import java.util.List;

public record CreateJeuRequest(
        String nom,
        LocalDate dateDeSortie,
        String description,
        Double prix,
        Long editeurId,
        Long genreId,
        List<Long> plateformeIds
) {}