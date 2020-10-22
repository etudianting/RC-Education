package com.rceduc.gestionentites.service;

import com.rceduc.gestionentites.dao.EntiteRepository;
import com.rceduc.gestionentites.entities.Entite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
@Service
public class CSVService {


    @Autowired
    EntiteRepository repository;

    public void save(MultipartFile file) {
        try {
            List<Entite> entites = CSVHelper.csvToEntites(file.getInputStream());
            repository.saveAll(entites);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public ByteArrayInputStream load() {
        List<Entite> tutorials = repository.findAll();

        ByteArrayInputStream in = CSVHelper.tutorialsToCSV(tutorials);
        return in;
    }

    public List<Entite> getAllTutorials() {
        return repository.findAll();
    }


}
