package com.tresor.myapp.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.tresor.myapp.domain.enumeration.UniteNombre;
import com.tresor.myapp.domain.enumeration.TypeValeur;
import com.tresor.myapp.domain.enumeration.TypeOperation;

/**
 * A DTO for the {@link com.tresor.myapp.domain.DetailCalendrier} entity.
 */
@ApiModel(description = "Entite Detail Calendrier")
public class DetailCalendrierDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String periode;

    @NotNull
    private Integer annee;

    @NotNull
    private LocalDate dateAnnonce;

    private LocalDate dateAdjudication;

    @NotNull
    private LocalDate dateEcheance;

    private String duree;

    @NotNull
    @DecimalMin(value = "0")
    private Double volumeEmission;

    @NotNull
    private UniteNombre uniteVolume;

    @NotNull
    private TypeValeur devise;

    private LocalDate dateValeur;

    @NotNull
    private TypeOperation nature;

    @NotNull
    private String operateur;

    @NotNull
    private ZonedDateTime dateOperation;


    private Long calendrierId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public LocalDate getDateAnnonce() {
        return dateAnnonce;
    }

    public void setDateAnnonce(LocalDate dateAnnonce) {
        this.dateAnnonce = dateAnnonce;
    }

    public LocalDate getDateAdjudication() {
        return dateAdjudication;
    }

    public void setDateAdjudication(LocalDate dateAdjudication) {
        this.dateAdjudication = dateAdjudication;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public Double getVolumeEmission() {
        return volumeEmission;
    }

    public void setVolumeEmission(Double volumeEmission) {
        this.volumeEmission = volumeEmission;
    }

    public UniteNombre getUniteVolume() {
        return uniteVolume;
    }

    public void setUniteVolume(UniteNombre uniteVolume) {
        this.uniteVolume = uniteVolume;
    }

    public TypeValeur getDevise() {
        return devise;
    }

    public void setDevise(TypeValeur devise) {
        this.devise = devise;
    }

    public LocalDate getDateValeur() {
        return dateValeur;
    }

    public void setDateValeur(LocalDate dateValeur) {
        this.dateValeur = dateValeur;
    }

    public TypeOperation getNature() {
        return nature;
    }

    public void setNature(TypeOperation nature) {
        this.nature = nature;
    }

    public String getOperateur() {
        return operateur;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    public ZonedDateTime getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(ZonedDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    public Long getCalendrierId() {
        return calendrierId;
    }

    public void setCalendrierId(Long calendrierId) {
        this.calendrierId = calendrierId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DetailCalendrierDTO)) {
            return false;
        }

        return id != null && id.equals(((DetailCalendrierDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DetailCalendrierDTO{" +
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
            ", calendrierId=" + getCalendrierId() +
            "}";
    }
}
