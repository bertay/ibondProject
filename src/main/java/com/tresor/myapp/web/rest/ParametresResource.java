package com.tresor.myapp.web.rest;

import com.tresor.myapp.service.ParametresService;
import com.tresor.myapp.web.rest.errors.BadRequestAlertException;
import com.tresor.myapp.service.dto.ParametresDTO;

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
 * REST controller for managing {@link com.tresor.myapp.domain.Parametres}.
 */
@RestController
@RequestMapping("/api")
public class ParametresResource {

    private final Logger log = LoggerFactory.getLogger(ParametresResource.class);

    private static final String ENTITY_NAME = "parametres";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ParametresService parametresService;

    public ParametresResource(ParametresService parametresService) {
        this.parametresService = parametresService;
    }

    /**
     * {@code POST  /parametres} : Create a new parametres.
     *
     * @param parametresDTO the parametresDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new parametresDTO, or with status {@code 400 (Bad Request)} if the parametres has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/parametres")
    public ResponseEntity<ParametresDTO> createParametres(@Valid @RequestBody ParametresDTO parametresDTO) throws URISyntaxException {
        log.debug("REST request to save Parametres : {}", parametresDTO);
        if (parametresDTO.getId() != null) {
            throw new BadRequestAlertException("A new parametres cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ParametresDTO result = parametresService.save(parametresDTO);
        return ResponseEntity.created(new URI("/api/parametres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /parametres} : Updates an existing parametres.
     *
     * @param parametresDTO the parametresDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated parametresDTO,
     * or with status {@code 400 (Bad Request)} if the parametresDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the parametresDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/parametres")
    public ResponseEntity<ParametresDTO> updateParametres(@Valid @RequestBody ParametresDTO parametresDTO) throws URISyntaxException {
        log.debug("REST request to update Parametres : {}", parametresDTO);
        if (parametresDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ParametresDTO result = parametresService.save(parametresDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, parametresDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /parametres} : get all the parametres.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of parametres in body.
     */
    @GetMapping("/parametres")
    public List<ParametresDTO> getAllParametres() {
        log.debug("REST request to get all Parametres");
        return parametresService.findAll();
    }

    /**
     * {@code GET  /parametres/:id} : get the "id" parametres.
     *
     * @param id the id of the parametresDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the parametresDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/parametres/{id}")
    public ResponseEntity<ParametresDTO> getParametres(@PathVariable Long id) {
        log.debug("REST request to get Parametres : {}", id);
        Optional<ParametresDTO> parametresDTO = parametresService.findOne(id);
        return ResponseUtil.wrapOrNotFound(parametresDTO);
    }

    /**
     * {@code DELETE  /parametres/:id} : delete the "id" parametres.
     *
     * @param id the id of the parametresDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/parametres/{id}")
    public ResponseEntity<Void> deleteParametres(@PathVariable Long id) {
        log.debug("REST request to delete Parametres : {}", id);
        parametresService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
