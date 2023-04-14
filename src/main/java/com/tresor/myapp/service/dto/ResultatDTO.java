package com.tresor.myapp.service.dto;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.tresor.myapp.domain.enumeration.UniteNombre;

/**
 * A DTO for the {@link com.tresor.myapp.domain.Resultat} entity.
 */
@ApiModel(description = "Entite Resultat")
public class ResultatDTO implements Serializable {
    
    private Long id;

    @Min(value = 0)
    private Integer nbreSvtTotal;

    @Min(value = 0)
    private Integer nbreSvtSoumis;

    @NotNull
    @DecimalMin(value = "0")
    private Double montantTresor;

    private UniteNombre uniteTresor;

    @NotNull
    @DecimalMin(value = "0")
    private Double montantSoumis;

    private UniteNombre uniteSoumis;

    @NotNull
    @DecimalMin(value = "0")
    private Double montantServi;

    @Min(value = 0L)
    private Long nbreTitreTotal;

    @Min(value = 0L)
    private Long nbreTitreSoumis;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Float tauxMinPropose;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Float tauxMaxPropose;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Float tauxLimite;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Float tauxInteretMoyen;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Float tauxRendementMoyen;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Float tauxCouverture;


    private Long emissionId;

    private Long resultatId;

    private Long rachatId;

    private Long oncId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNbreSvtTotal() {
        return nbreSvtTotal;
    }

    public void setNbreSvtTotal(Integer nbreSvtTotal) {
        this.nbreSvtTotal = nbreSvtTotal;
    }

    public Integer getNbreSvtSoumis() {
        return nbreSvtSoumis;
    }

    public void setNbreSvtSoumis(Integer nbreSvtSoumis) {
        this.nbreSvtSoumis = nbreSvtSoumis;
    }

    public Double getMontantTresor() {
        return montantTresor;
    }

    public void setMontantTresor(Double montantTresor) {
        this.montantTresor = montantTresor;
    }

    public UniteNombre getUniteTresor() {
        return uniteTresor;
    }

    public void setUniteTresor(UniteNombre uniteTresor) {
        this.uniteTresor = uniteTresor;
    }

    public Double getMontantSoumis() {
        return montantSoumis;
    }

    public void setMontantSoumis(Double montantSoumis) {
        this.montantSoumis = montantSoumis;
    }

    public UniteNombre getUniteSoumis() {
        return uniteSoumis;
    }

    public void setUniteSoumis(UniteNombre uniteSoumis) {
        this.uniteSoumis = uniteSoumis;
    }

    public Double getMontantServi() {
        return montantServi;
    }

    public void setMontantServi(Double montantServi) {
        this.montantServi = montantServi;
    }

    public Long getNbreTitreTotal() {
        return nbreTitreTotal;
    }

    public void setNbreTitreTotal(Long nbreTitreTotal) {
        this.nbreTitreTotal = nbreTitreTotal;
    }

    public Long getNbreTitreSoumis() {
        return nbreTitreSoumis;
    }

    public void setNbreTitreSoumis(Long nbreTitreSoumis) {
        this.nbreTitreSoumis = nbreTitreSoumis;
    }

    public Float getTauxMinPropose() {
        return tauxMinPropose;
    }

    public void setTauxMinPropose(Float tauxMinPropose) {
        this.tauxMinPropose = tauxMinPropose;
    }

    public Float getTauxMaxPropose() {
        return tauxMaxPropose;
    }

    public void setTauxMaxPropose(Float tauxMaxPropose) {
        this.tauxMaxPropose = tauxMaxPropose;
    }

    public Float getTauxLimite() {
        return tauxLimite;
    }

    public void setTauxLimite(Float tauxLimite) {
        this.tauxLimite = tauxLimite;
    }

    public Float getTauxInteretMoyen() {
        return tauxInteretMoyen;
    }

    public void setTauxInteretMoyen(Float tauxInteretMoyen) {
        this.tauxInteretMoyen = tauxInteretMoyen;
    }

    public Float getTauxRendementMoyen() {
        return tauxRendementMoyen;
    }

    public void setTauxRendementMoyen(Float tauxRendementMoyen) {
        this.tauxRendementMoyen = tauxRendementMoyen;
    }

    public Float getTauxCouverture() {
        return tauxCouverture;
    }

    public void setTauxCouverture(Float tauxCouverture) {
        this.tauxCouverture = tauxCouverture;
    }

    public Long getEmissionId() {
        return emissionId;
    }

    public void setEmissionId(Long emissionId) {
        this.emissionId = emissionId;
    }

    public Long getResultatId() {
        return resultatId;
    }

    public void setResultatId(Long reouvertureId) {
        this.resultatId = reouvertureId;
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
        if (!(o instanceof ResultatDTO)) {
            return false;
        }

        return id != null && id.equals(((ResultatDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ResultatDTO{" +
            "id=" + getId() +
            ", nbreSvtTotal=" + getNbreSvtTotal() +
            ", nbreSvtSoumis=" + getNbreSvtSoumis() +
            ", montantTresor=" + getMontantTresor() +
            ", uniteTresor='" + getUniteTresor() + "'" +
            ", montantSoumis=" + getMontantSoumis() +
            ", uniteSoumis='" + getUniteSoumis() + "'" +
            ", montantServi=" + getMontantServi() +
            ", nbreTitreTotal=" + getNbreTitreTotal() +
            ", nbreTitreSoumis=" + getNbreTitreSoumis() +
            ", tauxMinPropose=" + getTauxMinPropose() +
            ", tauxMaxPropose=" + getTauxMaxPropose() +
            ", tauxLimite=" + getTauxLimite() +
            ", tauxInteretMoyen=" + getTauxInteretMoyen() +
            ", tauxRendementMoyen=" + getTauxRendementMoyen() +
            ", tauxCouverture=" + getTauxCouverture() +
            ", emissionId=" + getEmissionId() +
            ", resultatId=" + getResultatId() +
            ", rachatId=" + getRachatId() +
            ", oncId=" + getOncId() +
            "}";
    }
}
