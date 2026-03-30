package fr.esgi.avis.persistance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class GenreEntity {

    @Id
    private Long id;

    private String nom;

    @OneToMany(mappedBy = "genre")
    private Set<JeuEntity> jeux = new HashSet<>();
}
