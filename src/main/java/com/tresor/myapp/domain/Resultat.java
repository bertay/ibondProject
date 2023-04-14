package com.tresor.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import com.tresor.myapp.domain.enumeration.UniteNombre;

/**
 * Entite Resultat
 */
@Entity
@Table(name = "resultat")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Resultat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0)
    @Column(name = "nbre_svt_total")
    private Integer nbreSvtTotal;

    @Min(value = 0)
    @Column(name = "nbre_svt_soumis")
    private Integer nbreSvtSoumis;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "montant_tresor", nullable = false)
    private Double montantTresor;

    @Enumerated(EnumType.STRING)
    @Column(name = "unite_tresor")
    private UniteNombre uniteTresor;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "montant_soumis", nullable = false)
    private Double montantSoumis;

    @Enumerated(EnumType.STRING)
    @Column(name = "unite_soumis")
    private UniteNombre uniteSoumis;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "montant_servi", nullable = false)
    private Double montantServi;

    @Min(value = 0L)
    @Column(name = "nbre_titre_total")
    private Long nbreTitreTotal;

    @Min(value = 0L)
    @Column(name = "nbre_titre_soumis")
    private Long nbreTitreSoumis;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "taux_min_propose")
    private Float tauxMinPropose;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "taux_max_propose")
    private Float tauxMaxPropose;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "taux_limite")
    private Float tauxLimite;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "taux_interet_moyen")
    private Float tauxInteretMoyen;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "taux_rendement_moyen")
    private Float tauxRendementMoyen;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "taux_couverture")
    private Float tauxCouverture;

    @ManyToOne
    @JsonIgnoreProperties(value = "resultats", allowSetters = true)
    private Emission emission;

    @ManyToOne
    @JsonIgnoreProperties(value = "resultats", allowSetters = true)
    private Reouverture resultat;

    @ManyToOne
    @JsonIgnoreProperties(value = "resultats", allowSetters = true)
    private Rachat rachat;

    @ManyToOne
    @JsonIgnoreProperties(value = "resultats", allowSetters = true)
    private Onc onc;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNbreSvtTotal() {
        return nbreSvtTotal;
    }

    public Resultat nbreSvtTotal(Integer nbreSvtTotal) {
        this.nbreSvtTotal = nbreSvtTotal;
        return this;
    }

    public void setNbreSvtTotal(Integer nbreSvtTotal) {
        this.nbreSvtTotal = nbreSvtTotal;
    }

    public Integer getNbreSvtSoumis() {
        return nbreSvtSoumis;
    }

    public Resultat nbreSvtSoumis(Integer nbreSvtSoumis) {
        this.nbreSvtSoumis = nbreSvtSoumis;
        return this;
    }

    public void setNbreSvtSoumis(Integer nbreSvtSoumis) {
        this.nbreSvtSoumis = nbreSvtSoumis;
    }

    public Double getMontantTresor() {
        return montantTresor;
    }

    public Resultat montantTresor(Double montantTresor) {
        this.montantTresor = montantTresor;
        return this;
    }

    public void setMontantTresor(Double montantTresor) {
        this.montantTresor = montantTresor;
    }

    public UniteNombre getUniteTresor() {
        return uniteTresor;
    }

    public Resultat uniteTresor(UniteNombre uniteTresor) {
        this.uniteTresor = uniteTresor;
        return this;
    }

    public void setUniteTresor(UniteNombre uniteTresor) {
        this.uniteTresor = uniteTresor;
    }

    public Double getMontantSoumis() {
        return montantSoumis;
    }

    public Resultat montantSoumis(Double montantSoumis) {
        this.montantSoumis = montantSoumis;
        return this;
    }

    public void setMontantSoumis(Double montantSoumis) {
        this.montantSoumis = montantSoumis;
    }

    public UniteNombre getUniteSoumis() {
        return uniteSoumis;
    }

    public Resultat uniteSoumis(UniteNombre uniteSoumis) {
        this.uniteSoumis = uniteSoumis;
        return this;
    }

    public void setUniteSoumis(UniteNombre uniteSoumis) {
        this.uniteSoumis = uniteSoumis;
    }

    public Double getMontantServi() {
        return montantServi;
    }

    public Resultat montantServi(Double montantServi) {
        this.montantServi = montantServi;
        return this;
    }

    public void setMontantServi(Double montantServi) {
        this.montantServi = montantServi;
    }

    public Long getNbreTitreTotal() {
        return nbreTitreTotal;
    }

    public Resultat nbreTitreTotal(Long nbreTitreTotal) {
        this.nbreTitreTotal = nbreTitreTotal;
        return this;
    }

    public void setNbreTitreTotal(Long nbreTitreTotal) {
        this.nbreTitreTotal = nbreTitreTotal;
    }

    public Long getNbreTitreSoumis() {
        return nbreTitreSoumis;
    }

    public Resultat nbreTitreSoumis(Long nbreTitreSoumis) {
        this.nbreTitreSoumis = nbreTitreSoumis;
        return this;
    }

    public void setNbreTitreSoumis(Long nbreTitreSoumis) {
        this.nbreTitreSoumis = nbreTitreSoumis;
    }

    public Float getTauxMinPropose() {
        return tauxMinPropose;
    }

    public Resultat tauxMinPropose(Float tauxMinPropose) {
        this.tauxMinPropose = tauxMinPropose;
        return this;
    }

    public void setTauxMinPropose(Float tauxMinPropose) {
        this.tauxMinPropose = tauxMinPropose;
    }

    public Float getTauxMaxPropose() {
        return tauxMaxPropose;
    }

    public Resultat tauxMaxPropose(Float tauxMaxPropose) {
        this.tauxMaxPropose = tauxMaxPropose;
        return this;
    }

    public void setTauxMaxPropose(Float tauxMaxPropose) {
        this.tauxMaxPropose = tauxMaxPropose;
    }

    public Float getTauxLimite() {
        return tauxLimite;
    }

    public Resultat tauxLimite(Float tauxLimite) {
        this.tauxLimite = tauxLimite;
        return this;
    }

    public void setTauxLimite(Float tauxLimite) {
        this.tauxLimite = tauxLimite;
    }

    public Float getTauxInteretMoyen() {
        return tauxInteretMoyen;
    }

    public Resultat tauxInteretMoyen(Float tauxInteretMoyen) {
        this.tauxInteretMoyen = tauxInteretMoyen;
        return this;
    }

    public void setTauxInteretMoyen(Float tauxInteretMoyen) {
        this.tauxInteretMoyen = tauxInteretMoyen;
    }

    public Float getTauxRendementMoyen() {
        return tauxRendementMoyen;
    }

    public Resultat tauxRendementMoyen(Float tauxRendementMoyen) {
        this.tauxRendementMoyen = tauxRendementMoyen;
        return this;
    }

    public void setTauxRendementMoyen(Float tauxRendementMoyen) {
        this.tauxRendementMoyen = tauxRendementMoyen;
    }

    public Float getTauxCouverture() {
        return tauxCouverture;
    }

    public Resultat tauxCouverture(Float tauxCouverture) {
        this.tauxCouverture = tauxCouverture;
        return this;
    }

    public void setTauxCouverture(Float tauxCouverture) {
        this.tauxCouverture = tauxCouverture;
    }

    public Emission getEmission() {
        return emission;
    }

    public Resultat emission(Emission emission) {
        this.emission = emission;
        return this;
    }

    public void setEmission(Emission emission) {
        this.emission = emission;
    }

    public Reouverture getResultat() {
        return resultat;
    }

    public Resultat resultat(Reouverture reouverture) {
        this.resultat = reouverture;
        return this;
    }

    public void setResultat(Reouverture reouverture) {
        this.resultat = reouverture;
    }

    public Rachat getRachat() {
        return rachat;
    }

    public Resultat rachat(Rachat rachat) {
        this.rachat = rachat;
        return this;
    }

    public void setRachat(Rachat rachat) {
        this.rachat = rachat;
    }

    public Onc getOnc() {
        return onc;
    }

    public Resultat onc(Onc onc) {
        this.onc = onc;
        return this;
    }

    public void setOnc(Onc onc) {
        this.onc = onc;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Resultat)) {
            return false;
        }
        return id != null && id.equals(((Resultat) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Resultat{" +
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
            "}";
    }
}
