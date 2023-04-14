package com.tresor.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Entite DetailSoumission
 */
@Entity
@Table(name = "detail_soumission")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DetailSoumission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "montant_soumission", nullable = false)
    private Double montantSoumission;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "taux_propose", nullable = false)
    private Float tauxPropose;

    @NotNull
    @Column(name = "operateur", nullable = false)
    private String operateur;

    @NotNull
    @Column(name = "date_operation", nullable = false)
    private ZonedDateTime dateOperation;

    @OneToMany(mappedBy = "detailSoumission")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Classement> classements = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "detailSoumissions", allowSetters = true)
    private Soumission soumission;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMontantSoumission() {
        return montantSoumission;
    }

    public DetailSoumission montantSoumission(Double montantSoumission) {
        this.montantSoumission = montantSoumission;
        return this;
    }

    public void setMontantSoumission(Double montantSoumission) {
        this.montantSoumission = montantSoumission;
    }

    public Float getTauxPropose() {
        return tauxPropose;
    }

    public DetailSoumission tauxPropose(Float tauxPropose) {
        this.tauxPropose = tauxPropose;
        return this;
    }

    public void setTauxPropose(Float tauxPropose) {
        this.tauxPropose = tauxPropose;
    }

    public String getOperateur() {
        return operateur;
    }

    public DetailSoumission operateur(String operateur) {
        this.operateur = operateur;
        return this;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    public ZonedDateTime getDateOperation() {
        return dateOperation;
    }

    public DetailSoumission dateOperation(ZonedDateTime dateOperation) {
        this.dateOperation = dateOperation;
        return this;
    }

    public void setDateOperation(ZonedDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    public Set<Classement> getClassements() {
        return classements;
    }

    public DetailSoumission classements(Set<Classement> classements) {
        this.classements = classements;
        return this;
    }

    public DetailSoumission addClassement(Classement classement) {
        this.classements.add(classement);
        classement.setDetailSoumission(this);
        return this;
    }

    public DetailSoumission removeClassement(Classement classement) {
        this.classements.remove(classement);
        classement.setDetailSoumission(null);
        return this;
    }

    public void setClassements(Set<Classement> classements) {
        this.classements = classements;
    }

    public Soumission getSoumission() {
        return soumission;
    }

    public DetailSoumission soumission(Soumission soumission) {
        this.soumission = soumission;
        return this;
    }

    public void setSoumission(Soumission soumission) {
        this.soumission = soumission;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DetailSoumission)) {
            return false;
        }
        return id != null && id.equals(((DetailSoumission) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DetailSoumission{" +
            "id=" + getId() +
            ", montantSoumission=" + getMontantSoumission() +
            ", tauxPropose=" + getTauxPropose() +
            ", operateur='" + getOperateur() + "'" +
            ", dateOperation='" + getDateOperation() + "'" +
            "}";
    }
}
