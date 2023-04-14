package com.tresor.myapp.web.rest;

import com.tresor.myapp.service.AdjudicationService;
import com.tresor.myapp.web.rest.errors.BadRequestAlertException;
import com.tresor.myapp.service.dto.AdjudicationDTO;

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
 * REST controller for managing {@link com.tresor.myapp.domain.Adjudication}.
 */
@RestController
@RequestMapping("/api")
public class AdjudicationResource {

    private final Logger log = LoggerFactory.getLogger(AdjudicationResource.class);

    private static final String ENTITY_NAME = "adjudication";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdjudicationService adjudicationService;

    public AdjudicationResource(AdjudicationService adjudicationService) {
        this.adjudicationService = adjudicationService;
    }

    /**
     * {@code POST  /adjudications} : Create a new adjudication.
     *
     * @param adjudicationDTO the adjudicationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new adjudicationDTO, or with status {@code 400 (Bad Request)} if the adjudication has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/adjudications")
    public ResponseEntity<AdjudicationDTO> createAdjudication(@Valid @RequestBody AdjudicationDTO adjudicationDTO) throws URISyntaxException {
        log.debug("REST request to save Adjudication : {}", adjudicationDTO);
        if (adjudicationDTO.getId() != null) {
            throw new BadRequestAlertException("A new adjudication cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdjudicationDTO result = adjudicationService.save(adjudicationDTO);
        return ResponseEntity.created(new URI("/api/adjudications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /adjudications} : Updates an existing adjudication.
     *
     * @param adjudicationDTO the adjudicationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated adjudicationDTO,
     * or with status {@code 400 (Bad Request)} if the adjudicationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the adjudicationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/adjudications")
    public ResponseEntity<AdjudicationDTO> updateAdjudication(@Valid @RequestBody AdjudicationDTO adjudicationDTO) throws URISyntaxException {
        log.debug("REST request to update Adjudication : {}", adjudicationDTO);
        if (adjudicationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdjudicationDTO result = adjudicationService.save(adjudicationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, adjudicationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /adjudications} : get all the adjudications.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of adjudications in body.
     */
    @GetMapping("/adjudications")
    public List<AdjudicationDTO> getAllAdjudications() {
        log.debug("REST request to get all Adjudications");
        return adjudicationService.findAll();
    }

    /**
     * {@code GET  /adjudications/:id} : get the "id" adjudication.
     *
     * @param id the id of the adjudicationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the adjudicationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/adjudications/{id}")
    public ResponseEntity<AdjudicationDTO> getAdjudication(@PathVariable Long id) {
        log.debug("REST request to get Adjudication : {}", id);
        Optional<AdjudicationDTO> adjudicationDTO = adjudicationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(adjudicationDTO);
    }

    /**
     * {@code DELETE  /adjudications/:id} : delete the "id" adjudication.
     *
     * @param id the id of the adjudicationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/adjudications/{id}")
    public ResponseEntity<Void> deleteAdjudication(@PathVariable Long id) {
        log.debug("REST request to delete Adjudication : {}", id);
        adjudicationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
