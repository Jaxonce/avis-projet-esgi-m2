package fr.esgi.avis.domain.dto;

import java.util.List;

public record ReferentialDto(List<EditeurDto> editeurs, List<GenreDto> genres, List<PlateformeDto> plateformes) {}
