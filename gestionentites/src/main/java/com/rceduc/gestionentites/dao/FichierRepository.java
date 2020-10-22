package com.rceduc.gestionentites.dao;


import com.rceduc.gestionentites.entities.Fichier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource
public interface FichierRepository extends JpaRepository<Fichier, String> {
}
