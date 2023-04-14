package com.tresor.myapp.web.rest;

import com.tresor.myapp.IbondgeneApp;
import com.tresor.myapp.domain.Parametres;
import com.tresor.myapp.repository.ParametresRepository;
import com.tresor.myapp.service.ParametresService;
import com.tresor.myapp.service.dto.ParametresDTO;
import com.tresor.myapp.service.mapper.ParametresMapper;

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
 * Integration tests for the {@link ParametresResource} REST controller.
 */
@SpringBootTest(classes = IbondgeneApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ParametresResourceIT {

    private static final String DEFAULT_ADRESSE_SERVEUR = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE_SERVEUR = "BBBBBBBBBB";

    private static final String DEFAULT_TIMBRE_SERVICE_1 = "AAAAAAAAAA";
    private static final String UPDATED_TIMBRE_SERVICE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_TIMBRE_SERVICE_2 = "AAAAAAAAAA";
    private static final String UPDATED_TIMBRE_SERVICE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_TIMBRE_SERVICE_3 = "AAAAAAAAAA";
    private static final String UPDATED_TIMBRE_SERVICE_3 = "BBBBBBBBBB";

    private static final String DEFAULT_OPERATEUR = "AAAAAAAAAA";
    private static final String UPDATED_OPERATEUR = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_OPERATION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_OPERATION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private ParametresRepository parametresRepository;

    @Autowired
    private ParametresMapper parametresMapper;

    @Autowired
    private ParametresService parametresService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restParametresMockMvc;

    private Parametres parametres;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Parametres createEntity(EntityManager em) {
        Parametres parametres = new Parametres()
            .adresseServeur(DEFAULT_ADRESSE_SERVEUR)
            .timbreService1(DEFAULT_TIMBRE_SERVICE_1)
            .timbreService2(DEFAULT_TIMBRE_SERVICE_2)
            .timbreService3(DEFAULT_TIMBRE_SERVICE_3)
            .operateur(DEFAULT_OPERATEUR)
            .dateOperation(DEFAULT_DATE_OPERATION);
        return parametres;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Parametres createUpdatedEntity(EntityManager em) {
        Parametres parametres = new Parametres()
            .adresseServeur(UPDATED_ADRESSE_SERVEUR)
            .timbreService1(UPDATED_TIMBRE_SERVICE_1)
            .timbreService2(UPDATED_TIMBRE_SERVICE_2)
            .timbreService3(UPDATED_TIMBRE_SERVICE_3)
            .operateur(UPDATED_OPERATEUR)
            .dateOperation(UPDATED_DATE_OPERATION);
        return parametres;
    }

    @BeforeEach
    public void initTest() {
        parametres = createEntity(em);
    }

    @Test
    @Transactional
    public void createParametres() throws Exception {
        int databaseSizeBeforeCreate = parametresRepository.findAll().size();
        // Create the Parametres
        ParametresDTO parametresDTO = parametresMapper.toDto(parametres);
        restParametresMockMvc.perform(post("/api/parametres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(parametresDTO)))
            .andExpect(status().isCreated());

        // Validate the Parametres in the database
        List<Parametres> parametresList = parametresRepository.findAll();
        assertThat(parametresList).hasSize(databaseSizeBeforeCreate + 1);
        Parametres testParametres = parametresList.get(parametresList.size() - 1);
        assertThat(testParametres.getAdresseServeur()).isEqualTo(DEFAULT_ADRESSE_SERVEUR);
        assertThat(testParametres.getTimbreService1()).isEqualTo(DEFAULT_TIMBRE_SERVICE_1);
        assertThat(testParametres.getTimbreService2()).isEqualTo(DEFAULT_TIMBRE_SERVICE_2);
        assertThat(testParametres.getTimbreService3()).isEqualTo(DEFAULT_TIMBRE_SERVICE_3);
        assertThat(testParametres.getOperateur()).isEqualTo(DEFAULT_OPERATEUR);
        assertThat(testParametres.getDateOperation()).isEqualTo(DEFAULT_DATE_OPERATION);
    }

    @Test
    @Transactional
    public void createParametresWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = parametresRepository.findAll().size();

        // Create the Parametres with an existing ID
        parametres.setId(1L);
        ParametresDTO parametresDTO = parametresMapper.toDto(parametres);

        // An entity with an existing ID cannot be created, so this API call must fail
        restParametresMockMvc.perform(post("/api/parametres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(parametresDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Parametres in the database
        List<Parametres> parametresList = parametresRepository.findAll();
        assertThat(parametresList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkOperateurIsRequired() throws Exception {
        int databaseSizeBeforeTest = parametresRepository.findAll().size();
        // set the field null
        parametres.setOperateur(null);

        // Create the Parametres, which fails.
        ParametresDTO parametresDTO = parametresMapper.toDto(parametres);


        restParametresMockMvc.perform(post("/api/parametres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(parametresDTO)))
            .andExpect(status().isBadRequest());

        List<Parametres> parametresList = parametresRepository.findAll();
        assertThat(parametresList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateOperationIsRequired() throws Exception {
        int databaseSizeBeforeTest = parametresRepository.findAll().size();
        // set the field null
        parametres.setDateOperation(null);

        // Create the Parametres, which fails.
        ParametresDTO parametresDTO = parametresMapper.toDto(parametres);


        restParametresMockMvc.perform(post("/api/parametres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(parametresDTO)))
            .andExpect(status().isBadRequest());

        List<Parametres> parametresList = parametresRepository.findAll();
        assertThat(parametresList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllParametres() throws Exception {
        // Initialize the database
        parametresRepository.saveAndFlush(parametres);

        // Get all the parametresList
        restParametresMockMvc.perform(get("/api/parametres?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(parametres.getId().intValue())))
            .andExpect(jsonPath("$.[*].adresseServeur").value(hasItem(DEFAULT_ADRESSE_SERVEUR)))
            .andExpect(jsonPath("$.[*].timbreService1").value(hasItem(DEFAULT_TIMBRE_SERVICE_1)))
            .andExpect(jsonPath("$.[*].timbreService2").value(hasItem(DEFAULT_TIMBRE_SERVICE_2)))
            .andExpect(jsonPath("$.[*].timbreService3").value(hasItem(DEFAULT_TIMBRE_SERVICE_3)))
            .andExpect(jsonPath("$.[*].operateur").value(hasItem(DEFAULT_OPERATEUR)))
            .andExpect(jsonPath("$.[*].dateOperation").value(hasItem(sameInstant(DEFAULT_DATE_OPERATION))));
    }
    
    @Test
    @Transactional
    public void getParametres() throws Exception {
        // Initialize the database
        parametresRepository.saveAndFlush(parametres);

        // Get the parametres
        restParametresMockMvc.perform(get("/api/parametres/{id}", parametres.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(parametres.getId().intValue()))
            .andExpect(jsonPath("$.adresseServeur").value(DEFAULT_ADRESSE_SERVEUR))
            .andExpect(jsonPath("$.timbreService1").value(DEFAULT_TIMBRE_SERVICE_1))
            .andExpect(jsonPath("$.timbreService2").value(DEFAULT_TIMBRE_SERVICE_2))
            .andExpect(jsonPath("$.timbreService3").value(DEFAULT_TIMBRE_SERVICE_3))
            .andExpect(jsonPath("$.operateur").value(DEFAULT_OPERATEUR))
            .andExpect(jsonPath("$.dateOperation").value(sameInstant(DEFAULT_DATE_OPERATION)));
    }
    @Test
    @Transactional
    public void getNonExistingParametres() throws Exception {
        // Get the parametres
        restParametresMockMvc.perform(get("/api/parametres/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateParametres() throws Exception {
        // Initialize the database
        parametresRepository.saveAndFlush(parametres);

        int databaseSizeBeforeUpdate = parametresRepository.findAll().size();

        // Update the parametres
        Parametres updatedParametres = parametresRepository.findById(parametres.getId()).get();
        // Disconnect from session so that the updates on updatedParametres are not directly saved in db
        em.detach(updatedParametres);
        updatedParametres
            .adresseServeur(UPDATED_ADRESSE_SERVEUR)
            .timbreService1(UPDATED_TIMBRE_SERVICE_1)
            .timbreService2(UPDATED_TIMBRE_SERVICE_2)
            .timbreService3(UPDATED_TIMBRE_SERVICE_3)
            .operateur(UPDATED_OPERATEUR)
            .dateOperation(UPDATED_DATE_OPERATION);
        ParametresDTO parametresDTO = parametresMapper.toDto(updatedParametres);

        restParametresMockMvc.perform(put("/api/parametres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(parametresDTO)))
            .andExpect(status().isOk());

        // Validate the Parametres in the database
        List<Parametres> parametresList = parametresRepository.findAll();
        assertThat(parametresList).hasSize(databaseSizeBeforeUpdate);
        Parametres testParametres = parametresList.get(parametresList.size() - 1);
        assertThat(testParametres.getAdresseServeur()).isEqualTo(UPDATED_ADRESSE_SERVEUR);
        assertThat(testParametres.getTimbreService1()).isEqualTo(UPDATED_TIMBRE_SERVICE_1);
        assertThat(testParametres.getTimbreService2()).isEqualTo(UPDATED_TIMBRE_SERVICE_2);
        assertThat(testParametres.getTimbreService3()).isEqualTo(UPDATED_TIMBRE_SERVICE_3);
        assertThat(testParametres.getOperateur()).isEqualTo(UPDATED_OPERATEUR);
        assertThat(testParametres.getDateOperation()).isEqualTo(UPDATED_DATE_OPERATION);
    }

    @Test
    @Transactional
    public void updateNonExistingParametres() throws Exception {
        int databaseSizeBeforeUpdate = parametresRepository.findAll().size();

        // Create the Parametres
        ParametresDTO parametresDTO = parametresMapper.toDto(parametres);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restParametresMockMvc.perform(put("/api/parametres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(parametresDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Parametres in the database
        List<Parametres> parametresList = parametresRepository.findAll();
        assertThat(parametresList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteParametres() throws Exception {
        // Initialize the database
        parametresRepository.saveAndFlush(parametres);

        int databaseSizeBeforeDelete = parametresRepository.findAll().size();

        // Delete the parametres
        restParametresMockMvc.perform(delete("/api/parametres/{id}", parametres.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Parametres> parametresList = parametresRepository.findAll();
        assertThat(parametresList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
