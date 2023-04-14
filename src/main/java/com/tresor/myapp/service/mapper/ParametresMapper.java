package com.tresor.myapp.service.mapper;


import com.tresor.myapp.domain.*;
import com.tresor.myapp.service.dto.ParametresDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Parametres} and its DTO {@link ParametresDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ParametresMapper extends EntityMapper<ParametresDTO, Parametres> {



    default Parametres fromId(Long id) {
        if (id == null) {
            return null;
        }
        Parametres parametres = new Parametres();
        parametres.setId(id);
        return parametres;
    }
}
