package com.rceduc.gestionentites.service;

import com.rceduc.gestionentites.dao.OrganisationRepository;
import com.rceduc.gestionentites.entities.Organisation;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ReportServiceOrg {


    // report pdf de organisation


    @Autowired
    private OrganisationRepository repositoryor;


    public String exportReportorg(String reportFormat) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\Najib\\Desktop\\PFE-Docum";
        List<Organisation> organisations = repositoryor.findAll();
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:thematiques.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(organisations);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Rc");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\organisations.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\organisations.pdf");
        }

        return "report generated in path : " + path;
    }




}



