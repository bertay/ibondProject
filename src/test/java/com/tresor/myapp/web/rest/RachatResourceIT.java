package com.tresor.myapp.web.rest;

import com.tresor.myapp.IbondgeneApp;
import com.tresor.myapp.domain.Rachat;
import com.tresor.myapp.repository.RachatRepository;
import com.tresor.myapp.service.RachatService;
import com.tresor.myapp.service.dto.RachatDTO;
import com.tresor.myapp.service.mapper.RachatMapper;

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
 * Integration tests for the {@link RachatResource} REST controller.
 */
@SpringBootTest(classes = IbondgeneApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RachatResourceIT {

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

    private static final Double DEFAULT_MONTANT_NOMINAL = 0D;
    private static final Double UPDATED_MONTANT_NOMINAL = 1D;

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
    private RachatRepository rachatRepository;

    @Autowired
    private RachatMapper rachatMapper;

    @Autowired
    private RachatService rachatService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRachatMockMvc;

    private Rachat rachat;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Rachat createEntity(EntityManager em) {
        Rachat rachat = new Rachat()
            .codeValeur(DEFAULT_CODE_VALEUR)
            .designationFr(DEFAULT_DESIGNATION_FR)
            .designationEn(DEFAULT_DESIGNATION_EN)
            .designationPt(DEFAULT_DESIGNATION_PT)
            .dateEmission(DEFAULT_DATE_EMISSION)
            .tauxInteret(DEFAULT_TAUX_INTERET)
            .montantNominal(DEFAULT_MONTANT_NOMINAL)
            .uniteMontant(DEFAULT_UNITE_MONTANT)
            .devise(DEFAULT_DEVISE)
            .dateEcheance(DEFAULT_DATE_ECHEANCE)
            .dateValeur(DEFAULT_DATE_VALEUR)
            .operateur(DEFAULT_OPERATEUR)
            .dateOperation(DEFAULT_DATE_OPERATION);
        return rachat;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Rachat createUpdatedEntity(EntityManager em) {
        Rachat rachat = new Rachat()
            .codeValeur(UPDATED_CODE_VALEUR)
            .designationFr(UPDATED_DESIGNATION_FR)
            .designationEn(UPDATED_DESIGNATION_EN)
            .designationPt(UPDATED_DESIGNATION_PT)
            .dateEmission(UPDATED_DATE_EMISSION)
            .tauxInteret(UPDATED_TAUX_INTERET)
            .montantNominal(UPDATED_MONTANT_NOMINAL)
            .uniteMontant(UPDATED_UNITE_MONTANT)
            .devise(UPDATED_DEVISE)
            .dateEcheance(UPDATED_DATE_ECHEANCE)
            .dateValeur(UPDATED_DATE_VALEUR)
            .operateur(UPDATED_OPERATEUR)
            .dateOperation(UPDATED_DATE_OPERATION);
        return rachat;
    }

    @BeforeEach
    public void initTest() {
        rachat = createEntity(em);
    }

    @Test
    @Transactional
    public void createRachat() throws Exception {
        int databaseSizeBeforeCreate = rachatRepository.findAll().size();
        // Create the Rachat
        RachatDTO rachatDTO = rachatMapper.toDto(rachat);
        restRachatMockMvc.perform(post("/api/rachats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rachatDTO)))
            .andExpect(status().isCreated());

        // Validate the Rachat in the database
        List<Rachat> rachatList = rachatRepository.findAll();
        assertThat(rachatList).hasSize(databaseSizeBeforeCreate + 1);
        Rachat testRachat = rachatList.get(rachatList.size() - 1);
        assertThat(testRachat.getCodeValeur()).isEqualTo(DEFAULT_CODE_VALEUR);
        assertThat(testRachat.getDesignationFr()).isEqualTo(DEFAULT_DESIGNATION_FR);
        assertThat(testRachat.getDesignationEn()).isEqualTo(DEFAULT_DESIGNATION_EN);
        assertThat(testRachat.getDesignationPt()).isEqualTo(DEFAULT_DESIGNATION_PT);
        assertThat(testRachat.getDateEmission()).isEqualTo(DEFAULT_DATE_EMISSION);
        assertThat(testRachat.getTauxInteret()).isEqualTo(DEFAULT_TAUX_INTERET);
        assertThat(testRachat.getMontantNominal()).isEqualTo(DEFAULT_MONTANT_NOMINAL);
        assertThat(testRachat.getUniteMontant()).isEqualTo(DEFAULT_UNITE_MONTANT);
        assertThat(testRachat.getDevise()).isEqualTo(DEFAULT_DEVISE);
        assertThat(testRachat.getDateEcheance()).isEqualTo(DEFAULT_DATE_ECHEANCE);
        assertThat(testRachat.getDateValeur()).isEqualTo(DEFAULT_DATE_VALEUR);
        assertThat(testRachat.getOperateur()).isEqualTo(DEFAULT_OPERATEUR);
        assertThat(testRachat.getDateOperation()).isEqualTo(DEFAULT_DATE_OPERATION);
    }

    @Test
    @Transactional
    public void createRachatWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rachatRepository.findAll().size();

        // Create the Rachat with an existing ID
        rachat.setId(1L);
        RachatDTO rachatDTO = rachatMapper.toDto(rachat);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRachatMockMvc.perform(post("/api/rachats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rachatDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Rachat in the database
        List<Rachat> rachatList = rachatRepository.findAll();
        assertThat(rachatList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeValeurIsRequired() throws Exception {
        int databaseSizeBeforeTest = rachatRepository.findAll().size();
        // set the field null
        rachat.setCodeValeur(null);

        // Create the Rachat, which fails.
        RachatDTO rachatDTO = rachatMapper.toDto(rachat);


        restRachatMockMvc.perform(post("/api/rachats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rachatDTO)))
            .andExpect(status().isBadRequest());

        List<Rachat> rachatList = rachatRepository.findAll();
        assertThat(rachatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateEmissionIsRequired() throws Exception {
        int databaseSizeBeforeTest = rachatRepository.findAll().size();
        // set the field null
        rachat.setDateEmission(null);

        // Create the Rachat, which fails.
        RachatDTO rachatDTO = rachatMapper.toDto(rachat);


        restRachatMockMvc.perform(post("/api/rachats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rachatDTO)))
            .andExpect(status().isBadRequest());

        List<Rachat> rachatList = rachatRepository.findAll();
        assertThat(rachatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTauxInteretIsRequired() throws Exception {
        int databaseSizeBeforeTest = rachatRepository.findAll().size();
        // set the field null
        rachat.setTauxInteret(null);

        // Create the Rachat, which fails.
        RachatDTO rachatDTO = rachatMapper.toDto(rachat);


        restRachatMockMvc.perform(post("/api/rachats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rachatDTO)))
            .andExpect(status().isBadRequest());

        List<Rachat> rachatList = rachatRepository.findAll();
        assertThat(rachatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMontantNominalIsRequired() throws Exception {
        int databaseSizeBeforeTest = rachatRepository.findAll().size();
        // set the field null
        rachat.setMontantNominal(null);

        // Create the Rachat, which fails.
        RachatDTO rachatDTO = rachatMapper.toDto(rachat);


        restRachatMockMvc.perform(post("/api/rachats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rachatDTO)))
            .andExpect(status().isBadRequest());

        List<Rachat> rachatList = rachatRepository.findAll();
        assertThat(rachatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUniteMontantIsRequired() throws Exception {
        int databaseSizeBeforeTest = rachatRepository.findAll().size();
        // set the field null
        rachat.setUniteMontant(null);

        // Create the Rachat, which fails.
        RachatDTO rachatDTO = rachatMapper.toDto(rachat);


        restRachatMockMvc.perform(post("/api/rachats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rachatDTO)))
            .andExpect(status().isBadRequest());

        List<Rachat> rachatList = rachatRepository.findAll();
        assertThat(rachatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDeviseIsRequired() throws Exception {
        int databaseSizeBeforeTest = rachatRepository.findAll().size();
        // set the field null
        rachat.setDevise(null);

        // Create the Rachat, which fails.
        RachatDTO rachatDTO = rachatMapper.toDto(rachat);


        restRachatMockMvc.perform(post("/api/rachats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rachatDTO)))
            .andExpect(status().isBadRequest());

        List<Rachat> rachatList = rachatRepository.findAll();
        assertThat(rachatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateEcheanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = rachatRepository.findAll().size();
        // set the field null
        rachat.setDateEcheance(null);

        // Create the Rachat, which fails.
        RachatDTO rachatDTO = rachatMapper.toDto(rachat);


        restRachatMockMvc.perform(post("/api/rachats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rachatDTO)))
            .andExpect(status().isBadRequest());

        List<Rachat> rachatList = rachatRepository.findAll();
        assertThat(rachatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOperateurIsRequired() throws Exception {
        int databaseSizeBeforeTest = rachatRepository.findAll().size();
        // set the field null
        rachat.setOperateur(null);

        // Create the Rachat, which fails.
        RachatDTO rachatDTO = rachatMapper.toDto(rachat);


        restRachatMockMvc.perform(post("/api/rachats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rachatDTO)))
            .andExpect(status().isBadRequest());

        List<Rachat> rachatList = rachatRepository.findAll();
        assertThat(rachatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateOperationIsRequired() throws Exception {
        int databaseSizeBeforeTest = rachatRepository.findAll().size();
        // set the field null
        rachat.setDateOperation(null);

        // Create the Rachat, which fails.
        RachatDTO rachatDTO = rachatMapper.toDto(rachat);


        restRachatMockMvc.perform(post("/api/rachats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rachatDTO)))
            .andExpect(status().isBadRequest());

        List<Rachat> rachatList = rachatRepository.findAll();
        assertThat(rachatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRachats() throws Exception {
        // Initialize the database
        rachatRepository.saveAndFlush(rachat);

        // Get all the rachatList
        restRachatMockMvc.perform(get("/api/rachats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rachat.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeValeur").value(hasItem(DEFAULT_CODE_VALEUR)))
            .andExpect(jsonPath("$.[*].designationFr").value(hasItem(DEFAULT_DESIGNATION_FR)))
            .andExpect(jsonPath("$.[*].designationEn").value(hasItem(DEFAULT_DESIGNATION_EN)))
            .andExpect(jsonPath("$.[*].designationPt").value(hasItem(DEFAULT_DESIGNATION_PT)))
            .andExpect(jsonPath("$.[*].dateEmission").value(hasItem(DEFAULT_DATE_EMISSION.toString())))
            .andExpect(jsonPath("$.[*].tauxInteret").value(hasItem(DEFAULT_TAUX_INTERET.doubleValue())))
            .andExpect(jsonPath("$.[*].montantNominal").value(hasItem(DEFAULT_MONTANT_NOMINAL.doubleValue())))
            .andExpect(jsonPath("$.[*].uniteMontant").value(hasItem(DEFAULT_UNITE_MONTANT.toString())))
            .andExpect(jsonPath("$.[*].devise").value(hasItem(DEFAULT_DEVISE.toString())))
            .andExpect(jsonPath("$.[*].dateEcheance").value(hasItem(DEFAULT_DATE_ECHEANCE.toString())))
            .andExpect(jsonPath("$.[*].dateValeur").value(hasItem(DEFAULT_DATE_VALEUR.toString())))
            .andExpect(jsonPath("$.[*].operateur").value(hasItem(DEFAULT_OPERATEUR)))
            .andExpect(jsonPath("$.[*].dateOperation").value(hasItem(sameInstant(DEFAULT_DATE_OPERATION))));
    }
    
    @Test
    @Transactional
    public void getRachat() throws Exception {
        // Initialize the database
        rachatRepository.saveAndFlush(rachat);

        // Get the rachat
        restRachatMockMvc.perform(get("/api/rachats/{id}", rachat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rachat.getId().intValue()))
            .andExpect(jsonPath("$.codeValeur").value(DEFAULT_CODE_VALEUR))
            .andExpect(jsonPath("$.designationFr").value(DEFAULT_DESIGNATION_FR))
            .andExpect(jsonPath("$.designationEn").value(DEFAULT_DESIGNATION_EN))
            .andExpect(jsonPath("$.designationPt").value(DEFAULT_DESIGNATION_PT))
            .andExpect(jsonPath("$.dateEmission").value(DEFAULT_DATE_EMISSION.toString()))
            .andExpect(jsonPath("$.tauxInteret").value(DEFAULT_TAUX_INTERET.doubleValue()))
            .andExpect(jsonPath("$.montantNominal").value(DEFAULT_MONTANT_NOMINAL.doubleValue()))
            .andExpect(jsonPath("$.uniteMontant").value(DEFAULT_UNITE_MONTANT.toString()))
            .andExpect(jsonPath("$.devise").value(DEFAULT_DEVISE.toString()))
            .andExpect(jsonPath("$.dateEcheance").value(DEFAULT_DATE_ECHEANCE.toString()))
            .andExpect(jsonPath("$.dateValeur").value(DEFAULT_DATE_VALEUR.toString()))
            .andExpect(jsonPath("$.operateur").value(DEFAULT_OPERATEUR))
            .andExpect(jsonPath("$.dateOperation").value(sameInstant(DEFAULT_DATE_OPERATION)));
    }
    @Test
    @Transactional
    public void getNonExistingRachat() throws Exception {
        // Get the rachat
        restRachatMockMvc.perform(get("/api/rachats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRachat() throws Exception {
        // Initialize the database
        rachatRepository.saveAndFlush(rachat);

        int databaseSizeBeforeUpdate = rachatRepository.findAll().size();

        // Update the rachat
        Rachat updatedRachat = rachatRepository.findById(rachat.getId()).get();
        // Disconnect from session so that the updates on updatedRachat are not directly saved in db
        em.detach(updatedRachat);
        updatedRachat
            .codeValeur(UPDATED_CODE_VALEUR)
            .designationFr(UPDATED_DESIGNATION_FR)
            .designationEn(UPDATED_DESIGNATION_EN)
            .designationPt(UPDATED_DESIGNATION_PT)
            .dateEmission(UPDATED_DATE_EMISSION)
            .tauxInteret(UPDATED_TAUX_INTERET)
            .montantNominal(UPDATED_MONTANT_NOMINAL)
            .uniteMontant(UPDATED_UNITE_MONTANT)
            .devise(UPDATED_DEVISE)
            .dateEcheance(UPDATED_DATE_ECHEANCE)
            .dateValeur(UPDATED_DATE_VALEUR)
            .operateur(UPDATED_OPERATEUR)
            .dateOperation(UPDATED_DATE_OPERATION);
        RachatDTO rachatDTO = rachatMapper.toDto(updatedRachat);

        restRachatMockMvc.perform(put("/api/rachats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rachatDTO)))
            .andExpect(status().isOk());

        // Validate the Rachat in the database
        List<Rachat> rachatList = rachatRepository.findAll();
        assertThat(rachatList).hasSize(databaseSizeBeforeUpdate);
        Rachat testRachat = rachatList.get(rachatList.size() - 1);
        assertThat(testRachat.getCodeValeur()).isEqualTo(UPDATED_CODE_VALEUR);
        assertThat(testRachat.getDesignationFr()).isEqualTo(UPDATED_DESIGNATION_FR);
        assertThat(testRachat.getDesignationEn()).isEqualTo(UPDATED_DESIGNATION_EN);
        assertThat(testRachat.getDesignationPt()).isEqualTo(UPDATED_DESIGNATION_PT);
        assertThat(testRachat.getDateEmission()).isEqualTo(UPDATED_DATE_EMISSION);
        assertThat(testRachat.getTauxInteret()).isEqualTo(UPDATED_TAUX_INTERET);
        assertThat(testRachat.getMontantNominal()).isEqualTo(UPDATED_MONTANT_NOMINAL);
        assertThat(testRachat.getUniteMontant()).isEqualTo(UPDATED_UNITE_MONTANT);
        assertThat(testRachat.getDevise()).isEqualTo(UPDATED_DEVISE);
        assertThat(testRachat.getDateEcheance()).isEqualTo(UPDATED_DATE_ECHEANCE);
        assertThat(testRachat.getDateValeur()).isEqualTo(UPDATED_DATE_VALEUR);
        assertThat(testRachat.getOperateur()).isEqualTo(UPDATED_OPERATEUR);
        assertThat(testRachat.getDateOperation()).isEqualTo(UPDATED_DATE_OPERATION);
    }

    @Test
    @Transactional
    public void updateNonExistingRachat() throws Exception {
        int databaseSizeBeforeUpdate = rachatRepository.findAll().size();

        // Create the Rachat
        RachatDTO rachatDTO = rachatMapper.toDto(rachat);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRachatMockMvc.perform(put("/api/rachats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(rachatDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Rachat in the database
        List<Rachat> rachatList = rachatRepository.findAll();
        assertThat(rachatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRachat() throws Exception {
        // Initialize the database
        rachatRepository.saveAndFlush(rachat);

        int databaseSizeBeforeDelete = rachatRepository.findAll().size();

        // Delete the rachat
        restRachatMockMvc.perform(delete("/api/rachats/{id}", rachat.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Rachat> rachatList = rachatRepository.findAll();
        assertThat(rachatList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
