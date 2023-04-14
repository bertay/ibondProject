package com.tresor.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Entite Pays
 */
@Entity
@Table(name = "pays")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Pays implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_pays")
    private String codePays;

    @Column(name = "designation_fr")
    private String designationFr;

    @Column(name = "designation_en")
    private String designationEn;

    @Column(name = "designation_pt")
    private String designationPt;

    @NotNull
    @Column(name = "operateur", nullable = false)
    private String operateur;

    @NotNull
    @Column(name = "date_operation", nullable = false)
    private ZonedDateTime dateOperation;

    @OneToMany(mappedBy = "pays")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Svt> svts = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodePays() {
        return codePays;
    }

    public Pays codePays(String codePays) {
        this.codePays = codePays;
        return this;
    }

    public void setCodePays(String codePays) {
        this.codePays = codePays;
    }

    public String getDesignationFr() {
        return designationFr;
    }

    public Pays designationFr(String designationFr) {
        this.designationFr = designationFr;
        return this;
    }

    public void setDesignationFr(String designationFr) {
        this.designationFr = designationFr;
    }

    public String getDesignationEn() {
        return designationEn;
    }

    public Pays designationEn(String designationEn) {
        this.designationEn = designationEn;
        return this;
    }

    public void setDesignationEn(String designationEn) {
        this.designationEn = designationEn;
    }

    public String getDesignationPt() {
        return designationPt;
    }

    public Pays designationPt(String designationPt) {
        this.designationPt = designationPt;
        return this;
    }

    public void setDesignationPt(String designationPt) {
        this.designationPt = designationPt;
    }

    public String getOperateur() {
        return operateur;
    }

    public Pays operateur(String operateur) {
        this.operateur = operateur;
        return this;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    public ZonedDateTime getDateOperation() {
        return dateOperation;
    }

    public Pays dateOperation(ZonedDateTime dateOperation) {
        this.dateOperation = dateOperation;
        return this;
    }

    public void setDateOperation(ZonedDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    public Set<Svt> getSvts() {
        return svts;
    }

    public Pays svts(Set<Svt> svts) {
        this.svts = svts;
        return this;
    }

    public Pays addSvt(Svt svt) {
        this.svts.add(svt);
        svt.setPays(this);
        return this;
    }

    public Pays removeSvt(Svt svt) {
        this.svts.remove(svt);
        svt.setPays(null);
        return this;
    }

    public void setSvts(Set<Svt> svts) {
        this.svts = svts;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pays)) {
            return false;
        }
        return id != null && id.equals(((Pays) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Pays{" +
            "id=" + getId() +
            ", codePays='" + getCodePays() + "'" +
            ", designationFr='" + getDesignationFr() + "'" +
            ", designationEn='" + getDesignationEn() + "'" +
            ", designationPt='" + getDesignationPt() + "'" +
            ", operateur='" + getOperateur() + "'" +
            ", dateOperation='" + getDateOperation() + "'" +
            "}";
    }
}
