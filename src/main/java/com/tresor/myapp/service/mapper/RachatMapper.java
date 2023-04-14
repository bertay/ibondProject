package com.tresor.myapp.service.mapper;


import com.tresor.myapp.domain.*;
import com.tresor.myapp.service.dto.RachatDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Rachat} and its DTO {@link RachatDTO}.
 */
@Mapper(componentModel = "spring", uses = {EmissionMapper.class})
public interface RachatMapper extends EntityMapper<RachatDTO, Rachat> {

    @Mapping(source = "emission.id", target = "emissionId")
    RachatDTO toDto(Rachat rachat);

    @Mapping(target = "soumissions", ignore = true)
    @Mapping(target = "removeSoumission", ignore = true)
    @Mapping(target = "adjudications", ignore = true)
    @Mapping(target = "removeAdjudication", ignore = true)
    @Mapping(target = "classements", ignore = true)
    @Mapping(target = "removeClassement", ignore = true)
    @Mapping(target = "resultats", ignore = true)
    @Mapping(target = "removeResultat", ignore = true)
    @Mapping(source = "emissionId", target = "emission")
    Rachat toEntity(RachatDTO rachatDTO);

    default Rachat fromId(Long id) {
        if (id == null) {
            return null;
        }
        Rachat rachat = new Rachat();
        rachat.setId(id);
        return rachat;
    }
}
