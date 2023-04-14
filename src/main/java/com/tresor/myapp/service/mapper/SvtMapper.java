package com.tresor.myapp.service.mapper;


import com.tresor.myapp.domain.*;
import com.tresor.myapp.service.dto.SvtDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Svt} and its DTO {@link SvtDTO}.
 */
@Mapper(componentModel = "spring", uses = {PaysMapper.class})
public interface SvtMapper extends EntityMapper<SvtDTO, Svt> {

    @Mapping(source = "pays.id", target = "paysId")
    SvtDTO toDto(Svt svt);

    @Mapping(source = "paysId", target = "pays")
    Svt toEntity(SvtDTO svtDTO);

    default Svt fromId(Long id) {
        if (id == null) {
            return null;
        }
        Svt svt = new Svt();
        svt.setId(id);
        return svt;
    }
}
