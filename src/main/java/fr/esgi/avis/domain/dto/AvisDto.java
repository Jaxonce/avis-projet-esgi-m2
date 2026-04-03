package fr.esgi.avis.domain.dto;

public record AvisDto(Long id, String description, Double note, JeuDto jeu, JoueurDto joueur, ModerateurDto moderateur) {
}
