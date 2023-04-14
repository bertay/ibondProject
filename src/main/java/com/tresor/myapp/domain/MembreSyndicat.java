package com.tresor.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

import com.tresor.myapp.domain.enumeration.TypeRang;

/**
 * Entite Syndicat membre
 */
@Entity
@Table(name = "membre_syndicat")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MembreSyndicat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "rang")
    private TypeRang rang;

    @Column(name = "commission")
    private Float commission;

    @NotNull
    @Column(name = "operateur", nullable = false)
    private String operateur;

    @NotNull
    @Column(name = "date_operation", nullable = false)
    private ZonedDateTime dateOperation;

    @ManyToOne
    @JsonIgnoreProperties(value = "membreSyndicats", allowSetters = true)
    private Emission emission;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeRang getRang() {
        return rang;
    }

    public MembreSyndicat rang(TypeRang rang) {
        this.rang = rang;
        return this;
    }

    public void setRang(TypeRang rang) {
        this.rang = rang;
    }

    public Float getCommission() {
        return commission;
    }

    public MembreSyndicat commission(Float commission) {
        this.commission = commission;
        return this;
    }

    public void setCommission(Float commission) {
        this.commission = commission;
    }

    public String getOperateur() {
        return operateur;
    }

    public MembreSyndicat operateur(String operateur) {
        this.operateur = operateur;
        return this;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    public ZonedDateTime getDateOperation() {
        return dateOperation;
    }

    public MembreSyndicat dateOperation(ZonedDateTime dateOperation) {
        this.dateOperation = dateOperation;
        return this;
    }

    public void setDateOperation(ZonedDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    public Emission getEmission() {
        return emission;
    }

    public MembreSyndicat emission(Emission emission) {
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
        if (!(o instanceof MembreSyndicat)) {
            return false;
        }
        return id != null && id.equals(((MembreSyndicat) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MembreSyndicat{" +
            "id=" + getId() +
            ", rang='" + getRang() + "'" +
            ", commission=" + getCommission() +
            ", operateur='" + getOperateur() + "'" +
            ", dateOperation='" + getDateOperation() + "'" +
            "}";
    }
}
