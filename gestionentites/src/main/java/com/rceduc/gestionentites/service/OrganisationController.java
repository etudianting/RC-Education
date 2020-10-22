package com.rceduc.gestionentites.service;



import com.rceduc.gestionentites.dao.OrganisationRepository;
import com.rceduc.gestionentites.entities.Entite;
import com.rceduc.gestionentites.entities.Organisation;
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
@RequestMapping("/organisations")
public class OrganisationController {


    @Autowired
    private OrganisationRepository organisationRepository;
    @Autowired
    private ReportServiceOrg serviceor;

    @GetMapping("/organisations")
    public List<Organisation> getAll()
    {
        return this.organisationRepository.findAll();
    }

// cherche par nom

    @GetMapping("/getbynom/{nom}")
    public List<Organisation> getByNom(@PathVariable(value = "nom") String nom) {
        return this.organisationRepository.findByNom(nom);
    }


    //reporting files au format pdf

    @GetMapping("/getAll")
    public List<Organisation> getOrganisations() {

        return this.organisationRepository.findAll();
    }

    @GetMapping("/reportorg/{format}")
    public String generateReportorg(@PathVariable String format) throws FileNotFoundException, JRException {
        return serviceor.exportReportorg(format);
    }

}
