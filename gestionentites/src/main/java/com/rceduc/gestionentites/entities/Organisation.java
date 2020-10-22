package com.rceduc.gestionentites.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
public class Organisation {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idOrganisation ;
    private String nom;
    private String description;
    private Date datecreation ;
    @OneToMany(mappedBy="organis")
    private Collection<Entite> listeentite;


    public Organisation() {
        super();
    }

    public Organisation(Long idOrganisation, String nom, String description, Date datecreation) {
        this.idOrganisation = idOrganisation;
        this.nom = nom;
        this.description = description;
        this.datecreation = datecreation;
    }

    public Long getIdOrganisation() {
        return idOrganisation;
    }

    public void setIdOrganisation(Long idOrganisation) {
        this.idOrganisation = idOrganisation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }
}
