package com.rceduc.gestionentites.service;

import com.rceduc.gestionentites.dao.ThematiqueRepository;
import com.rceduc.gestionentites.entities.Thematique;
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
public class ReportServiceTh {


    @Autowired
    private ThematiqueRepository repositoryth;

    // report pdf de thematiques

    public String exportReportth(String reportFormat) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\Najib\\Desktop\\PFE-Docum";
        List<Thematique> thematiques = repositoryth.findAll();
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:thematiques.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(thematiques);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Rc");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\thematiques.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\thematiques.pdf");
        }

        return "report generated in path : " + path;
    }


}
