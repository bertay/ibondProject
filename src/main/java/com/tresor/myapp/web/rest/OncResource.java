package com.tresor.myapp.web.rest;

import com.tresor.myapp.service.OncService;
import com.tresor.myapp.web.rest.errors.BadRequestAlertException;
import com.tresor.myapp.service.dto.OncDTO;

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
 * REST controller for managing {@link com.tresor.myapp.domain.Onc}.
 */
@RestController
@RequestMapping("/api")
public class OncResource {

    private final Logger log = LoggerFactory.getLogger(OncResource.class);

    private static final String ENTITY_NAME = "onc";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OncService oncService;

    public OncResource(OncService oncService) {
        this.oncService = oncService;
    }

    /**
     * {@code POST  /oncs} : Create a new onc.
     *
     * @param oncDTO the oncDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new oncDTO, or with status {@code 400 (Bad Request)} if the onc has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/oncs")
    public ResponseEntity<OncDTO> createOnc(@Valid @RequestBody OncDTO oncDTO) throws URISyntaxException {
        log.debug("REST request to save Onc : {}", oncDTO);
        if (oncDTO.getId() != null) {
            throw new BadRequestAlertException("A new onc cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OncDTO result = oncService.save(oncDTO);
        return ResponseEntity.created(new URI("/api/oncs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /oncs} : Updates an existing onc.
     *
     * @param oncDTO the oncDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated oncDTO,
     * or with status {@code 400 (Bad Request)} if the oncDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the oncDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/oncs")
    public ResponseEntity<OncDTO> updateOnc(@Valid @RequestBody OncDTO oncDTO) throws URISyntaxException {
        log.debug("REST request to update Onc : {}", oncDTO);
        if (oncDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OncDTO result = oncService.save(oncDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, oncDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /oncs} : get all the oncs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of oncs in body.
     */
    @GetMapping("/oncs")
    public List<OncDTO> getAllOncs() {
        log.debug("REST request to get all Oncs");
        return oncService.findAll();
    }

    /**
     * {@code GET  /oncs/:id} : get the "id" onc.
     *
     * @param id the id of the oncDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the oncDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/oncs/{id}")
    public ResponseEntity<OncDTO> getOnc(@PathVariable Long id) {
        log.debug("REST request to get Onc : {}", id);
        Optional<OncDTO> oncDTO = oncService.findOne(id);
        return ResponseUtil.wrapOrNotFound(oncDTO);
    }

    /**
     * {@code DELETE  /oncs/:id} : delete the "id" onc.
     *
     * @param id the id of the oncDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/oncs/{id}")
    public ResponseEntity<Void> deleteOnc(@PathVariable Long id) {
        log.debug("REST request to delete Onc : {}", id);
        oncService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
