package com.tresor.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import com.tresor.myapp.domain.enumeration.NatureActe;

import com.tresor.myapp.domain.enumeration.EtatActe;

/**
 * Entite AvisEmission
 */
@Entity
@Table(name = "avis_emission")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AvisEmission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "nature")
    private NatureActe nature;

    @Column(name = "numero")
    private String numero;

    @Column(name = "reference")
    private String reference;

    @Column(name = "signataire")
    private String signataire;

    @Column(name = "objet_avis_fr")
    private String objetAvisFr;

    @Column(name = "objet_avis_en")
    private String objetAvisEn;

    @Column(name = "objet_avis_pt")
    private String objetAvisPt;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat_avis")
    private EtatActe etatAvis;

    @NotNull
    @Column(name = "operateur", nullable = false)
    private String operateur;

    @NotNull
    @Column(name = "date_operation", nullable = false)
    private ZonedDateTime dateOperation;

    @OneToMany(mappedBy = "avisEmission")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Emission> emissions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NatureActe getNature() {
        return nature;
    }

    public AvisEmission nature(NatureActe nature) {
        this.nature = nature;
        return this;
    }

    public void setNature(NatureActe nature) {
        this.nature = nature;
    }

    public String getNumero() {
        return numero;
    }

    public AvisEmission numero(String numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getReference() {
        return reference;
    }

    public AvisEmission reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getSignataire() {
        return signataire;
    }

    public AvisEmission signataire(String signataire) {
        this.signataire = signataire;
        return this;
    }

    public void setSignataire(String signataire) {
        this.signataire = signataire;
    }

    public String getObjetAvisFr() {
        return objetAvisFr;
    }

    public AvisEmission objetAvisFr(String objetAvisFr) {
        this.objetAvisFr = objetAvisFr;
        return this;
    }

    public void setObjetAvisFr(String objetAvisFr) {
        this.objetAvisFr = objetAvisFr;
    }

    public String getObjetAvisEn() {
        return objetAvisEn;
    }

    public AvisEmission objetAvisEn(String objetAvisEn) {
        this.objetAvisEn = objetAvisEn;
        return this;
    }

    public void setObjetAvisEn(String objetAvisEn) {
        this.objetAvisEn = objetAvisEn;
    }

    public String getObjetAvisPt() {
        return objetAvisPt;
    }

    public AvisEmission objetAvisPt(String objetAvisPt) {
        this.objetAvisPt = objetAvisPt;
        return this;
    }

    public void setObjetAvisPt(String objetAvisPt) {
        this.objetAvisPt = objetAvisPt;
    }

    public EtatActe getEtatAvis() {
        return etatAvis;
    }

    public AvisEmission etatAvis(EtatActe etatAvis) {
        this.etatAvis = etatAvis;
        return this;
    }

    public void setEtatAvis(EtatActe etatAvis) {
        this.etatAvis = etatAvis;
    }

    public String getOperateur() {
        return operateur;
    }

    public AvisEmission operateur(String operateur) {
        this.operateur = operateur;
        return this;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    public ZonedDateTime getDateOperation() {
        return dateOperation;
    }

    public AvisEmission dateOperation(ZonedDateTime dateOperation) {
        this.dateOperation = dateOperation;
        return this;
    }

    public void setDateOperation(ZonedDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    public Set<Emission> getEmissions() {
        return emissions;
    }

    public AvisEmission emissions(Set<Emission> emissions) {
        this.emissions = emissions;
        return this;
    }

    public AvisEmission addEmission(Emission emission) {
        this.emissions.add(emission);
        emission.setAvisEmission(this);
        return this;
    }

    public AvisEmission removeEmission(Emission emission) {
        this.emissions.remove(emission);
        emission.setAvisEmission(null);
        return this;
    }

    public void setEmissions(Set<Emission> emissions) {
        this.emissions = emissions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AvisEmission)) {
            return false;
        }
        return id != null && id.equals(((AvisEmission) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AvisEmission{" +
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
