package com.rceduc.gestionentites.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
public class Notification {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotif;
    private String objet;
    private String contenu;
    private Date dateenvoi;
    @OneToMany(mappedBy = "notif")
    private Collection<Entite> listeentite;


    public Notification() {
        super();
    }

    public Notification(Long idNotif, String objet, String contenu, Date dateenvoi, Collection<Entite> listeentite) {
        this.idNotif = idNotif;
        this.objet = objet;
        this.contenu = contenu;
        this.dateenvoi = dateenvoi;
        this.listeentite = listeentite;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "idNotif=" + idNotif +
                ", objet='" + objet + '\'' +
                ", contenu='" + contenu + '\'' +
                ", dateenvoi=" + dateenvoi +
                ", listeentite=" + listeentite +
                '}';
    }

    public Long getIdNotif() {
        return idNotif;
    }

    public void setIdNotif(Long idNotif) {
        this.idNotif = idNotif;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDateenvoi() {
        return dateenvoi;
    }

    public void setDateenvoi(Date dateenvoi) {
        this.dateenvoi = dateenvoi;
    }

    public Collection<Entite> getListeentite() {
        return listeentite;
    }

    public void setListeentite(Collection<Entite> listeentite) {
        this.listeentite = listeentite;
    }
}

