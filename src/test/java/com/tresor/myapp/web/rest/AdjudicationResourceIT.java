package com.tresor.myapp.web.rest;

import com.tresor.myapp.IbondgeneApp;
import com.tresor.myapp.domain.Adjudication;
import com.tresor.myapp.repository.AdjudicationRepository;
import com.tresor.myapp.service.AdjudicationService;
import com.tresor.myapp.service.dto.AdjudicationDTO;
import com.tresor.myapp.service.mapper.AdjudicationMapper;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AdjudicationResource} REST controller.
 */
@SpringBootTest(classes = IbondgeneApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AdjudicationResourceIT {

    private static final Integer DEFAULT_RANG = 0;
    private static final Integer UPDATED_RANG = 1;

    private static final String DEFAULT_OBSERVATION_FR = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVATION_FR = "BBBBBBBBBB";

    private static final String DEFAULT_OBSERVATION_EN = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVATION_EN = "BBBBBBBBBB";

    private static final String DEFAULT_OBSERVATION_PT = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVATION_PT = "BBBBBBBBBB";

    @Autowired
    private AdjudicationRepository adjudicationRepository;

    @Autowired
    private AdjudicationMapper adjudicationMapper;

    @Autowired
    private AdjudicationService adjudicationService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdjudicationMockMvc;

    private Adjudication adjudication;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Adjudication createEntity(EntityManager em) {
        Adjudication adjudication = new Adjudication()
            .rang(DEFAULT_RANG)
            .observationFr(DEFAULT_OBSERVATION_FR)
            .observationEn(DEFAULT_OBSERVATION_EN)
            .observationPt(DEFAULT_OBSERVATION_PT);
        return adjudication;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Adjudication createUpdatedEntity(EntityManager em) {
        Adjudication adjudication = new Adjudication()
            .rang(UPDATED_RANG)
            .observationFr(UPDATED_OBSERVATION_FR)
            .observationEn(UPDATED_OBSERVATION_EN)
            .observationPt(UPDATED_OBSERVATION_PT);
        return adjudication;
    }

    @BeforeEach
    public void initTest() {
        adjudication = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdjudication() throws Exception {
        int databaseSizeBeforeCreate = adjudicationRepository.findAll().size();
        // Create the Adjudication
        AdjudicationDTO adjudicationDTO = adjudicationMapper.toDto(adjudication);
        restAdjudicationMockMvc.perform(post("/api/adjudications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjudicationDTO)))
            .andExpect(status().isCreated());

        // Validate the Adjudication in the database
        List<Adjudication> adjudicationList = adjudicationRepository.findAll();
        assertThat(adjudicationList).hasSize(databaseSizeBeforeCreate + 1);
        Adjudication testAdjudication = adjudicationList.get(adjudicationList.size() - 1);
        assertThat(testAdjudication.getRang()).isEqualTo(DEFAULT_RANG);
        assertThat(testAdjudication.getObservationFr()).isEqualTo(DEFAULT_OBSERVATION_FR);
        assertThat(testAdjudication.getObservationEn()).isEqualTo(DEFAULT_OBSERVATION_EN);
        assertThat(testAdjudication.getObservationPt()).isEqualTo(DEFAULT_OBSERVATION_PT);
    }

    @Test
    @Transactional
    public void createAdjudicationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = adjudicationRepository.findAll().size();

        // Create the Adjudication with an existing ID
        adjudication.setId(1L);
        AdjudicationDTO adjudicationDTO = adjudicationMapper.toDto(adjudication);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdjudicationMockMvc.perform(post("/api/adjudications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjudicationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Adjudication in the database
        List<Adjudication> adjudicationList = adjudicationRepository.findAll();
        assertThat(adjudicationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRangIsRequired() throws Exception {
        int databaseSizeBeforeTest = adjudicationRepository.findAll().size();
        // set the field null
        adjudication.setRang(null);

        // Create the Adjudication, which fails.
        AdjudicationDTO adjudicationDTO = adjudicationMapper.toDto(adjudication);


        restAdjudicationMockMvc.perform(post("/api/adjudications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjudicationDTO)))
            .andExpect(status().isBadRequest());

        List<Adjudication> adjudicationList = adjudicationRepository.findAll();
        assertThat(adjudicationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAdjudications() throws Exception {
        // Initialize the database
        adjudicationRepository.saveAndFlush(adjudication);

        // Get all the adjudicationList
        restAdjudicationMockMvc.perform(get("/api/adjudications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(adjudication.getId().intValue())))
            .andExpect(jsonPath("$.[*].rang").value(hasItem(DEFAULT_RANG)))
            .andExpect(jsonPath("$.[*].observationFr").value(hasItem(DEFAULT_OBSERVATION_FR)))
            .andExpect(jsonPath("$.[*].observationEn").value(hasItem(DEFAULT_OBSERVATION_EN)))
            .andExpect(jsonPath("$.[*].observationPt").value(hasItem(DEFAULT_OBSERVATION_PT)));
    }
    
    @Test
    @Transactional
    public void getAdjudication() throws Exception {
        // Initialize the database
        adjudicationRepository.saveAndFlush(adjudication);

        // Get the adjudication
        restAdjudicationMockMvc.perform(get("/api/adjudications/{id}", adjudication.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(adjudication.getId().intValue()))
            .andExpect(jsonPath("$.rang").value(DEFAULT_RANG))
            .andExpect(jsonPath("$.observationFr").value(DEFAULT_OBSERVATION_FR))
            .andExpect(jsonPath("$.observationEn").value(DEFAULT_OBSERVATION_EN))
            .andExpect(jsonPath("$.observationPt").value(DEFAULT_OBSERVATION_PT));
    }
    @Test
    @Transactional
    public void getNonExistingAdjudication() throws Exception {
        // Get the adjudication
        restAdjudicationMockMvc.perform(get("/api/adjudications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdjudication() throws Exception {
        // Initialize the database
        adjudicationRepository.saveAndFlush(adjudication);

        int databaseSizeBeforeUpdate = adjudicationRepository.findAll().size();

        // Update the adjudication
        Adjudication updatedAdjudication = adjudicationRepository.findById(adjudication.getId()).get();
        // Disconnect from session so that the updates on updatedAdjudication are not directly saved in db
        em.detach(updatedAdjudication);
        updatedAdjudication
            .rang(UPDATED_RANG)
            .observationFr(UPDATED_OBSERVATION_FR)
            .observationEn(UPDATED_OBSERVATION_EN)
            .observationPt(UPDATED_OBSERVATION_PT);
        AdjudicationDTO adjudicationDTO = adjudicationMapper.toDto(updatedAdjudication);

        restAdjudicationMockMvc.perform(put("/api/adjudications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjudicationDTO)))
            .andExpect(status().isOk());

        // Validate the Adjudication in the database
        List<Adjudication> adjudicationList = adjudicationRepository.findAll();
        assertThat(adjudicationList).hasSize(databaseSizeBeforeUpdate);
        Adjudication testAdjudication = adjudicationList.get(adjudicationList.size() - 1);
        assertThat(testAdjudication.getRang()).isEqualTo(UPDATED_RANG);
        assertThat(testAdjudication.getObservationFr()).isEqualTo(UPDATED_OBSERVATION_FR);
        assertThat(testAdjudication.getObservationEn()).isEqualTo(UPDATED_OBSERVATION_EN);
        assertThat(testAdjudication.getObservationPt()).isEqualTo(UPDATED_OBSERVATION_PT);
    }

    @Test
    @Transactional
    public void updateNonExistingAdjudication() throws Exception {
        int databaseSizeBeforeUpdate = adjudicationRepository.findAll().size();

        // Create the Adjudication
        AdjudicationDTO adjudicationDTO = adjudicationMapper.toDto(adjudication);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdjudicationMockMvc.perform(put("/api/adjudications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(adjudicationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Adjudication in the database
        List<Adjudication> adjudicationList = adjudicationRepository.findAll();
        assertThat(adjudicationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdjudication() throws Exception {
        // Initialize the database
        adjudicationRepository.saveAndFlush(adjudication);

        int databaseSizeBeforeDelete = adjudicationRepository.findAll().size();

        // Delete the adjudication
        restAdjudicationMockMvc.perform(delete("/api/adjudications/{id}", adjudication.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Adjudication> adjudicationList = adjudicationRepository.findAll();
        assertThat(adjudicationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
