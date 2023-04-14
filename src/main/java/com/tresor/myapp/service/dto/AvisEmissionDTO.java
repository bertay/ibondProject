package com.tresor.myapp.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import com.tresor.myapp.domain.enumeration.NatureActe;
import com.tresor.myapp.domain.enumeration.EtatActe;

/**
 * A DTO for the {@link com.tresor.myapp.domain.AvisEmission} entity.
 */
@ApiModel(description = "Entite AvisEmission")
public class AvisEmissionDTO implements Serializable {
    
    private Long id;

    private NatureActe nature;

    private String numero;

    private String reference;

    private String signataire;

    private String objetAvisFr;

    private String objetAvisEn;

    private String objetAvisPt;

    private EtatActe etatAvis;

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

    public String getObjetAvisFr() {
        return objetAvisFr;
    }

    public void setObjetAvisFr(String objetAvisFr) {
        this.objetAvisFr = objetAvisFr;
    }

    public String getObjetAvisEn() {
        return objetAvisEn;
    }

    public void setObjetAvisEn(String objetAvisEn) {
        this.objetAvisEn = objetAvisEn;
    }

    public String getObjetAvisPt() {
        return objetAvisPt;
    }

    public void setObjetAvisPt(String objetAvisPt) {
        this.objetAvisPt = objetAvisPt;
    }

    public EtatActe getEtatAvis() {
        return etatAvis;
    }

    public void setEtatAvis(EtatActe etatAvis) {
        this.etatAvis = etatAvis;
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
        if (!(o instanceof AvisEmissionDTO)) {
            return false;
        }

        return id != null && id.equals(((AvisEmissionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AvisEmissionDTO{" +
            "id=" + getId() +
            ", nature='" + getNature() + "'" +
            ", numero='" + getNumero() + "'" +
            ", reference='" + getReference() + "'" +
            ", signataire='" + getSignataire() + "'" +
            ", objetAvisFr='" + getObjetAvisFr() + "'" +
            ", objetAvisEn='" + getObjetAvisEn() + "'" +
            ", objetAvisPt='" + getObjetAvisPt() + "'" +
            ", etatAvis='" + getEtatAvis() + "'" +
            ", operateur='" + getOperateur() + "'" +
            ", dateOperation='" + getDateOperation() + "'" +
            "}";
    }
}
