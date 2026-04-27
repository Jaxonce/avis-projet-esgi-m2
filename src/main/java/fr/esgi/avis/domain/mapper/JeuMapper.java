package fr.esgi.avis.domain.mapper;

import fr.esgi.avis.domain.dto.ClassificationDto;
import fr.esgi.avis.domain.dto.EditeurDto;
import fr.esgi.avis.domain.dto.GenreDto;
import fr.esgi.avis.domain.dto.JeuDto;
import fr.esgi.avis.domain.dto.PlateformeDto;
import fr.esgi.avis.domain.model.Classification;
import fr.esgi.avis.domain.model.Editeur;
import fr.esgi.avis.domain.model.Genre;
import fr.esgi.avis.domain.model.Jeu;
import fr.esgi.avis.domain.model.Plateforme;
import fr.esgi.avis.persistance.entity.ClassificationEntity;
import fr.esgi.avis.persistance.entity.EditeurEntity;
import fr.esgi.avis.persistance.entity.GenreEntity;
import fr.esgi.avis.persistance.entity.JeuEntity;
import fr.esgi.avis.persistance.entity.PlateformeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JeuMapper {
    JeuMapper INSTANCE = Mappers.getMapper(JeuMapper.class);

    // Entity → Model (avec relations)
    Jeu jeuEntityToJeu(JeuEntity jeuEntity);

    // Model → Entity (relations ignorées, gérées manuellement dans l'adapter)
    @Mapping(target = "plateformes", ignore = true)
    @Mapping(target = "editeur", ignore = true)
    @Mapping(target = "classification", ignore = true)
    @Mapping(target = "genre", ignore = true)
    JeuEntity jeuToJeuEntity(Jeu jeu);

    // Model → DTO (avec relations)
    JeuDto jeuToJeuDto(Jeu jeu);

    // Sous-mappers Entity → Model (jeux ignorés pour éviter les références circulaires)
    @Mapping(target = "jeux", ignore = true)
    Editeur editeurEntityToEditeur(EditeurEntity editeur);

    @Mapping(target = "jeux", ignore = true)
    Genre genreEntityToGenre(GenreEntity genre);

    @Mapping(target = "jeux", ignore = true)
    Plateforme plateformeEntityToPlateforme(PlateformeEntity plateforme);

    @Mapping(target = "jeux", ignore = true)
    Classification classificationEntityToClassification(ClassificationEntity classification);

    // Sous-mappers Model → DTO (jeux ignorés pour éviter les références circulaires)
    @Mapping(target = "jeux", ignore = true)
    EditeurDto editeurToEditeurDto(Editeur editeur);

    @Mapping(target = "jeux", ignore = true)
    GenreDto genreToGenreDto(Genre genre);

    @Mapping(target = "jeux", ignore = true)
    PlateformeDto plateformeToPlateformeDto(Plateforme plateforme);

    @Mapping(target = "jeux", ignore = true)
    ClassificationDto classificationToClassificationDto(Classification classification);
}