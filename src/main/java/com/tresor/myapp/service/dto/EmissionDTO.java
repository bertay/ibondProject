package com.tresor.myapp.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.tresor.myapp.domain.enumeration.UniteNombre;
import com.tresor.myapp.domain.enumeration.TypeValeur;

/**
 * A DTO for the {@link com.tresor.myapp.domain.Emission} entity.
 */
@ApiModel(description = "Entite Emission")
public class EmissionDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String codeEmission;

    private String designationFr;

    private String designationEn;

    private String designationPt;

    @NotNull
    private LocalDate dateEmission;

    @NotNull
    private LocalDate echeance;

    private String duree;

    private String remboursement;

    private String formeTitre;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Float tauxInteret;

    @NotNull
    @DecimalMin(value = "0")
    private Double volumeEmission;

    @NotNull
    private UniteNombre uniteVolume;

    @NotNull
    @DecimalMin(value = "0")
    private Double valeurNominale;

    @NotNull
    private TypeValeur devise;

    @Min(value = 0)
    private Integer quantiteTitre;

    private String rendement;

    @NotNull
    private ZonedDateTime dateLimite;

    @NotNull
    private String lieuSouscription;

    private ZonedDateTime dateResultat;

    private ZonedDateTime dateReglement;

    private LocalDate dateValeur;

    @NotNull
    private String operateur;

    @NotNull
    private ZonedDateTime dateOperation;


    private Long avisEmissionId;

    private Long natureTitreId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeEmission() {
        return codeEmission;
    }

    public void setCodeEmission(String codeEmission) {
        this.codeEmission = codeEmission;
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

    public LocalDate getEcheance() {
        return echeance;
    }

    public void setEcheance(LocalDate echeance) {
        this.echeance = echeance;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getRemboursement() {
        return remboursement;
    }

    public void setRemboursement(String remboursement) {
        this.remboursement = remboursement;
    }

    public String getFormeTitre() {
        return formeTitre;
    }

    public void setFormeTitre(String formeTitre) {
        this.formeTitre = formeTitre;
    }

    public Float getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(Float tauxInteret) {
        this.tauxInteret = tauxInteret;
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

    public Double getValeurNominale() {
        return valeurNominale;
    }

    public void setValeurNominale(Double valeurNominale) {
        this.valeurNominale = valeurNominale;
    }

    public TypeValeur getDevise() {
        return devise;
    }

    public void setDevise(TypeValeur devise) {
        this.devise = devise;
    }

    public Integer getQuantiteTitre() {
        return quantiteTitre;
    }

    public void setQuantiteTitre(Integer quantiteTitre) {
        this.quantiteTitre = quantiteTitre;
    }

    public String getRendement() {
        return rendement;
    }

    public void setRendement(String rendement) {
        this.rendement = rendement;
    }

    public ZonedDateTime getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(ZonedDateTime dateLimite) {
        this.dateLimite = dateLimite;
    }

    public String getLieuSouscription() {
        return lieuSouscription;
    }

    public void setLieuSouscription(String lieuSouscription) {
        this.lieuSouscription = lieuSouscription;
    }

    public ZonedDateTime getDateResultat() {
        return dateResultat;
    }

    public void setDateResultat(ZonedDateTime dateResultat) {
        this.dateResultat = dateResultat;
    }

    public ZonedDateTime getDateReglement() {
        return dateReglement;
    }

    public void setDateReglement(ZonedDateTime dateReglement) {
        this.dateReglement = dateReglement;
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

    public Long getAvisEmissionId() {
        return avisEmissionId;
    }

    public void setAvisEmissionId(Long avisEmissionId) {
        this.avisEmissionId = avisEmissionId;
    }

    public Long getNatureTitreId() {
        return natureTitreId;
    }

    public void setNatureTitreId(Long natureTitreId) {
        this.natureTitreId = natureTitreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmissionDTO)) {
            return false;
        }

        return id != null && id.equals(((EmissionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmissionDTO{" +
            "id=" + getId() +
            ", codeEmission='" + getCodeEmission() + "'" +
            ", designationFr='" + getDesignationFr() + "'" +
            ", designationEn='" + getDesignationEn() + "'" +
            ", designationPt='" + getDesignationPt() + "'" +
            ", dateEmission='" + getDateEmission() + "'" +
            ", echeance='" + getEcheance() + "'" +
            ", duree='" + getDuree() + "'" +
            ", remboursement='" + getRemboursement() + "'" +
            ", formeTitre='" + getFormeTitre() + "'" +
            ", tauxInteret=" + getTauxInteret() +
            ", volumeEmission=" + getVolumeEmission() +
            ", uniteVolume='" + getUniteVolume() + "'" +
            ", valeurNominale=" + getValeurNominale() +
            ", devise='" + getDevise() + "'" +
            ", quantiteTitre=" + getQuantiteTitre() +
            ", rendement='" + getRendement() + "'" +
            ", dateLimite='" + getDateLimite() + "'" +
            ", lieuSouscription='" + getLieuSouscription() + "'" +
            ", dateResultat='" + getDateResultat() + "'" +
            ", dateReglement='" + getDateReglement() + "'" +
            ", dateValeur='" + getDateValeur() + "'" +
            ", operateur='" + getOperateur() + "'" +
            ", dateOperation='" + getDateOperation() + "'" +
            ", avisEmissionId=" + getAvisEmissionId() +
            ", natureTitreId=" + getNatureTitreId() +
            "}";
    }
}
