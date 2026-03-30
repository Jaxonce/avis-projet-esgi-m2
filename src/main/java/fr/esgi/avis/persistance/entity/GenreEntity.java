package fr.esgi.avis.persistance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class GenreEntity {

    @Id
    private Long id;

    private String nom;

    private List<JeuEntity> jeux = new ArrayList<>();
}
