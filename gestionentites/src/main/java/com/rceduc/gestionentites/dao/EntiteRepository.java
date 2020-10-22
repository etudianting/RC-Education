package com.rceduc.gestionentites.dao;


import com.rceduc.gestionentites.entities.Entite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RepositoryRestResource
public interface EntiteRepository extends JpaRepository<Entite, Long> {
    Page<Entite> findByTitre(String text, Pageable pageable);
    List<Entite> findByTitre(String text);

    //List<Entite> findByTitre(String titre, Sort sort);

   Page<Entite> findByAuteur(String auteur, Pageable pageable);
    List<Entite> findByAuteur(String auteur);

   // Page<Entite> findByDatecreation(LocalDateTime datecreation, Pageable pageable);
    List<Entite> findByDatecreation(LocalDateTime datecreation);


    List<Entite> findByMainteneur(String mainteneur);


    //List<Entite> findByDate(LocalDateTime datecreation);

    List<Entite> findByemailmainteneur(String emailmainteneur);

    List<Entite> findByemailauteur(String emailauteur);

    Page<Entite> findByTitreContaining(String titre, Pageable pagingSort);
}
