package com.tresor.myapp.service.mapper;


import com.tresor.myapp.domain.*;
import com.tresor.myapp.service.dto.EmissionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Emission} and its DTO {@link EmissionDTO}.
 */
@Mapper(componentModel = "spring", uses = {AvisEmissionMapper.class, NatureTitreMapper.class})
public interface EmissionMapper extends EntityMapper<EmissionDTO, Emission> {

    @Mapping(source = "avisEmission.id", target = "avisEmissionId")
    @Mapping(source = "natureTitre.id", target = "natureTitreId")
    EmissionDTO toDto(Emission emission);

    @Mapping(target = "soumissions", ignore = true)
    @Mapping(target = "removeSoumission", ignore = true)
    @Mapping(target = "reouvertures", ignore = true)
    @Mapping(target = "removeReouverture", ignore = true)
    @Mapping(target = "rachats", ignore = true)
    @Mapping(target = "removeRachat", ignore = true)
    @Mapping(target = "oncs", ignore = true)
    @Mapping(target = "removeOnc", ignore = true)
    @Mapping(target = "classements", ignore = true)
    @Mapping(target = "removeClassement", ignore = true)
    @Mapping(target = "resultats", ignore = true)
    @Mapping(target = "removeResultat", ignore = true)
    @Mapping(target = "membreSyndicats", ignore = true)
    @Mapping(target = "removeMembreSyndicat", ignore = true)
    @Mapping(source = "avisEmissionId", target = "avisEmission")
    @Mapping(source = "natureTitreId", target = "natureTitre")
    Emission toEntity(EmissionDTO emissionDTO);

    default Emission fromId(Long id) {
        if (id == null) {
            return null;
        }
        Emission emission = new Emission();
        emission.setId(id);
        return emission;
    }
}
