package fr.esgi.avis.domain.dto;

import java.util.List;

public record ClassificationDto(Long id, String nom, String couleurRGB, List<JeuDto> jeux) {

}
