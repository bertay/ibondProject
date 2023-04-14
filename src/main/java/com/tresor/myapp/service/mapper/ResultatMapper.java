package com.tresor.myapp.service.mapper;


import com.tresor.myapp.domain.*;
import com.tresor.myapp.service.dto.ResultatDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Resultat} and its DTO {@link ResultatDTO}.
 */
@Mapper(componentModel = "spring", uses = {EmissionMapper.class, ReouvertureMapper.class, RachatMapper.class, OncMapper.class})
public interface ResultatMapper extends EntityMapper<ResultatDTO, Resultat> {

    @Mapping(source = "emission.id", target = "emissionId")
    @Mapping(source = "resultat.id", target = "resultatId")
    @Mapping(source = "rachat.id", target = "rachatId")
    @Mapping(source = "onc.id", target = "oncId")
    ResultatDTO toDto(Resultat resultat);

    @Mapping(source = "emissionId", target = "emission")
    @Mapping(source = "resultatId", target = "resultat")
    @Mapping(source = "rachatId", target = "rachat")
    @Mapping(source = "oncId", target = "onc")
    Resultat toEntity(ResultatDTO resultatDTO);

    default Resultat fromId(Long id) {
        if (id == null) {
            return null;
        }
        Resultat resultat = new Resultat();
        resultat.setId(id);
        return resultat;
    }
}
