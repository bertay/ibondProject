package com.tresor.myapp.web.rest;

import com.tresor.myapp.IbondgeneApp;
import com.tresor.myapp.domain.DetailCalendrier;
import com.tresor.myapp.repository.DetailCalendrierRepository;
import com.tresor.myapp.service.DetailCalendrierService;
import com.tresor.myapp.service.dto.DetailCalendrierDTO;
import com.tresor.myapp.service.mapper.DetailCalendrierMapper;

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
import java.time.LocalDate;
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

import com.tresor.myapp.domain.enumeration.UniteNombre;
import com.tresor.myapp.domain.enumeration.TypeValeur;
import com.tresor.myapp.domain.enumeration.TypeOperation;
/**
 * Integration tests for the {@link DetailCalendrierResource} REST controller.
 */
@SpringBootTest(classes = IbondgeneApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DetailCalendrierResourceIT {

    private static final String DEFAULT_PERIODE = "AAAAAAAAAA";
    private static final String UPDATED_PERIODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_ANNEE = 1;
    private static final Integer UPDATED_ANNEE = 2;

    private static final LocalDate DEFAULT_DATE_ANNONCE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_ANNONCE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_ADJUDICATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_ADJUDICATION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_ECHEANCE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_ECHEANCE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DUREE = "AAAAAAAAAA";
    private static final String UPDATED_DUREE = "BBBBBBBBBB";

    private static final Double DEFAULT_VOLUME_EMISSION = 0D;
    private static final Double UPDATED_VOLUME_EMISSION = 1D;

    private static final UniteNombre DEFAULT_UNITE_VOLUME = UniteNombre.Milles;
    private static final UniteNombre UPDATED_UNITE_VOLUME = UniteNombre.Millions;

    private static final TypeValeur DEFAULT_DEVISE = TypeValeur.FCFA;
    private static final TypeValeur UPDATED_DEVISE = TypeValeur.Euro;

    private static final LocalDate DEFAULT_DATE_VALEUR = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_VALEUR = LocalDate.now(ZoneId.systemDefault());

    private static final TypeOperation DEFAULT_NATURE = TypeOperation.Normale;
    private static final TypeOperation UPDATED_NATURE = TypeOperation.Reouverture;

    private static final String DEFAULT_OPERATEUR = "AAAAAAAAAA";
    private static final String UPDATED_OPERATEUR = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_OPERATION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_OPERATION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private DetailCalendrierRepository detailCalendrierRepository;

    @Autowired
    private DetailCalendrierMapper detailCalendrierMapper;

    @Autowired
    private DetailCalendrierService detailCalendrierService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDetailCalendrierMockMvc;

    private DetailCalendrier detailCalendrier;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DetailCalendrier createEntity(EntityManager em) {
        DetailCalendrier detailCalendrier = new DetailCalendrier()
            .periode(DEFAULT_PERIODE)
            .annee(DEFAULT_ANNEE)
            .dateAnnonce(DEFAULT_DATE_ANNONCE)
            .dateAdjudication(DEFAULT_DATE_ADJUDICATION)
            .dateEcheance(DEFAULT_DATE_ECHEANCE)
            .duree(DEFAULT_DUREE)
            .volumeEmission(DEFAULT_VOLUME_EMISSION)
            .uniteVolume(DEFAULT_UNITE_VOLUME)
            .devise(DEFAULT_DEVISE)
            .dateValeur(DEFAULT_DATE_VALEUR)
            .nature(DEFAULT_NATURE)
            .operateur(DEFAULT_OPERATEUR)
            .dateOperation(DEFAULT_DATE_OPERATION);
        return detailCalendrier;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DetailCalendrier createUpdatedEntity(EntityManager em) {
        DetailCalendrier detailCalendrier = new DetailCalendrier()
            .periode(UPDATED_PERIODE)
            .annee(UPDATED_ANNEE)
            .dateAnnonce(UPDATED_DATE_ANNONCE)
            .dateAdjudication(UPDATED_DATE_ADJUDICATION)
            .dateEcheance(UPDATED_DATE_ECHEANCE)
            .duree(UPDATED_DUREE)
            .volumeEmission(UPDATED_VOLUME_EMISSION)
            .uniteVolume(UPDATED_UNITE_VOLUME)
            .devise(UPDATED_DEVISE)
            .dateValeur(UPDATED_DATE_VALEUR)
            .nature(UPDATED_NATURE)
            .operateur(UPDATED_OPERATEUR)
            .dateOperation(UPDATED_DATE_OPERATION);
        return detailCalendrier;
    }

    @BeforeEach
    public void initTest() {
        detailCalendrier = createEntity(em);
    }

    @Test
    @Transactional
    public void createDetailCalendrier() throws Exception {
        int databaseSizeBeforeCreate = detailCalendrierRepository.findAll().size();
        // Create the DetailCalendrier
        DetailCalendrierDTO detailCalendrierDTO = detailCalendrierMapper.toDto(detailCalendrier);
        restDetailCalendrierMockMvc.perform(post("/api/detail-calendriers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailCalendrierDTO)))
            .andExpect(status().isCreated());

        // Validate the DetailCalendrier in the database
        List<DetailCalendrier> detailCalendrierList = detailCalendrierRepository.findAll();
        assertThat(detailCalendrierList).hasSize(databaseSizeBeforeCreate + 1);
        DetailCalendrier testDetailCalendrier = detailCalendrierList.get(detailCalendrierList.size() - 1);
        assertThat(testDetailCalendrier.getPeriode()).isEqualTo(DEFAULT_PERIODE);
        assertThat(testDetailCalendrier.getAnnee()).isEqualTo(DEFAULT_ANNEE);
        assertThat(testDetailCalendrier.getDateAnnonce()).isEqualTo(DEFAULT_DATE_ANNONCE);
        assertThat(testDetailCalendrier.getDateAdjudication()).isEqualTo(DEFAULT_DATE_ADJUDICATION);
        assertThat(testDetailCalendrier.getDateEcheance()).isEqualTo(DEFAULT_DATE_ECHEANCE);
        assertThat(testDetailCalendrier.getDuree()).isEqualTo(DEFAULT_DUREE);
        assertThat(testDetailCalendrier.getVolumeEmission()).isEqualTo(DEFAULT_VOLUME_EMISSION);
        assertThat(testDetailCalendrier.getUniteVolume()).isEqualTo(DEFAULT_UNITE_VOLUME);
        assertThat(testDetailCalendrier.getDevise()).isEqualTo(DEFAULT_DEVISE);
        assertThat(testDetailCalendrier.getDateValeur()).isEqualTo(DEFAULT_DATE_VALEUR);
        assertThat(testDetailCalendrier.getNature()).isEqualTo(DEFAULT_NATURE);
        assertThat(testDetailCalendrier.getOperateur()).isEqualTo(DEFAULT_OPERATEUR);
        assertThat(testDetailCalendrier.getDateOperation()).isEqualTo(DEFAULT_DATE_OPERATION);
    }

    @Test
    @Transactional
    public void createDetailCalendrierWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = detailCalendrierRepository.findAll().size();

        // Create the DetailCalendrier with an existing ID
        detailCalendrier.setId(1L);
        DetailCalendrierDTO detailCalendrierDTO = detailCalendrierMapper.toDto(detailCalendrier);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDetailCalendrierMockMvc.perform(post("/api/detail-calendriers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailCalendrierDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DetailCalendrier in the database
        List<DetailCalendrier> detailCalendrierList = detailCalendrierRepository.findAll();
        assertThat(detailCalendrierList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPeriodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailCalendrierRepository.findAll().size();
        // set the field null
        detailCalendrier.setPeriode(null);

        // Create the DetailCalendrier, which fails.
        DetailCalendrierDTO detailCalendrierDTO = detailCalendrierMapper.toDto(detailCalendrier);


        restDetailCalendrierMockMvc.perform(post("/api/detail-calendriers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailCalendrierDTO)))
            .andExpect(status().isBadRequest());

        List<DetailCalendrier> detailCalendrierList = detailCalendrierRepository.findAll();
        assertThat(detailCalendrierList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAnneeIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailCalendrierRepository.findAll().size();
        // set the field null
        detailCalendrier.setAnnee(null);

        // Create the DetailCalendrier, which fails.
        DetailCalendrierDTO detailCalendrierDTO = detailCalendrierMapper.toDto(detailCalendrier);


        restDetailCalendrierMockMvc.perform(post("/api/detail-calendriers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailCalendrierDTO)))
            .andExpect(status().isBadRequest());

        List<DetailCalendrier> detailCalendrierList = detailCalendrierRepository.findAll();
        assertThat(detailCalendrierList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateAnnonceIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailCalendrierRepository.findAll().size();
        // set the field null
        detailCalendrier.setDateAnnonce(null);

        // Create the DetailCalendrier, which fails.
        DetailCalendrierDTO detailCalendrierDTO = detailCalendrierMapper.toDto(detailCalendrier);


        restDetailCalendrierMockMvc.perform(post("/api/detail-calendriers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailCalendrierDTO)))
            .andExpect(status().isBadRequest());

        List<DetailCalendrier> detailCalendrierList = detailCalendrierRepository.findAll();
        assertThat(detailCalendrierList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateEcheanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailCalendrierRepository.findAll().size();
        // set the field null
        detailCalendrier.setDateEcheance(null);

        // Create the DetailCalendrier, which fails.
        DetailCalendrierDTO detailCalendrierDTO = detailCalendrierMapper.toDto(detailCalendrier);


        restDetailCalendrierMockMvc.perform(post("/api/detail-calendriers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailCalendrierDTO)))
            .andExpect(status().isBadRequest());

        List<DetailCalendrier> detailCalendrierList = detailCalendrierRepository.findAll();
        assertThat(detailCalendrierList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVolumeEmissionIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailCalendrierRepository.findAll().size();
        // set the field null
        detailCalendrier.setVolumeEmission(null);

        // Create the DetailCalendrier, which fails.
        DetailCalendrierDTO detailCalendrierDTO = detailCalendrierMapper.toDto(detailCalendrier);


        restDetailCalendrierMockMvc.perform(post("/api/detail-calendriers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailCalendrierDTO)))
            .andExpect(status().isBadRequest());

        List<DetailCalendrier> detailCalendrierList = detailCalendrierRepository.findAll();
        assertThat(detailCalendrierList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUniteVolumeIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailCalendrierRepository.findAll().size();
        // set the field null
        detailCalendrier.setUniteVolume(null);

        // Create the DetailCalendrier, which fails.
        DetailCalendrierDTO detailCalendrierDTO = detailCalendrierMapper.toDto(detailCalendrier);


        restDetailCalendrierMockMvc.perform(post("/api/detail-calendriers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailCalendrierDTO)))
            .andExpect(status().isBadRequest());

        List<DetailCalendrier> detailCalendrierList = detailCalendrierRepository.findAll();
        assertThat(detailCalendrierList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDeviseIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailCalendrierRepository.findAll().size();
        // set the field null
        detailCalendrier.setDevise(null);

        // Create the DetailCalendrier, which fails.
        DetailCalendrierDTO detailCalendrierDTO = detailCalendrierMapper.toDto(detailCalendrier);


        restDetailCalendrierMockMvc.perform(post("/api/detail-calendriers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailCalendrierDTO)))
            .andExpect(status().isBadRequest());

        List<DetailCalendrier> detailCalendrierList = detailCalendrierRepository.findAll();
        assertThat(detailCalendrierList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNatureIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailCalendrierRepository.findAll().size();
        // set the field null
        detailCalendrier.setNature(null);

        // Create the DetailCalendrier, which fails.
        DetailCalendrierDTO detailCalendrierDTO = detailCalendrierMapper.toDto(detailCalendrier);


        restDetailCalendrierMockMvc.perform(post("/api/detail-calendriers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailCalendrierDTO)))
            .andExpect(status().isBadRequest());

        List<DetailCalendrier> detailCalendrierList = detailCalendrierRepository.findAll();
        assertThat(detailCalendrierList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOperateurIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailCalendrierRepository.findAll().size();
        // set the field null
        detailCalendrier.setOperateur(null);

        // Create the DetailCalendrier, which fails.
        DetailCalendrierDTO detailCalendrierDTO = detailCalendrierMapper.toDto(detailCalendrier);


        restDetailCalendrierMockMvc.perform(post("/api/detail-calendriers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailCalendrierDTO)))
            .andExpect(status().isBadRequest());

        List<DetailCalendrier> detailCalendrierList = detailCalendrierRepository.findAll();
        assertThat(detailCalendrierList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateOperationIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailCalendrierRepository.findAll().size();
        // set the field null
        detailCalendrier.setDateOperation(null);

        // Create the DetailCalendrier, which fails.
        DetailCalendrierDTO detailCalendrierDTO = detailCalendrierMapper.toDto(detailCalendrier);


        restDetailCalendrierMockMvc.perform(post("/api/detail-calendriers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailCalendrierDTO)))
            .andExpect(status().isBadRequest());

        List<DetailCalendrier> detailCalendrierList = detailCalendrierRepository.findAll();
        assertThat(detailCalendrierList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDetailCalendriers() throws Exception {
        // Initialize the database
        detailCalendrierRepository.saveAndFlush(detailCalendrier);

        // Get all the detailCalendrierList
        restDetailCalendrierMockMvc.perform(get("/api/detail-calendriers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(detailCalendrier.getId().intValue())))
            .andExpect(jsonPath("$.[*].periode").value(hasItem(DEFAULT_PERIODE)))
            .andExpect(jsonPath("$.[*].annee").value(hasItem(DEFAULT_ANNEE)))
            .andExpect(jsonPath("$.[*].dateAnnonce").value(hasItem(DEFAULT_DATE_ANNONCE.toString())))
            .andExpect(jsonPath("$.[*].dateAdjudication").value(hasItem(DEFAULT_DATE_ADJUDICATION.toString())))
            .andExpect(jsonPath("$.[*].dateEcheance").value(hasItem(DEFAULT_DATE_ECHEANCE.toString())))
            .andExpect(jsonPath("$.[*].duree").value(hasItem(DEFAULT_DUREE)))
            .andExpect(jsonPath("$.[*].volumeEmission").value(hasItem(DEFAULT_VOLUME_EMISSION.doubleValue())))
            .andExpect(jsonPath("$.[*].uniteVolume").value(hasItem(DEFAULT_UNITE_VOLUME.toString())))
            .andExpect(jsonPath("$.[*].devise").value(hasItem(DEFAULT_DEVISE.toString())))
            .andExpect(jsonPath("$.[*].dateValeur").value(hasItem(DEFAULT_DATE_VALEUR.toString())))
            .andExpect(jsonPath("$.[*].nature").value(hasItem(DEFAULT_NATURE.toString())))
            .andExpect(jsonPath("$.[*].operateur").value(hasItem(DEFAULT_OPERATEUR)))
            .andExpect(jsonPath("$.[*].dateOperation").value(hasItem(sameInstant(DEFAULT_DATE_OPERATION))));
    }
    
    @Test
    @Transactional
    public void getDetailCalendrier() throws Exception {
        // Initialize the database
        detailCalendrierRepository.saveAndFlush(detailCalendrier);

        // Get the detailCalendrier
        restDetailCalendrierMockMvc.perform(get("/api/detail-calendriers/{id}", detailCalendrier.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(detailCalendrier.getId().intValue()))
            .andExpect(jsonPath("$.periode").value(DEFAULT_PERIODE))
            .andExpect(jsonPath("$.annee").value(DEFAULT_ANNEE))
            .andExpect(jsonPath("$.dateAnnonce").value(DEFAULT_DATE_ANNONCE.toString()))
            .andExpect(jsonPath("$.dateAdjudication").value(DEFAULT_DATE_ADJUDICATION.toString()))
            .andExpect(jsonPath("$.dateEcheance").value(DEFAULT_DATE_ECHEANCE.toString()))
            .andExpect(jsonPath("$.duree").value(DEFAULT_DUREE))
            .andExpect(jsonPath("$.volumeEmission").value(DEFAULT_VOLUME_EMISSION.doubleValue()))
            .andExpect(jsonPath("$.uniteVolume").value(DEFAULT_UNITE_VOLUME.toString()))
            .andExpect(jsonPath("$.devise").value(DEFAULT_DEVISE.toString()))
            .andExpect(jsonPath("$.dateValeur").value(DEFAULT_DATE_VALEUR.toString()))
            .andExpect(jsonPath("$.nature").value(DEFAULT_NATURE.toString()))
            .andExpect(jsonPath("$.operateur").value(DEFAULT_OPERATEUR))
            .andExpect(jsonPath("$.dateOperation").value(sameInstant(DEFAULT_DATE_OPERATION)));
    }
    @Test
    @Transactional
    public void getNonExistingDetailCalendrier() throws Exception {
        // Get the detailCalendrier
        restDetailCalendrierMockMvc.perform(get("/api/detail-calendriers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDetailCalendrier() throws Exception {
        // Initialize the database
        detailCalendrierRepository.saveAndFlush(detailCalendrier);

        int databaseSizeBeforeUpdate = detailCalendrierRepository.findAll().size();

        // Update the detailCalendrier
        DetailCalendrier updatedDetailCalendrier = detailCalendrierRepository.findById(detailCalendrier.getId()).get();
        // Disconnect from session so that the updates on updatedDetailCalendrier are not directly saved in db
        em.detach(updatedDetailCalendrier);
        updatedDetailCalendrier
            .periode(UPDATED_PERIODE)
            .annee(UPDATED_ANNEE)
            .dateAnnonce(UPDATED_DATE_ANNONCE)
            .dateAdjudication(UPDATED_DATE_ADJUDICATION)
            .dateEcheance(UPDATED_DATE_ECHEANCE)
            .duree(UPDATED_DUREE)
            .volumeEmission(UPDATED_VOLUME_EMISSION)
            .uniteVolume(UPDATED_UNITE_VOLUME)
            .devise(UPDATED_DEVISE)
            .dateValeur(UPDATED_DATE_VALEUR)
            .nature(UPDATED_NATURE)
            .operateur(UPDATED_OPERATEUR)
            .dateOperation(UPDATED_DATE_OPERATION);
        DetailCalendrierDTO detailCalendrierDTO = detailCalendrierMapper.toDto(updatedDetailCalendrier);

        restDetailCalendrierMockMvc.perform(put("/api/detail-calendriers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailCalendrierDTO)))
            .andExpect(status().isOk());

        // Validate the DetailCalendrier in the database
        List<DetailCalendrier> detailCalendrierList = detailCalendrierRepository.findAll();
        assertThat(detailCalendrierList).hasSize(databaseSizeBeforeUpdate);
        DetailCalendrier testDetailCalendrier = detailCalendrierList.get(detailCalendrierList.size() - 1);
        assertThat(testDetailCalendrier.getPeriode()).isEqualTo(UPDATED_PERIODE);
        assertThat(testDetailCalendrier.getAnnee()).isEqualTo(UPDATED_ANNEE);
        assertThat(testDetailCalendrier.getDateAnnonce()).isEqualTo(UPDATED_DATE_ANNONCE);
        assertThat(testDetailCalendrier.getDateAdjudication()).isEqualTo(UPDATED_DATE_ADJUDICATION);
        assertThat(testDetailCalendrier.getDateEcheance()).isEqualTo(UPDATED_DATE_ECHEANCE);
        assertThat(testDetailCalendrier.getDuree()).isEqualTo(UPDATED_DUREE);
        assertThat(testDetailCalendrier.getVolumeEmission()).isEqualTo(UPDATED_VOLUME_EMISSION);
        assertThat(testDetailCalendrier.getUniteVolume()).isEqualTo(UPDATED_UNITE_VOLUME);
        assertThat(testDetailCalendrier.getDevise()).isEqualTo(UPDATED_DEVISE);
        assertThat(testDetailCalendrier.getDateValeur()).isEqualTo(UPDATED_DATE_VALEUR);
        assertThat(testDetailCalendrier.getNature()).isEqualTo(UPDATED_NATURE);
        assertThat(testDetailCalendrier.getOperateur()).isEqualTo(UPDATED_OPERATEUR);
        assertThat(testDetailCalendrier.getDateOperation()).isEqualTo(UPDATED_DATE_OPERATION);
    }

    @Test
    @Transactional
    public void updateNonExistingDetailCalendrier() throws Exception {
        int databaseSizeBeforeUpdate = detailCalendrierRepository.findAll().size();

        // Create the DetailCalendrier
        DetailCalendrierDTO detailCalendrierDTO = detailCalendrierMapper.toDto(detailCalendrier);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDetailCalendrierMockMvc.perform(put("/api/detail-calendriers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detailCalendrierDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DetailCalendrier in the database
        List<DetailCalendrier> detailCalendrierList = detailCalendrierRepository.findAll();
        assertThat(detailCalendrierList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDetailCalendrier() throws Exception {
        // Initialize the database
        detailCalendrierRepository.saveAndFlush(detailCalendrier);

        int databaseSizeBeforeDelete = detailCalendrierRepository.findAll().size();

        // Delete the detailCalendrier
        restDetailCalendrierMockMvc.perform(delete("/api/detail-calendriers/{id}", detailCalendrier.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DetailCalendrier> detailCalendrierList = detailCalendrierRepository.findAll();
        assertThat(detailCalendrierList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
