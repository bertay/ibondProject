package com.tresor.myapp.service;

import com.tresor.myapp.service.dto.ReouvertureDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.tresor.myapp.domain.Reouverture}.
 */
public interface ReouvertureService {

    /**
     * Save a reouverture.
     *
     * @param reouvertureDTO the entity to save.
     * @return the persisted entity.
     */
    ReouvertureDTO save(ReouvertureDTO reouvertureDTO);

    /**
     * Get all the reouvertures.
     *
     * @return the list of entities.
     */
    List<ReouvertureDTO> findAll();


    /**
     * Get the "id" reouverture.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ReouvertureDTO> findOne(Long id);

    /**
     * Delete the "id" reouverture.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
