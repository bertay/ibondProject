package com.tresor.myapp.web.rest;

import com.tresor.myapp.IbondgeneApp;
import com.tresor.myapp.domain.Svt;
import com.tresor.myapp.repository.SvtRepository;
import com.tresor.myapp.service.SvtService;
import com.tresor.myapp.service.dto.SvtDTO;
import com.tresor.myapp.service.mapper.SvtMapper;

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

import com.tresor.myapp.domain.enumeration.EtatSvt;
/**
 * Integration tests for the {@link SvtResource} REST controller.
 */
@SpringBootTest(classes = IbondgeneApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SvtResourceIT {

    private static final String DEFAULT_ABREVIATION_FR = "AAAAAAAAAA";
    private static final String UPDATED_ABREVIATION_FR = "BBBBBBBBBB";

    private static final String DEFAULT_ABREVIATION_EN = "AAAAAAAAAA";
    private static final String UPDATED_ABREVIATION_EN = "BBBBBBBBBB";

    private static final String DEFAULT_ABREVIATION_PT = "AAAAAAAAAA";
    private static final String UPDATED_ABREVIATION_PT = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGNATION_FR = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION_FR = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGNATION_EN = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION_EN = "BBBBBBBBBB";

    private static final EtatSvt DEFAULT_ETAT = EtatSvt.Agree;
    private static final EtatSvt UPDATED_ETAT = EtatSvt.Suspendu;

    private static final String DEFAULT_OPERATEUR = "AAAAAAAAAA";
    private static final String UPDATED_OPERATEUR = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_OPERATION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_OPERATION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private SvtRepository svtRepository;

    @Autowired
    private SvtMapper svtMapper;

    @Autowired
    private SvtService svtService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSvtMockMvc;

    private Svt svt;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Svt createEntity(EntityManager em) {
        Svt svt = new Svt()
            .abreviationFr(DEFAULT_ABREVIATION_FR)
            .abreviationEn(DEFAULT_ABREVIATION_EN)
            .abreviationPt(DEFAULT_ABREVIATION_PT)
            .designationFr(DEFAULT_DESIGNATION_FR)
            .designationEn(DEFAULT_DESIGNATION_EN)
            .etat(DEFAULT_ETAT)
            .operateur(DEFAULT_OPERATEUR)
            .dateOperation(DEFAULT_DATE_OPERATION);
        return svt;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Svt createUpdatedEntity(EntityManager em) {
        Svt svt = new Svt()
            .abreviationFr(UPDATED_ABREVIATION_FR)
            .abreviationEn(UPDATED_ABREVIATION_EN)
            .abreviationPt(UPDATED_ABREVIATION_PT)
            .designationFr(UPDATED_DESIGNATION_FR)
            .designationEn(UPDATED_DESIGNATION_EN)
            .etat(UPDATED_ETAT)
            .operateur(UPDATED_OPERATEUR)
            .dateOperation(UPDATED_DATE_OPERATION);
        return svt;
    }

    @BeforeEach
    public void initTest() {
        svt = createEntity(em);
    }

    @Test
    @Transactional
    public void createSvt() throws Exception {
        int databaseSizeBeforeCreate = svtRepository.findAll().size();
        // Create the Svt
        SvtDTO svtDTO = svtMapper.toDto(svt);
        restSvtMockMvc.perform(post("/api/svts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(svtDTO)))
            .andExpect(status().isCreated());

        // Validate the Svt in the database
        List<Svt> svtList = svtRepository.findAll();
        assertThat(svtList).hasSize(databaseSizeBeforeCreate + 1);
        Svt testSvt = svtList.get(svtList.size() - 1);
        assertThat(testSvt.getAbreviationFr()).isEqualTo(DEFAULT_ABREVIATION_FR);
        assertThat(testSvt.getAbreviationEn()).isEqualTo(DEFAULT_ABREVIATION_EN);
        assertThat(testSvt.getAbreviationPt()).isEqualTo(DEFAULT_ABREVIATION_PT);
        assertThat(testSvt.getDesignationFr()).isEqualTo(DEFAULT_DESIGNATION_FR);
        assertThat(testSvt.getDesignationEn()).isEqualTo(DEFAULT_DESIGNATION_EN);
        assertThat(testSvt.getEtat()).isEqualTo(DEFAULT_ETAT);
        assertThat(testSvt.getOperateur()).isEqualTo(DEFAULT_OPERATEUR);
        assertThat(testSvt.getDateOperation()).isEqualTo(DEFAULT_DATE_OPERATION);
    }

    @Test
    @Transactional
    public void createSvtWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = svtRepository.findAll().size();

        // Create the Svt with an existing ID
        svt.setId(1L);
        SvtDTO svtDTO = svtMapper.toDto(svt);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSvtMockMvc.perform(post("/api/svts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(svtDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Svt in the database
        List<Svt> svtList = svtRepository.findAll();
        assertThat(svtList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDesignationFrIsRequired() throws Exception {
        int databaseSizeBeforeTest = svtRepository.findAll().size();
        // set the field null
        svt.setDesignationFr(null);

        // Create the Svt, which fails.
        SvtDTO svtDTO = svtMapper.toDto(svt);


        restSvtMockMvc.perform(post("/api/svts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(svtDTO)))
            .andExpect(status().isBadRequest());

        List<Svt> svtList = svtRepository.findAll();
        assertThat(svtList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOperateurIsRequired() throws Exception {
        int databaseSizeBeforeTest = svtRepository.findAll().size();
        // set the field null
        svt.setOperateur(null);

        // Create the Svt, which fails.
        SvtDTO svtDTO = svtMapper.toDto(svt);


        restSvtMockMvc.perform(post("/api/svts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(svtDTO)))
            .andExpect(status().isBadRequest());

        List<Svt> svtList = svtRepository.findAll();
        assertThat(svtList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateOperationIsRequired() throws Exception {
        int databaseSizeBeforeTest = svtRepository.findAll().size();
        // set the field null
        svt.setDateOperation(null);

        // Create the Svt, which fails.
        SvtDTO svtDTO = svtMapper.toDto(svt);


        restSvtMockMvc.perform(post("/api/svts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(svtDTO)))
            .andExpect(status().isBadRequest());

        List<Svt> svtList = svtRepository.findAll();
        assertThat(svtList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSvts() throws Exception {
        // Initialize the database
        svtRepository.saveAndFlush(svt);

        // Get all the svtList
        restSvtMockMvc.perform(get("/api/svts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(svt.getId().intValue())))
            .andExpect(jsonPath("$.[*].abreviationFr").value(hasItem(DEFAULT_ABREVIATION_FR)))
            .andExpect(jsonPath("$.[*].abreviationEn").value(hasItem(DEFAULT_ABREVIATION_EN)))
            .andExpect(jsonPath("$.[*].abreviationPt").value(hasItem(DEFAULT_ABREVIATION_PT)))
            .andExpect(jsonPath("$.[*].designationFr").value(hasItem(DEFAULT_DESIGNATION_FR)))
            .andExpect(jsonPath("$.[*].designationEn").value(hasItem(DEFAULT_DESIGNATION_EN)))
            .andExpect(jsonPath("$.[*].etat").value(hasItem(DEFAULT_ETAT.toString())))
            .andExpect(jsonPath("$.[*].operateur").value(hasItem(DEFAULT_OPERATEUR)))
            .andExpect(jsonPath("$.[*].dateOperation").value(hasItem(sameInstant(DEFAULT_DATE_OPERATION))));
    }
    
    @Test
    @Transactional
    public void getSvt() throws Exception {
        // Initialize the database
        svtRepository.saveAndFlush(svt);

        // Get the svt
        restSvtMockMvc.perform(get("/api/svts/{id}", svt.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(svt.getId().intValue()))
            .andExpect(jsonPath("$.abreviationFr").value(DEFAULT_ABREVIATION_FR))
            .andExpect(jsonPath("$.abreviationEn").value(DEFAULT_ABREVIATION_EN))
            .andExpect(jsonPath("$.abreviationPt").value(DEFAULT_ABREVIATION_PT))
            .andExpect(jsonPath("$.designationFr").value(DEFAULT_DESIGNATION_FR))
            .andExpect(jsonPath("$.designationEn").value(DEFAULT_DESIGNATION_EN))
            .andExpect(jsonPath("$.etat").value(DEFAULT_ETAT.toString()))
            .andExpect(jsonPath("$.operateur").value(DEFAULT_OPERATEUR))
            .andExpect(jsonPath("$.dateOperation").value(sameInstant(DEFAULT_DATE_OPERATION)));
    }
    @Test
    @Transactional
    public void getNonExistingSvt() throws Exception {
        // Get the svt
        restSvtMockMvc.perform(get("/api/svts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSvt() throws Exception {
        // Initialize the database
        svtRepository.saveAndFlush(svt);

        int databaseSizeBeforeUpdate = svtRepository.findAll().size();

        // Update the svt
        Svt updatedSvt = svtRepository.findById(svt.getId()).get();
        // Disconnect from session so that the updates on updatedSvt are not directly saved in db
        em.detach(updatedSvt);
        updatedSvt
            .abreviationFr(UPDATED_ABREVIATION_FR)
            .abreviationEn(UPDATED_ABREVIATION_EN)
            .abreviationPt(UPDATED_ABREVIATION_PT)
            .designationFr(UPDATED_DESIGNATION_FR)
            .designationEn(UPDATED_DESIGNATION_EN)
            .etat(UPDATED_ETAT)
            .operateur(UPDATED_OPERATEUR)
            .dateOperation(UPDATED_DATE_OPERATION);
        SvtDTO svtDTO = svtMapper.toDto(updatedSvt);

        restSvtMockMvc.perform(put("/api/svts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(svtDTO)))
            .andExpect(status().isOk());

        // Validate the Svt in the database
        List<Svt> svtList = svtRepository.findAll();
        assertThat(svtList).hasSize(databaseSizeBeforeUpdate);
        Svt testSvt = svtList.get(svtList.size() - 1);
        assertThat(testSvt.getAbreviationFr()).isEqualTo(UPDATED_ABREVIATION_FR);
        assertThat(testSvt.getAbreviationEn()).isEqualTo(UPDATED_ABREVIATION_EN);
        assertThat(testSvt.getAbreviationPt()).isEqualTo(UPDATED_ABREVIATION_PT);
        assertThat(testSvt.getDesignationFr()).isEqualTo(UPDATED_DESIGNATION_FR);
        assertThat(testSvt.getDesignationEn()).isEqualTo(UPDATED_DESIGNATION_EN);
        assertThat(testSvt.getEtat()).isEqualTo(UPDATED_ETAT);
        assertThat(testSvt.getOperateur()).isEqualTo(UPDATED_OPERATEUR);
        assertThat(testSvt.getDateOperation()).isEqualTo(UPDATED_DATE_OPERATION);
    }

    @Test
    @Transactional
    public void updateNonExistingSvt() throws Exception {
        int databaseSizeBeforeUpdate = svtRepository.findAll().size();

        // Create the Svt
        SvtDTO svtDTO = svtMapper.toDto(svt);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSvtMockMvc.perform(put("/api/svts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(svtDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Svt in the database
        List<Svt> svtList = svtRepository.findAll();
        assertThat(svtList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSvt() throws Exception {
        // Initialize the database
        svtRepository.saveAndFlush(svt);

        int databaseSizeBeforeDelete = svtRepository.findAll().size();

        // Delete the svt
        restSvtMockMvc.perform(delete("/api/svts/{id}", svt.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Svt> svtList = svtRepository.findAll();
        assertThat(svtList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
