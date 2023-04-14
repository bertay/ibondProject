package com.tresor.myapp.web.rest;

import com.tresor.myapp.service.MembreSyndicatService;
import com.tresor.myapp.web.rest.errors.BadRequestAlertException;
import com.tresor.myapp.service.dto.MembreSyndicatDTO;

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
 * REST controller for managing {@link com.tresor.myapp.domain.MembreSyndicat}.
 */
@RestController
@RequestMapping("/api")
public class MembreSyndicatResource {

    private final Logger log = LoggerFactory.getLogger(MembreSyndicatResource.class);

    private static final String ENTITY_NAME = "membreSyndicat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MembreSyndicatService membreSyndicatService;

    public MembreSyndicatResource(MembreSyndicatService membreSyndicatService) {
        this.membreSyndicatService = membreSyndicatService;
    }

    /**
     * {@code POST  /membre-syndicats} : Create a new membreSyndicat.
     *
     * @param membreSyndicatDTO the membreSyndicatDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new membreSyndicatDTO, or with status {@code 400 (Bad Request)} if the membreSyndicat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/membre-syndicats")
    public ResponseEntity<MembreSyndicatDTO> createMembreSyndicat(@Valid @RequestBody MembreSyndicatDTO membreSyndicatDTO) throws URISyntaxException {
        log.debug("REST request to save MembreSyndicat : {}", membreSyndicatDTO);
        if (membreSyndicatDTO.getId() != null) {
            throw new BadRequestAlertException("A new membreSyndicat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MembreSyndicatDTO result = membreSyndicatService.save(membreSyndicatDTO);
        return ResponseEntity.created(new URI("/api/membre-syndicats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /membre-syndicats} : Updates an existing membreSyndicat.
     *
     * @param membreSyndicatDTO the membreSyndicatDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated membreSyndicatDTO,
     * or with status {@code 400 (Bad Request)} if the membreSyndicatDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the membreSyndicatDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/membre-syndicats")
    public ResponseEntity<MembreSyndicatDTO> updateMembreSyndicat(@Valid @RequestBody MembreSyndicatDTO membreSyndicatDTO) throws URISyntaxException {
        log.debug("REST request to update MembreSyndicat : {}", membreSyndicatDTO);
        if (membreSyndicatDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MembreSyndicatDTO result = membreSyndicatService.save(membreSyndicatDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, membreSyndicatDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /membre-syndicats} : get all the membreSyndicats.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of membreSyndicats in body.
     */
    @GetMapping("/membre-syndicats")
    public List<MembreSyndicatDTO> getAllMembreSyndicats() {
        log.debug("REST request to get all MembreSyndicats");
        return membreSyndicatService.findAll();
    }

    /**
     * {@code GET  /membre-syndicats/:id} : get the "id" membreSyndicat.
     *
     * @param id the id of the membreSyndicatDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the membreSyndicatDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/membre-syndicats/{id}")
    public ResponseEntity<MembreSyndicatDTO> getMembreSyndicat(@PathVariable Long id) {
        log.debug("REST request to get MembreSyndicat : {}", id);
        Optional<MembreSyndicatDTO> membreSyndicatDTO = membreSyndicatService.findOne(id);
        return ResponseUtil.wrapOrNotFound(membreSyndicatDTO);
    }

    /**
     * {@code DELETE  /membre-syndicats/:id} : delete the "id" membreSyndicat.
     *
     * @param id the id of the membreSyndicatDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/membre-syndicats/{id}")
    public ResponseEntity<Void> deleteMembreSyndicat(@PathVariable Long id) {
        log.debug("REST request to delete MembreSyndicat : {}", id);
        membreSyndicatService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
