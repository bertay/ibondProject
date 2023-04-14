package com.tresor.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * Entite Parametre
 */
@Entity
@Table(name = "parametres")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Parametres implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "adresse_serveur")
    private String adresseServeur;

    @Column(name = "timbre_service_1")
    private String timbreService1;

    @Column(name = "timbre_service_2")
    private String timbreService2;

    @Column(name = "timbre_service_3")
    private String timbreService3;

    @NotNull
    @Column(name = "operateur", nullable = false)
    private String operateur;

    @NotNull
    @Column(name = "date_operation", nullable = false)
    private ZonedDateTime dateOperation;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdresseServeur() {
        return adresseServeur;
    }

    public Parametres adresseServeur(String adresseServeur) {
        this.adresseServeur = adresseServeur;
        return this;
    }

    public void setAdresseServeur(String adresseServeur) {
        this.adresseServeur = adresseServeur;
    }

    public String getTimbreService1() {
        return timbreService1;
    }

    public Parametres timbreService1(String timbreService1) {
        this.timbreService1 = timbreService1;
        return this;
    }

    public void setTimbreService1(String timbreService1) {
        this.timbreService1 = timbreService1;
    }

    public String getTimbreService2() {
        return timbreService2;
    }

    public Parametres timbreService2(String timbreService2) {
        this.timbreService2 = timbreService2;
        return this;
    }

    public void setTimbreService2(String timbreService2) {
        this.timbreService2 = timbreService2;
    }

    public String getTimbreService3() {
        return timbreService3;
    }

    public Parametres timbreService3(String timbreService3) {
        this.timbreService3 = timbreService3;
        return this;
    }

    public void setTimbreService3(String timbreService3) {
        this.timbreService3 = timbreService3;
    }

    public String getOperateur() {
        return operateur;
    }

    public Parametres operateur(String operateur) {
        this.operateur = operateur;
        return this;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    public ZonedDateTime getDateOperation() {
        return dateOperation;
    }

    public Parametres dateOperation(ZonedDateTime dateOperation) {
        this.dateOperation = dateOperation;
        return this;
    }

    public void setDateOperation(ZonedDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Parametres)) {
            return false;
        }
        return id != null && id.equals(((Parametres) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Parametres{" +
            "id=" + getId() +
            ", adresseServeur='" + getAdresseServeur() + "'" +
            ", timbreService1='" + getTimbreService1() + "'" +
            ", timbreService2='" + getTimbreService2() + "'" +
            ", timbreService3='" + getTimbreService3() + "'" +
            ", operateur='" + getOperateur() + "'" +
            ", dateOperation='" + getDateOperation() + "'" +
            "}";
    }
}
