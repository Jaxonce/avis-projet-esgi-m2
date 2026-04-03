package fr.esgi.avis.domain.mapper;

import fr.esgi.avis.domain.model.Avis;
import fr.esgi.avis.persistance.entity.AvisEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AvisMapper {
    AvisMapper INSTANCE = Mappers.getMapper(AvisMapper.class);

    AvisEntity avisToAvisEntity(Avis avis);
    Avis avisEntityToAvis(AvisEntity avisEntity);
}
