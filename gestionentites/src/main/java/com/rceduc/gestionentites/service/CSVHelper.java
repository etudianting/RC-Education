package com.rceduc.gestionentites.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.rceduc.gestionentites.entities.Entite;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;



public class CSVHelper {


    public static String TYPE = "text/csv";
    static String[] HEADERs = {"Titre", "Auteur", "Description", "Mainteneur"};

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Entite> csvToEntites(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Entite> entites = new ArrayList<Entite>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Entite entite = new Entite(
                        csvRecord.get("Titre"),
                        csvRecord.get("Auteur"),
                        csvRecord.get("Description"),
                        csvRecord.get("Mainteneur")
                );

                entites.add(entite);
            }

            return entites;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream tutorialsToCSV(List<Entite> entites) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for (Entite entite : entites) {
                List<String> data = Arrays.asList(
                        String.valueOf(entite.getTitre()),
                        entite.getAuteur(),
                        entite.getDescription(),
                        entite.getMainteneur()
                );

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}