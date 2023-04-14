package com.tresor.myapp.web.rest;

import com.tresor.myapp.service.SoumissionService;
import com.tresor.myapp.web.rest.errors.BadRequestAlertException;
import com.tresor.myapp.service.dto.SoumissionDTO;

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
 * REST controller for managing {@link com.tresor.myapp.domain.Soumission}.
 */
@RestController
@RequestMapping("/api")
public class SoumissionResource {

    private final Logger log = LoggerFactory.getLogger(SoumissionResource.class);

    private static final String ENTITY_NAME = "soumission";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SoumissionService soumissionService;

    public SoumissionResource(SoumissionService soumissionService) {
        this.soumissionService = soumissionService;
    }

    /**
     * {@code POST  /soumissions} : Create a new soumission.
     *
     * @param soumissionDTO the soumissionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new soumissionDTO, or with status {@code 400 (Bad Request)} if the soumission has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/soumissions")
    public ResponseEntity<SoumissionDTO> createSoumission(@Valid @RequestBody SoumissionDTO soumissionDTO) throws URISyntaxException {
        log.debug("REST request to save Soumission : {}", soumissionDTO);
        if (soumissionDTO.getId() != null) {
            throw new BadRequestAlertException("A new soumission cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SoumissionDTO result = soumissionService.save(soumissionDTO);
        return ResponseEntity.created(new URI("/api/soumissions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /soumissions} : Updates an existing soumission.
     *
     * @param soumissionDTO the soumissionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated soumissionDTO,
     * or with status {@code 400 (Bad Request)} if the soumissionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the soumissionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/soumissions")
    public ResponseEntity<SoumissionDTO> updateSoumission(@Valid @RequestBody SoumissionDTO soumissionDTO) throws URISyntaxException {
        log.debug("REST request to update Soumission : {}", soumissionDTO);
        if (soumissionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SoumissionDTO result = soumissionService.save(soumissionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, soumissionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /soumissions} : get all the soumissions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of soumissions in body.
     */
    @GetMapping("/soumissions")
    public List<SoumissionDTO> getAllSoumissions() {
        log.debug("REST request to get all Soumissions");
        return soumissionService.findAll();
    }

    /**
     * {@code GET  /soumissions/:id} : get the "id" soumission.
     *
     * @param id the id of the soumissionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the soumissionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/soumissions/{id}")
    public ResponseEntity<SoumissionDTO> getSoumission(@PathVariable Long id) {
        log.debug("REST request to get Soumission : {}", id);
        Optional<SoumissionDTO> soumissionDTO = soumissionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(soumissionDTO);
    }

    /**
     * {@code DELETE  /soumissions/:id} : delete the "id" soumission.
     *
     * @param id the id of the soumissionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/soumissions/{id}")
    public ResponseEntity<Void> deleteSoumission(@PathVariable Long id) {
        log.debug("REST request to delete Soumission : {}", id);
        soumissionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
