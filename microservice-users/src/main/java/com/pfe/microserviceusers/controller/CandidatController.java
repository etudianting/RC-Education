package com.pfe.microserviceusers.controller;

import com.pfe.microserviceusers.exceptions.BadRequestException;
import com.pfe.microserviceusers.models.Candidat;
import com.pfe.microserviceusers.repository.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/candidats")
public class CandidatController {

    @Autowired
    private CandidatRepository candidatRepository;

    @PutMapping("/update/{id}")
    public Candidat updateCandidat(@RequestBody Candidat candidat, @PathVariable Long id)
    {
        return candidatRepository.findById(id).map(c -> {
            c.setLastName(candidat.getLastName());
            c.setName(candidat.getName());
            c.setTelephone(candidat.getTelephone());
            c.setUsername(candidat.getUsername());
            c.setLoacked(candidat.isLoacked());
            c.setNiveau(candidat.getNiveau());
            c.setInstitut(candidat.getInstitut());
            c.setDiplome(candidat.getDiplome());
            c.setAddress(candidat.getAddress());
            return candidatRepository.save(c);
        }).orElseThrow(()-> new BadRequestException("Ce candidat n'existe pas!!!"));
    }
    @GetMapping("/findById/{id}")
    public Candidat findById(@Valid @PathVariable Long id)
    {
        return this.candidatRepository.findById(id)
                .orElseThrow(()->new BadRequestException("Ce candidat : "+id+" n'existe pas"));
    }
}
