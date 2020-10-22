package com.rceduc.gestionentites.service;

import com.rceduc.gestionentites.dao.EntiteRepository;
import com.rceduc.gestionentites.dao.OrganisationRepository;
import com.rceduc.gestionentites.dao.ThematiqueRepository;
import com.rceduc.gestionentites.entities.Entite;
import com.rceduc.gestionentites.entities.Organisation;
import com.rceduc.gestionentites.entities.Thematique;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




    @Service
    public class ReportService {

        @Autowired
        private EntiteRepository repository;


        public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
            String path = "C:\\Users\\Najib\\Desktop\\PFE-Docum";
            List<Entite> entites = repository.findAll();
            //load file and compile it
            File file = ResourceUtils.getFile("classpath:entites.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(entites);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy", "Rc");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            if (reportFormat.equalsIgnoreCase("html")) {
                JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\entites.html");
            }
            if (reportFormat.equalsIgnoreCase("pdf")) {
                JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\entites.pdf");
            }

            return "report generated in path : " + path;
        }



















}
