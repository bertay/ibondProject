package com.tresor.myapp.service.dto;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.tresor.myapp.domain.Adjudication} entity.
 */
@ApiModel(description = "Entite Adjudication")
public class AdjudicationDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Min(value = 0)
    private Integer rang;

    private String observationFr;

    private String observationEn;

    private String observationPt;


    private Long reouvertureId;

    private Long rachatId;

    private Long oncId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRang() {
        return rang;
    }

    public void setRang(Integer rang) {
        this.rang = rang;
    }

    public String getObservationFr() {
        return observationFr;
    }

    public void setObservationFr(String observationFr) {
        this.observationFr = observationFr;
    }

    public String getObservationEn() {
        return observationEn;
    }

    public void setObservationEn(String observationEn) {
        this.observationEn = observationEn;
    }

    public String getObservationPt() {
        return observationPt;
    }

    public void setObservationPt(String observationPt) {
        this.observationPt = observationPt;
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
        if (!(o instanceof AdjudicationDTO)) {
            return false;
        }

        return id != null && id.equals(((AdjudicationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdjudicationDTO{" +
            "id=" + getId() +
            ", rang=" + getRang() +
            ", observationFr='" + getObservationFr() + "'" +
            ", observationEn='" + getObservationEn() + "'" +
            ", observationPt='" + getObservationPt() + "'" +
            ", reouvertureId=" + getReouvertureId() +
            ", rachatId=" + getRachatId() +
            ", oncId=" + getOncId() +
            "}";
    }
}
