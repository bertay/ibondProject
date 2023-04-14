package com.tresor.myapp.service;

import com.tresor.myapp.service.dto.ClassementDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.tresor.myapp.domain.Classement}.
 */
public interface ClassementService {

    /**
     * Save a classement.
     *
     * @param classementDTO the entity to save.
     * @return the persisted entity.
     */
    ClassementDTO save(ClassementDTO classementDTO);

    /**
     * Get all the classements.
     *
     * @return the list of entities.
     */
    List<ClassementDTO> findAll();


    /**
     * Get the "id" classement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClassementDTO> findOne(Long id);

    /**
     * Delete the "id" classement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
