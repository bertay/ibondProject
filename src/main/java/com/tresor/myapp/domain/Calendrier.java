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
 * Entite Calendrier
 */
@Entity
@Table(name = "calendrier")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Calendrier implements Serializable {

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

    @Column(name = "titre_calendrier_fr")
    private String titreCalendrierFr;

    @Column(name = "titre_calendrier_en")
    private String titreCalendrierEn;

    @Column(name = "titre_calendrier_pt")
    private String titreCalendrierPt;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat_calendrier")
    private EtatActe etatCalendrier;

    @NotNull
    @Column(name = "operateur", nullable = false)
    private String operateur;

    @NotNull
    @Column(name = "date_operation", nullable = false)
    private ZonedDateTime dateOperation;

    @OneToMany(mappedBy = "calendrier")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<DetailCalendrier> detailCalendriers = new HashSet<>();

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

    public Calendrier nature(NatureActe nature) {
        this.nature = nature;
        return this;
    }

    public void setNature(NatureActe nature) {
        this.nature = nature;
    }

    public String getNumero() {
        return numero;
    }

    public Calendrier numero(String numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getReference() {
        return reference;
    }

    public Calendrier reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getSignataire() {
        return signataire;
    }

    public Calendrier signataire(String signataire) {
        this.signataire = signataire;
        return this;
    }

    public void setSignataire(String signataire) {
        this.signataire = signataire;
    }

    public String getTitreCalendrierFr() {
        return titreCalendrierFr;
    }

    public Calendrier titreCalendrierFr(String titreCalendrierFr) {
        this.titreCalendrierFr = titreCalendrierFr;
        return this;
    }

    public void setTitreCalendrierFr(String titreCalendrierFr) {
        this.titreCalendrierFr = titreCalendrierFr;
    }

    public String getTitreCalendrierEn() {
        return titreCalendrierEn;
    }

    public Calendrier titreCalendrierEn(String titreCalendrierEn) {
        this.titreCalendrierEn = titreCalendrierEn;
        return this;
    }

    public void setTitreCalendrierEn(String titreCalendrierEn) {
        this.titreCalendrierEn = titreCalendrierEn;
    }

    public String getTitreCalendrierPt() {
        return titreCalendrierPt;
    }

    public Calendrier titreCalendrierPt(String titreCalendrierPt) {
        this.titreCalendrierPt = titreCalendrierPt;
        return this;
    }

    public void setTitreCalendrierPt(String titreCalendrierPt) {
        this.titreCalendrierPt = titreCalendrierPt;
    }

    public EtatActe getEtatCalendrier() {
        return etatCalendrier;
    }

    public Calendrier etatCalendrier(EtatActe etatCalendrier) {
        this.etatCalendrier = etatCalendrier;
        return this;
    }

    public void setEtatCalendrier(EtatActe etatCalendrier) {
        this.etatCalendrier = etatCalendrier;
    }

    public String getOperateur() {
        return operateur;
    }

    public Calendrier operateur(String operateur) {
        this.operateur = operateur;
        return this;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    public ZonedDateTime getDateOperation() {
        return dateOperation;
    }

    public Calendrier dateOperation(ZonedDateTime dateOperation) {
        this.dateOperation = dateOperation;
        return this;
    }

    public void setDateOperation(ZonedDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    public Set<DetailCalendrier> getDetailCalendriers() {
        return detailCalendriers;
    }

    public Calendrier detailCalendriers(Set<DetailCalendrier> detailCalendriers) {
        this.detailCalendriers = detailCalendriers;
        return this;
    }

    public Calendrier addDetailCalendrier(DetailCalendrier detailCalendrier) {
        this.detailCalendriers.add(detailCalendrier);
        detailCalendrier.setCalendrier(this);
        return this;
    }

    public Calendrier removeDetailCalendrier(DetailCalendrier detailCalendrier) {
        this.detailCalendriers.remove(detailCalendrier);
        detailCalendrier.setCalendrier(null);
        return this;
    }

    public void setDetailCalendriers(Set<DetailCalendrier> detailCalendriers) {
        this.detailCalendriers = detailCalendriers;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Calendrier)) {
            return false;
        }
        return id != null && id.equals(((Calendrier) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Calendrier{" +
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
