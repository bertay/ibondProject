package com.tresor.myapp.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.tresor.myapp.domain.Parametres} entity.
 */
@ApiModel(description = "Entite Parametre")
public class ParametresDTO implements Serializable {
    
    private Long id;

    private String adresseServeur;

    private String timbreService1;

    private String timbreService2;

    private String timbreService3;

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

    public String getAdresseServeur() {
        return adresseServeur;
    }

    public void setAdresseServeur(String adresseServeur) {
        this.adresseServeur = adresseServeur;
    }

    public String getTimbreService1() {
        return timbreService1;
    }

    public void setTimbreService1(String timbreService1) {
        this.timbreService1 = timbreService1;
    }

    public String getTimbreService2() {
        return timbreService2;
    }

    public void setTimbreService2(String timbreService2) {
        this.timbreService2 = timbreService2;
    }

    public String getTimbreService3() {
        return timbreService3;
    }

    public void setTimbreService3(String timbreService3) {
        this.timbreService3 = timbreService3;
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
        if (!(o instanceof ParametresDTO)) {
            return false;
        }

        return id != null && id.equals(((ParametresDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ParametresDTO{" +
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
