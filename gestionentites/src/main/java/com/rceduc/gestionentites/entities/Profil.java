package com.rceduc.gestionentites.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Profil {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idProfil ;
    private boolean valide ;
    private Date dateexpire;
    @ManyToOne
    @JoinColumn(name="idUtilisateur")
    private Utilisateur utilisateur;

    public Profil() {
        super();
    }

    public Profil(Long idProfil, boolean valide, Date dateexpire) {
        this.idProfil = idProfil;
        this.valide = valide;
        this.dateexpire = dateexpire;
    }

    @Override
    public String toString() {
        return "Profil{" +
                "idProfil=" + idProfil +
                ", valide=" + valide +
                ", dateexpire=" + dateexpire +
                ", utilisateur=" + utilisateur +
                '}';
    }

    public Long getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(Long idProfil) {
        this.idProfil = idProfil;
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

    public Date getDateexpire() {
        return dateexpire;
    }

    public void setDateexpire(Date dateexpire) {
        this.dateexpire = dateexpire;
    }
}
