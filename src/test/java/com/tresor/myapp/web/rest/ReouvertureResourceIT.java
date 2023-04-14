package com.tresor.myapp.web.rest;

import com.tresor.myapp.IbondgeneApp;
import com.tresor.myapp.domain.Reouverture;
import com.tresor.myapp.repository.ReouvertureRepository;
import com.tresor.myapp.service.ReouvertureService;
import com.tresor.myapp.service.dto.ReouvertureDTO;
import com.tresor.myapp.service.mapper.ReouvertureMapper;

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
import com.tresor.myapp.domain.enumeration.UniteNombre;
import com.tresor.myapp.domain.enumeration.TypeValeur;
/**
 * Integration tests for the {@link ReouvertureResource} REST controller.
 */
@SpringBootTest(classes = IbondgeneApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ReouvertureResourceIT {

    private static final String DEFAULT_CODE_VALEUR = "AAAAAAAAAA";
    private static final String UPDATED_CODE_VALEUR = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGNATION_FR = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION_FR = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGNATION_EN = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION_EN = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGNATION_PT = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION_PT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_EMISSION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_EMISSION = LocalDate.now(ZoneId.systemDefault());

    private static final Float DEFAULT_TAUX_INTERET = 0F;
    private static final Float UPDATED_TAUX_INTERET = 1F;

    private static final Double DEFAULT_ENCOURS_EMISSION = 0D;
    private static final Double UPDATED_ENCOURS_EMISSION = 1D;

    private static final UniteNombre DEFAULT_UNITE_VOLUME = UniteNombre.Milles;
    private static final UniteNombre UPDATED_UNITE_VOLUME = UniteNombre.Millions;

    private static final Double DEFAULT_MONTANT_SOLLICITE = 0D;
    private static final Double UPDATED_MONTANT_SOLLICITE = 1D;

    private static final UniteNombre DEFAULT_UNITE_MONTANT = UniteNombre.Milles;
    private static final UniteNombre UPDATED_UNITE_MONTANT = UniteNombre.Millions;

    private static final TypeValeur DEFAULT_DEVISE = TypeValeur.FCFA;
    private static final TypeValeur UPDATED_DEVISE = TypeValeur.Euro;

    private static final LocalDate DEFAULT_DATE_ECHEANCE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_ECHEANCE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_VALEUR = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_VALEUR = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_OPERATEUR = "AAAAAAAAAA";
    private static final String UPDATED_OPERATEUR = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_OPERATION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_OPERATION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private ReouvertureRepository reouvertureRepository;

    @Autowired
    private ReouvertureMapper reouvertureMapper;

    @Autowired
    private ReouvertureService reouvertureService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restReouvertureMockMvc;

    private Reouverture reouverture;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Reouverture createEntity(EntityManager em) {
        Reouverture reouverture = new Reouverture()
            .codeValeur(DEFAULT_CODE_VALEUR)
            .designationFr(DEFAULT_DESIGNATION_FR)
            .designationEn(DEFAULT_DESIGNATION_EN)
            .designationPt(DEFAULT_DESIGNATION_PT)
            .dateEmission(DEFAULT_DATE_EMISSION)
            .tauxInteret(DEFAULT_TAUX_INTERET)
            .encoursEmission(DEFAULT_ENCOURS_EMISSION)
            .uniteVolume(DEFAULT_UNITE_VOLUME)
            .montantSollicite(DEFAULT_MONTANT_SOLLICITE)
            .uniteMontant(DEFAULT_UNITE_MONTANT)
            .devise(DEFAULT_DEVISE)
            .dateEcheance(DEFAULT_DATE_ECHEANCE)
            .dateValeur(DEFAULT_DATE_VALEUR)
            .operateur(DEFAULT_OPERATEUR)
            .dateOperation(DEFAULT_DATE_OPERATION);
        return reouverture;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Reouverture createUpdatedEntity(EntityManager em) {
        Reouverture reouverture = new Reouverture()
            .codeValeur(UPDATED_CODE_VALEUR)
            .designationFr(UPDATED_DESIGNATION_FR)
            .designationEn(UPDATED_DESIGNATION_EN)
            .designationPt(UPDATED_DESIGNATION_PT)
            .dateEmission(UPDATED_DATE_EMISSION)
            .tauxInteret(UPDATED_TAUX_INTERET)
            .encoursEmission(UPDATED_ENCOURS_EMISSION)
            .uniteVolume(UPDATED_UNITE_VOLUME)
            .montantSollicite(UPDATED_MONTANT_SOLLICITE)
            .uniteMontant(UPDATED_UNITE_MONTANT)
            .devise(UPDATED_DEVISE)
            .dateEcheance(UPDATED_DATE_ECHEANCE)
            .dateValeur(UPDATED_DATE_VALEUR)
            .operateur(UPDATED_OPERATEUR)
            .dateOperation(UPDATED_DATE_OPERATION);
        return reouverture;
    }

    @BeforeEach
    public void initTest() {
        reouverture = createEntity(em);
    }

    @Test
    @Transactional
    public void createReouverture() throws Exception {
        int databaseSizeBeforeCreate = reouvertureRepository.findAll().size();
        // Create the Reouverture
        ReouvertureDTO reouvertureDTO = reouvertureMapper.toDto(reouverture);
        restReouvertureMockMvc.perform(post("/api/reouvertures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reouvertureDTO)))
            .andExpect(status().isCreated());

        // Validate the Reouverture in the database
        List<Reouverture> reouvertureList = reouvertureRepository.findAll();
        assertThat(reouvertureList).hasSize(databaseSizeBeforeCreate + 1);
        Reouverture testReouverture = reouvertureList.get(reouvertureList.size() - 1);
        assertThat(testReouverture.getCodeValeur()).isEqualTo(DEFAULT_CODE_VALEUR);
        assertThat(testReouverture.getDesignationFr()).isEqualTo(DEFAULT_DESIGNATION_FR);
        assertThat(testReouverture.getDesignationEn()).isEqualTo(DEFAULT_DESIGNATION_EN);
        assertThat(testReouverture.getDesignationPt()).isEqualTo(DEFAULT_DESIGNATION_PT);
        assertThat(testReouverture.getDateEmission()).isEqualTo(DEFAULT_DATE_EMISSION);
        assertThat(testReouverture.getTauxInteret()).isEqualTo(DEFAULT_TAUX_INTERET);
        assertThat(testReouverture.getEncoursEmission()).isEqualTo(DEFAULT_ENCOURS_EMISSION);
        assertThat(testReouverture.getUniteVolume()).isEqualTo(DEFAULT_UNITE_VOLUME);
        assertThat(testReouverture.getMontantSollicite()).isEqualTo(DEFAULT_MONTANT_SOLLICITE);
        assertThat(testReouverture.getUniteMontant()).isEqualTo(DEFAULT_UNITE_MONTANT);
        assertThat(testReouverture.getDevise()).isEqualTo(DEFAULT_DEVISE);
        assertThat(testReouverture.getDateEcheance()).isEqualTo(DEFAULT_DATE_ECHEANCE);
        assertThat(testReouverture.getDateValeur()).isEqualTo(DEFAULT_DATE_VALEUR);
        assertThat(testReouverture.getOperateur()).isEqualTo(DEFAULT_OPERATEUR);
        assertThat(testReouverture.getDateOperation()).isEqualTo(DEFAULT_DATE_OPERATION);
    }

    @Test
    @Transactional
    public void createReouvertureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = reouvertureRepository.findAll().size();

        // Create the Reouverture with an existing ID
        reouverture.setId(1L);
        ReouvertureDTO reouvertureDTO = reouvertureMapper.toDto(reouverture);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReouvertureMockMvc.perform(post("/api/reouvertures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reouvertureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Reouverture in the database
        List<Reouverture> reouvertureList = reouvertureRepository.findAll();
        assertThat(reouvertureList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeValeurIsRequired() throws Exception {
        int databaseSizeBeforeTest = reouvertureRepository.findAll().size();
        // set the field null
        reouverture.setCodeValeur(null);

        // Create the Reouverture, which fails.
        ReouvertureDTO reouvertureDTO = reouvertureMapper.toDto(reouverture);


        restReouvertureMockMvc.perform(post("/api/reouvertures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reouvertureDTO)))
            .andExpect(status().isBadRequest());

        List<Reouverture> reouvertureList = reouvertureRepository.findAll();
        assertThat(reouvertureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateEmissionIsRequired() throws Exception {
        int databaseSizeBeforeTest = reouvertureRepository.findAll().size();
        // set the field null
        reouverture.setDateEmission(null);

        // Create the Reouverture, which fails.
        ReouvertureDTO reouvertureDTO = reouvertureMapper.toDto(reouverture);


        restReouvertureMockMvc.perform(post("/api/reouvertures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reouvertureDTO)))
            .andExpect(status().isBadRequest());

        List<Reouverture> reouvertureList = reouvertureRepository.findAll();
        assertThat(reouvertureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTauxInteretIsRequired() throws Exception {
        int databaseSizeBeforeTest = reouvertureRepository.findAll().size();
        // set the field null
        reouverture.setTauxInteret(null);

        // Create the Reouverture, which fails.
        ReouvertureDTO reouvertureDTO = reouvertureMapper.toDto(reouverture);


        restReouvertureMockMvc.perform(post("/api/reouvertures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reouvertureDTO)))
            .andExpect(status().isBadRequest());

        List<Reouverture> reouvertureList = reouvertureRepository.findAll();
        assertThat(reouvertureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEncoursEmissionIsRequired() throws Exception {
        int databaseSizeBeforeTest = reouvertureRepository.findAll().size();
        // set the field null
        reouverture.setEncoursEmission(null);

        // Create the Reouverture, which fails.
        ReouvertureDTO reouvertureDTO = reouvertureMapper.toDto(reouverture);


        restReouvertureMockMvc.perform(post("/api/reouvertures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reouvertureDTO)))
            .andExpect(status().isBadRequest());

        List<Reouverture> reouvertureList = reouvertureRepository.findAll();
        assertThat(reouvertureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUniteVolumeIsRequired() throws Exception {
        int databaseSizeBeforeTest = reouvertureRepository.findAll().size();
        // set the field null
        reouverture.setUniteVolume(null);

        // Create the Reouverture, which fails.
        ReouvertureDTO reouvertureDTO = reouvertureMapper.toDto(reouverture);


        restReouvertureMockMvc.perform(post("/api/reouvertures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reouvertureDTO)))
            .andExpect(status().isBadRequest());

        List<Reouverture> reouvertureList = reouvertureRepository.findAll();
        assertThat(reouvertureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMontantSolliciteIsRequired() throws Exception {
        int databaseSizeBeforeTest = reouvertureRepository.findAll().size();
        // set the field null
        reouverture.setMontantSollicite(null);

        // Create the Reouverture, which fails.
        ReouvertureDTO reouvertureDTO = reouvertureMapper.toDto(reouverture);


        restReouvertureMockMvc.perform(post("/api/reouvertures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reouvertureDTO)))
            .andExpect(status().isBadRequest());

        List<Reouverture> reouvertureList = reouvertureRepository.findAll();
        assertThat(reouvertureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUniteMontantIsRequired() throws Exception {
        int databaseSizeBeforeTest = reouvertureRepository.findAll().size();
        // set the field null
        reouverture.setUniteMontant(null);

        // Create the Reouverture, which fails.
        ReouvertureDTO reouvertureDTO = reouvertureMapper.toDto(reouverture);


        restReouvertureMockMvc.perform(post("/api/reouvertures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reouvertureDTO)))
            .andExpect(status().isBadRequest());

        List<Reouverture> reouvertureList = reouvertureRepository.findAll();
        assertThat(reouvertureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDeviseIsRequired() throws Exception {
        int databaseSizeBeforeTest = reouvertureRepository.findAll().size();
        // set the field null
        reouverture.setDevise(null);

        // Create the Reouverture, which fails.
        ReouvertureDTO reouvertureDTO = reouvertureMapper.toDto(reouverture);


        restReouvertureMockMvc.perform(post("/api/reouvertures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reouvertureDTO)))
            .andExpect(status().isBadRequest());

        List<Reouverture> reouvertureList = reouvertureRepository.findAll();
        assertThat(reouvertureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateEcheanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = reouvertureRepository.findAll().size();
        // set the field null
        reouverture.setDateEcheance(null);

        // Create the Reouverture, which fails.
        ReouvertureDTO reouvertureDTO = reouvertureMapper.toDto(reouverture);


        restReouvertureMockMvc.perform(post("/api/reouvertures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reouvertureDTO)))
            .andExpect(status().isBadRequest());

        List<Reouverture> reouvertureList = reouvertureRepository.findAll();
        assertThat(reouvertureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOperateurIsRequired() throws Exception {
        int databaseSizeBeforeTest = reouvertureRepository.findAll().size();
        // set the field null
        reouverture.setOperateur(null);

        // Create the Reouverture, which fails.
        ReouvertureDTO reouvertureDTO = reouvertureMapper.toDto(reouverture);


        restReouvertureMockMvc.perform(post("/api/reouvertures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reouvertureDTO)))
            .andExpect(status().isBadRequest());

        List<Reouverture> reouvertureList = reouvertureRepository.findAll();
        assertThat(reouvertureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateOperationIsRequired() throws Exception {
        int databaseSizeBeforeTest = reouvertureRepository.findAll().size();
        // set the field null
        reouverture.setDateOperation(null);

        // Create the Reouverture, which fails.
        ReouvertureDTO reouvertureDTO = reouvertureMapper.toDto(reouverture);


        restReouvertureMockMvc.perform(post("/api/reouvertures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reouvertureDTO)))
            .andExpect(status().isBadRequest());

        List<Reouverture> reouvertureList = reouvertureRepository.findAll();
        assertThat(reouvertureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllReouvertures() throws Exception {
        // Initialize the database
        reouvertureRepository.saveAndFlush(reouverture);

        // Get all the reouvertureList
        restReouvertureMockMvc.perform(get("/api/reouvertures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reouverture.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeValeur").value(hasItem(DEFAULT_CODE_VALEUR)))
            .andExpect(jsonPath("$.[*].designationFr").value(hasItem(DEFAULT_DESIGNATION_FR)))
            .andExpect(jsonPath("$.[*].designationEn").value(hasItem(DEFAULT_DESIGNATION_EN)))
            .andExpect(jsonPath("$.[*].designationPt").value(hasItem(DEFAULT_DESIGNATION_PT)))
            .andExpect(jsonPath("$.[*].dateEmission").value(hasItem(DEFAULT_DATE_EMISSION.toString())))
            .andExpect(jsonPath("$.[*].tauxInteret").value(hasItem(DEFAULT_TAUX_INTERET.doubleValue())))
            .andExpect(jsonPath("$.[*].encoursEmission").value(hasItem(DEFAULT_ENCOURS_EMISSION.doubleValue())))
            .andExpect(jsonPath("$.[*].uniteVolume").value(hasItem(DEFAULT_UNITE_VOLUME.toString())))
            .andExpect(jsonPath("$.[*].montantSollicite").value(hasItem(DEFAULT_MONTANT_SOLLICITE.doubleValue())))
            .andExpect(jsonPath("$.[*].uniteMontant").value(hasItem(DEFAULT_UNITE_MONTANT.toString())))
            .andExpect(jsonPath("$.[*].devise").value(hasItem(DEFAULT_DEVISE.toString())))
            .andExpect(jsonPath("$.[*].dateEcheance").value(hasItem(DEFAULT_DATE_ECHEANCE.toString())))
            .andExpect(jsonPath("$.[*].dateValeur").value(hasItem(DEFAULT_DATE_VALEUR.toString())))
            .andExpect(jsonPath("$.[*].operateur").value(hasItem(DEFAULT_OPERATEUR)))
            .andExpect(jsonPath("$.[*].dateOperation").value(hasItem(sameInstant(DEFAULT_DATE_OPERATION))));
    }
    
    @Test
    @Transactional
    public void getReouverture() throws Exception {
        // Initialize the database
        reouvertureRepository.saveAndFlush(reouverture);

        // Get the reouverture
        restReouvertureMockMvc.perform(get("/api/reouvertures/{id}", reouverture.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(reouverture.getId().intValue()))
            .andExpect(jsonPath("$.codeValeur").value(DEFAULT_CODE_VALEUR))
            .andExpect(jsonPath("$.designationFr").value(DEFAULT_DESIGNATION_FR))
            .andExpect(jsonPath("$.designationEn").value(DEFAULT_DESIGNATION_EN))
            .andExpect(jsonPath("$.designationPt").value(DEFAULT_DESIGNATION_PT))
            .andExpect(jsonPath("$.dateEmission").value(DEFAULT_DATE_EMISSION.toString()))
            .andExpect(jsonPath("$.tauxInteret").value(DEFAULT_TAUX_INTERET.doubleValue()))
            .andExpect(jsonPath("$.encoursEmission").value(DEFAULT_ENCOURS_EMISSION.doubleValue()))
            .andExpect(jsonPath("$.uniteVolume").value(DEFAULT_UNITE_VOLUME.toString()))
            .andExpect(jsonPath("$.montantSollicite").value(DEFAULT_MONTANT_SOLLICITE.doubleValue()))
            .andExpect(jsonPath("$.uniteMontant").value(DEFAULT_UNITE_MONTANT.toString()))
            .andExpect(jsonPath("$.devise").value(DEFAULT_DEVISE.toString()))
            .andExpect(jsonPath("$.dateEcheance").value(DEFAULT_DATE_ECHEANCE.toString()))
            .andExpect(jsonPath("$.dateValeur").value(DEFAULT_DATE_VALEUR.toString()))
            .andExpect(jsonPath("$.operateur").value(DEFAULT_OPERATEUR))
            .andExpect(jsonPath("$.dateOperation").value(sameInstant(DEFAULT_DATE_OPERATION)));
    }
    @Test
    @Transactional
    public void getNonExistingReouverture() throws Exception {
        // Get the reouverture
        restReouvertureMockMvc.perform(get("/api/reouvertures/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReouverture() throws Exception {
        // Initialize the database
        reouvertureRepository.saveAndFlush(reouverture);

        int databaseSizeBeforeUpdate = reouvertureRepository.findAll().size();

        // Update the reouverture
        Reouverture updatedReouverture = reouvertureRepository.findById(reouverture.getId()).get();
        // Disconnect from session so that the updates on updatedReouverture are not directly saved in db
        em.detach(updatedReouverture);
        updatedReouverture
            .codeValeur(UPDATED_CODE_VALEUR)
            .designationFr(UPDATED_DESIGNATION_FR)
            .designationEn(UPDATED_DESIGNATION_EN)
            .designationPt(UPDATED_DESIGNATION_PT)
            .dateEmission(UPDATED_DATE_EMISSION)
            .tauxInteret(UPDATED_TAUX_INTERET)
            .encoursEmission(UPDATED_ENCOURS_EMISSION)
            .uniteVolume(UPDATED_UNITE_VOLUME)
            .montantSollicite(UPDATED_MONTANT_SOLLICITE)
            .uniteMontant(UPDATED_UNITE_MONTANT)
            .devise(UPDATED_DEVISE)
            .dateEcheance(UPDATED_DATE_ECHEANCE)
            .dateValeur(UPDATED_DATE_VALEUR)
            .operateur(UPDATED_OPERATEUR)
            .dateOperation(UPDATED_DATE_OPERATION);
        ReouvertureDTO reouvertureDTO = reouvertureMapper.toDto(updatedReouverture);

        restReouvertureMockMvc.perform(put("/api/reouvertures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reouvertureDTO)))
            .andExpect(status().isOk());

        // Validate the Reouverture in the database
        List<Reouverture> reouvertureList = reouvertureRepository.findAll();
        assertThat(reouvertureList).hasSize(databaseSizeBeforeUpdate);
        Reouverture testReouverture = reouvertureList.get(reouvertureList.size() - 1);
        assertThat(testReouverture.getCodeValeur()).isEqualTo(UPDATED_CODE_VALEUR);
        assertThat(testReouverture.getDesignationFr()).isEqualTo(UPDATED_DESIGNATION_FR);
        assertThat(testReouverture.getDesignationEn()).isEqualTo(UPDATED_DESIGNATION_EN);
        assertThat(testReouverture.getDesignationPt()).isEqualTo(UPDATED_DESIGNATION_PT);
        assertThat(testReouverture.getDateEmission()).isEqualTo(UPDATED_DATE_EMISSION);
        assertThat(testReouverture.getTauxInteret()).isEqualTo(UPDATED_TAUX_INTERET);
        assertThat(testReouverture.getEncoursEmission()).isEqualTo(UPDATED_ENCOURS_EMISSION);
        assertThat(testReouverture.getUniteVolume()).isEqualTo(UPDATED_UNITE_VOLUME);
        assertThat(testReouverture.getMontantSollicite()).isEqualTo(UPDATED_MONTANT_SOLLICITE);
        assertThat(testReouverture.getUniteMontant()).isEqualTo(UPDATED_UNITE_MONTANT);
        assertThat(testReouverture.getDevise()).isEqualTo(UPDATED_DEVISE);
        assertThat(testReouverture.getDateEcheance()).isEqualTo(UPDATED_DATE_ECHEANCE);
        assertThat(testReouverture.getDateValeur()).isEqualTo(UPDATED_DATE_VALEUR);
        assertThat(testReouverture.getOperateur()).isEqualTo(UPDATED_OPERATEUR);
        assertThat(testReouverture.getDateOperation()).isEqualTo(UPDATED_DATE_OPERATION);
    }

    @Test
    @Transactional
    public void updateNonExistingReouverture() throws Exception {
        int databaseSizeBeforeUpdate = reouvertureRepository.findAll().size();

        // Create the Reouverture
        ReouvertureDTO reouvertureDTO = reouvertureMapper.toDto(reouverture);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReouvertureMockMvc.perform(put("/api/reouvertures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reouvertureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Reouverture in the database
        List<Reouverture> reouvertureList = reouvertureRepository.findAll();
        assertThat(reouvertureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteReouverture() throws Exception {
        // Initialize the database
        reouvertureRepository.saveAndFlush(reouverture);

        int databaseSizeBeforeDelete = reouvertureRepository.findAll().size();

        // Delete the reouverture
        restReouvertureMockMvc.perform(delete("/api/reouvertures/{id}", reouverture.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Reouverture> reouvertureList = reouvertureRepository.findAll();
        assertThat(reouvertureList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
