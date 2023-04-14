package com.tresor.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import com.tresor.myapp.domain.enumeration.TypeValeur;

import com.tresor.myapp.domain.enumeration.UniteDuree;

/**
 * Entite NatureTitre
 */
@Entity
@Table(name = "nature_titre")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NatureTitre implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "code_nature", nullable = false)
    private String codeNature;

    @Column(name = "designation_fr")
    private String designationFr;

    @Column(name = "designation_en")
    private String designationEn;

    @Column(name = "designation_pt")
    private String designationPt;

    @NotNull
    @Min(value = 0L)
    @Column(name = "nominal_unitaire", nullable = false)
    private Long nominalUnitaire;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "unite_valeur", nullable = false)
    private TypeValeur uniteValeur;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "nature_echeance", nullable = false)
    private UniteDuree natureEcheance;

    @NotNull
    @Column(name = "operateur", nullable = false)
    private String operateur;

    @NotNull
    @Column(name = "date_operation", nullable = false)
    private ZonedDateTime dateOperation;

    @OneToMany(mappedBy = "natureTitre")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Emission> emissions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeNature() {
        return codeNature;
    }

    public NatureTitre codeNature(String codeNature) {
        this.codeNature = codeNature;
        return this;
    }

    public void setCodeNature(String codeNature) {
        this.codeNature = codeNature;
    }

    public String getDesignationFr() {
        return designationFr;
    }

    public NatureTitre designationFr(String designationFr) {
        this.designationFr = designationFr;
        return this;
    }

    public void setDesignationFr(String designationFr) {
        this.designationFr = designationFr;
    }

    public String getDesignationEn() {
        return designationEn;
    }

    public NatureTitre designationEn(String designationEn) {
        this.designationEn = designationEn;
        return this;
    }

    public void setDesignationEn(String designationEn) {
        this.designationEn = designationEn;
    }

    public String getDesignationPt() {
        return designationPt;
    }

    public NatureTitre designationPt(String designationPt) {
        this.designationPt = designationPt;
        return this;
    }

    public void setDesignationPt(String designationPt) {
        this.designationPt = designationPt;
    }

    public Long getNominalUnitaire() {
        return nominalUnitaire;
    }

    public NatureTitre nominalUnitaire(Long nominalUnitaire) {
        this.nominalUnitaire = nominalUnitaire;
        return this;
    }

    public void setNominalUnitaire(Long nominalUnitaire) {
        this.nominalUnitaire = nominalUnitaire;
    }

    public TypeValeur getUniteValeur() {
        return uniteValeur;
    }

    public NatureTitre uniteValeur(TypeValeur uniteValeur) {
        this.uniteValeur = uniteValeur;
        return this;
    }

    public void setUniteValeur(TypeValeur uniteValeur) {
        this.uniteValeur = uniteValeur;
    }

    public UniteDuree getNatureEcheance() {
        return natureEcheance;
    }

    public NatureTitre natureEcheance(UniteDuree natureEcheance) {
        this.natureEcheance = natureEcheance;
        return this;
    }

    public void setNatureEcheance(UniteDuree natureEcheance) {
        this.natureEcheance = natureEcheance;
    }

    public String getOperateur() {
        return operateur;
    }

    public NatureTitre operateur(String operateur) {
        this.operateur = operateur;
        return this;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    public ZonedDateTime getDateOperation() {
        return dateOperation;
    }

    public NatureTitre dateOperation(ZonedDateTime dateOperation) {
        this.dateOperation = dateOperation;
        return this;
    }

    public void setDateOperation(ZonedDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    public Set<Emission> getEmissions() {
        return emissions;
    }

    public NatureTitre emissions(Set<Emission> emissions) {
        this.emissions = emissions;
        return this;
    }

    public NatureTitre addEmission(Emission emission) {
        this.emissions.add(emission);
        emission.setNatureTitre(this);
        return this;
    }

    public NatureTitre removeEmission(Emission emission) {
        this.emissions.remove(emission);
        emission.setNatureTitre(null);
        return this;
    }

    public void setEmissions(Set<Emission> emissions) {
        this.emissions = emissions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NatureTitre)) {
            return false;
        }
        return id != null && id.equals(((NatureTitre) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NatureTitre{" +
            "id=" + getId() +
            ", codeNature='" + getCodeNature() + "'" +
            ", designationFr='" + getDesignationFr() + "'" +
            ", designationEn='" + getDesignationEn() + "'" +
            ", designationPt='" + getDesignationPt() + "'" +
            ", nominalUnitaire=" + getNominalUnitaire() +
            ", uniteValeur='" + getUniteValeur() + "'" +
            ", natureEcheance='" + getNatureEcheance() + "'" +
            ", operateur='" + getOperateur() + "'" +
            ", dateOperation='" + getDateOperation() + "'" +
            "}";
    }
}
