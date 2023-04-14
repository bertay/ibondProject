package com.tresor.myapp.service.mapper;


import com.tresor.myapp.domain.*;
import com.tresor.myapp.service.dto.ClassementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Classement} and its DTO {@link ClassementDTO}.
 */
@Mapper(componentModel = "spring", uses = {DetailSoumissionMapper.class, EmissionMapper.class, ReouvertureMapper.class, RachatMapper.class, OncMapper.class})
public interface ClassementMapper extends EntityMapper<ClassementDTO, Classement> {

    @Mapping(source = "detailSoumission.id", target = "detailSoumissionId")
    @Mapping(source = "emission.id", target = "emissionId")
    @Mapping(source = "reouverture.id", target = "reouvertureId")
    @Mapping(source = "rachat.id", target = "rachatId")
    @Mapping(source = "onc.id", target = "oncId")
    ClassementDTO toDto(Classement classement);

    @Mapping(source = "detailSoumissionId", target = "detailSoumission")
    @Mapping(source = "emissionId", target = "emission")
    @Mapping(source = "reouvertureId", target = "reouverture")
    @Mapping(source = "rachatId", target = "rachat")
    @Mapping(source = "oncId", target = "onc")
    Classement toEntity(ClassementDTO classementDTO);

    default Classement fromId(Long id) {
        if (id == null) {
            return null;
        }
        Classement classement = new Classement();
        classement.setId(id);
        return classement;
    }
}
