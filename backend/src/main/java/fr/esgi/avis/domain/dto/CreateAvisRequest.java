package fr.esgi.avis.domain.dto;

public record CreateAvisRequest(String description, Double note, Long jeuId, Long joueurId) {}