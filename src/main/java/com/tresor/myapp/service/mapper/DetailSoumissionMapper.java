package com.tresor.myapp.service.mapper;


import com.tresor.myapp.domain.*;
import com.tresor.myapp.service.dto.DetailSoumissionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DetailSoumission} and its DTO {@link DetailSoumissionDTO}.
 */
@Mapper(componentModel = "spring", uses = {SoumissionMapper.class})
public interface DetailSoumissionMapper extends EntityMapper<DetailSoumissionDTO, DetailSoumission> {

    @Mapping(source = "soumission.id", target = "soumissionId")
    DetailSoumissionDTO toDto(DetailSoumission detailSoumission);

    @Mapping(target = "classements", ignore = true)
    @Mapping(target = "removeClassement", ignore = true)
    @Mapping(source = "soumissionId", target = "soumission")
    DetailSoumission toEntity(DetailSoumissionDTO detailSoumissionDTO);

    default DetailSoumission fromId(Long id) {
        if (id == null) {
            return null;
        }
        DetailSoumission detailSoumission = new DetailSoumission();
        detailSoumission.setId(id);
        return detailSoumission;
    }
}
