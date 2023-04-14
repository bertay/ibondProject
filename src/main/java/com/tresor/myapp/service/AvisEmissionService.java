package com.tresor.myapp.service;

import com.tresor.myapp.service.dto.AvisEmissionDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.tresor.myapp.domain.AvisEmission}.
 */
public interface AvisEmissionService {

    /**
     * Save a avisEmission.
     *
     * @param avisEmissionDTO the entity to save.
     * @return the persisted entity.
     */
    AvisEmissionDTO save(AvisEmissionDTO avisEmissionDTO);

    /**
     * Get all the avisEmissions.
     *
     * @return the list of entities.
     */
    List<AvisEmissionDTO> findAll();


    /**
     * Get the "id" avisEmission.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AvisEmissionDTO> findOne(Long id);

    /**
     * Delete the "id" avisEmission.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
