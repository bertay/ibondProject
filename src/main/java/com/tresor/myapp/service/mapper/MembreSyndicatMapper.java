package com.tresor.myapp.service.mapper;


import com.tresor.myapp.domain.*;
import com.tresor.myapp.service.dto.MembreSyndicatDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MembreSyndicat} and its DTO {@link MembreSyndicatDTO}.
 */
@Mapper(componentModel = "spring", uses = {EmissionMapper.class})
public interface MembreSyndicatMapper extends EntityMapper<MembreSyndicatDTO, MembreSyndicat> {

    @Mapping(source = "emission.id", target = "emissionId")
    MembreSyndicatDTO toDto(MembreSyndicat membreSyndicat);

    @Mapping(source = "emissionId", target = "emission")
    MembreSyndicat toEntity(MembreSyndicatDTO membreSyndicatDTO);

    default MembreSyndicat fromId(Long id) {
        if (id == null) {
            return null;
        }
        MembreSyndicat membreSyndicat = new MembreSyndicat();
        membreSyndicat.setId(id);
        return membreSyndicat;
    }
}
