package fr.esgi.avis.domain.dto;

import java.time.LocalDate;
import java.util.List;

public record PlateformeDto(Long id, String nom, LocalDate dateDeSortie, List<JeuDto> jeux) {
}
