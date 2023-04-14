package com.tresor.myapp.service.mapper;


import com.tresor.myapp.domain.*;
import com.tresor.myapp.service.dto.AdjudicationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Adjudication} and its DTO {@link AdjudicationDTO}.
 */
@Mapper(componentModel = "spring", uses = {ReouvertureMapper.class, RachatMapper.class, OncMapper.class})
public interface AdjudicationMapper extends EntityMapper<AdjudicationDTO, Adjudication> {

    @Mapping(source = "reouverture.id", target = "reouvertureId")
    @Mapping(source = "rachat.id", target = "rachatId")
    @Mapping(source = "onc.id", target = "oncId")
    AdjudicationDTO toDto(Adjudication adjudication);

    @Mapping(source = "reouvertureId", target = "reouverture")
    @Mapping(source = "rachatId", target = "rachat")
    @Mapping(source = "oncId", target = "onc")
    Adjudication toEntity(AdjudicationDTO adjudicationDTO);

    default Adjudication fromId(Long id) {
        if (id == null) {
            return null;
        }
        Adjudication adjudication = new Adjudication();
        adjudication.setId(id);
        return adjudication;
    }
}
