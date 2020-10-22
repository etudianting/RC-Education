package com.rceduc.gestionentites.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Fichier {



    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "file_id")
    private String fileId;

    @Column(name = "file_type")
    private String type;

    @Column(name = "file_name")
    private String name;

    @Column(name = "file_data")
    private byte[] data;

    @Column(name = "file_createdOn")
    private Date createdOn;
    public Fichier () {

    }

    public Fichier(String type, String name, byte[] data, Date createdOn) {
        this.type = type;
        this.name = name;
        this.data = data;
        this.createdOn = createdOn;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
