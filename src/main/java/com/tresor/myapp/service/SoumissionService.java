package com.tresor.myapp.service;

import com.tresor.myapp.service.dto.SoumissionDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.tresor.myapp.domain.Soumission}.
 */
public interface SoumissionService {

    /**
     * Save a soumission.
     *
     * @param soumissionDTO the entity to save.
     * @return the persisted entity.
     */
    SoumissionDTO save(SoumissionDTO soumissionDTO);

    /**
     * Get all the soumissions.
     *
     * @return the list of entities.
     */
    List<SoumissionDTO> findAll();


    /**
     * Get the "id" soumission.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SoumissionDTO> findOne(Long id);

    /**
     * Delete the "id" soumission.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
