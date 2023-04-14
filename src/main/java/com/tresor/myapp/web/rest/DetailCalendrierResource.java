package com.tresor.myapp.web.rest;

import com.tresor.myapp.service.DetailCalendrierService;
import com.tresor.myapp.web.rest.errors.BadRequestAlertException;
import com.tresor.myapp.service.dto.DetailCalendrierDTO;

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
 * REST controller for managing {@link com.tresor.myapp.domain.DetailCalendrier}.
 */
@RestController
@RequestMapping("/api")
public class DetailCalendrierResource {

    private final Logger log = LoggerFactory.getLogger(DetailCalendrierResource.class);

    private static final String ENTITY_NAME = "detailCalendrier";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DetailCalendrierService detailCalendrierService;

    public DetailCalendrierResource(DetailCalendrierService detailCalendrierService) {
        this.detailCalendrierService = detailCalendrierService;
    }

    /**
     * {@code POST  /detail-calendriers} : Create a new detailCalendrier.
     *
     * @param detailCalendrierDTO the detailCalendrierDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new detailCalendrierDTO, or with status {@code 400 (Bad Request)} if the detailCalendrier has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/detail-calendriers")
    public ResponseEntity<DetailCalendrierDTO> createDetailCalendrier(@Valid @RequestBody DetailCalendrierDTO detailCalendrierDTO) throws URISyntaxException {
        log.debug("REST request to save DetailCalendrier : {}", detailCalendrierDTO);
        if (detailCalendrierDTO.getId() != null) {
            throw new BadRequestAlertException("A new detailCalendrier cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DetailCalendrierDTO result = detailCalendrierService.save(detailCalendrierDTO);
        return ResponseEntity.created(new URI("/api/detail-calendriers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /detail-calendriers} : Updates an existing detailCalendrier.
     *
     * @param detailCalendrierDTO the detailCalendrierDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated detailCalendrierDTO,
     * or with status {@code 400 (Bad Request)} if the detailCalendrierDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the detailCalendrierDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/detail-calendriers")
    public ResponseEntity<DetailCalendrierDTO> updateDetailCalendrier(@Valid @RequestBody DetailCalendrierDTO detailCalendrierDTO) throws URISyntaxException {
        log.debug("REST request to update DetailCalendrier : {}", detailCalendrierDTO);
        if (detailCalendrierDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DetailCalendrierDTO result = detailCalendrierService.save(detailCalendrierDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, detailCalendrierDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /detail-calendriers} : get all the detailCalendriers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of detailCalendriers in body.
     */
    @GetMapping("/detail-calendriers")
    public List<DetailCalendrierDTO> getAllDetailCalendriers() {
        log.debug("REST request to get all DetailCalendriers");
        return detailCalendrierService.findAll();
    }

    /**
     * {@code GET  /detail-calendriers/:id} : get the "id" detailCalendrier.
     *
     * @param id the id of the detailCalendrierDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the detailCalendrierDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/detail-calendriers/{id}")
    public ResponseEntity<DetailCalendrierDTO> getDetailCalendrier(@PathVariable Long id) {
        log.debug("REST request to get DetailCalendrier : {}", id);
        Optional<DetailCalendrierDTO> detailCalendrierDTO = detailCalendrierService.findOne(id);
        return ResponseUtil.wrapOrNotFound(detailCalendrierDTO);
    }

    /**
     * {@code DELETE  /detail-calendriers/:id} : delete the "id" detailCalendrier.
     *
     * @param id the id of the detailCalendrierDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/detail-calendriers/{id}")
    public ResponseEntity<Void> deleteDetailCalendrier(@PathVariable Long id) {
        log.debug("REST request to delete DetailCalendrier : {}", id);
        detailCalendrierService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
