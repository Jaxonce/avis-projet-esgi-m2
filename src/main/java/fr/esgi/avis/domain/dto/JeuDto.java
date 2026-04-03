package fr.esgi.avis.domain.dto;

import fr.esgi.avis.domain.model.Classification;
import fr.esgi.avis.domain.model.Editeur;
import fr.esgi.avis.domain.model.Genre;
import fr.esgi.avis.domain.model.Plateforme;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

public record JeuDto(Long id, String nom, LocalDate dateDeSortie, String description, EditeurDto editeur, ClassificationDto classification, List<PlateformeDto> plateformes, GenreDto genre, Double prix, String image, boolean possedeImage) {
}
