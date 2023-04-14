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
 * Entite Onc
 */
@Entity
@Table(name = "onc")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Onc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "operateur", nullable = false)
    private String operateur;

    @NotNull
    @Column(name = "date_operation", nullable = false)
    private ZonedDateTime dateOperation;

    @OneToMany(mappedBy = "onc")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Soumission> soumissions = new HashSet<>();

    @OneToMany(mappedBy = "onc")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Adjudication> adjudications = new HashSet<>();

    @OneToMany(mappedBy = "onc")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Classement> classements = new HashSet<>();

    @OneToMany(mappedBy = "onc")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Resultat> resultats = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "oncs", allowSetters = true)
    private Emission emission;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperateur() {
        return operateur;
    }

    public Onc operateur(String operateur) {
        this.operateur = operateur;
        return this;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    public ZonedDateTime getDateOperation() {
        return dateOperation;
    }

    public Onc dateOperation(ZonedDateTime dateOperation) {
        this.dateOperation = dateOperation;
        return this;
    }

    public void setDateOperation(ZonedDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    public Set<Soumission> getSoumissions() {
        return soumissions;
    }

    public Onc soumissions(Set<Soumission> soumissions) {
        this.soumissions = soumissions;
        return this;
    }

    public Onc addSoumission(Soumission soumission) {
        this.soumissions.add(soumission);
        soumission.setOnc(this);
        return this;
    }

    public Onc removeSoumission(Soumission soumission) {
        this.soumissions.remove(soumission);
        soumission.setOnc(null);
        return this;
    }

    public void setSoumissions(Set<Soumission> soumissions) {
        this.soumissions = soumissions;
    }

    public Set<Adjudication> getAdjudications() {
        return adjudications;
    }

    public Onc adjudications(Set<Adjudication> adjudications) {
        this.adjudications = adjudications;
        return this;
    }

    public Onc addAdjudication(Adjudication adjudication) {
        this.adjudications.add(adjudication);
        adjudication.setOnc(this);
        return this;
    }

    public Onc removeAdjudication(Adjudication adjudication) {
        this.adjudications.remove(adjudication);
        adjudication.setOnc(null);
        return this;
    }

    public void setAdjudications(Set<Adjudication> adjudications) {
        this.adjudications = adjudications;
    }

    public Set<Classement> getClassements() {
        return classements;
    }

    public Onc classements(Set<Classement> classements) {
        this.classements = classements;
        return this;
    }

    public Onc addClassement(Classement classement) {
        this.classements.add(classement);
        classement.setOnc(this);
        return this;
    }

    public Onc removeClassement(Classement classement) {
        this.classements.remove(classement);
        classement.setOnc(null);
        return this;
    }

    public void setClassements(Set<Classement> classements) {
        this.classements = classements;
    }

    public Set<Resultat> getResultats() {
        return resultats;
    }

    public Onc resultats(Set<Resultat> resultats) {
        this.resultats = resultats;
        return this;
    }

    public Onc addResultat(Resultat resultat) {
        this.resultats.add(resultat);
        resultat.setOnc(this);
        return this;
    }

    public Onc removeResultat(Resultat resultat) {
        this.resultats.remove(resultat);
        resultat.setOnc(null);
        return this;
    }

    public void setResultats(Set<Resultat> resultats) {
        this.resultats = resultats;
    }

    public Emission getEmission() {
        return emission;
    }

    public Onc emission(Emission emission) {
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
        if (!(o instanceof Onc)) {
            return false;
        }
        return id != null && id.equals(((Onc) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Onc{" +
            "id=" + getId() +
            ", operateur='" + getOperateur() + "'" +
            ", dateOperation='" + getDateOperation() + "'" +
            "}";
    }
}
