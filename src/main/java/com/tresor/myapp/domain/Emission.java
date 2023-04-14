package com.tresor.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import com.tresor.myapp.domain.enumeration.UniteNombre;

import com.tresor.myapp.domain.enumeration.TypeValeur;

/**
 * Entite Emission
 */
@Entity
@Table(name = "emission")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Emission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "code_emission", nullable = false)
    private String codeEmission;

    @Column(name = "designation_fr")
    private String designationFr;

    @Column(name = "designation_en")
    private String designationEn;

    @Column(name = "designation_pt")
    private String designationPt;

    @NotNull
    @Column(name = "date_emission", nullable = false)
    private LocalDate dateEmission;

    @NotNull
    @Column(name = "echeance", nullable = false)
    private LocalDate echeance;

    @Column(name = "duree")
    private String duree;

    @Column(name = "remboursement")
    private String remboursement;

    @Column(name = "forme_titre")
    private String formeTitre;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "taux_interet", nullable = false)
    private Float tauxInteret;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "volume_emission", nullable = false)
    private Double volumeEmission;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "unite_volume", nullable = false)
    private UniteNombre uniteVolume;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "valeur_nominale", nullable = false)
    private Double valeurNominale;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "devise", nullable = false)
    private TypeValeur devise;

    @Min(value = 0)
    @Column(name = "quantite_titre")
    private Integer quantiteTitre;

    @Column(name = "rendement")
    private String rendement;

    @NotNull
    @Column(name = "date_limite", nullable = false)
    private ZonedDateTime dateLimite;

    @NotNull
    @Column(name = "lieu_souscription", nullable = false)
    private String lieuSouscription;

    @Column(name = "date_resultat")
    private ZonedDateTime dateResultat;

    @Column(name = "date_reglement")
    private ZonedDateTime dateReglement;

    @Column(name = "date_valeur")
    private LocalDate dateValeur;

    @NotNull
    @Column(name = "operateur", nullable = false)
    private String operateur;

    @NotNull
    @Column(name = "date_operation", nullable = false)
    private ZonedDateTime dateOperation;

    @OneToMany(mappedBy = "emission")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Soumission> soumissions = new HashSet<>();

    @OneToMany(mappedBy = "emission")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Reouverture> reouvertures = new HashSet<>();

    @OneToMany(mappedBy = "emission")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Rachat> rachats = new HashSet<>();

    @OneToMany(mappedBy = "emission")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Onc> oncs = new HashSet<>();

    @OneToMany(mappedBy = "emission")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Classement> classements = new HashSet<>();

    @OneToMany(mappedBy = "emission")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Resultat> resultats = new HashSet<>();

    @OneToMany(mappedBy = "emission")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<MembreSyndicat> membreSyndicats = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "emissions", allowSetters = true)
    private AvisEmission avisEmission;

    @ManyToOne
    @JsonIgnoreProperties(value = "emissions", allowSetters = true)
    private NatureTitre natureTitre;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeEmission() {
        return codeEmission;
    }

    public Emission codeEmission(String codeEmission) {
        this.codeEmission = codeEmission;
        return this;
    }

    public void setCodeEmission(String codeEmission) {
        this.codeEmission = codeEmission;
    }

    public String getDesignationFr() {
        return designationFr;
    }

    public Emission designationFr(String designationFr) {
        this.designationFr = designationFr;
        return this;
    }

    public void setDesignationFr(String designationFr) {
        this.designationFr = designationFr;
    }

    public String getDesignationEn() {
        return designationEn;
    }

    public Emission designationEn(String designationEn) {
        this.designationEn = designationEn;
        return this;
    }

    public void setDesignationEn(String designationEn) {
        this.designationEn = designationEn;
    }

    public String getDesignationPt() {
        return designationPt;
    }

    public Emission designationPt(String designationPt) {
        this.designationPt = designationPt;
        return this;
    }

    public void setDesignationPt(String designationPt) {
        this.designationPt = designationPt;
    }

    public LocalDate getDateEmission() {
        return dateEmission;
    }

    public Emission dateEmission(LocalDate dateEmission) {
        this.dateEmission = dateEmission;
        return this;
    }

    public void setDateEmission(LocalDate dateEmission) {
        this.dateEmission = dateEmission;
    }

    public LocalDate getEcheance() {
        return echeance;
    }

    public Emission echeance(LocalDate echeance) {
        this.echeance = echeance;
        return this;
    }

    public void setEcheance(LocalDate echeance) {
        this.echeance = echeance;
    }

    public String getDuree() {
        return duree;
    }

    public Emission duree(String duree) {
        this.duree = duree;
        return this;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getRemboursement() {
        return remboursement;
    }

    public Emission remboursement(String remboursement) {
        this.remboursement = remboursement;
        return this;
    }

    public void setRemboursement(String remboursement) {
        this.remboursement = remboursement;
    }

    public String getFormeTitre() {
        return formeTitre;
    }

    public Emission formeTitre(String formeTitre) {
        this.formeTitre = formeTitre;
        return this;
    }

    public void setFormeTitre(String formeTitre) {
        this.formeTitre = formeTitre;
    }

    public Float getTauxInteret() {
        return tauxInteret;
    }

    public Emission tauxInteret(Float tauxInteret) {
        this.tauxInteret = tauxInteret;
        return this;
    }

    public void setTauxInteret(Float tauxInteret) {
        this.tauxInteret = tauxInteret;
    }

    public Double getVolumeEmission() {
        return volumeEmission;
    }

    public Emission volumeEmission(Double volumeEmission) {
        this.volumeEmission = volumeEmission;
        return this;
    }

    public void setVolumeEmission(Double volumeEmission) {
        this.volumeEmission = volumeEmission;
    }

    public UniteNombre getUniteVolume() {
        return uniteVolume;
    }

    public Emission uniteVolume(UniteNombre uniteVolume) {
        this.uniteVolume = uniteVolume;
        return this;
    }

    public void setUniteVolume(UniteNombre uniteVolume) {
        this.uniteVolume = uniteVolume;
    }

    public Double getValeurNominale() {
        return valeurNominale;
    }

    public Emission valeurNominale(Double valeurNominale) {
        this.valeurNominale = valeurNominale;
        return this;
    }

    public void setValeurNominale(Double valeurNominale) {
        this.valeurNominale = valeurNominale;
    }

    public TypeValeur getDevise() {
        return devise;
    }

    public Emission devise(TypeValeur devise) {
        this.devise = devise;
        return this;
    }

    public void setDevise(TypeValeur devise) {
        this.devise = devise;
    }

    public Integer getQuantiteTitre() {
        return quantiteTitre;
    }

    public Emission quantiteTitre(Integer quantiteTitre) {
        this.quantiteTitre = quantiteTitre;
        return this;
    }

    public void setQuantiteTitre(Integer quantiteTitre) {
        this.quantiteTitre = quantiteTitre;
    }

    public String getRendement() {
        return rendement;
    }

    public Emission rendement(String rendement) {
        this.rendement = rendement;
        return this;
    }

    public void setRendement(String rendement) {
        this.rendement = rendement;
    }

    public ZonedDateTime getDateLimite() {
        return dateLimite;
    }

    public Emission dateLimite(ZonedDateTime dateLimite) {
        this.dateLimite = dateLimite;
        return this;
    }

    public void setDateLimite(ZonedDateTime dateLimite) {
        this.dateLimite = dateLimite;
    }

    public String getLieuSouscription() {
        return lieuSouscription;
    }

    public Emission lieuSouscription(String lieuSouscription) {
        this.lieuSouscription = lieuSouscription;
        return this;
    }

    public void setLieuSouscription(String lieuSouscription) {
        this.lieuSouscription = lieuSouscription;
    }

    public ZonedDateTime getDateResultat() {
        return dateResultat;
    }

    public Emission dateResultat(ZonedDateTime dateResultat) {
        this.dateResultat = dateResultat;
        return this;
    }

    public void setDateResultat(ZonedDateTime dateResultat) {
        this.dateResultat = dateResultat;
    }

    public ZonedDateTime getDateReglement() {
        return dateReglement;
    }

    public Emission dateReglement(ZonedDateTime dateReglement) {
        this.dateReglement = dateReglement;
        return this;
    }

    public void setDateReglement(ZonedDateTime dateReglement) {
        this.dateReglement = dateReglement;
    }

    public LocalDate getDateValeur() {
        return dateValeur;
    }

    public Emission dateValeur(LocalDate dateValeur) {
        this.dateValeur = dateValeur;
        return this;
    }

    public void setDateValeur(LocalDate dateValeur) {
        this.dateValeur = dateValeur;
    }

    public String getOperateur() {
        return operateur;
    }

    public Emission operateur(String operateur) {
        this.operateur = operateur;
        return this;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    public ZonedDateTime getDateOperation() {
        return dateOperation;
    }

    public Emission dateOperation(ZonedDateTime dateOperation) {
        this.dateOperation = dateOperation;
        return this;
    }

    public void setDateOperation(ZonedDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    public Set<Soumission> getSoumissions() {
        return soumissions;
    }

    public Emission soumissions(Set<Soumission> soumissions) {
        this.soumissions = soumissions;
        return this;
    }

    public Emission addSoumission(Soumission soumission) {
        this.soumissions.add(soumission);
        soumission.setEmission(this);
        return this;
    }

    public Emission removeSoumission(Soumission soumission) {
        this.soumissions.remove(soumission);
        soumission.setEmission(null);
        return this;
    }

    public void setSoumissions(Set<Soumission> soumissions) {
        this.soumissions = soumissions;
    }

    public Set<Reouverture> getReouvertures() {
        return reouvertures;
    }

    public Emission reouvertures(Set<Reouverture> reouvertures) {
        this.reouvertures = reouvertures;
        return this;
    }

    public Emission addReouverture(Reouverture reouverture) {
        this.reouvertures.add(reouverture);
        reouverture.setEmission(this);
        return this;
    }

    public Emission removeReouverture(Reouverture reouverture) {
        this.reouvertures.remove(reouverture);
        reouverture.setEmission(null);
        return this;
    }

    public void setReouvertures(Set<Reouverture> reouvertures) {
        this.reouvertures = reouvertures;
    }

    public Set<Rachat> getRachats() {
        return rachats;
    }

    public Emission rachats(Set<Rachat> rachats) {
        this.rachats = rachats;
        return this;
    }

    public Emission addRachat(Rachat rachat) {
        this.rachats.add(rachat);
        rachat.setEmission(this);
        return this;
    }

    public Emission removeRachat(Rachat rachat) {
        this.rachats.remove(rachat);
        rachat.setEmission(null);
        return this;
    }

    public void setRachats(Set<Rachat> rachats) {
        this.rachats = rachats;
    }

    public Set<Onc> getOncs() {
        return oncs;
    }

    public Emission oncs(Set<Onc> oncs) {
        this.oncs = oncs;
        return this;
    }

    public Emission addOnc(Onc onc) {
        this.oncs.add(onc);
        onc.setEmission(this);
        return this;
    }

    public Emission removeOnc(Onc onc) {
        this.oncs.remove(onc);
        onc.setEmission(null);
        return this;
    }

    public void setOncs(Set<Onc> oncs) {
        this.oncs = oncs;
    }

    public Set<Classement> getClassements() {
        return classements;
    }

    public Emission classements(Set<Classement> classements) {
        this.classements = classements;
        return this;
    }

    public Emission addClassement(Classement classement) {
        this.classements.add(classement);
        classement.setEmission(this);
        return this;
    }

    public Emission removeClassement(Classement classement) {
        this.classements.remove(classement);
        classement.setEmission(null);
        return this;
    }

    public void setClassements(Set<Classement> classements) {
        this.classements = classements;
    }

    public Set<Resultat> getResultats() {
        return resultats;
    }

    public Emission resultats(Set<Resultat> resultats) {
        this.resultats = resultats;
        return this;
    }

    public Emission addResultat(Resultat resultat) {
        this.resultats.add(resultat);
        resultat.setEmission(this);
        return this;
    }

    public Emission removeResultat(Resultat resultat) {
        this.resultats.remove(resultat);
        resultat.setEmission(null);
        return this;
    }

    public void setResultats(Set<Resultat> resultats) {
        this.resultats = resultats;
    }

    public Set<MembreSyndicat> getMembreSyndicats() {
        return membreSyndicats;
    }

    public Emission membreSyndicats(Set<MembreSyndicat> membreSyndicats) {
        this.membreSyndicats = membreSyndicats;
        return this;
    }

    public Emission addMembreSyndicat(MembreSyndicat membreSyndicat) {
        this.membreSyndicats.add(membreSyndicat);
        membreSyndicat.setEmission(this);
        return this;
    }

    public Emission removeMembreSyndicat(MembreSyndicat membreSyndicat) {
        this.membreSyndicats.remove(membreSyndicat);
        membreSyndicat.setEmission(null);
        return this;
    }

    public void setMembreSyndicats(Set<MembreSyndicat> membreSyndicats) {
        this.membreSyndicats = membreSyndicats;
    }

    public AvisEmission getAvisEmission() {
        return avisEmission;
    }

    public Emission avisEmission(AvisEmission avisEmission) {
        this.avisEmission = avisEmission;
        return this;
    }

    public void setAvisEmission(AvisEmission avisEmission) {
        this.avisEmission = avisEmission;
    }

    public NatureTitre getNatureTitre() {
        return natureTitre;
    }

    public Emission natureTitre(NatureTitre natureTitre) {
        this.natureTitre = natureTitre;
        return this;
    }

    public void setNatureTitre(NatureTitre natureTitre) {
        this.natureTitre = natureTitre;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Emission)) {
            return false;
        }
        return id != null && id.equals(((Emission) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Emission{" +
            "id=" + getId() +
            ", codeEmission='" + getCodeEmission() + "'" +
            ", designationFr='" + getDesignationFr() + "'" +
            ", designationEn='" + getDesignationEn() + "'" +
            ", designationPt='" + getDesignationPt() + "'" +
            ", dateEmission='" + getDateEmission() + "'" +
            ", echeance='" + getEcheance() + "'" +
            ", duree='" + getDuree() + "'" +
            ", remboursement='" + getRemboursement() + "'" +
            ", formeTitre='" + getFormeTitre() + "'" +
            ", tauxInteret=" + getTauxInteret() +
            ", volumeEmission=" + getVolumeEmission() +
            ", uniteVolume='" + getUniteVolume() + "'" +
            ", valeurNominale=" + getValeurNominale() +
            ", devise='" + getDevise() + "'" +
            ", quantiteTitre=" + getQuantiteTitre() +
            ", rendement='" + getRendement() + "'" +
            ", dateLimite='" + getDateLimite() + "'" +
            ", lieuSouscription='" + getLieuSouscription() + "'" +
            ", dateResultat='" + getDateResultat() + "'" +
            ", dateReglement='" + getDateReglement() + "'" +
            ", dateValeur='" + getDateValeur() + "'" +
            ", operateur='" + getOperateur() + "'" +
            ", dateOperation='" + getDateOperation() + "'" +
            "}";
    }
}
