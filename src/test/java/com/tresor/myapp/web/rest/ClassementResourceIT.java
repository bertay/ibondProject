package com.tresor.myapp.web.rest;

import com.tresor.myapp.IbondgeneApp;
import com.tresor.myapp.domain.Classement;
import com.tresor.myapp.repository.ClassementRepository;
import com.tresor.myapp.service.ClassementService;
import com.tresor.myapp.service.dto.ClassementDTO;
import com.tresor.myapp.service.mapper.ClassementMapper;

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
 * Integration tests for the {@link ClassementResource} REST controller.
 */
@SpringBootTest(classes = IbondgeneApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ClassementResourceIT {

    private static final Integer DEFAULT_RANG = 0;
    private static final Integer UPDATED_RANG = 1;

    private static final String DEFAULT_OBSERVATION_FR = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVATION_FR = "BBBBBBBBBB";

    private static final String DEFAULT_OBSERVATION_EN = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVATION_EN = "BBBBBBBBBB";

    private static final String DEFAULT_OBSERVATION_PT = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVATION_PT = "BBBBBBBBBB";

    @Autowired
    private ClassementRepository classementRepository;

    @Autowired
    private ClassementMapper classementMapper;

    @Autowired
    private ClassementService classementService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClassementMockMvc;

    private Classement classement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Classement createEntity(EntityManager em) {
        Classement classement = new Classement()
            .rang(DEFAULT_RANG)
            .observationFr(DEFAULT_OBSERVATION_FR)
            .observationEn(DEFAULT_OBSERVATION_EN)
            .observationPt(DEFAULT_OBSERVATION_PT);
        return classement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Classement createUpdatedEntity(EntityManager em) {
        Classement classement = new Classement()
            .rang(UPDATED_RANG)
            .observationFr(UPDATED_OBSERVATION_FR)
            .observationEn(UPDATED_OBSERVATION_EN)
            .observationPt(UPDATED_OBSERVATION_PT);
        return classement;
    }

    @BeforeEach
    public void initTest() {
        classement = createEntity(em);
    }

    @Test
    @Transactional
    public void createClassement() throws Exception {
        int databaseSizeBeforeCreate = classementRepository.findAll().size();
        // Create the Classement
        ClassementDTO classementDTO = classementMapper.toDto(classement);
        restClassementMockMvc.perform(post("/api/classements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(classementDTO)))
            .andExpect(status().isCreated());

        // Validate the Classement in the database
        List<Classement> classementList = classementRepository.findAll();
        assertThat(classementList).hasSize(databaseSizeBeforeCreate + 1);
        Classement testClassement = classementList.get(classementList.size() - 1);
        assertThat(testClassement.getRang()).isEqualTo(DEFAULT_RANG);
        assertThat(testClassement.getObservationFr()).isEqualTo(DEFAULT_OBSERVATION_FR);
        assertThat(testClassement.getObservationEn()).isEqualTo(DEFAULT_OBSERVATION_EN);
        assertThat(testClassement.getObservationPt()).isEqualTo(DEFAULT_OBSERVATION_PT);
    }

    @Test
    @Transactional
    public void createClassementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = classementRepository.findAll().size();

        // Create the Classement with an existing ID
        classement.setId(1L);
        ClassementDTO classementDTO = classementMapper.toDto(classement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClassementMockMvc.perform(post("/api/classements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(classementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Classement in the database
        List<Classement> classementList = classementRepository.findAll();
        assertThat(classementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRangIsRequired() throws Exception {
        int databaseSizeBeforeTest = classementRepository.findAll().size();
        // set the field null
        classement.setRang(null);

        // Create the Classement, which fails.
        ClassementDTO classementDTO = classementMapper.toDto(classement);


        restClassementMockMvc.perform(post("/api/classements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(classementDTO)))
            .andExpect(status().isBadRequest());

        List<Classement> classementList = classementRepository.findAll();
        assertThat(classementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllClassements() throws Exception {
        // Initialize the database
        classementRepository.saveAndFlush(classement);

        // Get all the classementList
        restClassementMockMvc.perform(get("/api/classements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(classement.getId().intValue())))
            .andExpect(jsonPath("$.[*].rang").value(hasItem(DEFAULT_RANG)))
            .andExpect(jsonPath("$.[*].observationFr").value(hasItem(DEFAULT_OBSERVATION_FR)))
            .andExpect(jsonPath("$.[*].observationEn").value(hasItem(DEFAULT_OBSERVATION_EN)))
            .andExpect(jsonPath("$.[*].observationPt").value(hasItem(DEFAULT_OBSERVATION_PT)));
    }
    
    @Test
    @Transactional
    public void getClassement() throws Exception {
        // Initialize the database
        classementRepository.saveAndFlush(classement);

        // Get the classement
        restClassementMockMvc.perform(get("/api/classements/{id}", classement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(classement.getId().intValue()))
            .andExpect(jsonPath("$.rang").value(DEFAULT_RANG))
            .andExpect(jsonPath("$.observationFr").value(DEFAULT_OBSERVATION_FR))
            .andExpect(jsonPath("$.observationEn").value(DEFAULT_OBSERVATION_EN))
            .andExpect(jsonPath("$.observationPt").value(DEFAULT_OBSERVATION_PT));
    }
    @Test
    @Transactional
    public void getNonExistingClassement() throws Exception {
        // Get the classement
        restClassementMockMvc.perform(get("/api/classements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClassement() throws Exception {
        // Initialize the database
        classementRepository.saveAndFlush(classement);

        int databaseSizeBeforeUpdate = classementRepository.findAll().size();

        // Update the classement
        Classement updatedClassement = classementRepository.findById(classement.getId()).get();
        // Disconnect from session so that the updates on updatedClassement are not directly saved in db
        em.detach(updatedClassement);
        updatedClassement
            .rang(UPDATED_RANG)
            .observationFr(UPDATED_OBSERVATION_FR)
            .observationEn(UPDATED_OBSERVATION_EN)
            .observationPt(UPDATED_OBSERVATION_PT);
        ClassementDTO classementDTO = classementMapper.toDto(updatedClassement);

        restClassementMockMvc.perform(put("/api/classements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(classementDTO)))
            .andExpect(status().isOk());

        // Validate the Classement in the database
        List<Classement> classementList = classementRepository.findAll();
        assertThat(classementList).hasSize(databaseSizeBeforeUpdate);
        Classement testClassement = classementList.get(classementList.size() - 1);
        assertThat(testClassement.getRang()).isEqualTo(UPDATED_RANG);
        assertThat(testClassement.getObservationFr()).isEqualTo(UPDATED_OBSERVATION_FR);
        assertThat(testClassement.getObservationEn()).isEqualTo(UPDATED_OBSERVATION_EN);
        assertThat(testClassement.getObservationPt()).isEqualTo(UPDATED_OBSERVATION_PT);
    }

    @Test
    @Transactional
    public void updateNonExistingClassement() throws Exception {
        int databaseSizeBeforeUpdate = classementRepository.findAll().size();

        // Create the Classement
        ClassementDTO classementDTO = classementMapper.toDto(classement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClassementMockMvc.perform(put("/api/classements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(classementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Classement in the database
        List<Classement> classementList = classementRepository.findAll();
        assertThat(classementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteClassement() throws Exception {
        // Initialize the database
        classementRepository.saveAndFlush(classement);

        int databaseSizeBeforeDelete = classementRepository.findAll().size();

        // Delete the classement
        restClassementMockMvc.perform(delete("/api/classements/{id}", classement.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Classement> classementList = classementRepository.findAll();
        assertThat(classementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
