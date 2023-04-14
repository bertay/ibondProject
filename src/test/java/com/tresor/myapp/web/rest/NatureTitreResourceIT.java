package com.tresor.myapp.web.rest;

import com.tresor.myapp.IbondgeneApp;
import com.tresor.myapp.domain.NatureTitre;
import com.tresor.myapp.repository.NatureTitreRepository;
import com.tresor.myapp.service.NatureTitreService;
import com.tresor.myapp.service.dto.NatureTitreDTO;
import com.tresor.myapp.service.mapper.NatureTitreMapper;

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

import com.tresor.myapp.domain.enumeration.TypeValeur;
import com.tresor.myapp.domain.enumeration.UniteDuree;
/**
 * Integration tests for the {@link NatureTitreResource} REST controller.
 */
@SpringBootTest(classes = IbondgeneApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class NatureTitreResourceIT {

    private static final String DEFAULT_CODE_NATURE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_NATURE = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGNATION_FR = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION_FR = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGNATION_EN = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION_EN = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGNATION_PT = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION_PT = "BBBBBBBBBB";

    private static final Long DEFAULT_NOMINAL_UNITAIRE = 0L;
    private static final Long UPDATED_NOMINAL_UNITAIRE = 1L;

    private static final TypeValeur DEFAULT_UNITE_VALEUR = TypeValeur.FCFA;
    private static final TypeValeur UPDATED_UNITE_VALEUR = TypeValeur.Euro;

    private static final UniteDuree DEFAULT_NATURE_ECHEANCE = UniteDuree.Jours;
    private static final UniteDuree UPDATED_NATURE_ECHEANCE = UniteDuree.Semaines;

    private static final String DEFAULT_OPERATEUR = "AAAAAAAAAA";
    private static final String UPDATED_OPERATEUR = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATE_OPERATION = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_OPERATION = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private NatureTitreRepository natureTitreRepository;

    @Autowired
    private NatureTitreMapper natureTitreMapper;

    @Autowired
    private NatureTitreService natureTitreService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNatureTitreMockMvc;

    private NatureTitre natureTitre;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NatureTitre createEntity(EntityManager em) {
        NatureTitre natureTitre = new NatureTitre()
            .codeNature(DEFAULT_CODE_NATURE)
            .designationFr(DEFAULT_DESIGNATION_FR)
            .designationEn(DEFAULT_DESIGNATION_EN)
            .designationPt(DEFAULT_DESIGNATION_PT)
            .nominalUnitaire(DEFAULT_NOMINAL_UNITAIRE)
            .uniteValeur(DEFAULT_UNITE_VALEUR)
            .natureEcheance(DEFAULT_NATURE_ECHEANCE)
            .operateur(DEFAULT_OPERATEUR)
            .dateOperation(DEFAULT_DATE_OPERATION);
        return natureTitre;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NatureTitre createUpdatedEntity(EntityManager em) {
        NatureTitre natureTitre = new NatureTitre()
            .codeNature(UPDATED_CODE_NATURE)
            .designationFr(UPDATED_DESIGNATION_FR)
            .designationEn(UPDATED_DESIGNATION_EN)
            .designationPt(UPDATED_DESIGNATION_PT)
            .nominalUnitaire(UPDATED_NOMINAL_UNITAIRE)
            .uniteValeur(UPDATED_UNITE_VALEUR)
            .natureEcheance(UPDATED_NATURE_ECHEANCE)
            .operateur(UPDATED_OPERATEUR)
            .dateOperation(UPDATED_DATE_OPERATION);
        return natureTitre;
    }

    @BeforeEach
    public void initTest() {
        natureTitre = createEntity(em);
    }

    @Test
    @Transactional
    public void createNatureTitre() throws Exception {
        int databaseSizeBeforeCreate = natureTitreRepository.findAll().size();
        // Create the NatureTitre
        NatureTitreDTO natureTitreDTO = natureTitreMapper.toDto(natureTitre);
        restNatureTitreMockMvc.perform(post("/api/nature-titres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(natureTitreDTO)))
            .andExpect(status().isCreated());

        // Validate the NatureTitre in the database
        List<NatureTitre> natureTitreList = natureTitreRepository.findAll();
        assertThat(natureTitreList).hasSize(databaseSizeBeforeCreate + 1);
        NatureTitre testNatureTitre = natureTitreList.get(natureTitreList.size() - 1);
        assertThat(testNatureTitre.getCodeNature()).isEqualTo(DEFAULT_CODE_NATURE);
        assertThat(testNatureTitre.getDesignationFr()).isEqualTo(DEFAULT_DESIGNATION_FR);
        assertThat(testNatureTitre.getDesignationEn()).isEqualTo(DEFAULT_DESIGNATION_EN);
        assertThat(testNatureTitre.getDesignationPt()).isEqualTo(DEFAULT_DESIGNATION_PT);
        assertThat(testNatureTitre.getNominalUnitaire()).isEqualTo(DEFAULT_NOMINAL_UNITAIRE);
        assertThat(testNatureTitre.getUniteValeur()).isEqualTo(DEFAULT_UNITE_VALEUR);
        assertThat(testNatureTitre.getNatureEcheance()).isEqualTo(DEFAULT_NATURE_ECHEANCE);
        assertThat(testNatureTitre.getOperateur()).isEqualTo(DEFAULT_OPERATEUR);
        assertThat(testNatureTitre.getDateOperation()).isEqualTo(DEFAULT_DATE_OPERATION);
    }

    @Test
    @Transactional
    public void createNatureTitreWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = natureTitreRepository.findAll().size();

        // Create the NatureTitre with an existing ID
        natureTitre.setId(1L);
        NatureTitreDTO natureTitreDTO = natureTitreMapper.toDto(natureTitre);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNatureTitreMockMvc.perform(post("/api/nature-titres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(natureTitreDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NatureTitre in the database
        List<NatureTitre> natureTitreList = natureTitreRepository.findAll();
        assertThat(natureTitreList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeNatureIsRequired() throws Exception {
        int databaseSizeBeforeTest = natureTitreRepository.findAll().size();
        // set the field null
        natureTitre.setCodeNature(null);

        // Create the NatureTitre, which fails.
        NatureTitreDTO natureTitreDTO = natureTitreMapper.toDto(natureTitre);


        restNatureTitreMockMvc.perform(post("/api/nature-titres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(natureTitreDTO)))
            .andExpect(status().isBadRequest());

        List<NatureTitre> natureTitreList = natureTitreRepository.findAll();
        assertThat(natureTitreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNominalUnitaireIsRequired() throws Exception {
        int databaseSizeBeforeTest = natureTitreRepository.findAll().size();
        // set the field null
        natureTitre.setNominalUnitaire(null);

        // Create the NatureTitre, which fails.
        NatureTitreDTO natureTitreDTO = natureTitreMapper.toDto(natureTitre);


        restNatureTitreMockMvc.perform(post("/api/nature-titres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(natureTitreDTO)))
            .andExpect(status().isBadRequest());

        List<NatureTitre> natureTitreList = natureTitreRepository.findAll();
        assertThat(natureTitreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUniteValeurIsRequired() throws Exception {
        int databaseSizeBeforeTest = natureTitreRepository.findAll().size();
        // set the field null
        natureTitre.setUniteValeur(null);

        // Create the NatureTitre, which fails.
        NatureTitreDTO natureTitreDTO = natureTitreMapper.toDto(natureTitre);


        restNatureTitreMockMvc.perform(post("/api/nature-titres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(natureTitreDTO)))
            .andExpect(status().isBadRequest());

        List<NatureTitre> natureTitreList = natureTitreRepository.findAll();
        assertThat(natureTitreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNatureEcheanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = natureTitreRepository.findAll().size();
        // set the field null
        natureTitre.setNatureEcheance(null);

        // Create the NatureTitre, which fails.
        NatureTitreDTO natureTitreDTO = natureTitreMapper.toDto(natureTitre);


        restNatureTitreMockMvc.perform(post("/api/nature-titres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(natureTitreDTO)))
            .andExpect(status().isBadRequest());

        List<NatureTitre> natureTitreList = natureTitreRepository.findAll();
        assertThat(natureTitreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOperateurIsRequired() throws Exception {
        int databaseSizeBeforeTest = natureTitreRepository.findAll().size();
        // set the field null
        natureTitre.setOperateur(null);

        // Create the NatureTitre, which fails.
        NatureTitreDTO natureTitreDTO = natureTitreMapper.toDto(natureTitre);


        restNatureTitreMockMvc.perform(post("/api/nature-titres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(natureTitreDTO)))
            .andExpect(status().isBadRequest());

        List<NatureTitre> natureTitreList = natureTitreRepository.findAll();
        assertThat(natureTitreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateOperationIsRequired() throws Exception {
        int databaseSizeBeforeTest = natureTitreRepository.findAll().size();
        // set the field null
        natureTitre.setDateOperation(null);

        // Create the NatureTitre, which fails.
        NatureTitreDTO natureTitreDTO = natureTitreMapper.toDto(natureTitre);


        restNatureTitreMockMvc.perform(post("/api/nature-titres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(natureTitreDTO)))
            .andExpect(status().isBadRequest());

        List<NatureTitre> natureTitreList = natureTitreRepository.findAll();
        assertThat(natureTitreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNatureTitres() throws Exception {
        // Initialize the database
        natureTitreRepository.saveAndFlush(natureTitre);

        // Get all the natureTitreList
        restNatureTitreMockMvc.perform(get("/api/nature-titres?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(natureTitre.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeNature").value(hasItem(DEFAULT_CODE_NATURE)))
            .andExpect(jsonPath("$.[*].designationFr").value(hasItem(DEFAULT_DESIGNATION_FR)))
            .andExpect(jsonPath("$.[*].designationEn").value(hasItem(DEFAULT_DESIGNATION_EN)))
            .andExpect(jsonPath("$.[*].designationPt").value(hasItem(DEFAULT_DESIGNATION_PT)))
            .andExpect(jsonPath("$.[*].nominalUnitaire").value(hasItem(DEFAULT_NOMINAL_UNITAIRE.intValue())))
            .andExpect(jsonPath("$.[*].uniteValeur").value(hasItem(DEFAULT_UNITE_VALEUR.toString())))
            .andExpect(jsonPath("$.[*].natureEcheance").value(hasItem(DEFAULT_NATURE_ECHEANCE.toString())))
            .andExpect(jsonPath("$.[*].operateur").value(hasItem(DEFAULT_OPERATEUR)))
            .andExpect(jsonPath("$.[*].dateOperation").value(hasItem(sameInstant(DEFAULT_DATE_OPERATION))));
    }
    
    @Test
    @Transactional
    public void getNatureTitre() throws Exception {
        // Initialize the database
        natureTitreRepository.saveAndFlush(natureTitre);

        // Get the natureTitre
        restNatureTitreMockMvc.perform(get("/api/nature-titres/{id}", natureTitre.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(natureTitre.getId().intValue()))
            .andExpect(jsonPath("$.codeNature").value(DEFAULT_CODE_NATURE))
            .andExpect(jsonPath("$.designationFr").value(DEFAULT_DESIGNATION_FR))
            .andExpect(jsonPath("$.designationEn").value(DEFAULT_DESIGNATION_EN))
            .andExpect(jsonPath("$.designationPt").value(DEFAULT_DESIGNATION_PT))
            .andExpect(jsonPath("$.nominalUnitaire").value(DEFAULT_NOMINAL_UNITAIRE.intValue()))
            .andExpect(jsonPath("$.uniteValeur").value(DEFAULT_UNITE_VALEUR.toString()))
            .andExpect(jsonPath("$.natureEcheance").value(DEFAULT_NATURE_ECHEANCE.toString()))
            .andExpect(jsonPath("$.operateur").value(DEFAULT_OPERATEUR))
            .andExpect(jsonPath("$.dateOperation").value(sameInstant(DEFAULT_DATE_OPERATION)));
    }
    @Test
    @Transactional
    public void getNonExistingNatureTitre() throws Exception {
        // Get the natureTitre
        restNatureTitreMockMvc.perform(get("/api/nature-titres/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNatureTitre() throws Exception {
        // Initialize the database
        natureTitreRepository.saveAndFlush(natureTitre);

        int databaseSizeBeforeUpdate = natureTitreRepository.findAll().size();

        // Update the natureTitre
        NatureTitre updatedNatureTitre = natureTitreRepository.findById(natureTitre.getId()).get();
        // Disconnect from session so that the updates on updatedNatureTitre are not directly saved in db
        em.detach(updatedNatureTitre);
        updatedNatureTitre
            .codeNature(UPDATED_CODE_NATURE)
            .designationFr(UPDATED_DESIGNATION_FR)
            .designationEn(UPDATED_DESIGNATION_EN)
            .designationPt(UPDATED_DESIGNATION_PT)
            .nominalUnitaire(UPDATED_NOMINAL_UNITAIRE)
            .uniteValeur(UPDATED_UNITE_VALEUR)
            .natureEcheance(UPDATED_NATURE_ECHEANCE)
            .operateur(UPDATED_OPERATEUR)
            .dateOperation(UPDATED_DATE_OPERATION);
        NatureTitreDTO natureTitreDTO = natureTitreMapper.toDto(updatedNatureTitre);

        restNatureTitreMockMvc.perform(put("/api/nature-titres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(natureTitreDTO)))
            .andExpect(status().isOk());

        // Validate the NatureTitre in the database
        List<NatureTitre> natureTitreList = natureTitreRepository.findAll();
        assertThat(natureTitreList).hasSize(databaseSizeBeforeUpdate);
        NatureTitre testNatureTitre = natureTitreList.get(natureTitreList.size() - 1);
        assertThat(testNatureTitre.getCodeNature()).isEqualTo(UPDATED_CODE_NATURE);
        assertThat(testNatureTitre.getDesignationFr()).isEqualTo(UPDATED_DESIGNATION_FR);
        assertThat(testNatureTitre.getDesignationEn()).isEqualTo(UPDATED_DESIGNATION_EN);
        assertThat(testNatureTitre.getDesignationPt()).isEqualTo(UPDATED_DESIGNATION_PT);
        assertThat(testNatureTitre.getNominalUnitaire()).isEqualTo(UPDATED_NOMINAL_UNITAIRE);
        assertThat(testNatureTitre.getUniteValeur()).isEqualTo(UPDATED_UNITE_VALEUR);
        assertThat(testNatureTitre.getNatureEcheance()).isEqualTo(UPDATED_NATURE_ECHEANCE);
        assertThat(testNatureTitre.getOperateur()).isEqualTo(UPDATED_OPERATEUR);
        assertThat(testNatureTitre.getDateOperation()).isEqualTo(UPDATED_DATE_OPERATION);
    }

    @Test
    @Transactional
    public void updateNonExistingNatureTitre() throws Exception {
        int databaseSizeBeforeUpdate = natureTitreRepository.findAll().size();

        // Create the NatureTitre
        NatureTitreDTO natureTitreDTO = natureTitreMapper.toDto(natureTitre);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNatureTitreMockMvc.perform(put("/api/nature-titres")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(natureTitreDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NatureTitre in the database
        List<NatureTitre> natureTitreList = natureTitreRepository.findAll();
        assertThat(natureTitreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNatureTitre() throws Exception {
        // Initialize the database
        natureTitreRepository.saveAndFlush(natureTitre);

        int databaseSizeBeforeDelete = natureTitreRepository.findAll().size();

        // Delete the natureTitre
        restNatureTitreMockMvc.perform(delete("/api/nature-titres/{id}", natureTitre.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NatureTitre> natureTitreList = natureTitreRepository.findAll();
        assertThat(natureTitreList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
