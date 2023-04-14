package com.tresor.myapp.service;

import com.tresor.myapp.service.dto.ParametresDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.tresor.myapp.domain.Parametres}.
 */
public interface ParametresService {

    /**
     * Save a parametres.
     *
     * @param parametresDTO the entity to save.
     * @return the persisted entity.
     */
    ParametresDTO save(ParametresDTO parametresDTO);

    /**
     * Get all the parametres.
     *
     * @return the list of entities.
     */
    List<ParametresDTO> findAll();


    /**
     * Get the "id" parametres.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ParametresDTO> findOne(Long id);

    /**
     * Delete the "id" parametres.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
