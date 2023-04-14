package com.tresor.myapp.web.rest;

import com.tresor.myapp.IbondgeneApp;
import com.tresor.myapp.domain.Calendrier;
import com.tresor.myapp.repository.CalendrierRepository;
import com.tresor.myapp.service.CalendrierService;
import com.tresor.myapp.service.dto.CalendrierDTO;
import com.tresor.myapp.service.mapper.CalendrierMapper;

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
 * Integration tests for the {@link CalendrierResource} REST controller.
 */
@SpringBootTest(classes = IbondgeneApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CalendrierResourceIT {

    private static final NatureActe DEFAULT_NATURE = NatureActe.Avis;
    private static final NatureActe UPDATED_NATURE = NatureActe.Calendrier;

    private static final String DEFAULT_NUMERO = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

    private static final String DEFAULT_SIGNATAIRE = "AAAAAAAAAA";
    private static final String UPDATED_SIGNATAIRE = "BBBBBBBBBB";

    private static final String DEFAULT_TITRE_CALENDRIER_FR = "AAAAAAAAAA";
    private static final String UPDATED_TITRE_CALENDRIER_FR = "BBBBBBBBBB";

    private static final String DEFAULT_TITRE_CALENDRIER_EN = "AAAAAAAAAA";
    private static final String UPDATED_TITRE_CALENDRIER_EN = "BBBBBBBBBB";

    private static final String DEFAULT_TITRE_CALENDRIER_PT = "AAAAAAAAAA";
    private static final String UPDATED_TITRE_CALENDRIER_PT = "BBBBBBBBBB";

    private static final EtatActe DEFAULT_ETAT_CALENDRIER = EtatActe.Projet;
    private static final EtatActe UPDATED_ETAT_CALENDRIER = EtatActe.Signe;

    private static final String DEFAULT_OPERATEUR = "AAAAAAAAAA";
    private static final String UPDATED_OPERATEUR = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_OPERATION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_OPERATION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private CalendrierRepository calendrierRepository;

    @Autowired
    private CalendrierMapper calendrierMapper;

    @Autowired
    private CalendrierService calendrierService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCalendrierMockMvc;

    private Calendrier calendrier;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Calendrier createEntity(EntityManager em) {
        Calendrier calendrier = new Calendrier()
            .nature(DEFAULT_NATURE)
            .numero(DEFAULT_NUMERO)
            .reference(DEFAULT_REFERENCE)
            .signataire(DEFAULT_SIGNATAIRE)
            .titreCalendrierFr(DEFAULT_TITRE_CALENDRIER_FR)
            .titreCalendrierEn(DEFAULT_TITRE_CALENDRIER_EN)
            .titreCalendrierPt(DEFAULT_TITRE_CALENDRIER_PT)
            .etatCalendrier(DEFAULT_ETAT_CALENDRIER)
            .operateur(DEFAULT_OPERATEUR)
            .dateOperation(DEFAULT_DATE_OPERATION);
        return calendrier;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Calendrier createUpdatedEntity(EntityManager em) {
        Calendrier calendrier = new Calendrier()
            .nature(UPDATED_NATURE)
            .numero(UPDATED_NUMERO)
            .reference(UPDATED_REFERENCE)
            .signataire(UPDATED_SIGNATAIRE)
            .titreCalendrierFr(UPDATED_TITRE_CALENDRIER_FR)
            .titreCalendrierEn(UPDATED_TITRE_CALENDRIER_EN)
            .titreCalendrierPt(UPDATED_TITRE_CALENDRIER_PT)
            .etatCalendrier(UPDATED_ETAT_CALENDRIER)
            .operateur(UPDATED_OPERATEUR)
            .dateOperation(UPDATED_DATE_OPERATION);
        return calendrier;
    }

    @BeforeEach
    public void initTest() {
        calendrier = createEntity(em);
    }

    @Test
    @Transactional
    public void createCalendrier() throws Exception {
        int databaseSizeBeforeCreate = calendrierRepository.findAll().size();
        // Create the Calendrier
        CalendrierDTO calendrierDTO = calendrierMapper.toDto(calendrier);
        restCalendrierMockMvc.perform(post("/api/calendriers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(calendrierDTO)))
            .andExpect(status().isCreated());

        // Validate the Calendrier in the database
        List<Calendrier> calendrierList = calendrierRepository.findAll();
        assertThat(calendrierList).hasSize(databaseSizeBeforeCreate + 1);
        Calendrier testCalendrier = calendrierList.get(calendrierList.size() - 1);
        assertThat(testCalendrier.getNature()).isEqualTo(DEFAULT_NATURE);
        assertThat(testCalendrier.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testCalendrier.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testCalendrier.getSignataire()).isEqualTo(DEFAULT_SIGNATAIRE);
        assertThat(testCalendrier.getTitreCalendrierFr()).isEqualTo(DEFAULT_TITRE_CALENDRIER_FR);
        assertThat(testCalendrier.getTitreCalendrierEn()).isEqualTo(DEFAULT_TITRE_CALENDRIER_EN);
        assertThat(testCalendrier.getTitreCalendrierPt()).isEqualTo(DEFAULT_TITRE_CALENDRIER_PT);
        assertThat(testCalendrier.getEtatCalendrier()).isEqualTo(DEFAULT_ETAT_CALENDRIER);
        assertThat(testCalendrier.getOperateur()).isEqualTo(DEFAULT_OPERATEUR);
        assertThat(testCalendrier.getDateOperation()).isEqualTo(DEFAULT_DATE_OPERATION);
    }

    @Test
    @Transactional
    public void createCalendrierWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = calendrierRepository.findAll().size();

        // Create the Calendrier with an existing ID
        calendrier.setId(1L);
        CalendrierDTO calendrierDTO = calendrierMapper.toDto(calendrier);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCalendrierMockMvc.perform(post("/api/calendriers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(calendrierDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Calendrier in the database
        List<Calendrier> calendrierList = calendrierRepository.findAll();
        assertThat(calendrierList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkOperateurIsRequired() throws Exception {
        int databaseSizeBeforeTest = calendrierRepository.findAll().size();
        // set the field null
        calendrier.setOperateur(null);

        // Create the Calendrier, which fails.
        CalendrierDTO calendrierDTO = calendrierMapper.toDto(calendrier);


        restCalendrierMockMvc.perform(post("/api/calendriers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(calendrierDTO)))
            .andExpect(status().isBadRequest());

        List<Calendrier> calendrierList = calendrierRepository.findAll();
        assertThat(calendrierList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateOperationIsRequired() throws Exception {
        int databaseSizeBeforeTest = calendrierRepository.findAll().size();
        // set the field null
        calendrier.setDateOperation(null);

        // Create the Calendrier, which fails.
        CalendrierDTO calendrierDTO = calendrierMapper.toDto(calendrier);


        restCalendrierMockMvc.perform(post("/api/calendriers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(calendrierDTO)))
            .andExpect(status().isBadRequest());

        List<Calendrier> calendrierList = calendrierRepository.findAll();
        assertThat(calendrierList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCalendriers() throws Exception {
        // Initialize the database
        calendrierRepository.saveAndFlush(calendrier);

        // Get all the calendrierList
        restCalendrierMockMvc.perform(get("/api/calendriers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(calendrier.getId().intValue())))
            .andExpect(jsonPath("$.[*].nature").value(hasItem(DEFAULT_NATURE.toString())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO)))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE)))
            .andExpect(jsonPath("$.[*].signataire").value(hasItem(DEFAULT_SIGNATAIRE)))
            .andExpect(jsonPath("$.[*].titreCalendrierFr").value(hasItem(DEFAULT_TITRE_CALENDRIER_FR)))
            .andExpect(jsonPath("$.[*].titreCalendrierEn").value(hasItem(DEFAULT_TITRE_CALENDRIER_EN)))
            .andExpect(jsonPath("$.[*].titreCalendrierPt").value(hasItem(DEFAULT_TITRE_CALENDRIER_PT)))
            .andExpect(jsonPath("$.[*].etatCalendrier").value(hasItem(DEFAULT_ETAT_CALENDRIER.toString())))
            .andExpect(jsonPath("$.[*].operateur").value(hasItem(DEFAULT_OPERATEUR)))
            .andExpect(jsonPath("$.[*].dateOperation").value(hasItem(sameInstant(DEFAULT_DATE_OPERATION))));
    }
    
    @Test
    @Transactional
    public void getCalendrier() throws Exception {
        // Initialize the database
        calendrierRepository.saveAndFlush(calendrier);

        // Get the calendrier
        restCalendrierMockMvc.perform(get("/api/calendriers/{id}", calendrier.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(calendrier.getId().intValue()))
            .andExpect(jsonPath("$.nature").value(DEFAULT_NATURE.toString()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE))
            .andExpect(jsonPath("$.signataire").value(DEFAULT_SIGNATAIRE))
            .andExpect(jsonPath("$.titreCalendrierFr").value(DEFAULT_TITRE_CALENDRIER_FR))
            .andExpect(jsonPath("$.titreCalendrierEn").value(DEFAULT_TITRE_CALENDRIER_EN))
            .andExpect(jsonPath("$.titreCalendrierPt").value(DEFAULT_TITRE_CALENDRIER_PT))
            .andExpect(jsonPath("$.etatCalendrier").value(DEFAULT_ETAT_CALENDRIER.toString()))
            .andExpect(jsonPath("$.operateur").value(DEFAULT_OPERATEUR))
            .andExpect(jsonPath("$.dateOperation").value(sameInstant(DEFAULT_DATE_OPERATION)));
    }
    @Test
    @Transactional
    public void getNonExistingCalendrier() throws Exception {
        // Get the calendrier
        restCalendrierMockMvc.perform(get("/api/calendriers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCalendrier() throws Exception {
        // Initialize the database
        calendrierRepository.saveAndFlush(calendrier);

        int databaseSizeBeforeUpdate = calendrierRepository.findAll().size();

        // Update the calendrier
        Calendrier updatedCalendrier = calendrierRepository.findById(calendrier.getId()).get();
        // Disconnect from session so that the updates on updatedCalendrier are not directly saved in db
        em.detach(updatedCalendrier);
        updatedCalendrier
            .nature(UPDATED_NATURE)
            .numero(UPDATED_NUMERO)
            .reference(UPDATED_REFERENCE)
            .signataire(UPDATED_SIGNATAIRE)
            .titreCalendrierFr(UPDATED_TITRE_CALENDRIER_FR)
            .titreCalendrierEn(UPDATED_TITRE_CALENDRIER_EN)
            .titreCalendrierPt(UPDATED_TITRE_CALENDRIER_PT)
            .etatCalendrier(UPDATED_ETAT_CALENDRIER)
            .operateur(UPDATED_OPERATEUR)
            .dateOperation(UPDATED_DATE_OPERATION);
        CalendrierDTO calendrierDTO = calendrierMapper.toDto(updatedCalendrier);

        restCalendrierMockMvc.perform(put("/api/calendriers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(calendrierDTO)))
            .andExpect(status().isOk());

        // Validate the Calendrier in the database
        List<Calendrier> calendrierList = calendrierRepository.findAll();
        assertThat(calendrierList).hasSize(databaseSizeBeforeUpdate);
        Calendrier testCalendrier = calendrierList.get(calendrierList.size() - 1);
        assertThat(testCalendrier.getNature()).isEqualTo(UPDATED_NATURE);
        assertThat(testCalendrier.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testCalendrier.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testCalendrier.getSignataire()).isEqualTo(UPDATED_SIGNATAIRE);
        assertThat(testCalendrier.getTitreCalendrierFr()).isEqualTo(UPDATED_TITRE_CALENDRIER_FR);
        assertThat(testCalendrier.getTitreCalendrierEn()).isEqualTo(UPDATED_TITRE_CALENDRIER_EN);
        assertThat(testCalendrier.getTitreCalendrierPt()).isEqualTo(UPDATED_TITRE_CALENDRIER_PT);
        assertThat(testCalendrier.getEtatCalendrier()).isEqualTo(UPDATED_ETAT_CALENDRIER);
        assertThat(testCalendrier.getOperateur()).isEqualTo(UPDATED_OPERATEUR);
        assertThat(testCalendrier.getDateOperation()).isEqualTo(UPDATED_DATE_OPERATION);
    }

    @Test
    @Transactional
    public void updateNonExistingCalendrier() throws Exception {
        int databaseSizeBeforeUpdate = calendrierRepository.findAll().size();

        // Create the Calendrier
        CalendrierDTO calendrierDTO = calendrierMapper.toDto(calendrier);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCalendrierMockMvc.perform(put("/api/calendriers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(calendrierDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Calendrier in the database
        List<Calendrier> calendrierList = calendrierRepository.findAll();
        assertThat(calendrierList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCalendrier() throws Exception {
        // Initialize the database
        calendrierRepository.saveAndFlush(calendrier);

        int databaseSizeBeforeDelete = calendrierRepository.findAll().size();

        // Delete the calendrier
        restCalendrierMockMvc.perform(delete("/api/calendriers/{id}", calendrier.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Calendrier> calendrierList = calendrierRepository.findAll();
        assertThat(calendrierList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
