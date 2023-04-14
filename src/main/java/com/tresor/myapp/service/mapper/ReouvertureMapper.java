package com.tresor.myapp.service.mapper;


import com.tresor.myapp.domain.*;
import com.tresor.myapp.service.dto.ReouvertureDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Reouverture} and its DTO {@link ReouvertureDTO}.
 */
@Mapper(componentModel = "spring", uses = {EmissionMapper.class})
public interface ReouvertureMapper extends EntityMapper<ReouvertureDTO, Reouverture> {

    @Mapping(source = "emission.id", target = "emissionId")
    ReouvertureDTO toDto(Reouverture reouverture);

    @Mapping(target = "soumissions", ignore = true)
    @Mapping(target = "removeSoumission", ignore = true)
    @Mapping(target = "adjudications", ignore = true)
    @Mapping(target = "removeAdjudication", ignore = true)
    @Mapping(target = "classements", ignore = true)
    @Mapping(target = "removeClassement", ignore = true)
    @Mapping(target = "resultats", ignore = true)
    @Mapping(target = "removeResultat", ignore = true)
    @Mapping(source = "emissionId", target = "emission")
    Reouverture toEntity(ReouvertureDTO reouvertureDTO);

    default Reouverture fromId(Long id) {
        if (id == null) {
            return null;
        }
        Reouverture reouverture = new Reouverture();
        reouverture.setId(id);
        return reouverture;
    }
}
