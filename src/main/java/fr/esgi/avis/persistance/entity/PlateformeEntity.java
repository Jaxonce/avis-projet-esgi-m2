package fr.esgi.avis.persistance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class PlateformeEntity {

    @Id
    private Long id;

    private String nom;

    private LocalDate dateDeSortie;

    @ManyToMany(mappedBy = "plateformes")
    private Set<JeuEntity> jeux = new HashSet<>();
}
