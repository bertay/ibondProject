package com.tresor.myapp.web.rest;

import com.tresor.myapp.service.AvisEmissionService;
import com.tresor.myapp.web.rest.errors.BadRequestAlertException;
import com.tresor.myapp.service.dto.AvisEmissionDTO;

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
 * REST controller for managing {@link com.tresor.myapp.domain.AvisEmission}.
 */
@RestController
@RequestMapping("/api")
public class AvisEmissionResource {

    private final Logger log = LoggerFactory.getLogger(AvisEmissionResource.class);

    private static final String ENTITY_NAME = "avisEmission";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AvisEmissionService avisEmissionService;

    public AvisEmissionResource(AvisEmissionService avisEmissionService) {
        this.avisEmissionService = avisEmissionService;
    }

    /**
     * {@code POST  /avis-emissions} : Create a new avisEmission.
     *
     * @param avisEmissionDTO the avisEmissionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new avisEmissionDTO, or with status {@code 400 (Bad Request)} if the avisEmission has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/avis-emissions")
    public ResponseEntity<AvisEmissionDTO> createAvisEmission(@Valid @RequestBody AvisEmissionDTO avisEmissionDTO) throws URISyntaxException {
        log.debug("REST request to save AvisEmission : {}", avisEmissionDTO);
        if (avisEmissionDTO.getId() != null) {
            throw new BadRequestAlertException("A new avisEmission cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AvisEmissionDTO result = avisEmissionService.save(avisEmissionDTO);
        return ResponseEntity.created(new URI("/api/avis-emissions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /avis-emissions} : Updates an existing avisEmission.
     *
     * @param avisEmissionDTO the avisEmissionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated avisEmissionDTO,
     * or with status {@code 400 (Bad Request)} if the avisEmissionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the avisEmissionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/avis-emissions")
    public ResponseEntity<AvisEmissionDTO> updateAvisEmission(@Valid @RequestBody AvisEmissionDTO avisEmissionDTO) throws URISyntaxException {
        log.debug("REST request to update AvisEmission : {}", avisEmissionDTO);
        if (avisEmissionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AvisEmissionDTO result = avisEmissionService.save(avisEmissionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, avisEmissionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /avis-emissions} : get all the avisEmissions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of avisEmissions in body.
     */
    @GetMapping("/avis-emissions")
    public List<AvisEmissionDTO> getAllAvisEmissions() {
        log.debug("REST request to get all AvisEmissions");
        return avisEmissionService.findAll();
    }

    /**
     * {@code GET  /avis-emissions/:id} : get the "id" avisEmission.
     *
     * @param id the id of the avisEmissionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the avisEmissionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/avis-emissions/{id}")
    public ResponseEntity<AvisEmissionDTO> getAvisEmission(@PathVariable Long id) {
        log.debug("REST request to get AvisEmission : {}", id);
        Optional<AvisEmissionDTO> avisEmissionDTO = avisEmissionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(avisEmissionDTO);
    }

    /**
     * {@code DELETE  /avis-emissions/:id} : delete the "id" avisEmission.
     *
     * @param id the id of the avisEmissionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/avis-emissions/{id}")
    public ResponseEntity<Void> deleteAvisEmission(@PathVariable Long id) {
        log.debug("REST request to delete AvisEmission : {}", id);
        avisEmissionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
