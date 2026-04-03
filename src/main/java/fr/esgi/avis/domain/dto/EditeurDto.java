package fr.esgi.avis.domain.dto;

import java.util.List;

public record EditeurDto(Long id, String nom, List<JeuDto> jeux) {
}
