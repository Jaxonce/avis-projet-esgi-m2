package fr.esgi.avis.persistance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PlateformeEntity {

    @Id
    private Long id;

    private String nom;

    private LocalDate dateDeSortie;

    private List<JeuEntity> jeux = new ArrayList<>();
}
