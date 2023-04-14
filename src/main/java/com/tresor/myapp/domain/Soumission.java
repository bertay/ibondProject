package com.tresor.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Entite Soumission
 */
@Entity
@Table(name = "soumission")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Soumission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "num_anonymat", nullable = false)
    private String numAnonymat;

    @NotNull
    @Column(name = "date_soumission", nullable = false)
    private ZonedDateTime dateSoumission;

    @Min(value = 0)
    @Column(name = "nbre_soumission")
    private Integer nbreSoumission;

    @NotNull
    @Column(name = "operateur", nullable = false)
    private String operateur;

    @NotNull
    @Column(name = "date_operation", nullable = false)
    private ZonedDateTime dateOperation;

    @OneToMany(mappedBy = "soumission")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<DetailSoumission> detailSoumissions = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "soumissions", allowSetters = true)
    private Emission emission;

    @ManyToOne
    @JsonIgnoreProperties(value = "soumissions", allowSetters = true)
    private Reouverture reouverture;

    @ManyToOne
    @JsonIgnoreProperties(value = "soumissions", allowSetters = true)
    private Rachat rachat;

    @ManyToOne
    @JsonIgnoreProperties(value = "soumissions", allowSetters = true)
    private Onc onc;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumAnonymat() {
        return numAnonymat;
    }

    public Soumission numAnonymat(String numAnonymat) {
        this.numAnonymat = numAnonymat;
        return this;
    }

    public void setNumAnonymat(String numAnonymat) {
        this.numAnonymat = numAnonymat;
    }

    public ZonedDateTime getDateSoumission() {
        return dateSoumission;
    }

    public Soumission dateSoumission(ZonedDateTime dateSoumission) {
        this.dateSoumission = dateSoumission;
        return this;
    }

    public void setDateSoumission(ZonedDateTime dateSoumission) {
        this.dateSoumission = dateSoumission;
    }

    public Integer getNbreSoumission() {
        return nbreSoumission;
    }

    public Soumission nbreSoumission(Integer nbreSoumission) {
        this.nbreSoumission = nbreSoumission;
        return this;
    }

    public void setNbreSoumission(Integer nbreSoumission) {
        this.nbreSoumission = nbreSoumission;
    }

    public String getOperateur() {
        return operateur;
    }

    public Soumission operateur(String operateur) {
        this.operateur = operateur;
        return this;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    public ZonedDateTime getDateOperation() {
        return dateOperation;
    }

    public Soumission dateOperation(ZonedDateTime dateOperation) {
        this.dateOperation = dateOperation;
        return this;
    }

    public void setDateOperation(ZonedDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    public Set<DetailSoumission> getDetailSoumissions() {
        return detailSoumissions;
    }

    public Soumission detailSoumissions(Set<DetailSoumission> detailSoumissions) {
        this.detailSoumissions = detailSoumissions;
        return this;
    }

    public Soumission addDetailSoumission(DetailSoumission detailSoumission) {
        this.detailSoumissions.add(detailSoumission);
        detailSoumission.setSoumission(this);
        return this;
    }

    public Soumission removeDetailSoumission(DetailSoumission detailSoumission) {
        this.detailSoumissions.remove(detailSoumission);
        detailSoumission.setSoumission(null);
        return this;
    }

    public void setDetailSoumissions(Set<DetailSoumission> detailSoumissions) {
        this.detailSoumissions = detailSoumissions;
    }

    public Emission getEmission() {
        return emission;
    }

    public Soumission emission(Emission emission) {
        this.emission = emission;
        return this;
    }

    public void setEmission(Emission emission) {
        this.emission = emission;
    }

    public Reouverture getReouverture() {
        return reouverture;
    }

    public Soumission reouverture(Reouverture reouverture) {
        this.reouverture = reouverture;
        return this;
    }

    public void setReouverture(Reouverture reouverture) {
        this.reouverture = reouverture;
    }

    public Rachat getRachat() {
        return rachat;
    }

    public Soumission rachat(Rachat rachat) {
        this.rachat = rachat;
        return this;
    }

    public void setRachat(Rachat rachat) {
        this.rachat = rachat;
    }

    public Onc getOnc() {
        return onc;
    }

    public Soumission onc(Onc onc) {
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
        if (!(o instanceof Soumission)) {
            return false;
        }
        return id != null && id.equals(((Soumission) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Soumission{" +
            "id=" + getId() +
            ", numAnonymat='" + getNumAnonymat() + "'" +
            ", dateSoumission='" + getDateSoumission() + "'" +
            ", nbreSoumission=" + getNbreSoumission() +
            ", operateur='" + getOperateur() + "'" +
            ", dateOperation='" + getDateOperation() + "'" +
            "}";
    }
}
