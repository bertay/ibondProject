package com.tresor.myapp.web.rest;

import com.tresor.myapp.service.SvtService;
import com.tresor.myapp.web.rest.errors.BadRequestAlertException;
import com.tresor.myapp.service.dto.SvtDTO;

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
 * REST controller for managing {@link com.tresor.myapp.domain.Svt}.
 */
@RestController
@RequestMapping("/api")
public class SvtResource {

    private final Logger log = LoggerFactory.getLogger(SvtResource.class);

    private static final String ENTITY_NAME = "svt";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SvtService svtService;

    public SvtResource(SvtService svtService) {
        this.svtService = svtService;
    }

    /**
     * {@code POST  /svts} : Create a new svt.
     *
     * @param svtDTO the svtDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new svtDTO, or with status {@code 400 (Bad Request)} if the svt has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/svts")
    public ResponseEntity<SvtDTO> createSvt(@Valid @RequestBody SvtDTO svtDTO) throws URISyntaxException {
        log.debug("REST request to save Svt : {}", svtDTO);
        if (svtDTO.getId() != null) {
            throw new BadRequestAlertException("A new svt cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SvtDTO result = svtService.save(svtDTO);
        return ResponseEntity.created(new URI("/api/svts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /svts} : Updates an existing svt.
     *
     * @param svtDTO the svtDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated svtDTO,
     * or with status {@code 400 (Bad Request)} if the svtDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the svtDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/svts")
    public ResponseEntity<SvtDTO> updateSvt(@Valid @RequestBody SvtDTO svtDTO) throws URISyntaxException {
        log.debug("REST request to update Svt : {}", svtDTO);
        if (svtDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SvtDTO result = svtService.save(svtDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, svtDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /svts} : get all the svts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of svts in body.
     */
    @GetMapping("/svts")
    public List<SvtDTO> getAllSvts() {
        log.debug("REST request to get all Svts");
        return svtService.findAll();
    }

    /**
     * {@code GET  /svts/:id} : get the "id" svt.
     *
     * @param id the id of the svtDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the svtDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/svts/{id}")
    public ResponseEntity<SvtDTO> getSvt(@PathVariable Long id) {
        log.debug("REST request to get Svt : {}", id);
        Optional<SvtDTO> svtDTO = svtService.findOne(id);
        return ResponseUtil.wrapOrNotFound(svtDTO);
    }

    /**
     * {@code DELETE  /svts/:id} : delete the "id" svt.
     *
     * @param id the id of the svtDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/svts/{id}")
    public ResponseEntity<Void> deleteSvt(@PathVariable Long id) {
        log.debug("REST request to delete Svt : {}", id);
        svtService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
