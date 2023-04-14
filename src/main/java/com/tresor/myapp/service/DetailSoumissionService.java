package com.tresor.myapp.service;

import com.tresor.myapp.service.dto.DetailSoumissionDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.tresor.myapp.domain.DetailSoumission}.
 */
public interface DetailSoumissionService {

    /**
     * Save a detailSoumission.
     *
     * @param detailSoumissionDTO the entity to save.
     * @return the persisted entity.
     */
    DetailSoumissionDTO save(DetailSoumissionDTO detailSoumissionDTO);

    /**
     * Get all the detailSoumissions.
     *
     * @return the list of entities.
     */
    List<DetailSoumissionDTO> findAll();


    /**
     * Get the "id" detailSoumission.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DetailSoumissionDTO> findOne(Long id);

    /**
     * Delete the "id" detailSoumission.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
