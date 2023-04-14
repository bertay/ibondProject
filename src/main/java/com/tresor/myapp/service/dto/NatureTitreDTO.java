package com.tresor.myapp.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.tresor.myapp.domain.enumeration.TypeValeur;
import com.tresor.myapp.domain.enumeration.UniteDuree;

/**
 * A DTO for the {@link com.tresor.myapp.domain.NatureTitre} entity.
 */
@ApiModel(description = "Entite NatureTitre")
public class NatureTitreDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String codeNature;

    private String designationFr;

    private String designationEn;

    private String designationPt;

    @NotNull
    @Min(value = 0L)
    private Long nominalUnitaire;

    @NotNull
    private TypeValeur uniteValeur;

    @NotNull
    private UniteDuree natureEcheance;

    @NotNull
    private String operateur;

    @NotNull
    private ZonedDateTime dateOperation;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeNature() {
        return codeNature;
    }

    public void setCodeNature(String codeNature) {
        this.codeNature = codeNature;
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

    public String getDesignationPt() {
        return designationPt;
    }

    public void setDesignationPt(String designationPt) {
        this.designationPt = designationPt;
    }

    public Long getNominalUnitaire() {
        return nominalUnitaire;
    }

    public void setNominalUnitaire(Long nominalUnitaire) {
        this.nominalUnitaire = nominalUnitaire;
    }

    public TypeValeur getUniteValeur() {
        return uniteValeur;
    }

    public void setUniteValeur(TypeValeur uniteValeur) {
        this.uniteValeur = uniteValeur;
    }

    public UniteDuree getNatureEcheance() {
        return natureEcheance;
    }

    public void setNatureEcheance(UniteDuree natureEcheance) {
        this.natureEcheance = natureEcheance;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NatureTitreDTO)) {
            return false;
        }

        return id != null && id.equals(((NatureTitreDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NatureTitreDTO{" +
            "id=" + getId() +
            ", codeNature='" + getCodeNature() + "'" +
            ", designationFr='" + getDesignationFr() + "'" +
            ", designationEn='" + getDesignationEn() + "'" +
            ", designationPt='" + getDesignationPt() + "'" +
            ", nominalUnitaire=" + getNominalUnitaire() +
            ", uniteValeur='" + getUniteValeur() + "'" +
            ", natureEcheance='" + getNatureEcheance() + "'" +
            ", operateur='" + getOperateur() + "'" +
            ", dateOperation='" + getDateOperation() + "'" +
            "}";
    }
}
