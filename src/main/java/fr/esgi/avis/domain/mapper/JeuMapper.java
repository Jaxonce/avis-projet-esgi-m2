package fr.esgi.avis.domain.mapper;

import fr.esgi.avis.domain.dto.JeuDto;
import fr.esgi.avis.domain.model.Jeu;
import fr.esgi.avis.persistance.entity.JeuEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JeuMapper {
    JeuMapper INSTANCE = Mappers.getMapper(JeuMapper.class);

    @Mapping(target = "plateformes", ignore = true)
    @Mapping(target = "editeur", ignore = true)
    @Mapping(target = "classification", ignore = true)
    @Mapping(target = "genre", ignore = true)
    Jeu jeuEntityToJeu(JeuEntity jeuEntity);

    @Mapping(target = "plateformes", ignore = true)
    @Mapping(target = "editeur", ignore = true)
    @Mapping(target = "classification", ignore = true)
    @Mapping(target = "genre", ignore = true)
    JeuEntity jeuToJeuEntity(Jeu jeu);

    @Mapping(target = "plateformes", ignore = true)
    @Mapping(target = "editeur", ignore = true)
    @Mapping(target = "classification", ignore = true)
    @Mapping(target = "genre", ignore = true)
    JeuDto jeuToJeuDto(Jeu jeu);
}
