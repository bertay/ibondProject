package com.tresor.myapp.service;

import com.tresor.myapp.service.dto.AdjudicationDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.tresor.myapp.domain.Adjudication}.
 */
public interface AdjudicationService {

    /**
     * Save a adjudication.
     *
     * @param adjudicationDTO the entity to save.
     * @return the persisted entity.
     */
    AdjudicationDTO save(AdjudicationDTO adjudicationDTO);

    /**
     * Get all the adjudications.
     *
     * @return the list of entities.
     */
    List<AdjudicationDTO> findAll();


    /**
     * Get the "id" adjudication.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AdjudicationDTO> findOne(Long id);

    /**
     * Delete the "id" adjudication.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
