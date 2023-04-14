package com.tresor.myapp.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.tresor.myapp.domain.enumeration.EtatSvt;

/**
 * A DTO for the {@link com.tresor.myapp.domain.Svt} entity.
 */
@ApiModel(description = "Entite Svt")
public class SvtDTO implements Serializable {
    
    private Long id;

    private String abreviationFr;

    private String abreviationEn;

    private String abreviationPt;

    @NotNull
    private String designationFr;

    private String designationEn;

    private EtatSvt etat;

    @NotNull
    private String operateur;

    @NotNull
    private ZonedDateTime dateOperation;


    private Long paysId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAbreviationFr() {
        return abreviationFr;
    }

    public void setAbreviationFr(String abreviationFr) {
        this.abreviationFr = abreviationFr;
    }

    public String getAbreviationEn() {
        return abreviationEn;
    }

    public void setAbreviationEn(String abreviationEn) {
        this.abreviationEn = abreviationEn;
    }

    public String getAbreviationPt() {
        return abreviationPt;
    }

    public void setAbreviationPt(String abreviationPt) {
        this.abreviationPt = abreviationPt;
    }

    public String getDesignationFr() {
        return designationFr;
    }

    public void setDesignationFr(String designationFr) {
        this.designationFr = designationFr;
    }

    public String getDesignationEn() {
        return designationEn;
    }

    public void setDesignationEn(String designationEn) {
        this.designationEn = designationEn;
    }

    public EtatSvt getEtat() {
        return etat;
    }

    public void setEtat(EtatSvt etat) {
        this.etat = etat;
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

    public Long getPaysId() {
        return paysId;
    }

    public void setPaysId(Long paysId) {
        this.paysId = paysId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SvtDTO)) {
            return false;
        }

        return id != null && id.equals(((SvtDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SvtDTO{" +
            "id=" + getId() +
            ", abreviationFr='" + getAbreviationFr() + "'" +
            ", abreviationEn='" + getAbreviationEn() + "'" +
            ", abreviationPt='" + getAbreviationPt() + "'" +
            ", designationFr='" + getDesignationFr() + "'" +
            ", designationEn='" + getDesignationEn() + "'" +
            ", etat='" + getEtat() + "'" +
            ", operateur='" + getOperateur() + "'" +
            ", dateOperation='" + getDateOperation() + "'" +
            ", paysId=" + getPaysId() +
            "}";
    }
}
