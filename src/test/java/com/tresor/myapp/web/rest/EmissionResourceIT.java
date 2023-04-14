package com.tresor.myapp.web.rest;

import com.tresor.myapp.IbondgeneApp;
import com.tresor.myapp.domain.Emission;
import com.tresor.myapp.repository.EmissionRepository;
import com.tresor.myapp.service.EmissionService;
import com.tresor.myapp.service.dto.EmissionDTO;
import com.tresor.myapp.service.mapper.EmissionMapper;

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
/**
 * Integration tests for the {@link EmissionResource} REST controller.
 */
@SpringBootTest(classes = IbondgeneApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EmissionResourceIT {

    private static final String DEFAULT_CODE_EMISSION = "AAAAAAAAAA";
    private static final String UPDATED_CODE_EMISSION = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGNATION_FR = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION_FR = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGNATION_EN = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION_EN = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGNATION_PT = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION_PT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_EMISSION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_EMISSION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ECHEANCE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ECHEANCE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DUREE = "AAAAAAAAAA";
    private static final String UPDATED_DUREE = "BBBBBBBBBB";

    private static final String DEFAULT_REMBOURSEMENT = "AAAAAAAAAA";
    private static final String UPDATED_REMBOURSEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_FORME_TITRE = "AAAAAAAAAA";
    private static final String UPDATED_FORME_TITRE = "BBBBBBBBBB";

    private static final Float DEFAULT_TAUX_INTERET = 0F;
    private static final Float UPDATED_TAUX_INTERET = 1F;

    private static final Double DEFAULT_VOLUME_EMISSION = 0D;
    private static final Double UPDATED_VOLUME_EMISSION = 1D;

    private static final UniteNombre DEFAULT_UNITE_VOLUME = UniteNombre.Milles;
    private static final UniteNombre UPDATED_UNITE_VOLUME = UniteNombre.Millions;

    private static final Double DEFAULT_VALEUR_NOMINALE = 0D;
    private static final Double UPDATED_VALEUR_NOMINALE = 1D;

    private static final TypeValeur DEFAULT_DEVISE = TypeValeur.FCFA;
    private static final TypeValeur UPDATED_DEVISE = TypeValeur.Euro;

    private static final Integer DEFAULT_QUANTITE_TITRE = 0;
    private static final Integer UPDATED_QUANTITE_TITRE = 1;

    private static final String DEFAULT_RENDEMENT = "AAAAAAAAAA";
    private static final String UPDATED_RENDEMENT = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_LIMITE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_LIMITE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_LIEU_SOUSCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_LIEU_SOUSCRIPTION = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_RESULTAT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_RESULTAT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATE_REGLEMENT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_REGLEMENT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final LocalDate DEFAULT_DATE_VALEUR = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_VALEUR = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_OPERATEUR = "AAAAAAAAAA";
    private static final String UPDATED_OPERATEUR = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_OPERATION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_OPERATION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private EmissionRepository emissionRepository;

    @Autowired
    private EmissionMapper emissionMapper;

    @Autowired
    private EmissionService emissionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEmissionMockMvc;

    private Emission emission;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Emission createEntity(EntityManager em) {
        Emission emission = new Emission()
            .codeEmission(DEFAULT_CODE_EMISSION)
            .designationFr(DEFAULT_DESIGNATION_FR)
            .designationEn(DEFAULT_DESIGNATION_EN)
            .designationPt(DEFAULT_DESIGNATION_PT)
            .dateEmission(DEFAULT_DATE_EMISSION)
            .echeance(DEFAULT_ECHEANCE)
            .duree(DEFAULT_DUREE)
            .remboursement(DEFAULT_REMBOURSEMENT)
            .formeTitre(DEFAULT_FORME_TITRE)
            .tauxInteret(DEFAULT_TAUX_INTERET)
            .volumeEmission(DEFAULT_VOLUME_EMISSION)
            .uniteVolume(DEFAULT_UNITE_VOLUME)
            .valeurNominale(DEFAULT_VALEUR_NOMINALE)
            .devise(DEFAULT_DEVISE)
            .quantiteTitre(DEFAULT_QUANTITE_TITRE)
            .rendement(DEFAULT_RENDEMENT)
            .dateLimite(DEFAULT_DATE_LIMITE)
            .lieuSouscription(DEFAULT_LIEU_SOUSCRIPTION)
            .dateResultat(DEFAULT_DATE_RESULTAT)
            .dateReglement(DEFAULT_DATE_REGLEMENT)
            .dateValeur(DEFAULT_DATE_VALEUR)
            .operateur(DEFAULT_OPERATEUR)
            .dateOperation(DEFAULT_DATE_OPERATION);
        return emission;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Emission createUpdatedEntity(EntityManager em) {
        Emission emission = new Emission()
            .codeEmission(UPDATED_CODE_EMISSION)
            .designationFr(UPDATED_DESIGNATION_FR)
            .designationEn(UPDATED_DESIGNATION_EN)
            .designationPt(UPDATED_DESIGNATION_PT)
            .dateEmission(UPDATED_DATE_EMISSION)
            .echeance(UPDATED_ECHEANCE)
            .duree(UPDATED_DUREE)
            .remboursement(UPDATED_REMBOURSEMENT)
            .formeTitre(UPDATED_FORME_TITRE)
            .tauxInteret(UPDATED_TAUX_INTERET)
            .volumeEmission(UPDATED_VOLUME_EMISSION)
            .uniteVolume(UPDATED_UNITE_VOLUME)
            .valeurNominale(UPDATED_VALEUR_NOMINALE)
            .devise(UPDATED_DEVISE)
            .quantiteTitre(UPDATED_QUANTITE_TITRE)
            .rendement(UPDATED_RENDEMENT)
            .dateLimite(UPDATED_DATE_LIMITE)
            .lieuSouscription(UPDATED_LIEU_SOUSCRIPTION)
            .dateResultat(UPDATED_DATE_RESULTAT)
            .dateReglement(UPDATED_DATE_REGLEMENT)
            .dateValeur(UPDATED_DATE_VALEUR)
            .operateur(UPDATED_OPERATEUR)
            .dateOperation(UPDATED_DATE_OPERATION);
        return emission;
    }

    @BeforeEach
    public void initTest() {
        emission = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmission() throws Exception {
        int databaseSizeBeforeCreate = emissionRepository.findAll().size();
        // Create the Emission
        EmissionDTO emissionDTO = emissionMapper.toDto(emission);
        restEmissionMockMvc.perform(post("/api/emissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emissionDTO)))
            .andExpect(status().isCreated());

        // Validate the Emission in the database
        List<Emission> emissionList = emissionRepository.findAll();
        assertThat(emissionList).hasSize(databaseSizeBeforeCreate + 1);
        Emission testEmission = emissionList.get(emissionList.size() - 1);
        assertThat(testEmission.getCodeEmission()).isEqualTo(DEFAULT_CODE_EMISSION);
        assertThat(testEmission.getDesignationFr()).isEqualTo(DEFAULT_DESIGNATION_FR);
        assertThat(testEmission.getDesignationEn()).isEqualTo(DEFAULT_DESIGNATION_EN);
        assertThat(testEmission.getDesignationPt()).isEqualTo(DEFAULT_DESIGNATION_PT);
        assertThat(testEmission.getDateEmission()).isEqualTo(DEFAULT_DATE_EMISSION);
        assertThat(testEmission.getEcheance()).isEqualTo(DEFAULT_ECHEANCE);
        assertThat(testEmission.getDuree()).isEqualTo(DEFAULT_DUREE);
        assertThat(testEmission.getRemboursement()).isEqualTo(DEFAULT_REMBOURSEMENT);
        assertThat(testEmission.getFormeTitre()).isEqualTo(DEFAULT_FORME_TITRE);
        assertThat(testEmission.getTauxInteret()).isEqualTo(DEFAULT_TAUX_INTERET);
        assertThat(testEmission.getVolumeEmission()).isEqualTo(DEFAULT_VOLUME_EMISSION);
        assertThat(testEmission.getUniteVolume()).isEqualTo(DEFAULT_UNITE_VOLUME);
        assertThat(testEmission.getValeurNominale()).isEqualTo(DEFAULT_VALEUR_NOMINALE);
        assertThat(testEmission.getDevise()).isEqualTo(DEFAULT_DEVISE);
        assertThat(testEmission.getQuantiteTitre()).isEqualTo(DEFAULT_QUANTITE_TITRE);
        assertThat(testEmission.getRendement()).isEqualTo(DEFAULT_RENDEMENT);
        assertThat(testEmission.getDateLimite()).isEqualTo(DEFAULT_DATE_LIMITE);
        assertThat(testEmission.getLieuSouscription()).isEqualTo(DEFAULT_LIEU_SOUSCRIPTION);
        assertThat(testEmission.getDateResultat()).isEqualTo(DEFAULT_DATE_RESULTAT);
        assertThat(testEmission.getDateReglement()).isEqualTo(DEFAULT_DATE_REGLEMENT);
        assertThat(testEmission.getDateValeur()).isEqualTo(DEFAULT_DATE_VALEUR);
        assertThat(testEmission.getOperateur()).isEqualTo(DEFAULT_OPERATEUR);
        assertThat(testEmission.getDateOperation()).isEqualTo(DEFAULT_DATE_OPERATION);
    }

    @Test
    @Transactional
    public void createEmissionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = emissionRepository.findAll().size();

        // Create the Emission with an existing ID
        emission.setId(1L);
        EmissionDTO emissionDTO = emissionMapper.toDto(emission);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmissionMockMvc.perform(post("/api/emissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emissionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Emission in the database
        List<Emission> emissionList = emissionRepository.findAll();
        assertThat(emissionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeEmissionIsRequired() throws Exception {
        int databaseSizeBeforeTest = emissionRepository.findAll().size();
        // set the field null
        emission.setCodeEmission(null);

        // Create the Emission, which fails.
        EmissionDTO emissionDTO = emissionMapper.toDto(emission);


        restEmissionMockMvc.perform(post("/api/emissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emissionDTO)))
            .andExpect(status().isBadRequest());

        List<Emission> emissionList = emissionRepository.findAll();
        assertThat(emissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateEmissionIsRequired() throws Exception {
        int databaseSizeBeforeTest = emissionRepository.findAll().size();
        // set the field null
        emission.setDateEmission(null);

        // Create the Emission, which fails.
        EmissionDTO emissionDTO = emissionMapper.toDto(emission);


        restEmissionMockMvc.perform(post("/api/emissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emissionDTO)))
            .andExpect(status().isBadRequest());

        List<Emission> emissionList = emissionRepository.findAll();
        assertThat(emissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEcheanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = emissionRepository.findAll().size();
        // set the field null
        emission.setEcheance(null);

        // Create the Emission, which fails.
        EmissionDTO emissionDTO = emissionMapper.toDto(emission);


        restEmissionMockMvc.perform(post("/api/emissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emissionDTO)))
            .andExpect(status().isBadRequest());

        List<Emission> emissionList = emissionRepository.findAll();
        assertThat(emissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTauxInteretIsRequired() throws Exception {
        int databaseSizeBeforeTest = emissionRepository.findAll().size();
        // set the field null
        emission.setTauxInteret(null);

        // Create the Emission, which fails.
        EmissionDTO emissionDTO = emissionMapper.toDto(emission);


        restEmissionMockMvc.perform(post("/api/emissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emissionDTO)))
            .andExpect(status().isBadRequest());

        List<Emission> emissionList = emissionRepository.findAll();
        assertThat(emissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVolumeEmissionIsRequired() throws Exception {
        int databaseSizeBeforeTest = emissionRepository.findAll().size();
        // set the field null
        emission.setVolumeEmission(null);

        // Create the Emission, which fails.
        EmissionDTO emissionDTO = emissionMapper.toDto(emission);


        restEmissionMockMvc.perform(post("/api/emissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emissionDTO)))
            .andExpect(status().isBadRequest());

        List<Emission> emissionList = emissionRepository.findAll();
        assertThat(emissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUniteVolumeIsRequired() throws Exception {
        int databaseSizeBeforeTest = emissionRepository.findAll().size();
        // set the field null
        emission.setUniteVolume(null);

        // Create the Emission, which fails.
        EmissionDTO emissionDTO = emissionMapper.toDto(emission);


        restEmissionMockMvc.perform(post("/api/emissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emissionDTO)))
            .andExpect(status().isBadRequest());

        List<Emission> emissionList = emissionRepository.findAll();
        assertThat(emissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValeurNominaleIsRequired() throws Exception {
        int databaseSizeBeforeTest = emissionRepository.findAll().size();
        // set the field null
        emission.setValeurNominale(null);

        // Create the Emission, which fails.
        EmissionDTO emissionDTO = emissionMapper.toDto(emission);


        restEmissionMockMvc.perform(post("/api/emissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emissionDTO)))
            .andExpect(status().isBadRequest());

        List<Emission> emissionList = emissionRepository.findAll();
        assertThat(emissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDeviseIsRequired() throws Exception {
        int databaseSizeBeforeTest = emissionRepository.findAll().size();
        // set the field null
        emission.setDevise(null);

        // Create the Emission, which fails.
        EmissionDTO emissionDTO = emissionMapper.toDto(emission);


        restEmissionMockMvc.perform(post("/api/emissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emissionDTO)))
            .andExpect(status().isBadRequest());

        List<Emission> emissionList = emissionRepository.findAll();
        assertThat(emissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateLimiteIsRequired() throws Exception {
        int databaseSizeBeforeTest = emissionRepository.findAll().size();
        // set the field null
        emission.setDateLimite(null);

        // Create the Emission, which fails.
        EmissionDTO emissionDTO = emissionMapper.toDto(emission);


        restEmissionMockMvc.perform(post("/api/emissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emissionDTO)))
            .andExpect(status().isBadRequest());

        List<Emission> emissionList = emissionRepository.findAll();
        assertThat(emissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLieuSouscriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = emissionRepository.findAll().size();
        // set the field null
        emission.setLieuSouscription(null);

        // Create the Emission, which fails.
        EmissionDTO emissionDTO = emissionMapper.toDto(emission);


        restEmissionMockMvc.perform(post("/api/emissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emissionDTO)))
            .andExpect(status().isBadRequest());

        List<Emission> emissionList = emissionRepository.findAll();
        assertThat(emissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOperateurIsRequired() throws Exception {
        int databaseSizeBeforeTest = emissionRepository.findAll().size();
        // set the field null
        emission.setOperateur(null);

        // Create the Emission, which fails.
        EmissionDTO emissionDTO = emissionMapper.toDto(emission);


        restEmissionMockMvc.perform(post("/api/emissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emissionDTO)))
            .andExpect(status().isBadRequest());

        List<Emission> emissionList = emissionRepository.findAll();
        assertThat(emissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateOperationIsRequired() throws Exception {
        int databaseSizeBeforeTest = emissionRepository.findAll().size();
        // set the field null
        emission.setDateOperation(null);

        // Create the Emission, which fails.
        EmissionDTO emissionDTO = emissionMapper.toDto(emission);


        restEmissionMockMvc.perform(post("/api/emissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emissionDTO)))
            .andExpect(status().isBadRequest());

        List<Emission> emissionList = emissionRepository.findAll();
        assertThat(emissionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEmissions() throws Exception {
        // Initialize the database
        emissionRepository.saveAndFlush(emission);

        // Get all the emissionList
        restEmissionMockMvc.perform(get("/api/emissions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(emission.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeEmission").value(hasItem(DEFAULT_CODE_EMISSION)))
            .andExpect(jsonPath("$.[*].designationFr").value(hasItem(DEFAULT_DESIGNATION_FR)))
            .andExpect(jsonPath("$.[*].designationEn").value(hasItem(DEFAULT_DESIGNATION_EN)))
            .andExpect(jsonPath("$.[*].designationPt").value(hasItem(DEFAULT_DESIGNATION_PT)))
            .andExpect(jsonPath("$.[*].dateEmission").value(hasItem(DEFAULT_DATE_EMISSION.toString())))
            .andExpect(jsonPath("$.[*].echeance").value(hasItem(DEFAULT_ECHEANCE.toString())))
            .andExpect(jsonPath("$.[*].duree").value(hasItem(DEFAULT_DUREE)))
            .andExpect(jsonPath("$.[*].remboursement").value(hasItem(DEFAULT_REMBOURSEMENT)))
            .andExpect(jsonPath("$.[*].formeTitre").value(hasItem(DEFAULT_FORME_TITRE)))
            .andExpect(jsonPath("$.[*].tauxInteret").value(hasItem(DEFAULT_TAUX_INTERET.doubleValue())))
            .andExpect(jsonPath("$.[*].volumeEmission").value(hasItem(DEFAULT_VOLUME_EMISSION.doubleValue())))
            .andExpect(jsonPath("$.[*].uniteVolume").value(hasItem(DEFAULT_UNITE_VOLUME.toString())))
            .andExpect(jsonPath("$.[*].valeurNominale").value(hasItem(DEFAULT_VALEUR_NOMINALE.doubleValue())))
            .andExpect(jsonPath("$.[*].devise").value(hasItem(DEFAULT_DEVISE.toString())))
            .andExpect(jsonPath("$.[*].quantiteTitre").value(hasItem(DEFAULT_QUANTITE_TITRE)))
            .andExpect(jsonPath("$.[*].rendement").value(hasItem(DEFAULT_RENDEMENT)))
            .andExpect(jsonPath("$.[*].dateLimite").value(hasItem(sameInstant(DEFAULT_DATE_LIMITE))))
            .andExpect(jsonPath("$.[*].lieuSouscription").value(hasItem(DEFAULT_LIEU_SOUSCRIPTION)))
            .andExpect(jsonPath("$.[*].dateResultat").value(hasItem(sameInstant(DEFAULT_DATE_RESULTAT))))
            .andExpect(jsonPath("$.[*].dateReglement").value(hasItem(sameInstant(DEFAULT_DATE_REGLEMENT))))
            .andExpect(jsonPath("$.[*].dateValeur").value(hasItem(DEFAULT_DATE_VALEUR.toString())))
            .andExpect(jsonPath("$.[*].operateur").value(hasItem(DEFAULT_OPERATEUR)))
            .andExpect(jsonPath("$.[*].dateOperation").value(hasItem(sameInstant(DEFAULT_DATE_OPERATION))));
    }
    
    @Test
    @Transactional
    public void getEmission() throws Exception {
        // Initialize the database
        emissionRepository.saveAndFlush(emission);

        // Get the emission
        restEmissionMockMvc.perform(get("/api/emissions/{id}", emission.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(emission.getId().intValue()))
            .andExpect(jsonPath("$.codeEmission").value(DEFAULT_CODE_EMISSION))
            .andExpect(jsonPath("$.designationFr").value(DEFAULT_DESIGNATION_FR))
            .andExpect(jsonPath("$.designationEn").value(DEFAULT_DESIGNATION_EN))
            .andExpect(jsonPath("$.designationPt").value(DEFAULT_DESIGNATION_PT))
            .andExpect(jsonPath("$.dateEmission").value(DEFAULT_DATE_EMISSION.toString()))
            .andExpect(jsonPath("$.echeance").value(DEFAULT_ECHEANCE.toString()))
            .andExpect(jsonPath("$.duree").value(DEFAULT_DUREE))
            .andExpect(jsonPath("$.remboursement").value(DEFAULT_REMBOURSEMENT))
            .andExpect(jsonPath("$.formeTitre").value(DEFAULT_FORME_TITRE))
            .andExpect(jsonPath("$.tauxInteret").value(DEFAULT_TAUX_INTERET.doubleValue()))
            .andExpect(jsonPath("$.volumeEmission").value(DEFAULT_VOLUME_EMISSION.doubleValue()))
            .andExpect(jsonPath("$.uniteVolume").value(DEFAULT_UNITE_VOLUME.toString()))
            .andExpect(jsonPath("$.valeurNominale").value(DEFAULT_VALEUR_NOMINALE.doubleValue()))
            .andExpect(jsonPath("$.devise").value(DEFAULT_DEVISE.toString()))
            .andExpect(jsonPath("$.quantiteTitre").value(DEFAULT_QUANTITE_TITRE))
            .andExpect(jsonPath("$.rendement").value(DEFAULT_RENDEMENT))
            .andExpect(jsonPath("$.dateLimite").value(sameInstant(DEFAULT_DATE_LIMITE)))
            .andExpect(jsonPath("$.lieuSouscription").value(DEFAULT_LIEU_SOUSCRIPTION))
            .andExpect(jsonPath("$.dateResultat").value(sameInstant(DEFAULT_DATE_RESULTAT)))
            .andExpect(jsonPath("$.dateReglement").value(sameInstant(DEFAULT_DATE_REGLEMENT)))
            .andExpect(jsonPath("$.dateValeur").value(DEFAULT_DATE_VALEUR.toString()))
            .andExpect(jsonPath("$.operateur").value(DEFAULT_OPERATEUR))
            .andExpect(jsonPath("$.dateOperation").value(sameInstant(DEFAULT_DATE_OPERATION)));
    }
    @Test
    @Transactional
    public void getNonExistingEmission() throws Exception {
        // Get the emission
        restEmissionMockMvc.perform(get("/api/emissions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmission() throws Exception {
        // Initialize the database
        emissionRepository.saveAndFlush(emission);

        int databaseSizeBeforeUpdate = emissionRepository.findAll().size();

        // Update the emission
        Emission updatedEmission = emissionRepository.findById(emission.getId()).get();
        // Disconnect from session so that the updates on updatedEmission are not directly saved in db
        em.detach(updatedEmission);
        updatedEmission
            .codeEmission(UPDATED_CODE_EMISSION)
            .designationFr(UPDATED_DESIGNATION_FR)
            .designationEn(UPDATED_DESIGNATION_EN)
            .designationPt(UPDATED_DESIGNATION_PT)
            .dateEmission(UPDATED_DATE_EMISSION)
            .echeance(UPDATED_ECHEANCE)
            .duree(UPDATED_DUREE)
            .remboursement(UPDATED_REMBOURSEMENT)
            .formeTitre(UPDATED_FORME_TITRE)
            .tauxInteret(UPDATED_TAUX_INTERET)
            .volumeEmission(UPDATED_VOLUME_EMISSION)
            .uniteVolume(UPDATED_UNITE_VOLUME)
            .valeurNominale(UPDATED_VALEUR_NOMINALE)
            .devise(UPDATED_DEVISE)
            .quantiteTitre(UPDATED_QUANTITE_TITRE)
            .rendement(UPDATED_RENDEMENT)
            .dateLimite(UPDATED_DATE_LIMITE)
            .lieuSouscription(UPDATED_LIEU_SOUSCRIPTION)
            .dateResultat(UPDATED_DATE_RESULTAT)
            .dateReglement(UPDATED_DATE_REGLEMENT)
            .dateValeur(UPDATED_DATE_VALEUR)
            .operateur(UPDATED_OPERATEUR)
            .dateOperation(UPDATED_DATE_OPERATION);
        EmissionDTO emissionDTO = emissionMapper.toDto(updatedEmission);

        restEmissionMockMvc.perform(put("/api/emissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emissionDTO)))
            .andExpect(status().isOk());

        // Validate the Emission in the database
        List<Emission> emissionList = emissionRepository.findAll();
        assertThat(emissionList).hasSize(databaseSizeBeforeUpdate);
        Emission testEmission = emissionList.get(emissionList.size() - 1);
        assertThat(testEmission.getCodeEmission()).isEqualTo(UPDATED_CODE_EMISSION);
        assertThat(testEmission.getDesignationFr()).isEqualTo(UPDATED_DESIGNATION_FR);
        assertThat(testEmission.getDesignationEn()).isEqualTo(UPDATED_DESIGNATION_EN);
        assertThat(testEmission.getDesignationPt()).isEqualTo(UPDATED_DESIGNATION_PT);
        assertThat(testEmission.getDateEmission()).isEqualTo(UPDATED_DATE_EMISSION);
        assertThat(testEmission.getEcheance()).isEqualTo(UPDATED_ECHEANCE);
        assertThat(testEmission.getDuree()).isEqualTo(UPDATED_DUREE);
        assertThat(testEmission.getRemboursement()).isEqualTo(UPDATED_REMBOURSEMENT);
        assertThat(testEmission.getFormeTitre()).isEqualTo(UPDATED_FORME_TITRE);
        assertThat(testEmission.getTauxInteret()).isEqualTo(UPDATED_TAUX_INTERET);
        assertThat(testEmission.getVolumeEmission()).isEqualTo(UPDATED_VOLUME_EMISSION);
        assertThat(testEmission.getUniteVolume()).isEqualTo(UPDATED_UNITE_VOLUME);
        assertThat(testEmission.getValeurNominale()).isEqualTo(UPDATED_VALEUR_NOMINALE);
        assertThat(testEmission.getDevise()).isEqualTo(UPDATED_DEVISE);
        assertThat(testEmission.getQuantiteTitre()).isEqualTo(UPDATED_QUANTITE_TITRE);
        assertThat(testEmission.getRendement()).isEqualTo(UPDATED_RENDEMENT);
        assertThat(testEmission.getDateLimite()).isEqualTo(UPDATED_DATE_LIMITE);
        assertThat(testEmission.getLieuSouscription()).isEqualTo(UPDATED_LIEU_SOUSCRIPTION);
        assertThat(testEmission.getDateResultat()).isEqualTo(UPDATED_DATE_RESULTAT);
        assertThat(testEmission.getDateReglement()).isEqualTo(UPDATED_DATE_REGLEMENT);
        assertThat(testEmission.getDateValeur()).isEqualTo(UPDATED_DATE_VALEUR);
        assertThat(testEmission.getOperateur()).isEqualTo(UPDATED_OPERATEUR);
        assertThat(testEmission.getDateOperation()).isEqualTo(UPDATED_DATE_OPERATION);
    }

    @Test
    @Transactional
    public void updateNonExistingEmission() throws Exception {
        int databaseSizeBeforeUpdate = emissionRepository.findAll().size();

        // Create the Emission
        EmissionDTO emissionDTO = emissionMapper.toDto(emission);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmissionMockMvc.perform(put("/api/emissions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(emissionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Emission in the database
        List<Emission> emissionList = emissionRepository.findAll();
        assertThat(emissionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEmission() throws Exception {
        // Initialize the database
        emissionRepository.saveAndFlush(emission);

        int databaseSizeBeforeDelete = emissionRepository.findAll().size();

        // Delete the emission
        restEmissionMockMvc.perform(delete("/api/emissions/{id}", emission.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Emission> emissionList = emissionRepository.findAll();
        assertThat(emissionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
