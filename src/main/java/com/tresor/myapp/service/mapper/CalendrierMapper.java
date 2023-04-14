package com.tresor.myapp.service.mapper;


import com.tresor.myapp.domain.*;
import com.tresor.myapp.service.dto.CalendrierDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Calendrier} and its DTO {@link CalendrierDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CalendrierMapper extends EntityMapper<CalendrierDTO, Calendrier> {


    @Mapping(target = "detailCalendriers", ignore = true)
    @Mapping(target = "removeDetailCalendrier", ignore = true)
    Calendrier toEntity(CalendrierDTO calendrierDTO);

    default Calendrier fromId(Long id) {
        if (id == null) {
            return null;
        }
        Calendrier calendrier = new Calendrier();
        calendrier.setId(id);
        return calendrier;
    }
}
