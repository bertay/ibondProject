package com.tresor.myapp.web.rest;

import com.tresor.myapp.IbondgeneApp;
import com.tresor.myapp.domain.Onc;
import com.tresor.myapp.repository.OncRepository;
import com.tresor.myapp.service.OncService;
import com.tresor.myapp.service.dto.OncDTO;
import com.tresor.myapp.service.mapper.OncMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static com.tresor.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link OncResource} REST controller.
 */
@SpringBootTest(classes = IbondgeneApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class OncResourceIT {

    private static final String DEFAULT_OPERATEUR = "AAAAAAAAAA";
    private static final String UPDATED_OPERATEUR = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_OPERATION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_OPERATION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private OncRepository oncRepository;

    @Autowired
    private OncMapper oncMapper;

    @Autowired
    private OncService oncService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOncMockMvc;

    private Onc onc;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Onc createEntity(EntityManager em) {
        Onc onc = new Onc()
            .operateur(DEFAULT_OPERATEUR)
            .dateOperation(DEFAULT_DATE_OPERATION);
        return onc;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Onc createUpdatedEntity(EntityManager em) {
        Onc onc = new Onc()
            .operateur(UPDATED_OPERATEUR)
            .dateOperation(UPDATED_DATE_OPERATION);
        return onc;
    }

    @BeforeEach
    public void initTest() {
        onc = createEntity(em);
    }

    @Test
    @Transactional
    public void createOnc() throws Exception {
        int databaseSizeBeforeCreate = oncRepository.findAll().size();
        // Create the Onc
        OncDTO oncDTO = oncMapper.toDto(onc);
        restOncMockMvc.perform(post("/api/oncs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(oncDTO)))
            .andExpect(status().isCreated());

        // Validate the Onc in the database
        List<Onc> oncList = oncRepository.findAll();
        assertThat(oncList).hasSize(databaseSizeBeforeCreate + 1);
        Onc testOnc = oncList.get(oncList.size() - 1);
        assertThat(testOnc.getOperateur()).isEqualTo(DEFAULT_OPERATEUR);
        assertThat(testOnc.getDateOperation()).isEqualTo(DEFAULT_DATE_OPERATION);
    }

    @Test
    @Transactional
    public void createOncWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = oncRepository.findAll().size();

        // Create the Onc with an existing ID
        onc.setId(1L);
        OncDTO oncDTO = oncMapper.toDto(onc);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOncMockMvc.perform(post("/api/oncs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(oncDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Onc in the database
        List<Onc> oncList = oncRepository.findAll();
        assertThat(oncList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkOperateurIsRequired() throws Exception {
        int databaseSizeBeforeTest = oncRepository.findAll().size();
        // set the field null
        onc.setOperateur(null);

        // Create the Onc, which fails.
        OncDTO oncDTO = oncMapper.toDto(onc);


        restOncMockMvc.perform(post("/api/oncs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(oncDTO)))
            .andExpect(status().isBadRequest());

        List<Onc> oncList = oncRepository.findAll();
        assertThat(oncList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateOperationIsRequired() throws Exception {
        int databaseSizeBeforeTest = oncRepository.findAll().size();
        // set the field null
        onc.setDateOperation(null);

        // Create the Onc, which fails.
        OncDTO oncDTO = oncMapper.toDto(onc);


        restOncMockMvc.perform(post("/api/oncs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(oncDTO)))
            .andExpect(status().isBadRequest());

        List<Onc> oncList = oncRepository.findAll();
        assertThat(oncList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOncs() throws Exception {
        // Initialize the database
        oncRepository.saveAndFlush(onc);

        // Get all the oncList
        restOncMockMvc.perform(get("/api/oncs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(onc.getId().intValue())))
            .andExpect(jsonPath("$.[*].operateur").value(hasItem(DEFAULT_OPERATEUR)))
            .andExpect(jsonPath("$.[*].dateOperation").value(hasItem(sameInstant(DEFAULT_DATE_OPERATION))));
    }
    
    @Test
    @Transactional
    public void getOnc() throws Exception {
        // Initialize the database
        oncRepository.saveAndFlush(onc);

        // Get the onc
        restOncMockMvc.perform(get("/api/oncs/{id}", onc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(onc.getId().intValue()))
            .andExpect(jsonPath("$.operateur").value(DEFAULT_OPERATEUR))
            .andExpect(jsonPath("$.dateOperation").value(sameInstant(DEFAULT_DATE_OPERATION)));
    }
    @Test
    @Transactional
    public void getNonExistingOnc() throws Exception {
        // Get the onc
        restOncMockMvc.perform(get("/api/oncs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOnc() throws Exception {
        // Initialize the database
        oncRepository.saveAndFlush(onc);

        int databaseSizeBeforeUpdate = oncRepository.findAll().size();

        // Update the onc
        Onc updatedOnc = oncRepository.findById(onc.getId()).get();
        // Disconnect from session so that the updates on updatedOnc are not directly saved in db
        em.detach(updatedOnc);
        updatedOnc
            .operateur(UPDATED_OPERATEUR)
            .dateOperation(UPDATED_DATE_OPERATION);
        OncDTO oncDTO = oncMapper.toDto(updatedOnc);

        restOncMockMvc.perform(put("/api/oncs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(oncDTO)))
            .andExpect(status().isOk());

        // Validate the Onc in the database
        List<Onc> oncList = oncRepository.findAll();
        assertThat(oncList).hasSize(databaseSizeBeforeUpdate);
        Onc testOnc = oncList.get(oncList.size() - 1);
        assertThat(testOnc.getOperateur()).isEqualTo(UPDATED_OPERATEUR);
        assertThat(testOnc.getDateOperation()).isEqualTo(UPDATED_DATE_OPERATION);
    }

    @Test
    @Transactional
    public void updateNonExistingOnc() throws Exception {
        int databaseSizeBeforeUpdate = oncRepository.findAll().size();

        // Create the Onc
        OncDTO oncDTO = oncMapper.toDto(onc);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOncMockMvc.perform(put("/api/oncs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(oncDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Onc in the database
        List<Onc> oncList = oncRepository.findAll();
        assertThat(oncList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOnc() throws Exception {
        // Initialize the database
        oncRepository.saveAndFlush(onc);

        int databaseSizeBeforeDelete = oncRepository.findAll().size();

        // Delete the onc
        restOncMockMvc.perform(delete("/api/oncs/{id}", onc.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Onc> oncList = oncRepository.findAll();
        assertThat(oncList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
