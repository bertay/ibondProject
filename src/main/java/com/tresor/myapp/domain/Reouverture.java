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
 * Entite Reouverture
 */
@Entity
@Table(name = "reouverture")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Reouverture implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "code_valeur", nullable = false)
    private String codeValeur;

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
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "taux_interet", nullable = false)
    private Float tauxInteret;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "encours_emission", nullable = false)
    private Double encoursEmission;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "unite_volume", nullable = false)
    private UniteNombre uniteVolume;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "montant_sollicite", nullable = false)
    private Double montantSollicite;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "unite_montant", nullable = false)
    private UniteNombre uniteMontant;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "devise", nullable = false)
    private TypeValeur devise;

    @NotNull
    @Column(name = "date_echeance", nullable = false)
    private LocalDate dateEcheance;

    @Column(name = "date_valeur")
    private LocalDate dateValeur;

    @NotNull
    @Column(name = "operateur", nullable = false)
    private String operateur;

    @NotNull
    @Column(name = "date_operation", nullable = false)
    private ZonedDateTime dateOperation;

    @OneToMany(mappedBy = "reouverture")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Soumission> soumissions = new HashSet<>();

    @OneToMany(mappedBy = "reouverture")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Adjudication> adjudications = new HashSet<>();

    @OneToMany(mappedBy = "reouverture")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Classement> classements = new HashSet<>();

    @OneToMany(mappedBy = "resultat")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Resultat> resultats = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "reouvertures", allowSetters = true)
    private Emission emission;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeValeur() {
        return codeValeur;
    }

    public Reouverture codeValeur(String codeValeur) {
        this.codeValeur = codeValeur;
        return this;
    }

    public void setCodeValeur(String codeValeur) {
        this.codeValeur = codeValeur;
    }

    public String getDesignationFr() {
        return designationFr;
    }

    public Reouverture designationFr(String designationFr) {
        this.designationFr = designationFr;
        return this;
    }

    public void setDesignationFr(String designationFr) {
        this.designationFr = designationFr;
    }

    public String getDesignationEn() {
        return designationEn;
    }

    public Reouverture designationEn(String designationEn) {
        this.designationEn = designationEn;
        return this;
    }

    public void setDesignationEn(String designationEn) {
        this.designationEn = designationEn;
    }

    public String getDesignationPt() {
        return designationPt;
    }

    public Reouverture designationPt(String designationPt) {
        this.designationPt = designationPt;
        return this;
    }

    public void setDesignationPt(String designationPt) {
        this.designationPt = designationPt;
    }

    public LocalDate getDateEmission() {
        return dateEmission;
    }

    public Reouverture dateEmission(LocalDate dateEmission) {
        this.dateEmission = dateEmission;
        return this;
    }

    public void setDateEmission(LocalDate dateEmission) {
        this.dateEmission = dateEmission;
    }

    public Float getTauxInteret() {
        return tauxInteret;
    }

    public Reouverture tauxInteret(Float tauxInteret) {
        this.tauxInteret = tauxInteret;
        return this;
    }

    public void setTauxInteret(Float tauxInteret) {
        this.tauxInteret = tauxInteret;
    }

    public Double getEncoursEmission() {
        return encoursEmission;
    }

    public Reouverture encoursEmission(Double encoursEmission) {
        this.encoursEmission = encoursEmission;
        return this;
    }

    public void setEncoursEmission(Double encoursEmission) {
        this.encoursEmission = encoursEmission;
    }

    public UniteNombre getUniteVolume() {
        return uniteVolume;
    }

    public Reouverture uniteVolume(UniteNombre uniteVolume) {
        this.uniteVolume = uniteVolume;
        return this;
    }

    public void setUniteVolume(UniteNombre uniteVolume) {
        this.uniteVolume = uniteVolume;
    }

    public Double getMontantSollicite() {
        return montantSollicite;
    }

    public Reouverture montantSollicite(Double montantSollicite) {
        this.montantSollicite = montantSollicite;
        return this;
    }

    public void setMontantSollicite(Double montantSollicite) {
        this.montantSollicite = montantSollicite;
    }

    public UniteNombre getUniteMontant() {
        return uniteMontant;
    }

    public Reouverture uniteMontant(UniteNombre uniteMontant) {
        this.uniteMontant = uniteMontant;
        return this;
    }

    public void setUniteMontant(UniteNombre uniteMontant) {
        this.uniteMontant = uniteMontant;
    }

    public TypeValeur getDevise() {
        return devise;
    }

    public Reouverture devise(TypeValeur devise) {
        this.devise = devise;
        return this;
    }

    public void setDevise(TypeValeur devise) {
        this.devise = devise;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public Reouverture dateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
        return this;
    }

    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public LocalDate getDateValeur() {
        return dateValeur;
    }

    public Reouverture dateValeur(LocalDate dateValeur) {
        this.dateValeur = dateValeur;
        return this;
    }

    public void setDateValeur(LocalDate dateValeur) {
        this.dateValeur = dateValeur;
    }

    public String getOperateur() {
        return operateur;
    }

    public Reouverture operateur(String operateur) {
        this.operateur = operateur;
        return this;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    public ZonedDateTime getDateOperation() {
        return dateOperation;
    }

    public Reouverture dateOperation(ZonedDateTime dateOperation) {
        this.dateOperation = dateOperation;
        return this;
    }

    public void setDateOperation(ZonedDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    public Set<Soumission> getSoumissions() {
        return soumissions;
    }

    public Reouverture soumissions(Set<Soumission> soumissions) {
        this.soumissions = soumissions;
        return this;
    }

    public Reouverture addSoumission(Soumission soumission) {
        this.soumissions.add(soumission);
        soumission.setReouverture(this);
        return this;
    }

    public Reouverture removeSoumission(Soumission soumission) {
        this.soumissions.remove(soumission);
        soumission.setReouverture(null);
        return this;
    }

    public void setSoumissions(Set<Soumission> soumissions) {
        this.soumissions = soumissions;
    }

    public Set<Adjudication> getAdjudications() {
        return adjudications;
    }

    public Reouverture adjudications(Set<Adjudication> adjudications) {
        this.adjudications = adjudications;
        return this;
    }

    public Reouverture addAdjudication(Adjudication adjudication) {
        this.adjudications.add(adjudication);
        adjudication.setReouverture(this);
        return this;
    }

    public Reouverture removeAdjudication(Adjudication adjudication) {
        this.adjudications.remove(adjudication);
        adjudication.setReouverture(null);
        return this;
    }

    public void setAdjudications(Set<Adjudication> adjudications) {
        this.adjudications = adjudications;
    }

    public Set<Classement> getClassements() {
        return classements;
    }

    public Reouverture classements(Set<Classement> classements) {
        this.classements = classements;
        return this;
    }

    public Reouverture addClassement(Classement classement) {
        this.classements.add(classement);
        classement.setReouverture(this);
        return this;
    }

    public Reouverture removeClassement(Classement classement) {
        this.classements.remove(classement);
        classement.setReouverture(null);
        return this;
    }

    public void setClassements(Set<Classement> classements) {
        this.classements = classements;
    }

    public Set<Resultat> getResultats() {
        return resultats;
    }

    public Reouverture resultats(Set<Resultat> resultats) {
        this.resultats = resultats;
        return this;
    }

    public Reouverture addResultat(Resultat resultat) {
        this.resultats.add(resultat);
        resultat.setResultat(this);
        return this;
    }

    public Reouverture removeResultat(Resultat resultat) {
        this.resultats.remove(resultat);
        resultat.setResultat(null);
        return this;
    }

    public void setResultats(Set<Resultat> resultats) {
        this.resultats = resultats;
    }

    public Emission getEmission() {
        return emission;
    }

    public Reouverture emission(Emission emission) {
        this.emission = emission;
        return this;
    }

    public void setEmission(Emission emission) {
        this.emission = emission;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Reouverture)) {
            return false;
        }
        return id != null && id.equals(((Reouverture) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Reouverture{" +
            "id=" + getId() +
            ", codeValeur='" + getCodeValeur() + "'" +
            ", designationFr='" + getDesignationFr() + "'" +
            ", designationEn='" + getDesignationEn() + "'" +
            ", designationPt='" + getDesignationPt() + "'" +
            ", dateEmission='" + getDateEmission() + "'" +
            ", tauxInteret=" + getTauxInteret() +
            ", encoursEmission=" + getEncoursEmission() +
            ", uniteVolume='" + getUniteVolume() + "'" +
            ", montantSollicite=" + getMontantSollicite() +
            ", uniteMontant='" + getUniteMontant() + "'" +
            ", devise='" + getDevise() + "'" +
            ", dateEcheance='" + getDateEcheance() + "'" +
            ", dateValeur='" + getDateValeur() + "'" +
            ", operateur='" + getOperateur() + "'" +
            ", dateOperation='" + getDateOperation() + "'" +
            "}";
    }
}
