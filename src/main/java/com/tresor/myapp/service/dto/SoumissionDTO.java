package com.tresor.myapp.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.tresor.myapp.domain.Soumission} entity.
 */
@ApiModel(description = "Entite Soumission")
public class SoumissionDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String numAnonymat;

    @NotNull
    private ZonedDateTime dateSoumission;

    @Min(value = 0)
    private Integer nbreSoumission;

    @NotNull
    private String operateur;

    @NotNull
    private ZonedDateTime dateOperation;


    private Long emissionId;

    private Long reouvertureId;

    private Long rachatId;

    private Long oncId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumAnonymat() {
        return numAnonymat;
    }

    public void setNumAnonymat(String numAnonymat) {
        this.numAnonymat = numAnonymat;
    }

    public ZonedDateTime getDateSoumission() {
        return dateSoumission;
    }

    public void setDateSoumission(ZonedDateTime dateSoumission) {
        this.dateSoumission = dateSoumission;
    }

    public Integer getNbreSoumission() {
        return nbreSoumission;
    }

    public void setNbreSoumission(Integer nbreSoumission) {
        this.nbreSoumission = nbreSoumission;
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

    public Long getEmissionId() {
        return emissionId;
    }

    public void setEmissionId(Long emissionId) {
        this.emissionId = emissionId;
    }

    public Long getReouvertureId() {
        return reouvertureId;
    }

    public void setReouvertureId(Long reouvertureId) {
        this.reouvertureId = reouvertureId;
    }

    public Long getRachatId() {
        return rachatId;
    }

    public void setRachatId(Long rachatId) {
        this.rachatId = rachatId;
    }

    public Long getOncId() {
        return oncId;
    }

    public void setOncId(Long oncId) {
        this.oncId = oncId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SoumissionDTO)) {
            return false;
        }

        return id != null && id.equals(((SoumissionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SoumissionDTO{" +
            "id=" + getId() +
            ", numAnonymat='" + getNumAnonymat() + "'" +
            ", dateSoumission='" + getDateSoumission() + "'" +
            ", nbreSoumission=" + getNbreSoumission() +
            ", operateur='" + getOperateur() + "'" +
            ", dateOperation='" + getDateOperation() + "'" +
            ", emissionId=" + getEmissionId() +
            ", reouvertureId=" + getReouvertureId() +
            ", rachatId=" + getRachatId() +
            ", oncId=" + getOncId() +
            "}";
    }
}
