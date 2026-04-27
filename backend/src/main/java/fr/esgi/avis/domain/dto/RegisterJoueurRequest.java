package fr.esgi.avis.domain.dto;

import java.time.LocalDate;

public record RegisterJoueurRequest(String pseudo, String motDePasse, String email, LocalDate dateDeNaissance) {}
