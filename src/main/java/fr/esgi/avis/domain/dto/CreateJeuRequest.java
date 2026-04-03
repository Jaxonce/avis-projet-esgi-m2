package fr.esgi.avis.domain.dto;

import java.time.LocalDate;

public record CreateJeuRequest(String nom, LocalDate dateDeSortie, String description, Double prix) {}
