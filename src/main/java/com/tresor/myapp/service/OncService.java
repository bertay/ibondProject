package com.tresor.myapp.service;

import com.tresor.myapp.service.dto.OncDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.tresor.myapp.domain.Onc}.
 */
public interface OncService {

    /**
     * Save a onc.
     *
     * @param oncDTO the entity to save.
     * @return the persisted entity.
     */
    OncDTO save(OncDTO oncDTO);

    /**
     * Get all the oncs.
     *
     * @return the list of entities.
     */
    List<OncDTO> findAll();


    /**
     * Get the "id" onc.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OncDTO> findOne(Long id);

    /**
     * Delete the "id" onc.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
