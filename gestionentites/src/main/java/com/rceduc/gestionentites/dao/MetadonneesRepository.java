package com.rceduc.gestionentites.dao;



import com.rceduc.gestionentites.entities.Metadonnees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface MetadonneesRepository extends JpaRepository<Metadonnees, Long> {
}
