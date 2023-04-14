package com.tresor.myapp.service;

import com.tresor.myapp.service.dto.ResultatDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.tresor.myapp.domain.Resultat}.
 */
public interface ResultatService {

    /**
     * Save a resultat.
     *
     * @param resultatDTO the entity to save.
     * @return the persisted entity.
     */
    ResultatDTO save(ResultatDTO resultatDTO);

    /**
     * Get all the resultats.
     *
     * @return the list of entities.
     */
    List<ResultatDTO> findAll();


    /**
     * Get the "id" resultat.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ResultatDTO> findOne(Long id);

    /**
     * Delete the "id" resultat.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
