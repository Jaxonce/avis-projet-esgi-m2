package fr.esgi.avis.domain.mapper;

import fr.esgi.avis.domain.dto.AvisDto;
import fr.esgi.avis.domain.model.Avis;
import fr.esgi.avis.persistance.entity.AvisEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AvisMapper {
    AvisMapper INSTANCE = Mappers.getMapper(AvisMapper.class);

    @Mapping(target = "jeu.editeur", ignore = true)
    @Mapping(target = "jeu.classification", ignore = true)
    @Mapping(target = "jeu.plateformes", ignore = true)
    @Mapping(target = "jeu.genre", ignore = true)
    @Mapping(target = "joueur.avatar", ignore = true)
    @Mapping(target = "joueur.avis", ignore = true)
    @Mapping(target = "dateDEnvoi", source = "dateDenvoi")
    AvisEntity avisToAvisEntity(Avis avis);

    @Mapping(target = "jeu.editeur", ignore = true)
    @Mapping(target = "jeu.classification", ignore = true)
    @Mapping(target = "jeu.plateformes", ignore = true)
    @Mapping(target = "jeu.genre", ignore = true)
    @Mapping(target = "joueur.avatar", ignore = true)
    @Mapping(target = "joueur.avis", ignore = true)
    @Mapping(target = "dateDenvoi", source = "dateDEnvoi")
    Avis avisEntityToAvis(AvisEntity avisEntity);

    @Mapping(target = "dateDenvoi", ignore = true)
    Avis avisDtoToAvis(AvisDto avisDto);

    AvisDto avisToAvisDto(Avis avis);
}
