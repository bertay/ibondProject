package com.tresor.myapp.service.mapper;


import com.tresor.myapp.domain.*;
import com.tresor.myapp.service.dto.AvisEmissionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AvisEmission} and its DTO {@link AvisEmissionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AvisEmissionMapper extends EntityMapper<AvisEmissionDTO, AvisEmission> {


    @Mapping(target = "emissions", ignore = true)
    @Mapping(target = "removeEmission", ignore = true)
    AvisEmission toEntity(AvisEmissionDTO avisEmissionDTO);

    default AvisEmission fromId(Long id) {
        if (id == null) {
            return null;
        }
        AvisEmission avisEmission = new AvisEmission();
        avisEmission.setId(id);
        return avisEmission;
    }
}
