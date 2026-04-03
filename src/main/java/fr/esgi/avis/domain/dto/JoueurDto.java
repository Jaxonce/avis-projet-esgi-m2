package fr.esgi.avis.domain.dto;

import fr.esgi.avis.domain.model.Avatar;
import fr.esgi.avis.domain.model.Avis;

import java.time.LocalDate;
import java.util.List;

public record JoueurDto(LocalDate dateDeNaissance, Avatar avatar, List<Avis> avis) {
}
