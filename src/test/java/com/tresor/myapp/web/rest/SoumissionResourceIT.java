package com.tresor.myapp.web.rest;

import com.tresor.myapp.IbondgeneApp;
import com.tresor.myapp.domain.Soumission;
import com.tresor.myapp.repository.SoumissionRepository;
import com.tresor.myapp.service.SoumissionService;
import com.tresor.myapp.service.dto.SoumissionDTO;
import com.tresor.myapp.service.mapper.SoumissionMapper;

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
 * Integration tests for the {@link SoumissionResource} REST controller.
 */
@SpringBootTest(classes = IbondgeneApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SoumissionResourceIT {

    private static final String DEFAULT_NUM_ANONYMAT = "AAAAAAAAAA";
    private static final String UPDATED_NUM_ANONYMAT = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_SOUMISSION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_SOUMISSION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_NBRE_SOUMISSION = 0;
    private static final Integer UPDATED_NBRE_SOUMISSION = 1;

    private static final String DEFAULT_OPERATEUR = "AAAAAAAAAA";
    private static final String UPDATED_OPERATEUR = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_OPERATION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_OPERATION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private SoumissionRepository soumissionRepository;

    @Autowired
    private SoumissionMapper soumissionMapper;

    @Autowired
    private SoumissionService soumissionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSoumissionMockMvc;

    private Soumission soumission;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Soumission createEntity(EntityManager em) {
        Soumission soumission = new Soumission()
            .numAnonymat(DEFAULT_NUM_ANONYMAT)
            .dateSoumission(DEFAULT_DATE_SOUMISSION)
            .nbreSoumission(DEFAULT_NBRE_SOUMISSION)
            .operateur(DEFAULT_OPERATEUR)
            .dateOperation(DEFAULT_DATE_OPERATION);
        return soumission;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Soumission createUpdatedEntity(EntityManager em) {
        Soumission soumission = new Soumission()
            .numAnonymat(UPDATED_NUM_ANONYMAT)
            .dateSoumission(UPDATED_DATE_SOUMISSION)
            .nbreSoumission(UPDATED_NBRE_SOUMISSION)
            .operateur(UPDATED_OPERATEUR)
            .dateOperation(UPDATED_DATE_OPERATION);
        return soumission;
    }

    @BeforeEach
    public void initTest() {
        soumission = createEntity(em);
    }

    @Test
    @Transactional
    public void createSoumission() throws Exception {
        int databaseSizeBeforeCreate = soumissionRepository.findAll().size();
        // Create the Soumission
        SoumissionDTO soumissionDTO = soumissionMapper.toDto(soumission);
        restSoumissionMockMvc.perform(post("/api/soumissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(soumissionDTO)))
            .andExpect(status().isCreated());

        // Validate the Soumission in the database
        List<Soumission> soumissionList = soumissionRepository.findAll();
        assertThat(soumissionList).hasSize(databaseSizeBeforeCreate + 1);
        Soumission testSoumission = soumissionList.get(soumissionList.size() - 1);
        assertThat(testSoumission.getNumAnonymat()).isEqualTo(DEFAULT_NUM_ANONYMAT);
        assertThat(testSoumission.getDateSoumission()).isEqualTo(DEFAULT_DATE_SOUMISSION);
        assertThat(testSoumission.getNbreSoumission()).isEqualTo(DEFAULT_NBRE_SOUMISSION);
        assertThat(testSoumission.getOperateur()).isEqualTo(DEFAULT_OPERATEUR);
        assertThat(testSoumission.getDateOperation()).isEqualTo(DEFAULT_DATE_OPERATION);
    }

    @Test
    @Transactional
    public void createSoumissionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = soumissionRepository.findAll().size();

        // Create the Soumission with an existing ID
        soumission.setId(1L);
        SoumissionDTO soumissionDTO = soumissionMapper.toDto(soumission);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSoumissionMockMvc.perform(post("/api/soumissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(soumissionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Soumission in the database
        List<Soumission> soumissionList = soumissionRepository.findAll();
        assertThat(soumissionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNumAnonymatIsRequired() throws Exception {
        int databaseSizeBeforeTest = soumissionRepository.findAll().size();
        // set the field null
        soumission.setNumAnonymat(null);

        // Create the Soumission, which fails.
        SoumissionDTO soumissionDTO = soumissionMapper.toDto(soumission);


        restSoumissionMockMvc.perform(post("/api/soumissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(soumissionDTO)))
            .andExpect(status().isBadRequest());

        List<Soumission> soumissionList = soumissionRepository.findAll();
        assertThat(soumissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateSoumissionIsRequired() throws Exception {
        int databaseSizeBeforeTest = soumissionRepository.findAll().size();
        // set the field null
        soumission.setDateSoumission(null);

        // Create the Soumission, which fails.
        SoumissionDTO soumissionDTO = soumissionMapper.toDto(soumission);


        restSoumissionMockMvc.perform(post("/api/soumissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(soumissionDTO)))
            .andExpect(status().isBadRequest());

        List<Soumission> soumissionList = soumissionRepository.findAll();
        assertThat(soumissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOperateurIsRequired() throws Exception {
        int databaseSizeBeforeTest = soumissionRepository.findAll().size();
        // set the field null
        soumission.setOperateur(null);

        // Create the Soumission, which fails.
        SoumissionDTO soumissionDTO = soumissionMapper.toDto(soumission);


        restSoumissionMockMvc.perform(post("/api/soumissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(soumissionDTO)))
            .andExpect(status().isBadRequest());

        List<Soumission> soumissionList = soumissionRepository.findAll();
        assertThat(soumissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateOperationIsRequired() throws Exception {
        int databaseSizeBeforeTest = soumissionRepository.findAll().size();
        // set the field null
        soumission.setDateOperation(null);

        // Create the Soumission, which fails.
        SoumissionDTO soumissionDTO = soumissionMapper.toDto(soumission);


        restSoumissionMockMvc.perform(post("/api/soumissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(soumissionDTO)))
            .andExpect(status().isBadRequest());

        List<Soumission> soumissionList = soumissionRepository.findAll();
        assertThat(soumissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSoumissions() throws Exception {
        // Initialize the database
        soumissionRepository.saveAndFlush(soumission);

        // Get all the soumissionList
        restSoumissionMockMvc.perform(get("/api/soumissions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(soumission.getId().intValue())))
            .andExpect(jsonPath("$.[*].numAnonymat").value(hasItem(DEFAULT_NUM_ANONYMAT)))
            .andExpect(jsonPath("$.[*].dateSoumission").value(hasItem(sameInstant(DEFAULT_DATE_SOUMISSION))))
            .andExpect(jsonPath("$.[*].nbreSoumission").value(hasItem(DEFAULT_NBRE_SOUMISSION)))
            .andExpect(jsonPath("$.[*].operateur").value(hasItem(DEFAULT_OPERATEUR)))
            .andExpect(jsonPath("$.[*].dateOperation").value(hasItem(sameInstant(DEFAULT_DATE_OPERATION))));
    }
    
    @Test
    @Transactional
    public void getSoumission() throws Exception {
        // Initialize the database
        soumissionRepository.saveAndFlush(soumission);

        // Get the soumission
        restSoumissionMockMvc.perform(get("/api/soumissions/{id}", soumission.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(soumission.getId().intValue()))
            .andExpect(jsonPath("$.numAnonymat").value(DEFAULT_NUM_ANONYMAT))
            .andExpect(jsonPath("$.dateSoumission").value(sameInstant(DEFAULT_DATE_SOUMISSION)))
            .andExpect(jsonPath("$.nbreSoumission").value(DEFAULT_NBRE_SOUMISSION))
            .andExpect(jsonPath("$.operateur").value(DEFAULT_OPERATEUR))
            .andExpect(jsonPath("$.dateOperation").value(sameInstant(DEFAULT_DATE_OPERATION)));
    }
    @Test
    @Transactional
    public void getNonExistingSoumission() throws Exception {
        // Get the soumission
        restSoumissionMockMvc.perform(get("/api/soumissions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSoumission() throws Exception {
        // Initialize the database
        soumissionRepository.saveAndFlush(soumission);

        int databaseSizeBeforeUpdate = soumissionRepository.findAll().size();

        // Update the soumission
        Soumission updatedSoumission = soumissionRepository.findById(soumission.getId()).get();
        // Disconnect from session so that the updates on updatedSoumission are not directly saved in db
        em.detach(updatedSoumission);
        updatedSoumission
            .numAnonymat(UPDATED_NUM_ANONYMAT)
            .dateSoumission(UPDATED_DATE_SOUMISSION)
            .nbreSoumission(UPDATED_NBRE_SOUMISSION)
            .operateur(UPDATED_OPERATEUR)
            .dateOperation(UPDATED_DATE_OPERATION);
        SoumissionDTO soumissionDTO = soumissionMapper.toDto(updatedSoumission);

        restSoumissionMockMvc.perform(put("/api/soumissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(soumissionDTO)))
            .andExpect(status().isOk());

        // Validate the Soumission in the database
        List<Soumission> soumissionList = soumissionRepository.findAll();
        assertThat(soumissionList).hasSize(databaseSizeBeforeUpdate);
        Soumission testSoumission = soumissionList.get(soumissionList.size() - 1);
        assertThat(testSoumission.getNumAnonymat()).isEqualTo(UPDATED_NUM_ANONYMAT);
        assertThat(testSoumission.getDateSoumission()).isEqualTo(UPDATED_DATE_SOUMISSION);
        assertThat(testSoumission.getNbreSoumission()).isEqualTo(UPDATED_NBRE_SOUMISSION);
        assertThat(testSoumission.getOperateur()).isEqualTo(UPDATED_OPERATEUR);
        assertThat(testSoumission.getDateOperation()).isEqualTo(UPDATED_DATE_OPERATION);
    }

    @Test
    @Transactional
    public void updateNonExistingSoumission() throws Exception {
        int databaseSizeBeforeUpdate = soumissionRepository.findAll().size();

        // Create the Soumission
        SoumissionDTO soumissionDTO = soumissionMapper.toDto(soumission);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSoumissionMockMvc.perform(put("/api/soumissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(soumissionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Soumission in the database
        List<Soumission> soumissionList = soumissionRepository.findAll();
        assertThat(soumissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSoumission() throws Exception {
        // Initialize the database
        soumissionRepository.saveAndFlush(soumission);

        int databaseSizeBeforeDelete = soumissionRepository.findAll().size();

        // Delete the soumission
        restSoumissionMockMvc.perform(delete("/api/soumissions/{id}", soumission.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Soumission> soumissionList = soumissionRepository.findAll();
        assertThat(soumissionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
