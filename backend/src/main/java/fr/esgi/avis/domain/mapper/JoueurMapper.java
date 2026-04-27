package fr.esgi.avis.domain.mapper;

import fr.esgi.avis.domain.model.Joueur;
import fr.esgi.avis.persistance.entity.JoueurEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JoueurMapper {
    JoueurMapper INSTANCE = Mappers.getMapper(JoueurMapper.class);

    @Mapping(target = "avis", ignore = true)
    @Mapping(target = "avatar", ignore = true)
    Joueur joueurEntityToJoueur(JoueurEntity joueurEntity);

    @Mapping(target = "avis", ignore = true)
    @Mapping(target = "avatar", ignore = true)
    JoueurEntity joueurToJoueurEntity(Joueur joueur);
}
