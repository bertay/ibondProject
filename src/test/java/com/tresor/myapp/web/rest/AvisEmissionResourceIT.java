package com.tresor.myapp.web.rest;

import com.tresor.myapp.IbondgeneApp;
import com.tresor.myapp.domain.AvisEmission;
import com.tresor.myapp.repository.AvisEmissionRepository;
import com.tresor.myapp.service.AvisEmissionService;
import com.tresor.myapp.service.dto.AvisEmissionDTO;
import com.tresor.myapp.service.mapper.AvisEmissionMapper;

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

import com.tresor.myapp.domain.enumeration.NatureActe;
import com.tresor.myapp.domain.enumeration.EtatActe;
/**
 * Integration tests for the {@link AvisEmissionResource} REST controller.
 */
@SpringBootTest(classes = IbondgeneApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AvisEmissionResourceIT {

    private static final NatureActe DEFAULT_NATURE = NatureActe.Avis;
    private static final NatureActe UPDATED_NATURE = NatureActe.Calendrier;

    private static final String DEFAULT_NUMERO = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

    private static final String DEFAULT_SIGNATAIRE = "AAAAAAAAAA";
    private static final String UPDATED_SIGNATAIRE = "BBBBBBBBBB";

    private static final String DEFAULT_OBJET_AVIS_FR = "AAAAAAAAAA";
    private static final String UPDATED_OBJET_AVIS_FR = "BBBBBBBBBB";

    private static final String DEFAULT_OBJET_AVIS_EN = "AAAAAAAAAA";
    private static final String UPDATED_OBJET_AVIS_EN = "BBBBBBBBBB";

    private static final String DEFAULT_OBJET_AVIS_PT = "AAAAAAAAAA";
    private static final String UPDATED_OBJET_AVIS_PT = "BBBBBBBBBB";

    private static final EtatActe DEFAULT_ETAT_AVIS = EtatActe.Projet;
    private static final EtatActe UPDATED_ETAT_AVIS = EtatActe.Signe;

    private static final String DEFAULT_OPERATEUR = "AAAAAAAAAA";
    private static final String UPDATED_OPERATEUR = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_OPERATION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_OPERATION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private AvisEmissionRepository avisEmissionRepository;

    @Autowired
    private AvisEmissionMapper avisEmissionMapper;

    @Autowired
    private AvisEmissionService avisEmissionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAvisEmissionMockMvc;

    private AvisEmission avisEmission;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AvisEmission createEntity(EntityManager em) {
        AvisEmission avisEmission = new AvisEmission()
            .nature(DEFAULT_NATURE)
            .numero(DEFAULT_NUMERO)
            .reference(DEFAULT_REFERENCE)
            .signataire(DEFAULT_SIGNATAIRE)
            .objetAvisFr(DEFAULT_OBJET_AVIS_FR)
            .objetAvisEn(DEFAULT_OBJET_AVIS_EN)
            .objetAvisPt(DEFAULT_OBJET_AVIS_PT)
            .etatAvis(DEFAULT_ETAT_AVIS)
            .operateur(DEFAULT_OPERATEUR)
            .dateOperation(DEFAULT_DATE_OPERATION);
        return avisEmission;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AvisEmission createUpdatedEntity(EntityManager em) {
        AvisEmission avisEmission = new AvisEmission()
            .nature(UPDATED_NATURE)
            .numero(UPDATED_NUMERO)
            .reference(UPDATED_REFERENCE)
            .signataire(UPDATED_SIGNATAIRE)
            .objetAvisFr(UPDATED_OBJET_AVIS_FR)
            .objetAvisEn(UPDATED_OBJET_AVIS_EN)
            .objetAvisPt(UPDATED_OBJET_AVIS_PT)
            .etatAvis(UPDATED_ETAT_AVIS)
            .operateur(UPDATED_OPERATEUR)
            .dateOperation(UPDATED_DATE_OPERATION);
        return avisEmission;
    }

    @BeforeEach
    public void initTest() {
        avisEmission = createEntity(em);
    }

    @Test
    @Transactional
    public void createAvisEmission() throws Exception {
        int databaseSizeBeforeCreate = avisEmissionRepository.findAll().size();
        // Create the AvisEmission
        AvisEmissionDTO avisEmissionDTO = avisEmissionMapper.toDto(avisEmission);
        restAvisEmissionMockMvc.perform(post("/api/avis-emissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avisEmissionDTO)))
            .andExpect(status().isCreated());

        // Validate the AvisEmission in the database
        List<AvisEmission> avisEmissionList = avisEmissionRepository.findAll();
        assertThat(avisEmissionList).hasSize(databaseSizeBeforeCreate + 1);
        AvisEmission testAvisEmission = avisEmissionList.get(avisEmissionList.size() - 1);
        assertThat(testAvisEmission.getNature()).isEqualTo(DEFAULT_NATURE);
        assertThat(testAvisEmission.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testAvisEmission.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testAvisEmission.getSignataire()).isEqualTo(DEFAULT_SIGNATAIRE);
        assertThat(testAvisEmission.getObjetAvisFr()).isEqualTo(DEFAULT_OBJET_AVIS_FR);
        assertThat(testAvisEmission.getObjetAvisEn()).isEqualTo(DEFAULT_OBJET_AVIS_EN);
        assertThat(testAvisEmission.getObjetAvisPt()).isEqualTo(DEFAULT_OBJET_AVIS_PT);
        assertThat(testAvisEmission.getEtatAvis()).isEqualTo(DEFAULT_ETAT_AVIS);
        assertThat(testAvisEmission.getOperateur()).isEqualTo(DEFAULT_OPERATEUR);
        assertThat(testAvisEmission.getDateOperation()).isEqualTo(DEFAULT_DATE_OPERATION);
    }

    @Test
    @Transactional
    public void createAvisEmissionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = avisEmissionRepository.findAll().size();

        // Create the AvisEmission with an existing ID
        avisEmission.setId(1L);
        AvisEmissionDTO avisEmissionDTO = avisEmissionMapper.toDto(avisEmission);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAvisEmissionMockMvc.perform(post("/api/avis-emissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avisEmissionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AvisEmission in the database
        List<AvisEmission> avisEmissionList = avisEmissionRepository.findAll();
        assertThat(avisEmissionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkOperateurIsRequired() throws Exception {
        int databaseSizeBeforeTest = avisEmissionRepository.findAll().size();
        // set the field null
        avisEmission.setOperateur(null);

        // Create the AvisEmission, which fails.
        AvisEmissionDTO avisEmissionDTO = avisEmissionMapper.toDto(avisEmission);


        restAvisEmissionMockMvc.perform(post("/api/avis-emissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avisEmissionDTO)))
            .andExpect(status().isBadRequest());

        List<AvisEmission> avisEmissionList = avisEmissionRepository.findAll();
        assertThat(avisEmissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateOperationIsRequired() throws Exception {
        int databaseSizeBeforeTest = avisEmissionRepository.findAll().size();
        // set the field null
        avisEmission.setDateOperation(null);

        // Create the AvisEmission, which fails.
        AvisEmissionDTO avisEmissionDTO = avisEmissionMapper.toDto(avisEmission);


        restAvisEmissionMockMvc.perform(post("/api/avis-emissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avisEmissionDTO)))
            .andExpect(status().isBadRequest());

        List<AvisEmission> avisEmissionList = avisEmissionRepository.findAll();
        assertThat(avisEmissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAvisEmissions() throws Exception {
        // Initialize the database
        avisEmissionRepository.saveAndFlush(avisEmission);

        // Get all the avisEmissionList
        restAvisEmissionMockMvc.perform(get("/api/avis-emissions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(avisEmission.getId().intValue())))
            .andExpect(jsonPath("$.[*].nature").value(hasItem(DEFAULT_NATURE.toString())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO)))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE)))
            .andExpect(jsonPath("$.[*].signataire").value(hasItem(DEFAULT_SIGNATAIRE)))
            .andExpect(jsonPath("$.[*].objetAvisFr").value(hasItem(DEFAULT_OBJET_AVIS_FR)))
            .andExpect(jsonPath("$.[*].objetAvisEn").value(hasItem(DEFAULT_OBJET_AVIS_EN)))
            .andExpect(jsonPath("$.[*].objetAvisPt").value(hasItem(DEFAULT_OBJET_AVIS_PT)))
            .andExpect(jsonPath("$.[*].etatAvis").value(hasItem(DEFAULT_ETAT_AVIS.toString())))
            .andExpect(jsonPath("$.[*].operateur").value(hasItem(DEFAULT_OPERATEUR)))
            .andExpect(jsonPath("$.[*].dateOperation").value(hasItem(sameInstant(DEFAULT_DATE_OPERATION))));
    }
    
    @Test
    @Transactional
    public void getAvisEmission() throws Exception {
        // Initialize the database
        avisEmissionRepository.saveAndFlush(avisEmission);

        // Get the avisEmission
        restAvisEmissionMockMvc.perform(get("/api/avis-emissions/{id}", avisEmission.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(avisEmission.getId().intValue()))
            .andExpect(jsonPath("$.nature").value(DEFAULT_NATURE.toString()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE))
            .andExpect(jsonPath("$.signataire").value(DEFAULT_SIGNATAIRE))
            .andExpect(jsonPath("$.objetAvisFr").value(DEFAULT_OBJET_AVIS_FR))
            .andExpect(jsonPath("$.objetAvisEn").value(DEFAULT_OBJET_AVIS_EN))
            .andExpect(jsonPath("$.objetAvisPt").value(DEFAULT_OBJET_AVIS_PT))
            .andExpect(jsonPath("$.etatAvis").value(DEFAULT_ETAT_AVIS.toString()))
            .andExpect(jsonPath("$.operateur").value(DEFAULT_OPERATEUR))
            .andExpect(jsonPath("$.dateOperation").value(sameInstant(DEFAULT_DATE_OPERATION)));
    }
    @Test
    @Transactional
    public void getNonExistingAvisEmission() throws Exception {
        // Get the avisEmission
        restAvisEmissionMockMvc.perform(get("/api/avis-emissions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAvisEmission() throws Exception {
        // Initialize the database
        avisEmissionRepository.saveAndFlush(avisEmission);

        int databaseSizeBeforeUpdate = avisEmissionRepository.findAll().size();

        // Update the avisEmission
        AvisEmission updatedAvisEmission = avisEmissionRepository.findById(avisEmission.getId()).get();
        // Disconnect from session so that the updates on updatedAvisEmission are not directly saved in db
        em.detach(updatedAvisEmission);
        updatedAvisEmission
            .nature(UPDATED_NATURE)
            .numero(UPDATED_NUMERO)
            .reference(UPDATED_REFERENCE)
            .signataire(UPDATED_SIGNATAIRE)
            .objetAvisFr(UPDATED_OBJET_AVIS_FR)
            .objetAvisEn(UPDATED_OBJET_AVIS_EN)
            .objetAvisPt(UPDATED_OBJET_AVIS_PT)
            .etatAvis(UPDATED_ETAT_AVIS)
            .operateur(UPDATED_OPERATEUR)
            .dateOperation(UPDATED_DATE_OPERATION);
        AvisEmissionDTO avisEmissionDTO = avisEmissionMapper.toDto(updatedAvisEmission);

        restAvisEmissionMockMvc.perform(put("/api/avis-emissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avisEmissionDTO)))
            .andExpect(status().isOk());

        // Validate the AvisEmission in the database
        List<AvisEmission> avisEmissionList = avisEmissionRepository.findAll();
        assertThat(avisEmissionList).hasSize(databaseSizeBeforeUpdate);
        AvisEmission testAvisEmission = avisEmissionList.get(avisEmissionList.size() - 1);
        assertThat(testAvisEmission.getNature()).isEqualTo(UPDATED_NATURE);
        assertThat(testAvisEmission.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testAvisEmission.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testAvisEmission.getSignataire()).isEqualTo(UPDATED_SIGNATAIRE);
        assertThat(testAvisEmission.getObjetAvisFr()).isEqualTo(UPDATED_OBJET_AVIS_FR);
        assertThat(testAvisEmission.getObjetAvisEn()).isEqualTo(UPDATED_OBJET_AVIS_EN);
        assertThat(testAvisEmission.getObjetAvisPt()).isEqualTo(UPDATED_OBJET_AVIS_PT);
        assertThat(testAvisEmission.getEtatAvis()).isEqualTo(UPDATED_ETAT_AVIS);
        assertThat(testAvisEmission.getOperateur()).isEqualTo(UPDATED_OPERATEUR);
        assertThat(testAvisEmission.getDateOperation()).isEqualTo(UPDATED_DATE_OPERATION);
    }

    @Test
    @Transactional
    public void updateNonExistingAvisEmission() throws Exception {
        int databaseSizeBeforeUpdate = avisEmissionRepository.findAll().size();

        // Create the AvisEmission
        AvisEmissionDTO avisEmissionDTO = avisEmissionMapper.toDto(avisEmission);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAvisEmissionMockMvc.perform(put("/api/avis-emissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(avisEmissionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AvisEmission in the database
        List<AvisEmission> avisEmissionList = avisEmissionRepository.findAll();
        assertThat(avisEmissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAvisEmission() throws Exception {
        // Initialize the database
        avisEmissionRepository.saveAndFlush(avisEmission);

        int databaseSizeBeforeDelete = avisEmissionRepository.findAll().size();

        // Delete the avisEmission
        restAvisEmissionMockMvc.perform(delete("/api/avis-emissions/{id}", avisEmission.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AvisEmission> avisEmissionList = avisEmissionRepository.findAll();
        assertThat(avisEmissionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
