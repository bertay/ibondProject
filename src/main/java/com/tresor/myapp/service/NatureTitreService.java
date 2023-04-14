package com.tresor.myapp.service;

import com.tresor.myapp.service.dto.NatureTitreDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.tresor.myapp.domain.NatureTitre}.
 */
public interface NatureTitreService {

    /**
     * Save a natureTitre.
     *
     * @param natureTitreDTO the entity to save.
     * @return the persisted entity.
     */
    NatureTitreDTO save(NatureTitreDTO natureTitreDTO);

    /**
     * Get all the natureTitres.
     *
     * @return the list of entities.
     */
    List<NatureTitreDTO> findAll();


    /**
     * Get the "id" natureTitre.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NatureTitreDTO> findOne(Long id);

    /**
     * Delete the "id" natureTitre.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
