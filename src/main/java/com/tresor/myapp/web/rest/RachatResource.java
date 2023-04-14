package com.tresor.myapp.web.rest;

import com.tresor.myapp.service.RachatService;
import com.tresor.myapp.web.rest.errors.BadRequestAlertException;
import com.tresor.myapp.service.dto.RachatDTO;

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
 * REST controller for managing {@link com.tresor.myapp.domain.Rachat}.
 */
@RestController
@RequestMapping("/api")
public class RachatResource {

    private final Logger log = LoggerFactory.getLogger(RachatResource.class);

    private static final String ENTITY_NAME = "rachat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RachatService rachatService;

    public RachatResource(RachatService rachatService) {
        this.rachatService = rachatService;
    }

    /**
     * {@code POST  /rachats} : Create a new rachat.
     *
     * @param rachatDTO the rachatDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rachatDTO, or with status {@code 400 (Bad Request)} if the rachat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rachats")
    public ResponseEntity<RachatDTO> createRachat(@Valid @RequestBody RachatDTO rachatDTO) throws URISyntaxException {
        log.debug("REST request to save Rachat : {}", rachatDTO);
        if (rachatDTO.getId() != null) {
            throw new BadRequestAlertException("A new rachat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RachatDTO result = rachatService.save(rachatDTO);
        return ResponseEntity.created(new URI("/api/rachats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /rachats} : Updates an existing rachat.
     *
     * @param rachatDTO the rachatDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rachatDTO,
     * or with status {@code 400 (Bad Request)} if the rachatDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rachatDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rachats")
    public ResponseEntity<RachatDTO> updateRachat(@Valid @RequestBody RachatDTO rachatDTO) throws URISyntaxException {
        log.debug("REST request to update Rachat : {}", rachatDTO);
        if (rachatDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RachatDTO result = rachatService.save(rachatDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rachatDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /rachats} : get all the rachats.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rachats in body.
     */
    @GetMapping("/rachats")
    public List<RachatDTO> getAllRachats() {
        log.debug("REST request to get all Rachats");
        return rachatService.findAll();
    }

    /**
     * {@code GET  /rachats/:id} : get the "id" rachat.
     *
     * @param id the id of the rachatDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rachatDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rachats/{id}")
    public ResponseEntity<RachatDTO> getRachat(@PathVariable Long id) {
        log.debug("REST request to get Rachat : {}", id);
        Optional<RachatDTO> rachatDTO = rachatService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rachatDTO);
    }

    /**
     * {@code DELETE  /rachats/:id} : delete the "id" rachat.
     *
     * @param id the id of the rachatDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rachats/{id}")
    public ResponseEntity<Void> deleteRachat(@PathVariable Long id) {
        log.debug("REST request to delete Rachat : {}", id);
        rachatService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
