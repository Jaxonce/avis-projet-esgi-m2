package fr.esgi.avis.domain.dto;


import java.util.List;

public record GenreDto(Long id, String nom, List<JeuDto> jeux) {
}
