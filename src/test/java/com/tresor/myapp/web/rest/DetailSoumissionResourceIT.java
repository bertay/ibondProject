package com.tresor.myapp.web.rest;

import com.tresor.myapp.IbondgeneApp;
import com.tresor.myapp.domain.DetailSoumission;
import com.tresor.myapp.repository.DetailSoumissionRepository;
import com.tresor.myapp.service.DetailSoumissionService;
import com.tresor.myapp.service.dto.DetailSoumissionDTO;
import com.tresor.myapp.service.mapper.DetailSoumissionMapper;

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
 * Integration tests for the {@link DetailSoumissionResource} REST controller.
 */
@SpringBootTest(classes = IbondgeneApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DetailSoumissionResourceIT {

    private static final Double DEFAULT_MONTANT_SOUMISSION = 0D;
    private static final Double UPDATED_MONTANT_SOUMISSION = 1D;

    private static final Float DEFAULT_TAUX_PROPOSE = 0F;
    private static final Float UPDATED_TAUX_PROPOSE = 1F;

    private static final String DEFAULT_OPERATEUR = "AAAAAAAAAA";
    private static final String UPDATED_OPERATEUR = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_OPERATION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_OPERATION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private DetailSoumissionRepository detailSoumissionRepository;

    @Autowired
    private DetailSoumissionMapper detailSoumissionMapper;

    @Autowired
    private DetailSoumissionService detailSoumissionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDetailSoumissionMockMvc;

    private DetailSoumission detailSoumission;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DetailSoumission createEntity(EntityManager em) {
        DetailSoumission detailSoumission = new DetailSoumission()
            .montantSoumission(DEFAULT_MONTANT_SOUMISSION)
            .tauxPropose(DEFAULT_TAUX_PROPOSE)
            .operateur(DEFAULT_OPERATEUR)
            .dateOperation(DEFAULT_DATE_OPERATION);
        return detailSoumission;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DetailSoumission createUpdatedEntity(EntityManager em) {
        DetailSoumission detailSoumission = new DetailSoumission()
            .montantSoumission(UPDATED_MONTANT_SOUMISSION)
            .tauxPropose(UPDATED_TAUX_PROPOSE)
            .operateur(UPDATED_OPERATEUR)
            .dateOperation(UPDATED_DATE_OPERATION);
        return detailSoumission;
    }

    @BeforeEach
    public void initTest() {
        detailSoumission = createEntity(em);
    }

    @Test
    @Transactional
    public void createDetailSoumission() throws Exception {
        int databaseSizeBeforeCreate = detailSoumissionRepository.findAll().size();
        // Create the DetailSoumission
        DetailSoumissionDTO detailSoumissionDTO = detailSoumissionMapper.toDto(detailSoumission);
        restDetailSoumissionMockMvc.perform(post("/api/detail-soumissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailSoumissionDTO)))
            .andExpect(status().isCreated());

        // Validate the DetailSoumission in the database
        List<DetailSoumission> detailSoumissionList = detailSoumissionRepository.findAll();
        assertThat(detailSoumissionList).hasSize(databaseSizeBeforeCreate + 1);
        DetailSoumission testDetailSoumission = detailSoumissionList.get(detailSoumissionList.size() - 1);
        assertThat(testDetailSoumission.getMontantSoumission()).isEqualTo(DEFAULT_MONTANT_SOUMISSION);
        assertThat(testDetailSoumission.getTauxPropose()).isEqualTo(DEFAULT_TAUX_PROPOSE);
        assertThat(testDetailSoumission.getOperateur()).isEqualTo(DEFAULT_OPERATEUR);
        assertThat(testDetailSoumission.getDateOperation()).isEqualTo(DEFAULT_DATE_OPERATION);
    }

    @Test
    @Transactional
    public void createDetailSoumissionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = detailSoumissionRepository.findAll().size();

        // Create the DetailSoumission with an existing ID
        detailSoumission.setId(1L);
        DetailSoumissionDTO detailSoumissionDTO = detailSoumissionMapper.toDto(detailSoumission);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDetailSoumissionMockMvc.perform(post("/api/detail-soumissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailSoumissionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DetailSoumission in the database
        List<DetailSoumission> detailSoumissionList = detailSoumissionRepository.findAll();
        assertThat(detailSoumissionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMontantSoumissionIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailSoumissionRepository.findAll().size();
        // set the field null
        detailSoumission.setMontantSoumission(null);

        // Create the DetailSoumission, which fails.
        DetailSoumissionDTO detailSoumissionDTO = detailSoumissionMapper.toDto(detailSoumission);


        restDetailSoumissionMockMvc.perform(post("/api/detail-soumissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailSoumissionDTO)))
            .andExpect(status().isBadRequest());

        List<DetailSoumission> detailSoumissionList = detailSoumissionRepository.findAll();
        assertThat(detailSoumissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTauxProposeIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailSoumissionRepository.findAll().size();
        // set the field null
        detailSoumission.setTauxPropose(null);

        // Create the DetailSoumission, which fails.
        DetailSoumissionDTO detailSoumissionDTO = detailSoumissionMapper.toDto(detailSoumission);


        restDetailSoumissionMockMvc.perform(post("/api/detail-soumissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailSoumissionDTO)))
            .andExpect(status().isBadRequest());

        List<DetailSoumission> detailSoumissionList = detailSoumissionRepository.findAll();
        assertThat(detailSoumissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOperateurIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailSoumissionRepository.findAll().size();
        // set the field null
        detailSoumission.setOperateur(null);

        // Create the DetailSoumission, which fails.
        DetailSoumissionDTO detailSoumissionDTO = detailSoumissionMapper.toDto(detailSoumission);


        restDetailSoumissionMockMvc.perform(post("/api/detail-soumissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailSoumissionDTO)))
            .andExpect(status().isBadRequest());

        List<DetailSoumission> detailSoumissionList = detailSoumissionRepository.findAll();
        assertThat(detailSoumissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateOperationIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailSoumissionRepository.findAll().size();
        // set the field null
        detailSoumission.setDateOperation(null);

        // Create the DetailSoumission, which fails.
        DetailSoumissionDTO detailSoumissionDTO = detailSoumissionMapper.toDto(detailSoumission);


        restDetailSoumissionMockMvc.perform(post("/api/detail-soumissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailSoumissionDTO)))
            .andExpect(status().isBadRequest());

        List<DetailSoumission> detailSoumissionList = detailSoumissionRepository.findAll();
        assertThat(detailSoumissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDetailSoumissions() throws Exception {
        // Initialize the database
        detailSoumissionRepository.saveAndFlush(detailSoumission);

        // Get all the detailSoumissionList
        restDetailSoumissionMockMvc.perform(get("/api/detail-soumissions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(detailSoumission.getId().intValue())))
            .andExpect(jsonPath("$.[*].montantSoumission").value(hasItem(DEFAULT_MONTANT_SOUMISSION.doubleValue())))
            .andExpect(jsonPath("$.[*].tauxPropose").value(hasItem(DEFAULT_TAUX_PROPOSE.doubleValue())))
            .andExpect(jsonPath("$.[*].operateur").value(hasItem(DEFAULT_OPERATEUR)))
            .andExpect(jsonPath("$.[*].dateOperation").value(hasItem(sameInstant(DEFAULT_DATE_OPERATION))));
    }
    
    @Test
    @Transactional
    public void getDetailSoumission() throws Exception {
        // Initialize the database
        detailSoumissionRepository.saveAndFlush(detailSoumission);

        // Get the detailSoumission
        restDetailSoumissionMockMvc.perform(get("/api/detail-soumissions/{id}", detailSoumission.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(detailSoumission.getId().intValue()))
            .andExpect(jsonPath("$.montantSoumission").value(DEFAULT_MONTANT_SOUMISSION.doubleValue()))
            .andExpect(jsonPath("$.tauxPropose").value(DEFAULT_TAUX_PROPOSE.doubleValue()))
            .andExpect(jsonPath("$.operateur").value(DEFAULT_OPERATEUR))
            .andExpect(jsonPath("$.dateOperation").value(sameInstant(DEFAULT_DATE_OPERATION)));
    }
    @Test
    @Transactional
    public void getNonExistingDetailSoumission() throws Exception {
        // Get the detailSoumission
        restDetailSoumissionMockMvc.perform(get("/api/detail-soumissions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDetailSoumission() throws Exception {
        // Initialize the database
        detailSoumissionRepository.saveAndFlush(detailSoumission);

        int databaseSizeBeforeUpdate = detailSoumissionRepository.findAll().size();

        // Update the detailSoumission
        DetailSoumission updatedDetailSoumission = detailSoumissionRepository.findById(detailSoumission.getId()).get();
        // Disconnect from session so that the updates on updatedDetailSoumission are not directly saved in db
        em.detach(updatedDetailSoumission);
        updatedDetailSoumission
            .montantSoumission(UPDATED_MONTANT_SOUMISSION)
            .tauxPropose(UPDATED_TAUX_PROPOSE)
            .operateur(UPDATED_OPERATEUR)
            .dateOperation(UPDATED_DATE_OPERATION);
        DetailSoumissionDTO detailSoumissionDTO = detailSoumissionMapper.toDto(updatedDetailSoumission);

        restDetailSoumissionMockMvc.perform(put("/api/detail-soumissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailSoumissionDTO)))
            .andExpect(status().isOk());

        // Validate the DetailSoumission in the database
        List<DetailSoumission> detailSoumissionList = detailSoumissionRepository.findAll();
        assertThat(detailSoumissionList).hasSize(databaseSizeBeforeUpdate);
        DetailSoumission testDetailSoumission = detailSoumissionList.get(detailSoumissionList.size() - 1);
        assertThat(testDetailSoumission.getMontantSoumission()).isEqualTo(UPDATED_MONTANT_SOUMISSION);
        assertThat(testDetailSoumission.getTauxPropose()).isEqualTo(UPDATED_TAUX_PROPOSE);
        assertThat(testDetailSoumission.getOperateur()).isEqualTo(UPDATED_OPERATEUR);
        assertThat(testDetailSoumission.getDateOperation()).isEqualTo(UPDATED_DATE_OPERATION);
    }

    @Test
    @Transactional
    public void updateNonExistingDetailSoumission() throws Exception {
        int databaseSizeBeforeUpdate = detailSoumissionRepository.findAll().size();

        // Create the DetailSoumission
        DetailSoumissionDTO detailSoumissionDTO = detailSoumissionMapper.toDto(detailSoumission);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDetailSoumissionMockMvc.perform(put("/api/detail-soumissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailSoumissionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DetailSoumission in the database
        List<DetailSoumission> detailSoumissionList = detailSoumissionRepository.findAll();
        assertThat(detailSoumissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDetailSoumission() throws Exception {
        // Initialize the database
        detailSoumissionRepository.saveAndFlush(detailSoumission);

        int databaseSizeBeforeDelete = detailSoumissionRepository.findAll().size();

        // Delete the detailSoumission
        restDetailSoumissionMockMvc.perform(delete("/api/detail-soumissions/{id}", detailSoumission.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DetailSoumission> detailSoumissionList = detailSoumissionRepository.findAll();
        assertThat(detailSoumissionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
