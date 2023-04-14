package com.tresor.myapp.service;

import com.tresor.myapp.service.dto.RachatDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.tresor.myapp.domain.Rachat}.
 */
public interface RachatService {

    /**
     * Save a rachat.
     *
     * @param rachatDTO the entity to save.
     * @return the persisted entity.
     */
    RachatDTO save(RachatDTO rachatDTO);

    /**
     * Get all the rachats.
     *
     * @return the list of entities.
     */
    List<RachatDTO> findAll();


    /**
     * Get the "id" rachat.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RachatDTO> findOne(Long id);

    /**
     * Delete the "id" rachat.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
