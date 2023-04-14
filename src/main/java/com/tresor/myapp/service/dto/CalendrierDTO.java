package com.tresor.myapp.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.tresor.myapp.domain.enumeration.NatureActe;
import com.tresor.myapp.domain.enumeration.EtatActe;

/**
 * A DTO for the {@link com.tresor.myapp.domain.Calendrier} entity.
 */
@ApiModel(description = "Entite Calendrier")
public class CalendrierDTO implements Serializable {
    
    private Long id;

    private NatureActe nature;

    private String numero;

    private String reference;

    private String signataire;

    private String titreCalendrierFr;

    private String titreCalendrierEn;

    private String titreCalendrierPt;

    private EtatActe etatCalendrier;

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

    public NatureActe getNature() {
        return nature;
    }

    public void setNature(NatureActe nature) {
        this.nature = nature;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getSignataire() {
        return signataire;
    }

    public void setSignataire(String signataire) {
        this.signataire = signataire;
    }

    public String getTitreCalendrierFr() {
        return titreCalendrierFr;
    }

    public void setTitreCalendrierFr(String titreCalendrierFr) {
        this.titreCalendrierFr = titreCalendrierFr;
    }

    public String getTitreCalendrierEn() {
        return titreCalendrierEn;
    }

    public void setTitreCalendrierEn(String titreCalendrierEn) {
        this.titreCalendrierEn = titreCalendrierEn;
    }

    public String getTitreCalendrierPt() {
        return titreCalendrierPt;
    }

    public void setTitreCalendrierPt(String titreCalendrierPt) {
        this.titreCalendrierPt = titreCalendrierPt;
    }

    public EtatActe getEtatCalendrier() {
        return etatCalendrier;
    }

    public void setEtatCalendrier(EtatActe etatCalendrier) {
        this.etatCalendrier = etatCalendrier;
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
        if (!(o instanceof CalendrierDTO)) {
            return false;
        }

        return id != null && id.equals(((CalendrierDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CalendrierDTO{" +
            "id=" + getId() +
            ", nature='" + getNature() + "'" +
            ", numero='" + getNumero() + "'" +
            ", reference='" + getReference() + "'" +
            ", signataire='" + getSignataire() + "'" +
            ", titreCalendrierFr='" + getTitreCalendrierFr() + "'" +
            ", titreCalendrierEn='" + getTitreCalendrierEn() + "'" +
            ", titreCalendrierPt='" + getTitreCalendrierPt() + "'" +
            ", etatCalendrier='" + getEtatCalendrier() + "'" +
            ", operateur='" + getOperateur() + "'" +
            ", dateOperation='" + getDateOperation() + "'" +
            "}";
    }
}
