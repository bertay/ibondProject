package com.tresor.myapp.web.rest;

import com.tresor.myapp.service.NatureTitreService;
import com.tresor.myapp.web.rest.errors.BadRequestAlertException;
import com.tresor.myapp.service.dto.NatureTitreDTO;

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
 * REST controller for managing {@link com.tresor.myapp.domain.NatureTitre}.
 */
@RestController
@RequestMapping("/api")
public class NatureTitreResource {

    private final Logger log = LoggerFactory.getLogger(NatureTitreResource.class);

    private static final String ENTITY_NAME = "natureTitre";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NatureTitreService natureTitreService;

    public NatureTitreResource(NatureTitreService natureTitreService) {
        this.natureTitreService = natureTitreService;
    }

    /**
     * {@code POST  /nature-titres} : Create a new natureTitre.
     *
     * @param natureTitreDTO the natureTitreDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new natureTitreDTO, or with status {@code 400 (Bad Request)} if the natureTitre has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nature-titres")
    public ResponseEntity<NatureTitreDTO> createNatureTitre(@Valid @RequestBody NatureTitreDTO natureTitreDTO) throws URISyntaxException {
        log.debug("REST request to save NatureTitre : {}", natureTitreDTO);
        if (natureTitreDTO.getId() != null) {
            throw new BadRequestAlertException("A new natureTitre cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NatureTitreDTO result = natureTitreService.save(natureTitreDTO);
        return ResponseEntity.created(new URI("/api/nature-titres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nature-titres} : Updates an existing natureTitre.
     *
     * @param natureTitreDTO the natureTitreDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated natureTitreDTO,
     * or with status {@code 400 (Bad Request)} if the natureTitreDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the natureTitreDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nature-titres")
    public ResponseEntity<NatureTitreDTO> updateNatureTitre(@Valid @RequestBody NatureTitreDTO natureTitreDTO) throws URISyntaxException {
        log.debug("REST request to update NatureTitre : {}", natureTitreDTO);
        if (natureTitreDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NatureTitreDTO result = natureTitreService.save(natureTitreDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, natureTitreDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /nature-titres} : get all the natureTitres.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of natureTitres in body.
     */
    @GetMapping("/nature-titres")
    public List<NatureTitreDTO> getAllNatureTitres() {
        log.debug("REST request to get all NatureTitres");
        return natureTitreService.findAll();
    }

    /**
     * {@code GET  /nature-titres/:id} : get the "id" natureTitre.
     *
     * @param id the id of the natureTitreDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the natureTitreDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nature-titres/{id}")
    public ResponseEntity<NatureTitreDTO> getNatureTitre(@PathVariable Long id) {
        log.debug("REST request to get NatureTitre : {}", id);
        Optional<NatureTitreDTO> natureTitreDTO = natureTitreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(natureTitreDTO);
    }

    /**
     * {@code DELETE  /nature-titres/:id} : delete the "id" natureTitre.
     *
     * @param id the id of the natureTitreDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nature-titres/{id}")
    public ResponseEntity<Void> deleteNatureTitre(@PathVariable Long id) {
        log.debug("REST request to delete NatureTitre : {}", id);
        natureTitreService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
