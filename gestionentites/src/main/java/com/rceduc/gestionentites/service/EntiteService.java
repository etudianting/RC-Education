package com.rceduc.gestionentites.service;

import com.rceduc.gestionentites.entities.Entite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EntiteService {


    Optional<Entite> findById(Long idEntite);
    List<Entite> findAll();
    Page<Entite> findAll(Pageable pageable);

    Page<Entite> findByTitre(String text, Pageable pageable);
    List<Entite> findByTitre(String text);


    Page<Entite> findByAuteur(String auteur, Pageable pageable);
    List<Entite> findByAuteur(String auteur);

    Page<Entite> findByDatecreation(LocalDateTime datecreation, Pageable pageable);
    List<Entite> findByDatecreation(LocalDateTime datecreation);

    Page<Entite> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);





}
