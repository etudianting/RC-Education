package com.rceduc.gestionentites.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
public class Thematique {



    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idthem ;
    private String titre;
    private String description;
    private Date datecreation;
    @OneToMany(mappedBy="thematic")
    private Collection<Entite> listeentite;


    public Thematique() {
        super();
    }

    @Override
    public String toString() {
        return "Thematique{" +
                "idthem=" + idthem +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", datecreation=" + datecreation +
                '}';
    }

    public Long getIdthem() {
        return idthem;
    }

    public void setIdthem(Long idthem) {
        this.idthem = idthem;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
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

    public Thematique(Long idthem, String titre, String description, String organisation, Date datecreation) {
        this.idthem = idthem;
        this.titre = titre;
        this.description = description;
        this.datecreation = datecreation;
    }
}
