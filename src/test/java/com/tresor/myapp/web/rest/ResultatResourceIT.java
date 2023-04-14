package com.tresor.myapp.web.rest;

import com.tresor.myapp.IbondgeneApp;
import com.tresor.myapp.domain.Resultat;
import com.tresor.myapp.repository.ResultatRepository;
import com.tresor.myapp.service.ResultatService;
import com.tresor.myapp.service.dto.ResultatDTO;
import com.tresor.myapp.service.mapper.ResultatMapper;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.tresor.myapp.domain.enumeration.UniteNombre;
import com.tresor.myapp.domain.enumeration.UniteNombre;
/**
 * Integration tests for the {@link ResultatResource} REST controller.
 */
@SpringBootTest(classes = IbondgeneApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ResultatResourceIT {

    private static final Integer DEFAULT_NBRE_SVT_TOTAL = 0;
    private static final Integer UPDATED_NBRE_SVT_TOTAL = 1;

    private static final Integer DEFAULT_NBRE_SVT_SOUMIS = 0;
    private static final Integer UPDATED_NBRE_SVT_SOUMIS = 1;

    private static final Double DEFAULT_MONTANT_TRESOR = 0D;
    private static final Double UPDATED_MONTANT_TRESOR = 1D;

    private static final UniteNombre DEFAULT_UNITE_TRESOR = UniteNombre.Milles;
    private static final UniteNombre UPDATED_UNITE_TRESOR = UniteNombre.Millions;

    private static final Double DEFAULT_MONTANT_SOUMIS = 0D;
    private static final Double UPDATED_MONTANT_SOUMIS = 1D;

    private static final UniteNombre DEFAULT_UNITE_SOUMIS = UniteNombre.Milles;
    private static final UniteNombre UPDATED_UNITE_SOUMIS = UniteNombre.Millions;

    private static final Double DEFAULT_MONTANT_SERVI = 0D;
    private static final Double UPDATED_MONTANT_SERVI = 1D;

    private static final Long DEFAULT_NBRE_TITRE_TOTAL = 0L;
    private static final Long UPDATED_NBRE_TITRE_TOTAL = 1L;

    private static final Long DEFAULT_NBRE_TITRE_SOUMIS = 0L;
    private static final Long UPDATED_NBRE_TITRE_SOUMIS = 1L;

    private static final Float DEFAULT_TAUX_MIN_PROPOSE = 0F;
    private static final Float UPDATED_TAUX_MIN_PROPOSE = 1F;

    private static final Float DEFAULT_TAUX_MAX_PROPOSE = 0F;
    private static final Float UPDATED_TAUX_MAX_PROPOSE = 1F;

    private static final Float DEFAULT_TAUX_LIMITE = 0F;
    private static final Float UPDATED_TAUX_LIMITE = 1F;

    private static final Float DEFAULT_TAUX_INTERET_MOYEN = 0F;
    private static final Float UPDATED_TAUX_INTERET_MOYEN = 1F;

    private static final Float DEFAULT_TAUX_RENDEMENT_MOYEN = 0F;
    private static final Float UPDATED_TAUX_RENDEMENT_MOYEN = 1F;

    private static final Float DEFAULT_TAUX_COUVERTURE = 0F;
    private static final Float UPDATED_TAUX_COUVERTURE = 1F;

    @Autowired
    private ResultatRepository resultatRepository;

    @Autowired
    private ResultatMapper resultatMapper;

    @Autowired
    private ResultatService resultatService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restResultatMockMvc;

    private Resultat resultat;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Resultat createEntity(EntityManager em) {
        Resultat resultat = new Resultat()
            .nbreSvtTotal(DEFAULT_NBRE_SVT_TOTAL)
            .nbreSvtSoumis(DEFAULT_NBRE_SVT_SOUMIS)
            .montantTresor(DEFAULT_MONTANT_TRESOR)
            .uniteTresor(DEFAULT_UNITE_TRESOR)
            .montantSoumis(DEFAULT_MONTANT_SOUMIS)
            .uniteSoumis(DEFAULT_UNITE_SOUMIS)
            .montantServi(DEFAULT_MONTANT_SERVI)
            .nbreTitreTotal(DEFAULT_NBRE_TITRE_TOTAL)
            .nbreTitreSoumis(DEFAULT_NBRE_TITRE_SOUMIS)
            .tauxMinPropose(DEFAULT_TAUX_MIN_PROPOSE)
            .tauxMaxPropose(DEFAULT_TAUX_MAX_PROPOSE)
            .tauxLimite(DEFAULT_TAUX_LIMITE)
            .tauxInteretMoyen(DEFAULT_TAUX_INTERET_MOYEN)
            .tauxRendementMoyen(DEFAULT_TAUX_RENDEMENT_MOYEN)
            .tauxCouverture(DEFAULT_TAUX_COUVERTURE);
        return resultat;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Resultat createUpdatedEntity(EntityManager em) {
        Resultat resultat = new Resultat()
            .nbreSvtTotal(UPDATED_NBRE_SVT_TOTAL)
            .nbreSvtSoumis(UPDATED_NBRE_SVT_SOUMIS)
            .montantTresor(UPDATED_MONTANT_TRESOR)
            .uniteTresor(UPDATED_UNITE_TRESOR)
            .montantSoumis(UPDATED_MONTANT_SOUMIS)
            .uniteSoumis(UPDATED_UNITE_SOUMIS)
            .montantServi(UPDATED_MONTANT_SERVI)
            .nbreTitreTotal(UPDATED_NBRE_TITRE_TOTAL)
            .nbreTitreSoumis(UPDATED_NBRE_TITRE_SOUMIS)
            .tauxMinPropose(UPDATED_TAUX_MIN_PROPOSE)
            .tauxMaxPropose(UPDATED_TAUX_MAX_PROPOSE)
            .tauxLimite(UPDATED_TAUX_LIMITE)
            .tauxInteretMoyen(UPDATED_TAUX_INTERET_MOYEN)
            .tauxRendementMoyen(UPDATED_TAUX_RENDEMENT_MOYEN)
            .tauxCouverture(UPDATED_TAUX_COUVERTURE);
        return resultat;
    }

    @BeforeEach
    public void initTest() {
        resultat = createEntity(em);
    }

    @Test
    @Transactional
    public void createResultat() throws Exception {
        int databaseSizeBeforeCreate = resultatRepository.findAll().size();
        // Create the Resultat
        ResultatDTO resultatDTO = resultatMapper.toDto(resultat);
        restResultatMockMvc.perform(post("/api/resultats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(resultatDTO)))
            .andExpect(status().isCreated());

        // Validate the Resultat in the database
        List<Resultat> resultatList = resultatRepository.findAll();
        assertThat(resultatList).hasSize(databaseSizeBeforeCreate + 1);
        Resultat testResultat = resultatList.get(resultatList.size() - 1);
        assertThat(testResultat.getNbreSvtTotal()).isEqualTo(DEFAULT_NBRE_SVT_TOTAL);
        assertThat(testResultat.getNbreSvtSoumis()).isEqualTo(DEFAULT_NBRE_SVT_SOUMIS);
        assertThat(testResultat.getMontantTresor()).isEqualTo(DEFAULT_MONTANT_TRESOR);
        assertThat(testResultat.getUniteTresor()).isEqualTo(DEFAULT_UNITE_TRESOR);
        assertThat(testResultat.getMontantSoumis()).isEqualTo(DEFAULT_MONTANT_SOUMIS);
        assertThat(testResultat.getUniteSoumis()).isEqualTo(DEFAULT_UNITE_SOUMIS);
        assertThat(testResultat.getMontantServi()).isEqualTo(DEFAULT_MONTANT_SERVI);
        assertThat(testResultat.getNbreTitreTotal()).isEqualTo(DEFAULT_NBRE_TITRE_TOTAL);
        assertThat(testResultat.getNbreTitreSoumis()).isEqualTo(DEFAULT_NBRE_TITRE_SOUMIS);
        assertThat(testResultat.getTauxMinPropose()).isEqualTo(DEFAULT_TAUX_MIN_PROPOSE);
        assertThat(testResultat.getTauxMaxPropose()).isEqualTo(DEFAULT_TAUX_MAX_PROPOSE);
        assertThat(testResultat.getTauxLimite()).isEqualTo(DEFAULT_TAUX_LIMITE);
        assertThat(testResultat.getTauxInteretMoyen()).isEqualTo(DEFAULT_TAUX_INTERET_MOYEN);
        assertThat(testResultat.getTauxRendementMoyen()).isEqualTo(DEFAULT_TAUX_RENDEMENT_MOYEN);
        assertThat(testResultat.getTauxCouverture()).isEqualTo(DEFAULT_TAUX_COUVERTURE);
    }

    @Test
    @Transactional
    public void createResultatWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = resultatRepository.findAll().size();

        // Create the Resultat with an existing ID
        resultat.setId(1L);
        ResultatDTO resultatDTO = resultatMapper.toDto(resultat);

        // An entity with an existing ID cannot be created, so this API call must fail
        restResultatMockMvc.perform(post("/api/resultats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(resultatDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Resultat in the database
        List<Resultat> resultatList = resultatRepository.findAll();
        assertThat(resultatList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMontantTresorIsRequired() throws Exception {
        int databaseSizeBeforeTest = resultatRepository.findAll().size();
        // set the field null
        resultat.setMontantTresor(null);

        // Create the Resultat, which fails.
        ResultatDTO resultatDTO = resultatMapper.toDto(resultat);


        restResultatMockMvc.perform(post("/api/resultats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(resultatDTO)))
            .andExpect(status().isBadRequest());

        List<Resultat> resultatList = resultatRepository.findAll();
        assertThat(resultatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMontantSoumisIsRequired() throws Exception {
        int databaseSizeBeforeTest = resultatRepository.findAll().size();
        // set the field null
        resultat.setMontantSoumis(null);

        // Create the Resultat, which fails.
        ResultatDTO resultatDTO = resultatMapper.toDto(resultat);


        restResultatMockMvc.perform(post("/api/resultats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(resultatDTO)))
            .andExpect(status().isBadRequest());

        List<Resultat> resultatList = resultatRepository.findAll();
        assertThat(resultatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMontantServiIsRequired() throws Exception {
        int databaseSizeBeforeTest = resultatRepository.findAll().size();
        // set the field null
        resultat.setMontantServi(null);

        // Create the Resultat, which fails.
        ResultatDTO resultatDTO = resultatMapper.toDto(resultat);


        restResultatMockMvc.perform(post("/api/resultats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(resultatDTO)))
            .andExpect(status().isBadRequest());

        List<Resultat> resultatList = resultatRepository.findAll();
        assertThat(resultatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllResultats() throws Exception {
        // Initialize the database
        resultatRepository.saveAndFlush(resultat);

        // Get all the resultatList
        restResultatMockMvc.perform(get("/api/resultats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resultat.getId().intValue())))
            .andExpect(jsonPath("$.[*].nbreSvtTotal").value(hasItem(DEFAULT_NBRE_SVT_TOTAL)))
            .andExpect(jsonPath("$.[*].nbreSvtSoumis").value(hasItem(DEFAULT_NBRE_SVT_SOUMIS)))
            .andExpect(jsonPath("$.[*].montantTresor").value(hasItem(DEFAULT_MONTANT_TRESOR.doubleValue())))
            .andExpect(jsonPath("$.[*].uniteTresor").value(hasItem(DEFAULT_UNITE_TRESOR.toString())))
            .andExpect(jsonPath("$.[*].montantSoumis").value(hasItem(DEFAULT_MONTANT_SOUMIS.doubleValue())))
            .andExpect(jsonPath("$.[*].uniteSoumis").value(hasItem(DEFAULT_UNITE_SOUMIS.toString())))
            .andExpect(jsonPath("$.[*].montantServi").value(hasItem(DEFAULT_MONTANT_SERVI.doubleValue())))
            .andExpect(jsonPath("$.[*].nbreTitreTotal").value(hasItem(DEFAULT_NBRE_TITRE_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].nbreTitreSoumis").value(hasItem(DEFAULT_NBRE_TITRE_SOUMIS.intValue())))
            .andExpect(jsonPath("$.[*].tauxMinPropose").value(hasItem(DEFAULT_TAUX_MIN_PROPOSE.doubleValue())))
            .andExpect(jsonPath("$.[*].tauxMaxPropose").value(hasItem(DEFAULT_TAUX_MAX_PROPOSE.doubleValue())))
            .andExpect(jsonPath("$.[*].tauxLimite").value(hasItem(DEFAULT_TAUX_LIMITE.doubleValue())))
            .andExpect(jsonPath("$.[*].tauxInteretMoyen").value(hasItem(DEFAULT_TAUX_INTERET_MOYEN.doubleValue())))
            .andExpect(jsonPath("$.[*].tauxRendementMoyen").value(hasItem(DEFAULT_TAUX_RENDEMENT_MOYEN.doubleValue())))
            .andExpect(jsonPath("$.[*].tauxCouverture").value(hasItem(DEFAULT_TAUX_COUVERTURE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getResultat() throws Exception {
        // Initialize the database
        resultatRepository.saveAndFlush(resultat);

        // Get the resultat
        restResultatMockMvc.perform(get("/api/resultats/{id}", resultat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(resultat.getId().intValue()))
            .andExpect(jsonPath("$.nbreSvtTotal").value(DEFAULT_NBRE_SVT_TOTAL))
            .andExpect(jsonPath("$.nbreSvtSoumis").value(DEFAULT_NBRE_SVT_SOUMIS))
            .andExpect(jsonPath("$.montantTresor").value(DEFAULT_MONTANT_TRESOR.doubleValue()))
            .andExpect(jsonPath("$.uniteTresor").value(DEFAULT_UNITE_TRESOR.toString()))
            .andExpect(jsonPath("$.montantSoumis").value(DEFAULT_MONTANT_SOUMIS.doubleValue()))
            .andExpect(jsonPath("$.uniteSoumis").value(DEFAULT_UNITE_SOUMIS.toString()))
            .andExpect(jsonPath("$.montantServi").value(DEFAULT_MONTANT_SERVI.doubleValue()))
            .andExpect(jsonPath("$.nbreTitreTotal").value(DEFAULT_NBRE_TITRE_TOTAL.intValue()))
            .andExpect(jsonPath("$.nbreTitreSoumis").value(DEFAULT_NBRE_TITRE_SOUMIS.intValue()))
            .andExpect(jsonPath("$.tauxMinPropose").value(DEFAULT_TAUX_MIN_PROPOSE.doubleValue()))
            .andExpect(jsonPath("$.tauxMaxPropose").value(DEFAULT_TAUX_MAX_PROPOSE.doubleValue()))
            .andExpect(jsonPath("$.tauxLimite").value(DEFAULT_TAUX_LIMITE.doubleValue()))
            .andExpect(jsonPath("$.tauxInteretMoyen").value(DEFAULT_TAUX_INTERET_MOYEN.doubleValue()))
            .andExpect(jsonPath("$.tauxRendementMoyen").value(DEFAULT_TAUX_RENDEMENT_MOYEN.doubleValue()))
            .andExpect(jsonPath("$.tauxCouverture").value(DEFAULT_TAUX_COUVERTURE.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingResultat() throws Exception {
        // Get the resultat
        restResultatMockMvc.perform(get("/api/resultats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateResultat() throws Exception {
        // Initialize the database
        resultatRepository.saveAndFlush(resultat);

        int databaseSizeBeforeUpdate = resultatRepository.findAll().size();

        // Update the resultat
        Resultat updatedResultat = resultatRepository.findById(resultat.getId()).get();
        // Disconnect from session so that the updates on updatedResultat are not directly saved in db
        em.detach(updatedResultat);
        updatedResultat
            .nbreSvtTotal(UPDATED_NBRE_SVT_TOTAL)
            .nbreSvtSoumis(UPDATED_NBRE_SVT_SOUMIS)
            .montantTresor(UPDATED_MONTANT_TRESOR)
            .uniteTresor(UPDATED_UNITE_TRESOR)
            .montantSoumis(UPDATED_MONTANT_SOUMIS)
            .uniteSoumis(UPDATED_UNITE_SOUMIS)
            .montantServi(UPDATED_MONTANT_SERVI)
            .nbreTitreTotal(UPDATED_NBRE_TITRE_TOTAL)
            .nbreTitreSoumis(UPDATED_NBRE_TITRE_SOUMIS)
            .tauxMinPropose(UPDATED_TAUX_MIN_PROPOSE)
            .tauxMaxPropose(UPDATED_TAUX_MAX_PROPOSE)
            .tauxLimite(UPDATED_TAUX_LIMITE)
            .tauxInteretMoyen(UPDATED_TAUX_INTERET_MOYEN)
            .tauxRendementMoyen(UPDATED_TAUX_RENDEMENT_MOYEN)
            .tauxCouverture(UPDATED_TAUX_COUVERTURE);
        ResultatDTO resultatDTO = resultatMapper.toDto(updatedResultat);

        restResultatMockMvc.perform(put("/api/resultats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(resultatDTO)))
            .andExpect(status().isOk());

        // Validate the Resultat in the database
        List<Resultat> resultatList = resultatRepository.findAll();
        assertThat(resultatList).hasSize(databaseSizeBeforeUpdate);
        Resultat testResultat = resultatList.get(resultatList.size() - 1);
        assertThat(testResultat.getNbreSvtTotal()).isEqualTo(UPDATED_NBRE_SVT_TOTAL);
        assertThat(testResultat.getNbreSvtSoumis()).isEqualTo(UPDATED_NBRE_SVT_SOUMIS);
        assertThat(testResultat.getMontantTresor()).isEqualTo(UPDATED_MONTANT_TRESOR);
        assertThat(testResultat.getUniteTresor()).isEqualTo(UPDATED_UNITE_TRESOR);
        assertThat(testResultat.getMontantSoumis()).isEqualTo(UPDATED_MONTANT_SOUMIS);
        assertThat(testResultat.getUniteSoumis()).isEqualTo(UPDATED_UNITE_SOUMIS);
        assertThat(testResultat.getMontantServi()).isEqualTo(UPDATED_MONTANT_SERVI);
        assertThat(testResultat.getNbreTitreTotal()).isEqualTo(UPDATED_NBRE_TITRE_TOTAL);
        assertThat(testResultat.getNbreTitreSoumis()).isEqualTo(UPDATED_NBRE_TITRE_SOUMIS);
        assertThat(testResultat.getTauxMinPropose()).isEqualTo(UPDATED_TAUX_MIN_PROPOSE);
        assertThat(testResultat.getTauxMaxPropose()).isEqualTo(UPDATED_TAUX_MAX_PROPOSE);
        assertThat(testResultat.getTauxLimite()).isEqualTo(UPDATED_TAUX_LIMITE);
        assertThat(testResultat.getTauxInteretMoyen()).isEqualTo(UPDATED_TAUX_INTERET_MOYEN);
        assertThat(testResultat.getTauxRendementMoyen()).isEqualTo(UPDATED_TAUX_RENDEMENT_MOYEN);
        assertThat(testResultat.getTauxCouverture()).isEqualTo(UPDATED_TAUX_COUVERTURE);
    }

    @Test
    @Transactional
    public void updateNonExistingResultat() throws Exception {
        int databaseSizeBeforeUpdate = resultatRepository.findAll().size();

        // Create the Resultat
        ResultatDTO resultatDTO = resultatMapper.toDto(resultat);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResultatMockMvc.perform(put("/api/resultats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(resultatDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Resultat in the database
        List<Resultat> resultatList = resultatRepository.findAll();
        assertThat(resultatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteResultat() throws Exception {
        // Initialize the database
        resultatRepository.saveAndFlush(resultat);

        int databaseSizeBeforeDelete = resultatRepository.findAll().size();

        // Delete the resultat
        restResultatMockMvc.perform(delete("/api/resultats/{id}", resultat.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Resultat> resultatList = resultatRepository.findAll();
        assertThat(resultatList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
