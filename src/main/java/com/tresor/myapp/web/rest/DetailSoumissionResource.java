package com.tresor.myapp.web.rest;

import com.tresor.myapp.service.DetailSoumissionService;
import com.tresor.myapp.web.rest.errors.BadRequestAlertException;
import com.tresor.myapp.service.dto.DetailSoumissionDTO;

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
 * REST controller for managing {@link com.tresor.myapp.domain.DetailSoumission}.
 */
@RestController
@RequestMapping("/api")
public class DetailSoumissionResource {

    private final Logger log = LoggerFactory.getLogger(DetailSoumissionResource.class);

    private static final String ENTITY_NAME = "detailSoumission";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DetailSoumissionService detailSoumissionService;

    public DetailSoumissionResource(DetailSoumissionService detailSoumissionService) {
        this.detailSoumissionService = detailSoumissionService;
    }

    /**
     * {@code POST  /detail-soumissions} : Create a new detailSoumission.
     *
     * @param detailSoumissionDTO the detailSoumissionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new detailSoumissionDTO, or with status {@code 400 (Bad Request)} if the detailSoumission has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/detail-soumissions")
    public ResponseEntity<DetailSoumissionDTO> createDetailSoumission(@Valid @RequestBody DetailSoumissionDTO detailSoumissionDTO) throws URISyntaxException {
        log.debug("REST request to save DetailSoumission : {}", detailSoumissionDTO);
        if (detailSoumissionDTO.getId() != null) {
            throw new BadRequestAlertException("A new detailSoumission cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DetailSoumissionDTO result = detailSoumissionService.save(detailSoumissionDTO);
        return ResponseEntity.created(new URI("/api/detail-soumissions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /detail-soumissions} : Updates an existing detailSoumission.
     *
     * @param detailSoumissionDTO the detailSoumissionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated detailSoumissionDTO,
     * or with status {@code 400 (Bad Request)} if the detailSoumissionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the detailSoumissionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/detail-soumissions")
    public ResponseEntity<DetailSoumissionDTO> updateDetailSoumission(@Valid @RequestBody DetailSoumissionDTO detailSoumissionDTO) throws URISyntaxException {
        log.debug("REST request to update DetailSoumission : {}", detailSoumissionDTO);
        if (detailSoumissionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DetailSoumissionDTO result = detailSoumissionService.save(detailSoumissionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, detailSoumissionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /detail-soumissions} : get all the detailSoumissions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of detailSoumissions in body.
     */
    @GetMapping("/detail-soumissions")
    public List<DetailSoumissionDTO> getAllDetailSoumissions() {
        log.debug("REST request to get all DetailSoumissions");
        return detailSoumissionService.findAll();
    }

    /**
     * {@code GET  /detail-soumissions/:id} : get the "id" detailSoumission.
     *
     * @param id the id of the detailSoumissionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the detailSoumissionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/detail-soumissions/{id}")
    public ResponseEntity<DetailSoumissionDTO> getDetailSoumission(@PathVariable Long id) {
        log.debug("REST request to get DetailSoumission : {}", id);
        Optional<DetailSoumissionDTO> detailSoumissionDTO = detailSoumissionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(detailSoumissionDTO);
    }

    /**
     * {@code DELETE  /detail-soumissions/:id} : delete the "id" detailSoumission.
     *
     * @param id the id of the detailSoumissionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/detail-soumissions/{id}")
    public ResponseEntity<Void> deleteDetailSoumission(@PathVariable Long id) {
        log.debug("REST request to delete DetailSoumission : {}", id);
        detailSoumissionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
