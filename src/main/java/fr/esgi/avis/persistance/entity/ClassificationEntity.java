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
public class ClassificationEntity {

    @Id
    private Long id;

    private String nom;

    private String couleurRGB;

    @OneToMany(mappedBy = "classification")
    private Set<JeuEntity> jeux = new HashSet<JeuEntity>();
}
