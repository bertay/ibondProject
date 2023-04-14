package com.tresor.myapp.service;

import com.tresor.myapp.service.dto.CalendrierDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.tresor.myapp.domain.Calendrier}.
 */
public interface CalendrierService {

    /**
     * Save a calendrier.
     *
     * @param calendrierDTO the entity to save.
     * @return the persisted entity.
     */
    CalendrierDTO save(CalendrierDTO calendrierDTO);

    /**
     * Get all the calendriers.
     *
     * @return the list of entities.
     */
    List<CalendrierDTO> findAll();


    /**
     * Get the "id" calendrier.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CalendrierDTO> findOne(Long id);

    /**
     * Delete the "id" calendrier.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
