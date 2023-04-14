package com.tresor.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

import com.tresor.myapp.domain.enumeration.EtatSvt;

/**
 * Entite Svt
 */
@Entity
@Table(name = "svt")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Svt implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "abreviation_fr")
    private String abreviationFr;

    @Column(name = "abreviation_en")
    private String abreviationEn;

    @Column(name = "abreviation_pt")
    private String abreviationPt;

    @NotNull
    @Column(name = "designation_fr", nullable = false)
    private String designationFr;

    @Column(name = "designation_en")
    private String designationEn;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat")
    private EtatSvt etat;

    @NotNull
    @Column(name = "operateur", nullable = false)
    private String operateur;

    @NotNull
    @Column(name = "date_operation", nullable = false)
    private ZonedDateTime dateOperation;

    @ManyToOne
    @JsonIgnoreProperties(value = "svts", allowSetters = true)
    private Pays pays;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAbreviationFr() {
        return abreviationFr;
    }

    public Svt abreviationFr(String abreviationFr) {
        this.abreviationFr = abreviationFr;
        return this;
    }

    public void setAbreviationFr(String abreviationFr) {
        this.abreviationFr = abreviationFr;
    }

    public String getAbreviationEn() {
        return abreviationEn;
    }

    public Svt abreviationEn(String abreviationEn) {
        this.abreviationEn = abreviationEn;
        return this;
    }

    public void setAbreviationEn(String abreviationEn) {
        this.abreviationEn = abreviationEn;
    }

    public String getAbreviationPt() {
        return abreviationPt;
    }

    public Svt abreviationPt(String abreviationPt) {
        this.abreviationPt = abreviationPt;
        return this;
    }

    public void setAbreviationPt(String abreviationPt) {
        this.abreviationPt = abreviationPt;
    }

    public String getDesignationFr() {
        return designationFr;
    }

    public Svt designationFr(String designationFr) {
        this.designationFr = designationFr;
        return this;
    }

    public void setDesignationFr(String designationFr) {
        this.designationFr = designationFr;
    }

    public String getDesignationEn() {
        return designationEn;
    }

    public Svt designationEn(String designationEn) {
        this.designationEn = designationEn;
        return this;
    }

    public void setDesignationEn(String designationEn) {
        this.designationEn = designationEn;
    }

    public EtatSvt getEtat() {
        return etat;
    }

    public Svt etat(EtatSvt etat) {
        this.etat = etat;
        return this;
    }

    public void setEtat(EtatSvt etat) {
        this.etat = etat;
    }

    public String getOperateur() {
        return operateur;
    }

    public Svt operateur(String operateur) {
        this.operateur = operateur;
        return this;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    public ZonedDateTime getDateOperation() {
        return dateOperation;
    }

    public Svt dateOperation(ZonedDateTime dateOperation) {
        this.dateOperation = dateOperation;
        return this;
    }

    public void setDateOperation(ZonedDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    public Pays getPays() {
        return pays;
    }

    public Svt pays(Pays pays) {
        this.pays = pays;
        return this;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Svt)) {
            return false;
        }
        return id != null && id.equals(((Svt) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Svt{" +
            "id=" + getId() +
            ", abreviationFr='" + getAbreviationFr() + "'" +
            ", abreviationEn='" + getAbreviationEn() + "'" +
            ", abreviationPt='" + getAbreviationPt() + "'" +
            ", designationFr='" + getDesignationFr() + "'" +
            ", designationEn='" + getDesignationEn() + "'" +
            ", etat='" + getEtat() + "'" +
            ", operateur='" + getOperateur() + "'" +
            ", dateOperation='" + getDateOperation() + "'" +
            "}";
    }
}
