package com.tresor.myapp.service;

import com.tresor.myapp.service.dto.MembreSyndicatDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.tresor.myapp.domain.MembreSyndicat}.
 */
public interface MembreSyndicatService {

    /**
     * Save a membreSyndicat.
     *
     * @param membreSyndicatDTO the entity to save.
     * @return the persisted entity.
     */
    MembreSyndicatDTO save(MembreSyndicatDTO membreSyndicatDTO);

    /**
     * Get all the membreSyndicats.
     *
     * @return the list of entities.
     */
    List<MembreSyndicatDTO> findAll();


    /**
     * Get the "id" membreSyndicat.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MembreSyndicatDTO> findOne(Long id);

    /**
     * Delete the "id" membreSyndicat.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
