package com.tresor.myapp.web.rest;

import com.tresor.myapp.IbondgeneApp;
import com.tresor.myapp.domain.MembreSyndicat;
import com.tresor.myapp.repository.MembreSyndicatRepository;
import com.tresor.myapp.service.MembreSyndicatService;
import com.tresor.myapp.service.dto.MembreSyndicatDTO;
import com.tresor.myapp.service.mapper.MembreSyndicatMapper;

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

import com.tresor.myapp.domain.enumeration.TypeRang;
/**
 * Integration tests for the {@link MembreSyndicatResource} REST controller.
 */
@SpringBootTest(classes = IbondgeneApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MembreSyndicatResourceIT {

    private static final TypeRang DEFAULT_RANG = TypeRang.Chef_file;
    private static final TypeRang UPDATED_RANG = TypeRang.Membre;

    private static final Float DEFAULT_COMMISSION = 1F;
    private static final Float UPDATED_COMMISSION = 2F;

    private static final String DEFAULT_OPERATEUR = "AAAAAAAAAA";
    private static final String UPDATED_OPERATEUR = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_OPERATION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_OPERATION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private MembreSyndicatRepository membreSyndicatRepository;

    @Autowired
    private MembreSyndicatMapper membreSyndicatMapper;

    @Autowired
    private MembreSyndicatService membreSyndicatService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMembreSyndicatMockMvc;

    private MembreSyndicat membreSyndicat;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MembreSyndicat createEntity(EntityManager em) {
        MembreSyndicat membreSyndicat = new MembreSyndicat()
            .rang(DEFAULT_RANG)
            .commission(DEFAULT_COMMISSION)
            .operateur(DEFAULT_OPERATEUR)
            .dateOperation(DEFAULT_DATE_OPERATION);
        return membreSyndicat;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MembreSyndicat createUpdatedEntity(EntityManager em) {
        MembreSyndicat membreSyndicat = new MembreSyndicat()
            .rang(UPDATED_RANG)
            .commission(UPDATED_COMMISSION)
            .operateur(UPDATED_OPERATEUR)
            .dateOperation(UPDATED_DATE_OPERATION);
        return membreSyndicat;
    }

    @BeforeEach
    public void initTest() {
        membreSyndicat = createEntity(em);
    }

    @Test
    @Transactional
    public void createMembreSyndicat() throws Exception {
        int databaseSizeBeforeCreate = membreSyndicatRepository.findAll().size();
        // Create the MembreSyndicat
        MembreSyndicatDTO membreSyndicatDTO = membreSyndicatMapper.toDto(membreSyndicat);
        restMembreSyndicatMockMvc.perform(post("/api/membre-syndicats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(membreSyndicatDTO)))
            .andExpect(status().isCreated());

        // Validate the MembreSyndicat in the database
        List<MembreSyndicat> membreSyndicatList = membreSyndicatRepository.findAll();
        assertThat(membreSyndicatList).hasSize(databaseSizeBeforeCreate + 1);
        MembreSyndicat testMembreSyndicat = membreSyndicatList.get(membreSyndicatList.size() - 1);
        assertThat(testMembreSyndicat.getRang()).isEqualTo(DEFAULT_RANG);
        assertThat(testMembreSyndicat.getCommission()).isEqualTo(DEFAULT_COMMISSION);
        assertThat(testMembreSyndicat.getOperateur()).isEqualTo(DEFAULT_OPERATEUR);
        assertThat(testMembreSyndicat.getDateOperation()).isEqualTo(DEFAULT_DATE_OPERATION);
    }

    @Test
    @Transactional
    public void createMembreSyndicatWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = membreSyndicatRepository.findAll().size();

        // Create the MembreSyndicat with an existing ID
        membreSyndicat.setId(1L);
        MembreSyndicatDTO membreSyndicatDTO = membreSyndicatMapper.toDto(membreSyndicat);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMembreSyndicatMockMvc.perform(post("/api/membre-syndicats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(membreSyndicatDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MembreSyndicat in the database
        List<MembreSyndicat> membreSyndicatList = membreSyndicatRepository.findAll();
        assertThat(membreSyndicatList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkOperateurIsRequired() throws Exception {
        int databaseSizeBeforeTest = membreSyndicatRepository.findAll().size();
        // set the field null
        membreSyndicat.setOperateur(null);

        // Create the MembreSyndicat, which fails.
        MembreSyndicatDTO membreSyndicatDTO = membreSyndicatMapper.toDto(membreSyndicat);


        restMembreSyndicatMockMvc.perform(post("/api/membre-syndicats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(membreSyndicatDTO)))
            .andExpect(status().isBadRequest());

        List<MembreSyndicat> membreSyndicatList = membreSyndicatRepository.findAll();
        assertThat(membreSyndicatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateOperationIsRequired() throws Exception {
        int databaseSizeBeforeTest = membreSyndicatRepository.findAll().size();
        // set the field null
        membreSyndicat.setDateOperation(null);

        // Create the MembreSyndicat, which fails.
        MembreSyndicatDTO membreSyndicatDTO = membreSyndicatMapper.toDto(membreSyndicat);


        restMembreSyndicatMockMvc.perform(post("/api/membre-syndicats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(membreSyndicatDTO)))
            .andExpect(status().isBadRequest());

        List<MembreSyndicat> membreSyndicatList = membreSyndicatRepository.findAll();
        assertThat(membreSyndicatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMembreSyndicats() throws Exception {
        // Initialize the database
        membreSyndicatRepository.saveAndFlush(membreSyndicat);

        // Get all the membreSyndicatList
        restMembreSyndicatMockMvc.perform(get("/api/membre-syndicats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(membreSyndicat.getId().intValue())))
            .andExpect(jsonPath("$.[*].rang").value(hasItem(DEFAULT_RANG.toString())))
            .andExpect(jsonPath("$.[*].commission").value(hasItem(DEFAULT_COMMISSION.doubleValue())))
            .andExpect(jsonPath("$.[*].operateur").value(hasItem(DEFAULT_OPERATEUR)))
            .andExpect(jsonPath("$.[*].dateOperation").value(hasItem(sameInstant(DEFAULT_DATE_OPERATION))));
    }
    
    @Test
    @Transactional
    public void getMembreSyndicat() throws Exception {
        // Initialize the database
        membreSyndicatRepository.saveAndFlush(membreSyndicat);

        // Get the membreSyndicat
        restMembreSyndicatMockMvc.perform(get("/api/membre-syndicats/{id}", membreSyndicat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(membreSyndicat.getId().intValue()))
            .andExpect(jsonPath("$.rang").value(DEFAULT_RANG.toString()))
            .andExpect(jsonPath("$.commission").value(DEFAULT_COMMISSION.doubleValue()))
            .andExpect(jsonPath("$.operateur").value(DEFAULT_OPERATEUR))
            .andExpect(jsonPath("$.dateOperation").value(sameInstant(DEFAULT_DATE_OPERATION)));
    }
    @Test
    @Transactional
    public void getNonExistingMembreSyndicat() throws Exception {
        // Get the membreSyndicat
        restMembreSyndicatMockMvc.perform(get("/api/membre-syndicats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMembreSyndicat() throws Exception {
        // Initialize the database
        membreSyndicatRepository.saveAndFlush(membreSyndicat);

        int databaseSizeBeforeUpdate = membreSyndicatRepository.findAll().size();

        // Update the membreSyndicat
        MembreSyndicat updatedMembreSyndicat = membreSyndicatRepository.findById(membreSyndicat.getId()).get();
        // Disconnect from session so that the updates on updatedMembreSyndicat are not directly saved in db
        em.detach(updatedMembreSyndicat);
        updatedMembreSyndicat
            .rang(UPDATED_RANG)
            .commission(UPDATED_COMMISSION)
            .operateur(UPDATED_OPERATEUR)
            .dateOperation(UPDATED_DATE_OPERATION);
        MembreSyndicatDTO membreSyndicatDTO = membreSyndicatMapper.toDto(updatedMembreSyndicat);

        restMembreSyndicatMockMvc.perform(put("/api/membre-syndicats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(membreSyndicatDTO)))
            .andExpect(status().isOk());

        // Validate the MembreSyndicat in the database
        List<MembreSyndicat> membreSyndicatList = membreSyndicatRepository.findAll();
        assertThat(membreSyndicatList).hasSize(databaseSizeBeforeUpdate);
        MembreSyndicat testMembreSyndicat = membreSyndicatList.get(membreSyndicatList.size() - 1);
        assertThat(testMembreSyndicat.getRang()).isEqualTo(UPDATED_RANG);
        assertThat(testMembreSyndicat.getCommission()).isEqualTo(UPDATED_COMMISSION);
        assertThat(testMembreSyndicat.getOperateur()).isEqualTo(UPDATED_OPERATEUR);
        assertThat(testMembreSyndicat.getDateOperation()).isEqualTo(UPDATED_DATE_OPERATION);
    }

    @Test
    @Transactional
    public void updateNonExistingMembreSyndicat() throws Exception {
        int databaseSizeBeforeUpdate = membreSyndicatRepository.findAll().size();

        // Create the MembreSyndicat
        MembreSyndicatDTO membreSyndicatDTO = membreSyndicatMapper.toDto(membreSyndicat);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMembreSyndicatMockMvc.perform(put("/api/membre-syndicats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(membreSyndicatDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MembreSyndicat in the database
        List<MembreSyndicat> membreSyndicatList = membreSyndicatRepository.findAll();
        assertThat(membreSyndicatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMembreSyndicat() throws Exception {
        // Initialize the database
        membreSyndicatRepository.saveAndFlush(membreSyndicat);

        int databaseSizeBeforeDelete = membreSyndicatRepository.findAll().size();

        // Delete the membreSyndicat
        restMembreSyndicatMockMvc.perform(delete("/api/membre-syndicats/{id}", membreSyndicat.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MembreSyndicat> membreSyndicatList = membreSyndicatRepository.findAll();
        assertThat(membreSyndicatList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
