package com.rceduc.gestionentites.dao;



import com.rceduc.gestionentites.entities.Membre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface MembreRepository extends JpaRepository<Membre, Long> {
}
