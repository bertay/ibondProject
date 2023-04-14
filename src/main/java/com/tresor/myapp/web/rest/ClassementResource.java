package com.tresor.myapp.web.rest;

import com.tresor.myapp.service.ClassementService;
import com.tresor.myapp.web.rest.errors.BadRequestAlertException;
import com.tresor.myapp.service.dto.ClassementDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.tresor.myapp.domain.Classement}.
 */
@RestController
@RequestMapping("/api")
public class ClassementResource {

    private final Logger log = LoggerFactory.getLogger(ClassementResource.class);

    private static final String ENTITY_NAME = "classement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClassementService classementService;

    public ClassementResource(ClassementService classementService) {
        this.classementService = classementService;
    }

    /**
     * {@code POST  /classements} : Create a new classement.
     *
     * @param classementDTO the classementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new classementDTO, or with status {@code 400 (Bad Request)} if the classement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/classements")
    public ResponseEntity<ClassementDTO> createClassement(@Valid @RequestBody ClassementDTO classementDTO) throws URISyntaxException {
        log.debug("REST request to save Classement : {}", classementDTO);
        if (classementDTO.getId() != null) {
            throw new BadRequestAlertException("A new classement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClassementDTO result = classementService.save(classementDTO);
        return ResponseEntity.created(new URI("/api/classements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /classements} : Updates an existing classement.
     *
     * @param classementDTO the classementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated classementDTO,
     * or with status {@code 400 (Bad Request)} if the classementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the classementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/classements")
    public ResponseEntity<ClassementDTO> updateClassement(@Valid @RequestBody ClassementDTO classementDTO) throws URISyntaxException {
        log.debug("REST request to update Classement : {}", classementDTO);
        if (classementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ClassementDTO result = classementService.save(classementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, classementDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /classements} : get all the classements.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of classements in body.
     */
    @GetMapping("/classements")
    public List<ClassementDTO> getAllClassements() {
        log.debug("REST request to get all Classements");
        return classementService.findAll();
    }

    /**
     * {@code GET  /classements/:id} : get the "id" classement.
     *
     * @param id the id of the classementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the classementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/classements/{id}")
    public ResponseEntity<ClassementDTO> getClassement(@PathVariable Long id) {
        log.debug("REST request to get Classement : {}", id);
        Optional<ClassementDTO> classementDTO = classementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(classementDTO);
    }

    /**
     * {@code DELETE  /classements/:id} : delete the "id" classement.
     *
     * @param id the id of the classementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/classements/{id}")
    public ResponseEntity<Void> deleteClassement(@PathVariable Long id) {
        log.debug("REST request to delete Classement : {}", id);
        classementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
