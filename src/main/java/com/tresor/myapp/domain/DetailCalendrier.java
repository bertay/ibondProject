package com.tresor.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import com.tresor.myapp.domain.enumeration.UniteNombre;

import com.tresor.myapp.domain.enumeration.TypeValeur;

import com.tresor.myapp.domain.enumeration.TypeOperation;

/**
 * Entite Detail Calendrier
 */
@Entity
@Table(name = "detail_calendrier")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DetailCalendrier implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "periode", nullable = false)
    private String periode;

    @NotNull
    @Column(name = "annee", nullable = false)
    private Integer annee;

    @NotNull
    @Column(name = "date_annonce", nullable = false)
    private LocalDate dateAnnonce;

    @Column(name = "date_adjudication")
    private LocalDate dateAdjudication;

    @NotNull
    @Column(name = "date_echeance", nullable = false)
    private LocalDate dateEcheance;

    @Column(name = "duree")
    private String duree;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "volume_emission", nullable = false)
    private Double volumeEmission;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "unite_volume", nullable = false)
    private UniteNombre uniteVolume;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "devise", nullable = false)
    private TypeValeur devise;

    @Column(name = "date_valeur")
    private LocalDate dateValeur;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "nature", nullable = false)
    private TypeOperation nature;

    @NotNull
    @Column(name = "operateur", nullable = false)
    private String operateur;

    @NotNull
    @Column(name = "date_operation", nullable = false)
    private ZonedDateTime dateOperation;

    @ManyToOne
    @JsonIgnoreProperties(value = "detailCalendriers", allowSetters = true)
    private Calendrier calendrier;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPeriode() {
        return periode;
    }

    public DetailCalendrier periode(String periode) {
        this.periode = periode;
        return this;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public Integer getAnnee() {
        return annee;
    }

    public DetailCalendrier annee(Integer annee) {
        this.annee = annee;
        return this;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public LocalDate getDateAnnonce() {
        return dateAnnonce;
    }

    public DetailCalendrier dateAnnonce(LocalDate dateAnnonce) {
        this.dateAnnonce = dateAnnonce;
        return this;
    }

    public void setDateAnnonce(LocalDate dateAnnonce) {
        this.dateAnnonce = dateAnnonce;
    }

    public LocalDate getDateAdjudication() {
        return dateAdjudication;
    }

    public DetailCalendrier dateAdjudication(LocalDate dateAdjudication) {
        this.dateAdjudication = dateAdjudication;
        return this;
    }

    public void setDateAdjudication(LocalDate dateAdjudication) {
        this.dateAdjudication = dateAdjudication;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public DetailCalendrier dateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
        return this;
    }

    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public String getDuree() {
        return duree;
    }

    public DetailCalendrier duree(String duree) {
        this.duree = duree;
        return this;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public Double getVolumeEmission() {
        return volumeEmission;
    }

    public DetailCalendrier volumeEmission(Double volumeEmission) {
        this.volumeEmission = volumeEmission;
        return this;
    }

    public void setVolumeEmission(Double volumeEmission) {
        this.volumeEmission = volumeEmission;
    }

    public UniteNombre getUniteVolume() {
        return uniteVolume;
    }

    public DetailCalendrier uniteVolume(UniteNombre uniteVolume) {
        this.uniteVolume = uniteVolume;
        return this;
    }

    public void setUniteVolume(UniteNombre uniteVolume) {
        this.uniteVolume = uniteVolume;
    }

    public TypeValeur getDevise() {
        return devise;
    }

    public DetailCalendrier devise(TypeValeur devise) {
        this.devise = devise;
        return this;
    }

    public void setDevise(TypeValeur devise) {
        this.devise = devise;
    }

    public LocalDate getDateValeur() {
        return dateValeur;
    }

    public DetailCalendrier dateValeur(LocalDate dateValeur) {
        this.dateValeur = dateValeur;
        return this;
    }

    public void setDateValeur(LocalDate dateValeur) {
        this.dateValeur = dateValeur;
    }

    public TypeOperation getNature() {
        return nature;
    }

    public DetailCalendrier nature(TypeOperation nature) {
        this.nature = nature;
        return this;
    }

    public void setNature(TypeOperation nature) {
        this.nature = nature;
    }

    public String getOperateur() {
        return operateur;
    }

    public DetailCalendrier operateur(String operateur) {
        this.operateur = operateur;
        return this;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    public ZonedDateTime getDateOperation() {
        return dateOperation;
    }

    public DetailCalendrier dateOperation(ZonedDateTime dateOperation) {
        this.dateOperation = dateOperation;
        return this;
    }

    public void setDateOperation(ZonedDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    public Calendrier getCalendrier() {
        return calendrier;
    }

    public DetailCalendrier calendrier(Calendrier calendrier) {
        this.calendrier = calendrier;
        return this;
    }

    public void setCalendrier(Calendrier calendrier) {
        this.calendrier = calendrier;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DetailCalendrier)) {
            return false;
        }
        return id != null && id.equals(((DetailCalendrier) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DetailCalendrier{" +
            "id=" + getId() +
            ", periode='" + getPeriode() + "'" +
            ", annee=" + getAnnee() +
            ", dateAnnonce='" + getDateAnnonce() + "'" +
            ", dateAdjudication='" + getDateAdjudication() + "'" +
            ", dateEcheance='" + getDateEcheance() + "'" +
            ", duree='" + getDuree() + "'" +
            ", volumeEmission=" + getVolumeEmission() +
            ", uniteVolume='" + getUniteVolume() + "'" +
            ", devise='" + getDevise() + "'" +
            ", dateValeur='" + getDateValeur() + "'" +
            ", nature='" + getNature() + "'" +
            ", operateur='" + getOperateur() + "'" +
            ", dateOperation='" + getDateOperation() + "'" +
            "}";
    }
}
