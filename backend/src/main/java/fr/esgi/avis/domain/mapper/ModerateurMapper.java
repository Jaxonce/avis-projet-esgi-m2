package fr.esgi.avis.domain.mapper;

import fr.esgi.avis.domain.model.Moderateur;
import fr.esgi.avis.persistance.entity.ModerateurEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ModerateurMapper {
    ModerateurMapper INSTANCE = Mappers.getMapper(ModerateurMapper.class);

    ModerateurEntity moderateurToModerateurEntity(Moderateur moderateur);
    Moderateur moderateurEntityToModerateur(ModerateurEntity moderateurEntity);
}
