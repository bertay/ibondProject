package com.tresor.myapp.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.tresor.myapp.domain.enumeration.UniteNombre;
import com.tresor.myapp.domain.enumeration.TypeValeur;

/**
 * A DTO for the {@link com.tresor.myapp.domain.Reouverture} entity.
 */
@ApiModel(description = "Entite Reouverture")
public class ReouvertureDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String codeValeur;

    private String designationFr;

    private String designationEn;

    private String designationPt;

    @NotNull
    private LocalDate dateEmission;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Float tauxInteret;

    @NotNull
    @DecimalMin(value = "0")
    private Double encoursEmission;

    @NotNull
    private UniteNombre uniteVolume;

    @NotNull
    @DecimalMin(value = "0")
    private Double montantSollicite;

    @NotNull
    private UniteNombre uniteMontant;

    @NotNull
    private TypeValeur devise;

    @NotNull
    private LocalDate dateEcheance;

    private LocalDate dateValeur;

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

    public String getCodeValeur() {
        return codeValeur;
    }

    public void setCodeValeur(String codeValeur) {
        this.codeValeur = codeValeur;
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

    public LocalDate getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(LocalDate dateEmission) {
        this.dateEmission = dateEmission;
    }

    public Float getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(Float tauxInteret) {
        this.tauxInteret = tauxInteret;
    }

    public Double getEncoursEmission() {
        return encoursEmission;
    }

    public void setEncoursEmission(Double encoursEmission) {
        this.encoursEmission = encoursEmission;
    }

    public UniteNombre getUniteVolume() {
        return uniteVolume;
    }

    public void setUniteVolume(UniteNombre uniteVolume) {
        this.uniteVolume = uniteVolume;
    }

    public Double getMontantSollicite() {
        return montantSollicite;
    }

    public void setMontantSollicite(Double montantSollicite) {
        this.montantSollicite = montantSollicite;
    }

    public UniteNombre getUniteMontant() {
        return uniteMontant;
    }

    public void setUniteMontant(UniteNombre uniteMontant) {
        this.uniteMontant = uniteMontant;
    }

    public TypeValeur getDevise() {
        return devise;
    }

    public void setDevise(TypeValeur devise) {
        this.devise = devise;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public LocalDate getDateValeur() {
        return dateValeur;
    }

    public void setDateValeur(LocalDate dateValeur) {
        this.dateValeur = dateValeur;
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
        if (!(o instanceof ReouvertureDTO)) {
            return false;
        }

        return id != null && id.equals(((ReouvertureDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReouvertureDTO{" +
            "id=" + getId() +
            ", codeValeur='" + getCodeValeur() + "'" +
            ", designationFr='" + getDesignationFr() + "'" +
            ", designationEn='" + getDesignationEn() + "'" +
            ", designationPt='" + getDesignationPt() + "'" +
            ", dateEmission='" + getDateEmission() + "'" +
            ", tauxInteret=" + getTauxInteret() +
            ", encoursEmission=" + getEncoursEmission() +
            ", uniteVolume='" + getUniteVolume() + "'" +
            ", montantSollicite=" + getMontantSollicite() +
            ", uniteMontant='" + getUniteMontant() + "'" +
            ", devise='" + getDevise() + "'" +
            ", dateEcheance='" + getDateEcheance() + "'" +
            ", dateValeur='" + getDateValeur() + "'" +
            ", operateur='" + getOperateur() + "'" +
            ", dateOperation='" + getDateOperation() + "'" +
            ", emissionId=" + getEmissionId() +
            "}";
    }
}
