package com.tresor.myapp.service.mapper;


import com.tresor.myapp.domain.*;
import com.tresor.myapp.service.dto.SoumissionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Soumission} and its DTO {@link SoumissionDTO}.
 */
@Mapper(componentModel = "spring", uses = {EmissionMapper.class, ReouvertureMapper.class, RachatMapper.class, OncMapper.class})
public interface SoumissionMapper extends EntityMapper<SoumissionDTO, Soumission> {

    @Mapping(source = "emission.id", target = "emissionId")
    @Mapping(source = "reouverture.id", target = "reouvertureId")
    @Mapping(source = "rachat.id", target = "rachatId")
    @Mapping(source = "onc.id", target = "oncId")
    SoumissionDTO toDto(Soumission soumission);

    @Mapping(target = "detailSoumissions", ignore = true)
    @Mapping(target = "removeDetailSoumission", ignore = true)
    @Mapping(source = "emissionId", target = "emission")
    @Mapping(source = "reouvertureId", target = "reouverture")
    @Mapping(source = "rachatId", target = "rachat")
    @Mapping(source = "oncId", target = "onc")
    Soumission toEntity(SoumissionDTO soumissionDTO);

    default Soumission fromId(Long id) {
        if (id == null) {
            return null;
        }
        Soumission soumission = new Soumission();
        soumission.setId(id);
        return soumission;
    }
}
