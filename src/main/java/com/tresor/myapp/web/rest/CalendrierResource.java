package com.tresor.myapp.web.rest;

import com.tresor.myapp.service.CalendrierService;
import com.tresor.myapp.web.rest.errors.BadRequestAlertException;
import com.tresor.myapp.service.dto.CalendrierDTO;

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
 * REST controller for managing {@link com.tresor.myapp.domain.Calendrier}.
 */
@RestController
@RequestMapping("/api")
public class CalendrierResource {

    private final Logger log = LoggerFactory.getLogger(CalendrierResource.class);

    private static final String ENTITY_NAME = "calendrier";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CalendrierService calendrierService;

    public CalendrierResource(CalendrierService calendrierService) {
        this.calendrierService = calendrierService;
    }

    /**
     * {@code POST  /calendriers} : Create a new calendrier.
     *
     * @param calendrierDTO the calendrierDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new calendrierDTO, or with status {@code 400 (Bad Request)} if the calendrier has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/calendriers")
    public ResponseEntity<CalendrierDTO> createCalendrier(@Valid @RequestBody CalendrierDTO calendrierDTO) throws URISyntaxException {
        log.debug("REST request to save Calendrier : {}", calendrierDTO);
        if (calendrierDTO.getId() != null) {
            throw new BadRequestAlertException("A new calendrier cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CalendrierDTO result = calendrierService.save(calendrierDTO);
        return ResponseEntity.created(new URI("/api/calendriers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /calendriers} : Updates an existing calendrier.
     *
     * @param calendrierDTO the calendrierDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated calendrierDTO,
     * or with status {@code 400 (Bad Request)} if the calendrierDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the calendrierDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/calendriers")
    public ResponseEntity<CalendrierDTO> updateCalendrier(@Valid @RequestBody CalendrierDTO calendrierDTO) throws URISyntaxException {
        log.debug("REST request to update Calendrier : {}", calendrierDTO);
        if (calendrierDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CalendrierDTO result = calendrierService.save(calendrierDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, calendrierDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /calendriers} : get all the calendriers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of calendriers in body.
     */
    @GetMapping("/calendriers")
    public List<CalendrierDTO> getAllCalendriers() {
        log.debug("REST request to get all Calendriers");
        return calendrierService.findAll();
    }

    /**
     * {@code GET  /calendriers/:id} : get the "id" calendrier.
     *
     * @param id the id of the calendrierDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the calendrierDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/calendriers/{id}")
    public ResponseEntity<CalendrierDTO> getCalendrier(@PathVariable Long id) {
        log.debug("REST request to get Calendrier : {}", id);
        Optional<CalendrierDTO> calendrierDTO = calendrierService.findOne(id);
        return ResponseUtil.wrapOrNotFound(calendrierDTO);
    }

    /**
     * {@code DELETE  /calendriers/:id} : delete the "id" calendrier.
     *
     * @param id the id of the calendrierDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/calendriers/{id}")
    public ResponseEntity<Void> deleteCalendrier(@PathVariable Long id) {
        log.debug("REST request to delete Calendrier : {}", id);
        calendrierService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
