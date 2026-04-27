package fr.esgi.avis.domain.dto;

public record LoginResponse(String token, String role, String pseudo) {}