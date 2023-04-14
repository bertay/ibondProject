package com.tresor.myapp.web.rest;

import com.tresor.myapp.service.ReouvertureService;
import com.tresor.myapp.web.rest.errors.BadRequestAlertException;
import com.tresor.myapp.service.dto.ReouvertureDTO;

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
 * REST controller for managing {@link com.tresor.myapp.domain.Reouverture}.
 */
@RestController
@RequestMapping("/api")
public class ReouvertureResource {

    private final Logger log = LoggerFactory.getLogger(ReouvertureResource.class);

    private static final String ENTITY_NAME = "reouverture";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReouvertureService reouvertureService;

    public ReouvertureResource(ReouvertureService reouvertureService) {
        this.reouvertureService = reouvertureService;
    }

    /**
     * {@code POST  /reouvertures} : Create a new reouverture.
     *
     * @param reouvertureDTO the reouvertureDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reouvertureDTO, or with status {@code 400 (Bad Request)} if the reouverture has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/reouvertures")
    public ResponseEntity<ReouvertureDTO> createReouverture(@Valid @RequestBody ReouvertureDTO reouvertureDTO) throws URISyntaxException {
        log.debug("REST request to save Reouverture : {}", reouvertureDTO);
        if (reouvertureDTO.getId() != null) {
            throw new BadRequestAlertException("A new reouverture cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReouvertureDTO result = reouvertureService.save(reouvertureDTO);
        return ResponseEntity.created(new URI("/api/reouvertures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /reouvertures} : Updates an existing reouverture.
     *
     * @param reouvertureDTO the reouvertureDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reouvertureDTO,
     * or with status {@code 400 (Bad Request)} if the reouvertureDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reouvertureDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/reouvertures")
    public ResponseEntity<ReouvertureDTO> updateReouverture(@Valid @RequestBody ReouvertureDTO reouvertureDTO) throws URISyntaxException {
        log.debug("REST request to update Reouverture : {}", reouvertureDTO);
        if (reouvertureDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReouvertureDTO result = reouvertureService.save(reouvertureDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, reouvertureDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /reouvertures} : get all the reouvertures.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reouvertures in body.
     */
    @GetMapping("/reouvertures")
    public List<ReouvertureDTO> getAllReouvertures() {
        log.debug("REST request to get all Reouvertures");
        return reouvertureService.findAll();
    }

    /**
     * {@code GET  /reouvertures/:id} : get the "id" reouverture.
     *
     * @param id the id of the reouvertureDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reouvertureDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reouvertures/{id}")
    public ResponseEntity<ReouvertureDTO> getReouverture(@PathVariable Long id) {
        log.debug("REST request to get Reouverture : {}", id);
        Optional<ReouvertureDTO> reouvertureDTO = reouvertureService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reouvertureDTO);
    }

    /**
     * {@code DELETE  /reouvertures/:id} : delete the "id" reouverture.
     *
     * @param id the id of the reouvertureDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/reouvertures/{id}")
    public ResponseEntity<Void> deleteReouverture(@PathVariable Long id) {
        log.debug("REST request to delete Reouverture : {}", id);
        reouvertureService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
