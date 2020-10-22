package com.rceduc.gestionentites.dao;

import com.rceduc.gestionentites.entities.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface OrganisationRepository extends JpaRepository<Organisation, Long> {

    List<Organisation> findAll();
    List<Organisation> findByNom(String nom);
}
