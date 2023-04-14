package com.tresor.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * Entite Classement
 */
@Entity
@Table(name = "classement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Classement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(value = 0)
    @Column(name = "rang", nullable = false)
    private Integer rang;

    @Column(name = "observation_fr")
    private String observationFr;

    @Column(name = "observation_en")
    private String observationEn;

    @Column(name = "observation_pt")
    private String observationPt;

    @ManyToOne
    @JsonIgnoreProperties(value = "classements", allowSetters = true)
    private DetailSoumission detailSoumission;

    @ManyToOne
    @JsonIgnoreProperties(value = "classements", allowSetters = true)
    private Emission emission;

    @ManyToOne
    @JsonIgnoreProperties(value = "classements", allowSetters = true)
    private Reouverture reouverture;

    @ManyToOne
    @JsonIgnoreProperties(value = "classements", allowSetters = true)
    private Rachat rachat;

    @ManyToOne
    @JsonIgnoreProperties(value = "classements", allowSetters = true)
    private Onc onc;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRang() {
        return rang;
    }

    public Classement rang(Integer rang) {
        this.rang = rang;
        return this;
    }

    public void setRang(Integer rang) {
        this.rang = rang;
    }

    public String getObservationFr() {
        return observationFr;
    }

    public Classement observationFr(String observationFr) {
        this.observationFr = observationFr;
        return this;
    }

    public void setObservationFr(String observationFr) {
        this.observationFr = observationFr;
    }

    public String getObservationEn() {
        return observationEn;
    }

    public Classement observationEn(String observationEn) {
        this.observationEn = observationEn;
        return this;
    }

    public void setObservationEn(String observationEn) {
        this.observationEn = observationEn;
    }

    public String getObservationPt() {
        return observationPt;
    }

    public Classement observationPt(String observationPt) {
        this.observationPt = observationPt;
        return this;
    }

    public void setObservationPt(String observationPt) {
        this.observationPt = observationPt;
    }

    public DetailSoumission getDetailSoumission() {
        return detailSoumission;
    }

    public Classement detailSoumission(DetailSoumission detailSoumission) {
        this.detailSoumission = detailSoumission;
        return this;
    }

    public void setDetailSoumission(DetailSoumission detailSoumission) {
        this.detailSoumission = detailSoumission;
    }

    public Emission getEmission() {
        return emission;
    }

    public Classement emission(Emission emission) {
        this.emission = emission;
        return this;
    }

    public void setEmission(Emission emission) {
        this.emission = emission;
    }

    public Reouverture getReouverture() {
        return reouverture;
    }

    public Classement reouverture(Reouverture reouverture) {
        this.reouverture = reouverture;
        return this;
    }

    public void setReouverture(Reouverture reouverture) {
        this.reouverture = reouverture;
    }

    public Rachat getRachat() {
        return rachat;
    }

    public Classement rachat(Rachat rachat) {
        this.rachat = rachat;
        return this;
    }

    public void setRachat(Rachat rachat) {
        this.rachat = rachat;
    }

    public Onc getOnc() {
        return onc;
    }

    public Classement onc(Onc onc) {
        this.onc = onc;
        return this;
    }

    public void setOnc(Onc onc) {
        this.onc = onc;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Classement)) {
            return false;
        }
        return id != null && id.equals(((Classement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Classement{" +
            "id=" + getId() +
            ", rang=" + getRang() +
            ", observationFr='" + getObservationFr() + "'" +
            ", observationEn='" + getObservationEn() + "'" +
            ", observationPt='" + getObservationPt() + "'" +
            "}";
    }
}
