package com.tresor.myapp.service.mapper;


import com.tresor.myapp.domain.*;
import com.tresor.myapp.service.dto.NatureTitreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NatureTitre} and its DTO {@link NatureTitreDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NatureTitreMapper extends EntityMapper<NatureTitreDTO, NatureTitre> {


    @Mapping(target = "emissions", ignore = true)
    @Mapping(target = "removeEmission", ignore = true)
    NatureTitre toEntity(NatureTitreDTO natureTitreDTO);

    default NatureTitre fromId(Long id) {
        if (id == null) {
            return null;
        }
        NatureTitre natureTitre = new NatureTitre();
        natureTitre.setId(id);
        return natureTitre;
    }
}
