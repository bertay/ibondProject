package com.tresor.myapp.service;

import com.tresor.myapp.service.dto.DetailCalendrierDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.tresor.myapp.domain.DetailCalendrier}.
 */
public interface DetailCalendrierService {

    /**
     * Save a detailCalendrier.
     *
     * @param detailCalendrierDTO the entity to save.
     * @return the persisted entity.
     */
    DetailCalendrierDTO save(DetailCalendrierDTO detailCalendrierDTO);

    /**
     * Get all the detailCalendriers.
     *
     * @return the list of entities.
     */
    List<DetailCalendrierDTO> findAll();


    /**
     * Get the "id" detailCalendrier.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DetailCalendrierDTO> findOne(Long id);

    /**
     * Delete the "id" detailCalendrier.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
