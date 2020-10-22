package com.rceduc.gestionentites.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idUtilisateur ;
    @Column(unique=true)
    private String username;
    private String password;
    private Date datecreation;
    private String mail;

    @OneToMany(mappedBy="utilisateur")
    private Collection<Profil> profils;

    public Utilisateur(Long idUtilisateur, String username, String password, Date datecreation, String mail) {
        this.idUtilisateur = idUtilisateur;
        this.username = username;
        this.password = password;
        this.datecreation = datecreation;
        this.mail = mail;
    }

    public Utilisateur() {
        super();
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "idUtilisateur=" + idUtilisateur +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", datecreation=" + datecreation +
                ", mail='" + mail + '\'' +
                '}';
    }


    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
