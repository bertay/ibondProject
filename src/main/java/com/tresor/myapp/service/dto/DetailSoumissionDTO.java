package com.tresor.myapp.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.tresor.myapp.domain.DetailSoumission} entity.
 */
@ApiModel(description = "Entite DetailSoumission")
public class DetailSoumissionDTO implements Serializable {
    
    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    private Double montantSoumission;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Float tauxPropose;

    @NotNull
    private String operateur;

    @NotNull
    private ZonedDateTime dateOperation;


    private Long soumissionId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMontantSoumission() {
        return montantSoumission;
    }

    public void setMontantSoumission(Double montantSoumission) {
        this.montantSoumission = montantSoumission;
    }

    public Float getTauxPropose() {
        return tauxPropose;
    }

    public void setTauxPropose(Float tauxPropose) {
        this.tauxPropose = tauxPropose;
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

    public Long getSoumissionId() {
        return soumissionId;
    }

    public void setSoumissionId(Long soumissionId) {
        this.soumissionId = soumissionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DetailSoumissionDTO)) {
            return false;
        }

        return id != null && id.equals(((DetailSoumissionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DetailSoumissionDTO{" +
            "id=" + getId() +
            ", montantSoumission=" + getMontantSoumission() +
            ", tauxPropose=" + getTauxPropose() +
            ", operateur='" + getOperateur() + "'" +
            ", dateOperation='" + getDateOperation() + "'" +
            ", soumissionId=" + getSoumissionId() +
            "}";
    }
}
