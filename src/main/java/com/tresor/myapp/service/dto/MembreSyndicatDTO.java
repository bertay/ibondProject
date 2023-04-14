package com.tresor.myapp.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.tresor.myapp.domain.enumeration.TypeRang;

/**
 * A DTO for the {@link com.tresor.myapp.domain.MembreSyndicat} entity.
 */
@ApiModel(description = "Entite Syndicat membre")
public class MembreSyndicatDTO implements Serializable {
    
    private Long id;

    private TypeRang rang;

    private Float commission;

    @NotNull
    private String operateur;

    @NotNull
    private ZonedDateTime dateOperation;


    private Long emissionId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeRang getRang() {
        return rang;
    }

    public void setRang(TypeRang rang) {
        this.rang = rang;
    }

    public Float getCommission() {
        return commission;
    }

    public void setCommission(Float commission) {
        this.commission = commission;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MembreSyndicatDTO)) {
            return false;
        }

        return id != null && id.equals(((MembreSyndicatDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MembreSyndicatDTO{" +
            "id=" + getId() +
            ", rang='" + getRang() + "'" +
            ", commission=" + getCommission() +
            ", operateur='" + getOperateur() + "'" +
            ", dateOperation='" + getDateOperation() + "'" +
            ", emissionId=" + getEmissionId() +
            "}";
    }
}
