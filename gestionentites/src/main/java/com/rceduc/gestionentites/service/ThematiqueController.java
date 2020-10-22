package com.rceduc.gestionentites.service;


import com.rceduc.gestionentites.dao.ThematiqueRepository;
import com.rceduc.gestionentites.entities.Entite;
import com.rceduc.gestionentites.entities.Thematique;

import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/thematiques")
public class ThematiqueController {


    @Autowired
    private ThematiqueRepository thematiqueRepository;

    @GetMapping("/thematiques")
    public List<Thematique> getAll()
    {
        return this.thematiqueRepository.findAll();
    }

    @Autowired
    private ReportServiceTh serviceth;


    //reporting files au format pdf

    @GetMapping("/getthematiques")
    public List<Thematique> getThematiques() {

        return thematiqueRepository.findAll();
    }

    @GetMapping("/reportth/{format}")
    public String generateReportth(@PathVariable String format) throws FileNotFoundException, JRException {
        return serviceth.exportReportth(format);
    }


}
