package com.rceduc.gestionentites.entities;


import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;

@Entity
@Table(name = "entites")
public class Entite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEntite;
    private String description;
    private String titre;
    private String version;
    private String auteur;
    private String emailauteur;
    private String mainteneur;
    private String emailmainteneur;
    private Date datecreation;

    @ManyToOne
    @JoinColumn(name = "idthem")
    private Thematique thematic;

    @ManyToOne
    @JoinColumn(name = "idNotif")
    private Notification notif;

    @ManyToOne
    @JoinColumn(name = "idOrganisation")
    private Organisation organis;


    public Entite() {
        super();
    }


    public Entite(Long idEntite, String description, String titre, String version,
                  String auteur, String emailauteur, String mainteneur, String emailmainteneur, Date datecreation) {
        this.idEntite = idEntite;
        this.description = description;
        this.titre = titre;
        this.version = version;
        this.auteur = auteur;
        this.emailauteur = emailauteur;
        this.mainteneur = mainteneur;
        this.emailmainteneur = emailmainteneur;
        this.datecreation = datecreation;
    }

    public Entite(String titre, String auteur, String description, String mainteneur) {
    }

    @Override
    public String toString() {
        return "Entite{" +
                "idEntite=" + idEntite +
                ", description='" + description + '\'' +
                ", titre='" + titre + '\'' +
                ", version='" + version + '\'' +
                ", auteur='" + auteur + '\'' +
                ", emailauteur='" + emailauteur + '\'' +
                ", mainteneur='" + mainteneur + '\'' +
                ", emailmainteneur='" + emailmainteneur + '\'' +
                ", datecreation=" + datecreation +
                '}';
    }

    public Long getIdEntite() {
        return idEntite;
    }

    public void setIdEntite(Long idEntite) {
        this.idEntite = idEntite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getEmailauteur() {
        return emailauteur;
    }

    public void setEmailauteur(String emailauteur) {
        this.emailauteur = emailauteur;
    }

    public String getMainteneur() {
        return mainteneur;
    }

    public void setMainteneur(String mainteneur) {
        this.mainteneur = mainteneur;
    }

    public String getEmailmainteneur() {
        return emailmainteneur;
    }

    public void setEmailmainteneur(String emailmainteneur) {
        this.emailmainteneur = emailmainteneur;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }
}
