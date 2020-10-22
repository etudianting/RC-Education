package com.pfe.microserviceusers.repository;

import com.pfe.microserviceusers.models.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat, Long> {

}
