package com.rceduc.gestionentites.service;

import com.rceduc.gestionentites.dao.EntiteRepository;
import com.rceduc.gestionentites.entities.Entite;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EntiteServiceImpl implements EntiteService{


    @Autowired
    EntiteRepository entiteRepository;


    @Override
    public Optional<Entite> findById(Long idEntite) {
        return Optional.empty();
    }

    @Override
    public List<Entite> findAll() {
        List<Entite> l= new ArrayList<Entite>();
        this.entiteRepository.findAll().forEach(entite -> {
            l.add(entite);
        });
        return l;
    }

    @Override
    public Page<Entite> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Entite> findByTitre(String text, Pageable pageable) {
        return null;
    }

    @Override
    public List<Entite> findByTitre(String text) {
        return null;
    }

    @Override
    public Page<Entite> findByAuteur(String auteur, Pageable pageable) {
        return null;
    }

    @Override
    public List<Entite> findByAuteur(String auteur) {
        return null;
    }


    @Override
    public Page<Entite> findByDatecreation(LocalDateTime datecreation, Pageable pageable) {
        return null;
    }

    @Override
    public List<Entite> findByDatecreation(LocalDateTime datecreation) {
        return null;
    }


    @Override
    public Page<Entite> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.entiteRepository.findAll(pageable);
    }



    //upload file csv











}
