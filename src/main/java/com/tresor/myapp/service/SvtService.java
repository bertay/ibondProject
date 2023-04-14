package com.tresor.myapp.service;

import com.tresor.myapp.service.dto.SvtDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.tresor.myapp.domain.Svt}.
 */
public interface SvtService {

    /**
     * Save a svt.
     *
     * @param svtDTO the entity to save.
     * @return the persisted entity.
     */
    SvtDTO save(SvtDTO svtDTO);

    /**
     * Get all the svts.
     *
     * @return the list of entities.
     */
    List<SvtDTO> findAll();


    /**
     * Get the "id" svt.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SvtDTO> findOne(Long id);

    /**
     * Delete the "id" svt.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
