package com.tresor.myapp.service.mapper;


import com.tresor.myapp.domain.*;
import com.tresor.myapp.service.dto.DetailCalendrierDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DetailCalendrier} and its DTO {@link DetailCalendrierDTO}.
 */
@Mapper(componentModel = "spring", uses = {CalendrierMapper.class})
public interface DetailCalendrierMapper extends EntityMapper<DetailCalendrierDTO, DetailCalendrier> {

    @Mapping(source = "calendrier.id", target = "calendrierId")
    DetailCalendrierDTO toDto(DetailCalendrier detailCalendrier);

    @Mapping(source = "calendrierId", target = "calendrier")
    DetailCalendrier toEntity(DetailCalendrierDTO detailCalendrierDTO);

    default DetailCalendrier fromId(Long id) {
        if (id == null) {
            return null;
        }
        DetailCalendrier detailCalendrier = new DetailCalendrier();
        detailCalendrier.setId(id);
        return detailCalendrier;
    }
}
