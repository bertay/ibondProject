package com.tresor.myapp.service;

import com.tresor.myapp.service.dto.EmissionDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.tresor.myapp.domain.Emission}.
 */
public interface EmissionService {

    /**
     * Save a emission.
     *
     * @param emissionDTO the entity to save.
     * @return the persisted entity.
     */
    EmissionDTO save(EmissionDTO emissionDTO);

    /**
     * Get all the emissions.
     *
     * @return the list of entities.
     */
    List<EmissionDTO> findAll();


    /**
     * Get the "id" emission.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EmissionDTO> findOne(Long id);

    /**
     * Delete the "id" emission.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
