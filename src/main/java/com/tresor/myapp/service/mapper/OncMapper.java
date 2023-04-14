package com.tresor.myapp.service.mapper;


import com.tresor.myapp.domain.*;
import com.tresor.myapp.service.dto.OncDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Onc} and its DTO {@link OncDTO}.
 */
@Mapper(componentModel = "spring", uses = {EmissionMapper.class})
public interface OncMapper extends EntityMapper<OncDTO, Onc> {

    @Mapping(source = "emission.id", target = "emissionId")
    OncDTO toDto(Onc onc);

    @Mapping(target = "soumissions", ignore = true)
    @Mapping(target = "removeSoumission", ignore = true)
    @Mapping(target = "adjudications", ignore = true)
    @Mapping(target = "removeAdjudication", ignore = true)
    @Mapping(target = "classements", ignore = true)
    @Mapping(target = "removeClassement", ignore = true)
    @Mapping(target = "resultats", ignore = true)
    @Mapping(target = "removeResultat", ignore = true)
    @Mapping(source = "emissionId", target = "emission")
    Onc toEntity(OncDTO oncDTO);

    default Onc fromId(Long id) {
        if (id == null) {
            return null;
        }
        Onc onc = new Onc();
        onc.setId(id);
        return onc;
    }
}
