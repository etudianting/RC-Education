package com.rceduc.gestionentites.service;

import java.io.IOException;
import java.util.Date;

import com.rceduc.gestionentites.dao.FichierRepository;
import com.rceduc.gestionentites.entities.Fichier;
import com.rceduc.gestionentites.exception.FileStorageException;
import com.rceduc.gestionentites.exception.MyFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;



@Service
public class FileUploadService {

    @Autowired
    private FichierRepository fileUploadRepo;

    public Fichier storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Fichier dbFile = new Fichier(file.getContentType(),fileName, file.getBytes(), new Date());

            return fileUploadRepo.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }

    }
    public Fichier getFile(String fileId) {
        return fileUploadRepo.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }


}
